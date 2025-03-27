package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TossPaymentPageAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        ActionForward forward = new ActionForward();
        System.out.println("TossPaymentPage 로그: 도착");
        String productNum = request.getParameter("Product_Num");
        System.out.println("TossPaymentPageAction 로그: productNum = " + productNum);
        String productName = request.getParameter("Product_Name");
        System.out.println("TossPaymentPageAction 로그: productName = " + productName);
        String productPrice = request.getParameter("Product_Price");
        System.out.println("TossPaymentPageAction 로그: productPrice = "+productPrice);

        // 한글을 URL 인코딩
        String encodedProductName = URLEncoder.encode(productName, StandardCharsets.UTF_8);
        System.out.println("TossPaymentPageAction 로그: encodedProductName = " + encodedProductName);

        // .html로 갈거라 url에 담아서  Query parameter로 전달보냄
        forward.setPath("/widget/index.html?productName=" + encodedProductName +
                "&productPrice=" + productPrice + "&productNum=" + productNum);
        forward.setRedirect(true);
        return forward;
    }
}

