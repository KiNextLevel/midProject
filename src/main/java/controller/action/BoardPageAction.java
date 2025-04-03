package controller.action;

import java.util.ArrayList;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.BoardDAO;
import org.example.webapp.model.dto.BoardDTO;

public class BoardPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
			System.out.println("boardPage 로그: 도착");
		
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		boardDTO.setSearchKeyword((String)session.getAttribute("userEmail"));
		ArrayList<BoardDTO> datas = boardDAO.selectAll(boardDTO);	//이벤트 리스트
		System.out.println("board SELECTALL 로그:"+datas);
		
		request.setAttribute("datas", datas);
		forward.setPath("/Metronic-Shop-UI-master/theme/BoardPage.jsp");
		forward.setRedirect(false);
		return forward;

	}
}
