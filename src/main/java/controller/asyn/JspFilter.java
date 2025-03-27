//package controller.asyn;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//
//@WebFilter("*.jsp")  // 모든 .jsp 파일에 대해 필터 적용
//public class JspFilter implements Filter {
//
//    public JspFilter() {
//        super();
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        String requestURI = httpRequest.getRequestURI();
//        String pageName = requestURI.substring(requestURI.lastIndexOf("/") + 1);
//
//        // 세션에서 로그인된 사용자 정보 확인
//        String userEmail = (String) httpRequest.getSession().getAttribute("userEmail");
//        Integer userRole = (Integer) httpRequest.getSession().getAttribute("userRole");
//        System.out.println("pageName: " + pageName);
//        System.out.println("userRole: " + userRole);
//
//        if (pageName.equals("alert.jsp")) {
//        } else if (userEmail == null && !pageName.equals("Index.jsp") && !pageName.equals("Login.jsp")) {
//            System.out.println("User not logged in");// 한글 문자 인코딩
//            String msg = "로그인 후 이용해주세요";
//            String url = "login.do";
//
////            try {
//                msg = URLEncoder.encode(msg, "UTF-8");
//                url = URLEncoder.encode(url, "UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
////            httpResponse.sendRedirect("/Metronic-Shop-UI-master/theme/alert.jsp?msg=" + msg + "&url=" + url);
////            httpResponse.sendRedirect("alert.jsp?msg=" + msg + "&url=" + url);
////            httpResponse.sendRedirect("alert.jsp");
////            httpResponse.sendRedirect("loginPage.do");
//            return;
//        }
//        if (userEmail != null){
//            if (pageName.equals("Index.jsp") || pageName.equals("Login.jsp")) {
////                System.out.println("Request:" + request);
//                System.out.println("response:" + response);
//                String msg = "이미 로그인 한 상태입니다";
//                String url = "mainPage.do";
//                try {
//                    msg = URLEncoder.encode(msg, "UTF-8");
//                    url = URLEncoder.encode(url, "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                httpResponse.sendRedirect("/Metronic-Shop-UI-master/theme/alert.jsp?msg=" + msg + "&url=" + url);
//                return;
//            } else if (userRole == 0 && pageName.contains("Admin")) {
//                request.setAttribute("msg", "관리자 전용 페이지 입니다");
//                request.setAttribute("url", "mainPage.do");
////                httpResponse.sendRedirect("/Metronic-Shop-UI-master/theme/alert.jsp");
//                request.getRequestDispatcher("/Metronic-Shop-UI-master/theme/alert.jsp").forward(request, response);
//                return;
//            }
//        }
//
//        // 로그인된 사용자의 경우 요청을 계속 진행
//        chain.doFilter(request, response);
//    }
//
//    public void destroy() {
//        // 필터 종료 시 클린업 작업
//    }
//}
