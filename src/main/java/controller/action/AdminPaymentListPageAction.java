package controller.action;

import java.util.ArrayList;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.PaymentDAO;
import org.example.webapp.model.dto.PaymentDTO;

public class AdminPaymentListPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("[LOG] : AdminPaymentListPageAction");
		ActionForward forward = new ActionForward();
		PaymentDTO paymentDTO = new PaymentDTO();

		paymentDTO.setCondition("SELECTALL_ADMIN_PAYMENTS");

		PaymentDAO paymentDAO = new PaymentDAO();

		paymentDTO.setCondition("SELECTALL_ADMIN_PAYMENTS");
		ArrayList<PaymentDTO> datas = paymentDAO.selectAll(paymentDTO);	//결제 내역
		System.out.println("PaymentListPage log datas: "+datas);

		if(datas != null) {
			request.setAttribute("datas", datas);	//결제 내역 가지고 페이지 이동
			forward.setPath("/target-free-admin-template/AdminPaymentListPage.jsp");
			forward.setRedirect(false);
		}
		else {
			System.out.println("매출페이지 에러["+datas+"]");
			request.setAttribute("datas", datas);
			request.setAttribute("msg", "매출 페이지 이동 실패");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}
}