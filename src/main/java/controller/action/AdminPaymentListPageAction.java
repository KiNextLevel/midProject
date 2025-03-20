package controller.action;

import java.util.ArrayList;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import model.dao.PaymentDAO;
import model.dto.PaymentDTO;

public class AdminPaymentListPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		PaymentDTO paymentDTO = new PaymentDTO();
		PaymentDAO paymentDAO = new PaymentDAO();
		
		ArrayList<PaymentDTO> datas = paymentDAO.selectAll(paymentDTO);
		
		if(datas != null) {
			request.setAttribute("datas", datas);
			forward.setPath("/mywebapp/target-free-admin-template/AdminPaymentListPage.jsp");
			forward.setRedirect(false);
		}
		else {
			System.out.println("매출페이지 에러["+datas+"]");
			request.setAttribute("msg", "매출 페이지 이동 실패");
			request.setAttribute("flag", false);
			forward.setPath("/mywebapp/theme/alert.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}
}
