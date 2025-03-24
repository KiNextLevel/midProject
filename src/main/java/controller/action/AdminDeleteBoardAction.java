package controller.action;

import controller.common.Action;

import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.BoardDAO;
import org.example.webapp.model.dto.BoardDTO;


public class AdminDeleteBoardAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("apple 3");
		ActionForward forward = new ActionForward();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		System.out.println("adminDeleteBoardAcion 로그: 도착");
		
		System.out.println(Integer.parseInt(request.getParameter("boardNum")));
		boardDTO.setBoardNumber(Integer.parseInt(request.getParameter("boardNum")));
		
		if(boardDAO.delete(boardDTO)) {
			System.out.println("삭제 성공 로그");
			request.setAttribute("msg", "이벤트 삭제 성공");
			request.setAttribute("url", "boardPage.do");
			request.setAttribute("flag", true);
		}
		else {
			request.setAttribute("msg", "이벤트 삭제 실패");
			request.setAttribute("flag", false);
		}
		forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
		forward.setRedirect(false);
		System.out.println("apple 4");
		return forward;
	}
}
