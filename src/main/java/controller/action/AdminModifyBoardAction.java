package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class AdminModifyBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		
		boardDTO.setBoardNumber(Integer.parseInt((String)request.getAttribute("boardNum")));
		boardDTO.setBoardTitle((String)request.getAttribute("boardTitle"));
		boardDTO.setBoardContent((String)request.getAttribute("boardContent"));
		boardDTO.setBoardLimit(Integer.parseInt((String)request.getAttribute("boardLimit")));
		
		if(boardDAO.update(boardDTO)) {
			request.setAttribute("msg", "수정 완료");
			request.setAttribute("flag", true);
			request.setAttribute("url", "boardPage.do");
		}
		else {
			request.setAttribute("msg", "수정 실패");
			request.setAttribute("flag", false);
		}
		forward.setPath("/mywebapp/theme/alert.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
