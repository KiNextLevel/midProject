package org.example.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/data")
public class DataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // JSON 응답 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 샘플 데이터 생성
        Map<String, Object> data = new HashMap<>();
        data.put("message", "AJAX 요청 성공!");
        data.put("timestamp", System.currentTimeMillis());
        data.put("status", "success");

        // JSON으로 변환하여 응답
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        mapper.writeValue(out, data);
    }
}
