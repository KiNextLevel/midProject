package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.BoardDAO;
import org.example.webapp.model.dto.BoardDTO;


public class AdminModifyBoardPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		
		int boardNum = Integer.parseInt((String)request.getAttribute("boardNum"));
		boardDTO.setBoardNumber(boardNum);
		boardDTO = boardDAO.selectOne(boardDTO);
		
		if(boardDTO == null) {
			request.setAttribute("msg", "이벤트를 찾을 수 없습니다");
			request.setAttribute("flag", false);
			forward.setPath("/mywebapp/theme/alert.jsp");
			forward.setRedirect(false);
		}
		else {
			request.setAttribute("data", boardDTO);
			forward.setPath("/mywebapp/theme/AdminModifyBoardPage.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}
}

