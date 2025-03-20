package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.PreferenceDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.PreferenceDTO;
import org.example.webapp.model.dto.UserDTO;

public class ModifyMyPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();

		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();
		PreferenceDTO preferenceDTO = new PreferenceDTO();
		PreferenceDAO preferenceDAO = new PreferenceDAO();

		String userEmail = (String)session.getAttribute("userEmail");
		userDTO.setUserEmail(userEmail);
		userDTO = userDAO.selectOne(userDTO);

		userDTO.setUserDescription(request.getParameter("userDescription"));
		userDTO.setUserHeight(Integer.parseInt((String)request.getParameter("userHeight")));
		userDTO.setUserBody(request.getParameter("userBody"));
		userDTO.setUserEducation(request.getParameter("userEducation"));
		userDTO.setUserJob(request.getParameter("userJob"));
		userDTO.setUserReligion(request.getParameter("userReligion"));
		userDTO.setUserRegion(request.getParameter("userRegion"));
		userDTO.setUserMbti(request.getParameter("userMbti"));
		userDTO.setUserDrink(Integer.parseInt((String)request.getParameter("userDrink")));
		userDTO.setUserSmoke(Boolean.parseBoolean(request.getParameter("userSmoke")));
		preferenceDTO.setPreferenceHeight(Integer.parseInt((String)request.getParameter("preferenceHeight")));//선호 키
		preferenceDTO.setPreferenceBody(request.getParameter("preferenceBody"));//선호 체형
		preferenceDTO.setPreferenceAge("preferenceAge");//선호 나이

		if(userDAO.update(userDTO) && preferenceDAO.update(preferenceDTO)) {
			request.setAttribute("msg", "회원 정보가 수정되었습니다.");
			request.setAttribute("flag", true);
			request.setAttribute("url", "/myPageAction.do");
		}
		else {
			request.setAttribute("msg", "회원 정보 수정 실패했습니다");
			request.setAttribute("flag", false);
		}
		forward.setPath("/mywebapp/theme/alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
