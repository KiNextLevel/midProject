package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.dto.PreferenceDTO;
import model.dto.UserDTO;

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
		userDTO = userDAO.selectOne(userEmail);

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
			request.setAttribute("url", "/mywebapp/theme/controller.jsp?command=MYPAGEACTION");
		}
		else {
			request.setAttribute("msg", "회원 정보 수정 실패했습니다");
			request.setAttribute("flag", false);
		}
		forward.setPath("alert.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
