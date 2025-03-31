package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.BoardDAO;
import org.example.webapp.model.dto.BoardDTO;

public class AdminModifyBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("adminModifyBoardAction 로그: 도착");
		ActionForward forward = new ActionForward();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		System.out.println("boardNum: "+request.getParameter("boardNum"));
		System.out.println("boardTitle: "+request.getParameter("boardTitle"));
		String boardNum = (String)request.getParameter("boardNum");
		System.out.println("boardNum: ["+boardNum+"]");
		boardDTO.setBoardNumber((Integer.parseInt((String)request.getParameter("boardNum"))));
		boardDTO = boardDAO.selectOne(boardDTO);
		System.out.println("boardDTO = "+boardDTO);
		boardDTO.setBoardTitle((String)request.getParameter("boardTitle"));
		boardDTO.setBoardContent((String)request.getParameter("boardContent"));
		boardDTO.setBoardLimit(Integer.parseInt((String)request.getParameter("boardLimit")));

		if(boardDAO.update(boardDTO)) {
			request.setAttribute("msg", "수정 완료");
			request.setAttribute("flag", true);
			request.setAttribute("url", "boardPage.do");
		}
		else {
			request.setAttribute("msg", "수정 실패");
			request.setAttribute("flag", false);
		}
		forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}