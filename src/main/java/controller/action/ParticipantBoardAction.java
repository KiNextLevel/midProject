package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.dao.BoardDAO;
import model.dao.ParticipantDAO;
import model.dao.UserDAO;
import model.dto.BoardDTO;
import model.dto.ParticipantDTO;
import model.dto.UserDTO;

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

		int boardNum = Integer.parseInt(request.getParameter("boardNum"));	//이벤트 번호 받음
		boardDTO.setBoardNumber(boardNum);
		String userEmail = (String)session.getAttribute("userEmail");	//로그인 한 사용자 이메일

		boardDTO = boardDAO.selectOne(boardDTO);

		participantDTO.setParticipantBoardNumber(boardNum);
		participantDTO.setParticipantUserEmail(userEmail);

		if((participantDAO.selectOne(participantDTO)).getParticipantBoardNumber() >= boardDTO.getBoardLimit()){  //인원수 다 차면
			request.setAttribute("msg", "인원이 다 찼습니다");
			request.setAttribute("flag", false);
			forward.setPath("/mywebapp/theme/alert.jsp");	
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
		forward.setPath("/mywebapp/theme/alert.jsp");	
		forward.setRedirect(false);
		return forward;
	}
}

