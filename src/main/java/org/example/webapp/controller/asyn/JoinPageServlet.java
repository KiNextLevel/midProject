package org.example.webapp.controller.asyn;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/JoinPage")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 5 * 1024 * 1024,   // 5MB
        maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class JoinPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 파일 업로드 경로 설정
        String uploadPath = getServletContext().getRealPath("/uploads");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // 파일 처리
        String filePathForDB = null;
        Part filePart = request.getPart("USER_PROFILE");

        if (filePart != null && filePart.getSize() > 0) {
            String fileName = getSubmittedFileName(filePart);

            // 파일이 선택된 경우에만 처리
            if (fileName != null && !fileName.isEmpty()) {
                // 파일 확장자 검사
                String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if (!ext.equals("jpg") && !ext.equals("jpeg") && !ext.equals("png")) {
                    response.sendRedirect("signup.jsp?error=invalidFileType");
                    return;
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

        // UserDTO 객체 생성 및 데이터 설정
        UserDTO userDTO = new UserDTO();

        // 기본 정보 설정
        userDTO.setUserEmail(request.getParameter("email"));
        userDTO.setUserPassword(request.getParameter("password"));
        userDTO.setUserName(request.getParameter("name"));
        userDTO.setUserNickname(request.getParameter("USER_NICKNAME"));
        userDTO.setUserPhone(request.getParameter("USER_PHONE"));

        // 성별 설정
        String genderStr = request.getParameter("USER_GENDER");
        if (genderStr != null && !genderStr.isEmpty()) {
            userDTO.setUserGender(Integer.parseInt(genderStr) == 1);
        }

        // 생년월일 설정
        userDTO.setUserBirth(request.getParameter("USER_BIRTH"));

        // 키 설정
        String heightStr = request.getParameter("USER_HEIGHT");
        if (heightStr != null && !heightStr.isEmpty()) {
            userDTO.setUserHeight(Integer.parseInt(heightStr));
        }

        // 체형 설정
        userDTO.setUserBody(request.getParameter("USER_BODY"));

        // MBTI 설정
        userDTO.setUserMbti(request.getParameter("USER_MBTI"));

        // 프로필 사진 경로 설정
        if (filePathForDB != null) {
            userDTO.setUserProfile(filePathForDB);
        }

        // 학력 설정
        userDTO.setUserEducation(request.getParameter("USER_EDUCATION"));

        // 종교 설정
        userDTO.setUserReligion(request.getParameter("USER_RELIGION"));

        // 음주 설정
        String drinkStr = request.getParameter("USER_DRINK");
        if (drinkStr != null && !drinkStr.isEmpty()) {
            userDTO.setUserDrink(Integer.parseInt(drinkStr));
        }

        // 흡연 설정
        String smokeStr = request.getParameter("USER_SMOKE");
        if (smokeStr != null && !smokeStr.isEmpty()) {
            userDTO.setUserSmoke(Integer.parseInt(smokeStr) == 1);
        }

        // 직업 설정
        userDTO.setUserJob(request.getParameter("USER_JOB"));

        // 지역 설정
        userDTO.setUserRegion(request.getParameter("USER_REGION"));

        // 자기소개 설정
        userDTO.setUserDescription(request.getParameter("USER_DESCRIPTION"));

        // 회원가입 조건 설정
        if (filePathForDB != null && userDTO.getUserDescription() != null && !userDTO.getUserDescription().isEmpty()) {
            userDTO.setCondition("INSERT");
        } else if (filePathForDB != null) {
            userDTO.setCondition("INSERT_PROFILE");
        } else if (userDTO.getUserDescription() != null && !userDTO.getUserDescription().isEmpty()) {
            userDTO.setCondition("INSERT_DESCRIPTION");
        } else {
            userDTO.setCondition("INSERT");
        }

        // UserDAO를 사용하여 회원가입 처리
        UserDAO userDAO = new UserDAO();
        boolean result = userDAO.insert(userDTO);

        if (result) {
            // 회원가입 성공
            response.sendRedirect("Index.jsp");
        } else {
            // 회원가입 실패
            response.sendRedirect("signup.jsp?error=dbError");
        }
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