package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.BoardDAO;
import org.example.webapp.model.dto.BoardDTO;

public class AdminModifyBoardPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("adminModifyBoardPage log");
		ActionForward forward = new ActionForward();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();

		int boardNum = Integer.parseInt((String)request.getParameter("boardNum"));
		System.out.println("boardNum["+boardNum+"]");
		boardDTO.setBoardNumber(boardNum);
		boardDTO = boardDAO.selectOne(boardDTO);
//		String boardTitle = boardDTO.getBoardTitle();
//		String boardContent = boardDTO.getBoardContent();
//		int boardLimit = boardDTO.getBoardLimit();
		System.out.println("boardDTO ="+boardDTO);

		if(boardDTO == null) {
			request.setAttribute("msg", "이벤트를 찾을 수 없습니다");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
			forward.setRedirect(false);
		}
		else {
			request.setAttribute("data", boardDTO);
			forward.setPath("/Metronic-Shop-UI-master/theme/AdminModifyBoardPage.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}
}