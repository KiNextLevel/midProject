package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.BoardDAO;
import org.example.webapp.model.dao.ParticipantDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.BoardDTO;
import org.example.webapp.model.dto.ParticipantDTO;
import org.example.webapp.model.dto.UserDTO;

public class ParticipantBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();
		ParticipantDTO participantDTO = new ParticipantDTO();
		ParticipantDAO participantDAO = new ParticipantDAO();

		// boardNum 파라미터 확인
		String boardNumStr = request.getParameter("boardNumber");
		if (boardNumStr == null || boardNumStr.isEmpty()) {
			request.setAttribute("msg", "이벤트 번호가 제공되지 않았습니다.");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
			forward.setRedirect(false);
			return forward;
		}

		int boardNum = Integer.parseInt(boardNumStr);
		boardDTO.setBoardNumber(boardNum);

		String userEmail = (String)session.getAttribute("userEmail");
		if (userEmail == null) {
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
			forward.setRedirect(false);
			return forward;
		}

		boardDTO = boardDAO.selectOne(boardDTO);
		if (boardDTO == null) {
			request.setAttribute("msg", "존재하지 않는 이벤트입니다.");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
			forward.setRedirect(false);
			return forward;
		}

		participantDTO.setParticipantBoardNumber(boardNum);
		participantDTO.setParticipantUserEmail(userEmail);

		// 현재 참가자 수 확인
		ParticipantDTO participantCount = participantDAO.selectOne(participantDTO);
		if (participantCount != null && participantCount.getParticipantBoardNumber() >= boardDTO.getBoardLimit()) {
			request.setAttribute("msg", "인원이 다 찼습니다");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
			forward.setRedirect(false);
			return forward;
		}



		if(participantDAO.insert(participantDTO)) {
			request.setAttribute("msg", "이벤트 참가 성공");
			request.setAttribute("flag", true);
			request.setAttribute("url", "boardPage.do");
		}
		else {
			request.setAttribute("msg", "참가 실패");
			request.setAttribute("flag", false);
		}
		forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
		forward.setRedirect(false);
		return forward;
	}

}

