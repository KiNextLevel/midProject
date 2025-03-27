package controller.asyn;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("*.jsp")  // 모든 .jsp 파일에 대해 필터 적용
public class JspFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
        // 초기화 작업 (필요하면 추가)
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

        if (userEmail == null && !pageName.equals("Index.jsp") && !pageName.equals("Login.jsp")) {
            System.out.println("User not logged in");
            request.setAttribute("msg", "로그인 후 이용해주세요");
            request.setAttribute("url", "login.do");
            httpResponse.sendRedirect("/Metronic-Shop-UI-master/theme/alert.jsp");
            return;
        }
//        if (userEmail != null){
//            if (pageName.equals("Index.jsp") || pageName.equals("Login.jsp")) {
////                System.out.println("Request:" + request);
//                System.out.println("response:" + response);
//                request.setAttribute("msg","이미 로그인 한 상태입니다");
//                request.setAttribute("url", "mainPage.do");
//                request.getRequestDispatcher("/Metronic-Shop-UI-master/theme/alert.jsp").forward(request, response);
////                httpResponse.sendRedirect("/Metronic-Shop-UI-master/theme/mainPage.jsp");
//                return;
//            } else if (userRole == 0 && pageName.contains("Admin.jsp")) {
//                request.setAttribute("msg", "관리자 전용 페이지 입니다");
//                request.setAttribute("url", "mainPage.do");
//                httpResponse.sendRedirect("/Metronic-Shop-UI-master/theme/alert.jsp");
//                return;
//            }
//        }

        // 로그인된 사용자의 경우 요청을 계속 진행
        chain.doFilter(request, response);
    }

    public void destroy() {
        // 필터 종료 시 클린업 작업
    }
}
