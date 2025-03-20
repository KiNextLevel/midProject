package org.example.webapp.controller.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.controller.common.Action;
import org.example.webapp.controller.common.ActionForward;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;


public class PaymentAction implements Action {
    @Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: PAYMENT ACTION 도착");
		ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO();
		ProductDAO productDAO = new ProductDAO();
		ProductDTO productDTO = new ProductDTO();
		PaymentDAO paymentDAO = new PaymentDAO();
		PaymentDTO paymentDTO = new PaymentDTO();
        int productNum = (int)request.getAttribute("productNum");
        // 상품에 해당하는 정보 가져오기
        productDTO.setProductNumber(productNum);
        productDTO = productDAO.selectOne(productDTO);
        
        //유저가 상품을 결제
        // 결제 api 사용후 결과 
        // 결제 테이블에 결제금액, 이메일, 상품번호 저장
        paymentDTO.setPaymentProductNumber(productNum);
        paymentDTO.setPaymentPrice(productDTO.getProductPrice());
        paymentDTO.setPaymenUserEmail(session.getAttribute("userEamil"));
//      //상품번호에 따라서 행동이 다름
//      if (productNum == 1) { // 프리미엄 계정
//          System.out.println(" -- 프리미엄 구매");
//      } else { // 토큰 구매
//      	System.out.println(" -- 토큰 구매");
//      }

        // 결제정보 insert위해 정보 넣기
        if (paymentDAO.insert(paymentDTO)) {
        	
        }

        
		forward.setPath("alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
