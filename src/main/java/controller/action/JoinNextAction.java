package controller.action;


import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.example.webapp.model.common.GeoCodingUtil;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class JoinNextAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CONT 로그: JoinNextAction 도착");
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
        UserDAO userDAO = new UserDAO();

        try {
            // 파일 업로드 처리
            String filePathForDB = processFileUpload(request);

            // UserDTO 객체 생성 및 데이터 설정
            UserDTO userDTO = new UserDTO();

            // 기본 정보 설정
            userDTO.setUserEmail((String)session.getAttribute("userEmail"));
            userDTO.setUserPassword((String)session.getAttribute("userPassword"));
            userDTO.setUserName((String)session.getAttribute("userName"));

            // 소셜 로그인 타입 설정
            String socialType = (String)session.getAttribute("socialType");
            if (socialType != null && !socialType.isEmpty()) {
                userDTO.setSocialType(socialType);
            }

            //
            userDTO.setUserNickname(request.getParameter("userNickname"));
            userDTO.setUserPhone(request.getParameter("userPhone"));

            // 성별 설정
            String genderStr = request.getParameter("userGender");
            if (genderStr != null && !genderStr.isEmpty()) {
                userDTO.setUserGender(Integer.parseInt(genderStr) == 1); // 0이면 true, 1이면 false
            }

            // 생년월일 설정
            userDTO.setUserBirth(request.getParameter("userBirth"));

            // 키 설정
            String heightStr = request.getParameter("userHeight");
            if (heightStr != null && !heightStr.isEmpty()) {
                userDTO.setUserHeight(Integer.parseInt(heightStr));
            }

            // 체형 설정
            userDTO.setUserBody(request.getParameter("userBody"));

            // MBTI 설정
            userDTO.setUserMbti(request.getParameter("userMbti"));

            // 프로필 사진 경로 설정
            if (filePathForDB != null) {
                userDTO.setUserProfile(filePathForDB);
            } else {
                userDTO.setUserProfile(request.getParameter("userProfile"));
            }

            // 학력 설정
            userDTO.setUserEducation(request.getParameter("userEducation"));

            // 종교 설정
            userDTO.setUserReligion(request.getParameter("userReligion"));

            // 음주 설정
            String drinkStr = request.getParameter("userDrink");
            if (drinkStr != null && !drinkStr.isEmpty()) {
                userDTO.setUserDrink(Integer.parseInt(drinkStr));
            }

            // 흡연 설정
            String smokeStr = request.getParameter("userSmoke");
            if (smokeStr != null && !smokeStr.isEmpty()) {
                userDTO.setUserSmoke(Integer.parseInt(smokeStr) == 0); // 0이면 true, 1이면 false
            }

            // 직업 설정
            userDTO.setUserJob(request.getParameter("userJob"));

            // 지역 설정
            userDTO.setUserRegion(request.getParameter("userRegion"));

            // 위도, 경도 추가
//            try {
//                userDTO.setUserLatitude(Double.parseDouble(request.getParameter("userLatitude")));
//                userDTO.setUserLongitude(Double.parseDouble(request.getParameter("userLongitude")));
//                System.out.println(" 위도: " + userDTO.getUserLatitude() + ", 경도: " + userDTO.getUserLongitude());
//            } catch (Exception e) {
//                System.out.println("위도/경도 파싱 실패! 기본값 0.0으로 저장됨");
//                userDTO.setUserLatitude(0.0);
//                userDTO.setUserLongitude(0.0);
//            }

            //서버(Java)에서 직접 주소 → 위도/경도 변환
            // GeoCodingUtil을 통해 위도/경도 변환
            try {
                // 회원가입 시 입력된 주소를 가져옴
                String address = userDTO.getUserRegion();

                // GeoCodingUtil을 사용해서 해당 주소를 위도/경도로 변환
                double[] coords = GeoCodingUtil.getCoordinatesFromAddress(address);

                // 소수점 4자리까지만 저장되게 하기 (반올림 처리)
                double lat = Math.round(coords[0] * 10000) / 10000.0;
                double lng = Math.round(coords[1] * 10000) / 10000.0;

                // 변환된 위도, 경도 값을 userDTO에 저장
                userDTO.setUserLatitude(lat);
                userDTO.setUserLongitude(lng);

                System.out.println("위도/경도 설정됨: " + lat + ", " + lng);
                System.out.println("JOIN Next Action 로그 userDTO[" + userDTO + "]");

            } catch (Exception e) {
                System.out.println(" 주소 → 위경도 변환 실패! 기본값 0.0으로 저장됨");
                // 변환 실패 시 기본값 저장
                userDTO.setUserLatitude(0.0);
                userDTO.setUserLongitude(0.0);
            }


            // 자기소개 설정
            userDTO.setUserDescription(request.getParameter("userDescription"));

            // 회원가입 조건 설정
            if (userDTO.getUserProfile() != null && userDTO.getUserDescription() != null && !userDTO.getUserDescription().isEmpty()) {
                userDTO.setCondition("INSERT");
            } else if (userDTO.getUserProfile() != null) {
                userDTO.setCondition("INSERT_PROFILE");
            } else if (userDTO.getUserDescription() != null && !userDTO.getUserDescription().isEmpty()) {
                userDTO.setCondition("INSERT_DESCRIPTION");
            } else {
                userDTO.setCondition("INSERT");
            }

            System.out.println("JOIN Next Action 로그 userDTO[" + userDTO + "]");

            // UserDAO를 사용하여 회원가입 처리
            boolean result = userDAO.insert(userDTO);

            if (result) {
                // 세션에서 기본 정보 제거
                session.removeAttribute("userPassword");
                session.removeAttribute("userName");
                session.removeAttribute("socialType");

                // 회원가입 성공
                request.setAttribute("userDTO", userDTO);
                request.setAttribute("msg", "회원가입 성공!");
                request.setAttribute("flag", true);
                request.setAttribute("url", "userPreferencePage.do");
            } else {
                // 회원가입 실패
                request.setAttribute("msg", "회원가입 실패!");
                request.setAttribute("flag", false);
            }

            forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
            forward.setRedirect(false);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "회원가입 중 오류가 발생했습니다!");
            request.setAttribute("flag", false);
            forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
            forward.setRedirect(false);
        }

        return forward;
    }

    // 파일 업로드 처리 메서드
    private String processFileUpload(HttpServletRequest request) throws Exception {
        String filePathForDB = null;

        try {
            // 파일 업로드 경로 설정
            String uploadPath = request.getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // multipart/form-data 요청인지 확인
            boolean isMultipart = request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");

            if (isMultipart) {
                // 파일 처리
                Part filePart = request.getPart("userProfile");

                if (filePart != null && filePart.getSize() > 0) {
                    String fileName = getSubmittedFileName(filePart);

                    // 파일이 선택된 경우에만 처리
                    if (fileName != null && !fileName.isEmpty()) {
                        // 파일 확장자 검사
                        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                        if (!ext.equals("jpg") && !ext.equals("jpeg") && !ext.equals("png")) {
                            throw new IllegalArgumentException("지원하지 않는 파일 형식입니다.");
                        }

                        // 고유한 파일명 생성
                        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
                        File uploadedFile = new File(uploadPath, uniqueFileName);

                        // 파일 저장
                        filePart.write(uploadedFile.getAbsolutePath());

                        // DB에 저장할 파일 경로
                        filePathForDB = "/uploads/" + uniqueFileName;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return filePathForDB;
    }

    // 파일 이름 추출 메서드
    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}
