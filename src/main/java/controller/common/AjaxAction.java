package controller.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 비동기용 Action
public interface AjaxAction {
    void executeAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
