package controller.asyn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.webapp.model.dao.AlertDAO;
import org.example.webapp.model.dto.AlertDTO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateAlertStatus")
public class ReadAlert extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ReadAlert 로그: [doPost] 도착");
        String alertNumber = request.getParameter("alertNumber");
        System.out.println(request.getParameter("alertNumber"));
        boolean alertIsWatch = Boolean.parseBoolean(request.getParameter("alertIsWatch"));

        // 여기서 알림 상태를 DB에서 업데이트하는 로직을 구현
        boolean updateSuccess = updateAlertStatusInDatabase(alertNumber, alertIsWatch);

        // 상태 업데이트 결과를 JSON 형식으로 응답
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println("{ \"success\": " + updateSuccess + " }");
    }

    private boolean updateAlertStatusInDatabase(String alertNumber, boolean alertIsWatch) {
        System.out.println(" -- updateAlertStatusInDatabase -- 로그: [updateAlertStatusInDatabase] ");
        // DB 연결 후, 알림 상태를 업데이트하는 코드 작성
        AlertDAO alertDAO = new AlertDAO();
        AlertDTO alertDTO = new AlertDTO();
        System.out.println(" -- updateAlertStatusInDatabase -- alertDTO: " + alertNumber);
        alertDTO.setAlertNumber(Integer.parseInt(alertNumber));
        System.out.println(" -- updateAlertStatusInDatabase -- alertDTO: " + alertDTO);
        if(alertDAO.update(alertDTO)) {
            System.out.println(" -- updateAlertStatusInDatabase -- alertDTO: " + true);
            return true;
        }
        return false;  // 성공적으로 업데이트 되었다면 true 반환
    }
}