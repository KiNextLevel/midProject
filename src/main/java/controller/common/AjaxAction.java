package controller.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 비동기용 Action
public interface AjaxAction {
    // 기존 Action은 request만 있었는데 응답까지 하는 AjaxAction 생성
    void executeAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
