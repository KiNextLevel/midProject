package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.BoardDAO;
import org.example.webapp.model.dto.BoardDTO;

public class AdminAddBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("adminAddBoardAcion 로그: 도착");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();

		boardDTO.setBoardTitle(request.getParameter("boardTitle"));
		boardDTO.setBoardContent(request.getParameter("boardContent"));
		boardDTO.setBoardLimit(Integer.parseInt((String)request.getParameter("boardLimit")));

		if(boardDAO.insert(boardDTO)) {
			request.setAttribute("msg", "이벤트 추가 성공");
			request.setAttribute("flag", true);
			request.setAttribute("url", "boardPage.do");
		}
		else {
			request.setAttribute("msg", "이벤트 추가 실패");
			request.setAttribute("flag", false);
		}
		forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
