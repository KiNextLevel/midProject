package controller.action;

import java.util.ArrayList;

import org.example.webapp.model.dao.BoardDAO;
import org.example.webapp.model.dao.ParticipantDAO;
import org.example.webapp.model.dto.BoardDTO;
import org.example.webapp.model.dto.ParticipantDTO;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public class BoardPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
			System.out.println("boardPage 로그: 도착");
			String realPath = request.getServletContext().getRealPath("/theme/BoardPage.jsp");
			System.out.println("실제 파일 경로: " + realPath);
		
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		
		ArrayList<BoardDTO> datas = boardDAO.selectAll(boardDTO);
		System.out.println("BoardPage SELECTALL 로그:"+datas);
		
		request.setAttribute("datas", datas);
		forward.setPath("/Metronic-Shop-UI-master/theme/BoardPage.jsp");
		String path = forward.getPath();
		System.out.println(path);
					//	webapp/Metronic-Shop-UI-master/theme/BoardPage.jsp
		forward.setRedirect(false);
		return forward;
		
	}
}
