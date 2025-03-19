package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class AdminAddBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();


		boardDTO.setBoardTitle(request.getParameter("BOARDTITLE"));
		boardDTO.setBoardContent(request.getParameter("BOARDCONTENT"));
		boardDTO.setBoardLimit(Integer.parseInt((String)request.getParameter("BOARDLIMIT")));

		boardDAO.update(boardDTO);

		if(boardDAO.update(boardDTO)) {
			request.setAttribute("msg", "이벤트 추가 성공");
			request.setAttribute("flag", true);
			request.setAttribute("url", "/mywebapp/theme/controller.jsp?command=BOARDPAGE");
		}
		else {
			request.setAttribute("msg", "이벤트 추가 실패");
			request.setAttribute("flag", false);
		}
		forward.setPath("alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}

