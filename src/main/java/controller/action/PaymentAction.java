package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import controller.logic.SendMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.PaymentDAO;
import org.example.webapp.model.dao.ProductDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.PaymentDTO;
import org.example.webapp.model.dto.ProductDTO;
import org.example.webapp.model.dto.UserDTO;


public class PaymentAction implements Action {
    @Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: PAYMENT ACTION 도착1");
		ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO();
		ProductDAO productDAO = new ProductDAO();
		ProductDTO productDTO = new ProductDTO();
		PaymentDAO paymentDAO = new PaymentDAO();
		PaymentDTO paymentDTO = new PaymentDTO();
		SendMessage send = new SendMessage();

        int productNum = Integer.parseInt((String)request.getParameter("productNum"));
		//String productPrice = (String)request.getParameter("productPrice");
		System.out.println("productNum = "+productNum);
		System.out.println("CONT 로그: PAYMENT ACTION 도착2");
		userDTO.setUserEmail((String)session.getAttribute("userEmail"));
		userDTO.setCondition("SELECTONE_USERINFO");
		userDTO = userDAO.selectOne(userDTO);
		String userName = userDTO.getUserName();
		System.out.println("userName: ["+userName+"]");
		String phone = userDTO.getUserPhone();
		System.out.println("phone: ["+phone+"]");

        // 상품에 해당하는 정보 가져오기
        productDTO.setProductNumber(productNum);
        productDTO = productDAO.selectOne(productDTO);
		int productPrice = productDTO.getProductPrice();
		String productName = productDTO.getProductName();
		System.out.println("productPrice: ["+productPrice+"]");
		System.out.println("productName:["+productName+"]");
		System.out.println("productDTO: ["+productDTO+"]");
		if(productDTO == null){	//상품을 못 찾으면
			request.setAttribute("msg", "상품을 찾을 수 없습니다");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
			forward.setRedirect(false);
		}
		//이메일, 가격, 결제방법, 상품번호
		else{
			paymentDTO.setProductNumber(productDTO.getProductNumber());
			paymentDTO.setUserEmail((String)session.getAttribute("userEmail"));
			paymentDTO.setPaymentPrice(productDTO.getProductPrice());
			paymentDTO.setProductName(productDTO.getProductName());
			paymentDAO.insert(paymentDTO);
			//send.sendPay(phone, userName, productPrice, productName);
		}
		request.setAttribute("productNum", productNum);
		forward.setPath("addToken.do");
		forward.setRedirect(false);
        return forward;
        }
	}

