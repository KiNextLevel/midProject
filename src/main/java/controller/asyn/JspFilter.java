package controller.asyn;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("*.jsp")  // 모든 .jsp 파일에 대해 필터 적용
public class JspFilter implements Filter {

    public JspFilter() {
        super();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();
        String pageName = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        // 세션에서 로그인된 사용자 정보 확인
        String userEmail = (String) httpRequest.getSession().getAttribute("userEmail");
        Integer userRole = (Integer) httpRequest.getSession().getAttribute("userRole");
        System.out.println("pageName: " + pageName);
        System.out.println("userRole: " + userRole);

        if (userEmail == null && !pageName.equals("Index.jsp") && !pageName.equals("Login.jsp") && !pageName.equals("JoinPage.jsp")) {
            System.out.println("User not logged in");
            String url = "Index.jsp";
            httpResponse.sendRedirect(url);
            return;
        } else if (userEmail != null && !pageName.equals("JoinPage.jsp")) {
            if (pageName.equals("Index.jsp") || pageName.equals("Login.jsp")) {
                System.out.println("이미 로그인 한 상태");
                String url = "mainPage.do";
                httpResponse.sendRedirect(url);
                return;
            } else if (userRole == 0 && pageName.contains("Admin")) {
                System.out.println("관리자 전용 페이지 일반유저가 접근시도");
                String url = "mainPage.do";
                httpResponse.sendRedirect(url);
                return;
            }
        }

        // 로그인된 사용자의 경우 요청을 계속 진행
        chain.doFilter(request, response);
    }

    public void destroy() {
        // 필터 종료 시 클린업 작업
    }
}