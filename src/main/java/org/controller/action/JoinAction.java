package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setM_id(request.getParameter("m_id"));
		memberDTO.setM_pw(request.getParameter("m_pw"));
		memberDTO.setM_mail(request.getParameter("m_mail"));
		memberDTO.setM_name(request.getParameter("m_name"));
		memberDTO.setM_phone(request.getParameter("m_phone"));
		System.out.println("JOIN 로그 가져온 ID["+memberDTO.getM_id()+"]");
	      // 중복이 없으면 회원가입 진행
	      if (memberDAO.insert(memberDTO)) {
	         request.setAttribute("msg", "회원가입 성공!");
	         request.setAttribute("flag", true);
	         request.setAttribute("url", "controller.jsp?action=MAINPAGE");
	      }
	      else {
	         request.setAttribute("msg", "회원가입 실패!");
	         request.setAttribute("flag", false);
	      }
	    forward.setPath("alert.jsp");
	    forward.setRedirect(false);
	    return forward;
	}
}
