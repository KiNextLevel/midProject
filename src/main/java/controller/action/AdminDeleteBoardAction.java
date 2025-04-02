package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.BoardDAO;
import org.example.webapp.model.dao.ParticipantDAO;
import org.example.webapp.model.dto.BoardDTO;
import org.example.webapp.model.dto.ParticipantDTO;


public class AdminDeleteBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("apple 3");
		ActionForward forward = new ActionForward();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		ParticipantDTO participantDTO = new ParticipantDTO();
		ParticipantDAO participantDAO = new ParticipantDAO();
		System.out.println("adminDeleteBoardAcion 로그: 도착");

		System.out.println(Integer.parseInt(request.getParameter("boardNum")));	//삭제할 이벤트 번호
		boardDTO.setBoardNumber(Integer.parseInt(request.getParameter("boardNum")));
		participantDTO.setParticipantBoardNumber(Integer.parseInt(request.getParameter("boardNum")));
		participantDTO.setCondition("DELETE_BOARD_NUM");

		if(boardDAO.delete(boardDTO) && participantDAO.delete(participantDTO)) {	//이벤트 삭제하면
			System.out.println("삭제 성공 로그");										//참가자 테이블에서도 해당 이벤트 삭제
			request.setAttribute("msg", "이벤트 삭제 성공");
			request.setAttribute("url", "boardPage.do");
			request.setAttribute("flag", true);
		}
		else {
			request.setAttribute("msg", "이벤트 삭제 실패");
			request.setAttribute("flag", false);
		}
		forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
		forward.setRedirect(false);
		System.out.println("apple 4");
		return forward;
	}
}