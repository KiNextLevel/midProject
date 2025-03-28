package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;

public class UpdateProfileImageAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CTRL 로그: UpdateProfileImageAction - 시작");
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");
        System.out.println("CTRL 로그: userEmail = " + userEmail);

        // 로그인 체크
        if (userEmail == null) {
            System.out.println("UpdateProfileImage Action 로그: userEmail is null");
            request.setAttribute("message", "로그인이 필요합니다.");
            forward.setPath("/Metronic-Shop-UI-master/theme/myPage.do");  // 마이페이지로 리다이렉트
            forward.setRedirect(true);
            return forward;
        }

        try {
            // 멀티파트 요청 처리 (파일 업로드)
            Part filePart = request.getPart("profileImage");

            if (filePart == null || filePart.getSize() <= 0) {
                System.out.println("UpdateProfileImage Action 로그: 파일이 없습니다.");
                request.setAttribute("message", "파일이 선택되지 않았습니다.");
                forward.setPath("/Metronic-Shop-UI-master/theme/myPage.do");
                forward.setRedirect(true);
                return forward;
            }

            // 파일 크기 검증 (5MB 제한)
            if (filePart.getSize() > 5 * 1024 * 1024) {
                System.out.println("UpdateProfileImage Action 로그: 파일 크기 초과");
                request.setAttribute("message", "파일 크기는 5MB 이하여야 합니다.");
                forward.setPath("/Metronic-Shop-UI-master/theme/myPage.do");
                forward.setRedirect(true);
                return forward;
            }

            // 파일 타입 검증
            String contentType = filePart.getContentType();
            if (!contentType.startsWith("image/")) {
                System.out.println("UpdateProfileImage Action 로그: 이미지 파일이 아닙니다.");
                request.setAttribute("message", "이미지 파일만 업로드 가능합니다.");
                forward.setPath("/Metronic-Shop-UI-master/theme/myPage.do");
                forward.setRedirect(true);
                return forward;
            }

            // 업로드 디렉토리 설정
            String uploadDir = request.getServletContext().getRealPath("/uploads/profiles");
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            // 고유한 파일명 생성 (UUID 사용)
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
            String newFileName = UUID.randomUUID().toString() + fileExtension;
            String filePath = uploadDir + File.separator + newFileName;

            // 파일 저장
            filePart.write(filePath);

            // 웹 애플리케이션 상의 상대 경로
            String webPath = "/uploads/profiles/" + newFileName;

            // 데이터베이스에 프로필 이미지 경로 업데이트
            UserDTO userDTO = new UserDTO();
            userDTO.setUserEmail(userEmail);
            userDTO.setUserProfile(webPath);
            userDTO.setCondition("UPDATE_PROFILE_IMAGE");

            UserDAO userDAO = new UserDAO();
            boolean updateResult = userDAO.update(userDTO);

            if (updateResult) {
                System.out.println("UpdateProfileImage Action 로그: 프로필 이미지 업데이트 성공");

                // 세션에 프로필 이미지 경로 업데이트
                session.setAttribute("userProfile", webPath);

                // 성공 메시지와 함께 마이페이지로 리다이렉트
                request.setAttribute("message", "프로필 이미지가 성공적으로 업데이트되었습니다.");
                forward.setPath("/Metronic-Shop-UI-master/theme/myPage.do");
                forward.setRedirect(true);
            } else {
                System.out.println("UpdateProfileImage Action 로그: 프로필 이미지 업데이트 실패");
                request.setAttribute("message", "프로필 이미지 업데이트에 실패했습니다.");
                forward.setPath("/Metronic-Shop-UI-master/theme/myPage.do");
                forward.setRedirect(true);
            }

        } catch (Exception e) {
            System.out.println("UpdateProfileImage Action 로그: 예외 발생 - " + e.getMessage());
            e.printStackTrace();

            // 예외 발생 시 에러 메시지와 함께 마이페이지로 리다이렉트
            request.setAttribute("message", "프로필 이미지 처리 중 오류가 발생했습니다: " + e.getMessage());
            forward.setPath("/Metronic-Shop-UI-master/theme/myPage.do");
            forward.setRedirect(true);
        }

        return forward;
    }
}