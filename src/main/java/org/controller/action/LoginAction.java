package controller.action;

import java.util.HashMap;
import java.util.Map;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.dao.MemberDAO;
import model.dto.MemberDTO;
import model.dto.ProductDTO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward actionForward = new ActionForward();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setM_id(request.getParameter("m_id"));
		memberDTO.setM_pw(request.getParameter("m_pw"));
		MemberDAO memberDAO = new MemberDAO();
		HttpSession session = request.getSession();
		// 컨디션"로그인"
       memberDTO.setCondition("SELECTONE");
//       memberDTO.setM_id(request.getParameter("m_id"));
//       memberDTO.setM_pw(request.getParameter("m_pw"));
       memberDTO = memberDAO.selectOne(memberDTO);
       System.out.println("로그인 로그: "+memberDTO);
       if (memberDTO != null) {
          //cart 만들기
         Map<ProductDTO, Integer> cart = (HashMap<ProductDTO,Integer>) session.getAttribute("cart");
         if (cart == null) {
            cart = new HashMap<>();
         }
           //session에 userId, userName, role, cart 저장
           session.setAttribute("userId", memberDTO.getM_id());
           session.setAttribute("userName", memberDTO.getM_name());
           session.setAttribute("role", memberDTO.getM_role());
           session.setAttribute("cart", cart);
   
           // url, flag, msg 요청단위 저장
           // alert.jsp에 url, true, msg 보내기
           request.setAttribute("msg", "로그인 성공!");
           request.setAttribute("flag", true);
           request.setAttribute("url", "controller.jsp?action=MAINPAGE");
       }
       else {
           // url, flag, msg 요청단위 저장
           // alert.jsp에 url, false, msg 보내기
           request.setAttribute("msg", "로그인정보가 틀렸습니다");
           request.setAttribute("flag", false);
       }
       actionForward.setPath("alert.jsp");
       actionForward.setRedirect(false);
		return actionForward;
	}
	
}
