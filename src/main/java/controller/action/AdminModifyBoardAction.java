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
		boardDTO.setBoardNumber((Integer.parseInt(request.getParameter("boardNum"))));//수정할 이벤트 번호
		boardDTO = boardDAO.selectOne(boardDTO);
		System.out.println("boardDTO = "+boardDTO);
		boardDTO.setBoardTitle(request.getParameter("boardTitle"));	//수정할 이벤트 제목
		boardDTO.setBoardContent(request.getParameter("boardContent"));	//수정할 이벤트 내용
		System.out.println("boardLimit: ["+request.getParameter("boardLimit")+"]");
		boardDTO.setBoardLimit(Integer.parseInt(request.getParameter("boardLimit")));	////수정할 이벤트 제한인원
		boardDTO.setCondition("UPDATE_BOARD");

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