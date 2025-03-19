package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class AdminDeleteBoardAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		
		boardDTO.setBoardNumber(Integer.parseInt(request.getParameter("boardNum")));
		
		if(boardDAO.delete(boardDTO)) {
			request.setAttribute("msg", "이벤트 삭제 성공");
			request.setAttribute("flag", true);
			request.setAttribute("url", "/mywebapp/theme/BoardPage.jsp");
		}
		else {
			request.setAttribute("msg", "이벤트 삭제 실패");
			request.setAttribute("flag", false);
		}
		forward.setPath("alert.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
