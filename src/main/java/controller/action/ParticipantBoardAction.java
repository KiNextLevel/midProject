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
		System.out.println("ParticipantBoardAcion 도착");

		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));	//이벤트 번호 받음
		System.out.println("boardNumber 로그: "+boardNumber);

		boardDTO.setBoardNumber(boardNumber);
		String userEmail = (String)session.getAttribute("userEmail");	//로그인 한 사용자 이메일

		boardDTO = boardDAO.selectOne(boardDTO);

		participantDTO.setParticipantBoardNumber(boardNumber);	//해당 게시글 번호
		participantDTO.setParticipantUserEmail((String)session.getAttribute("userEmail"));	//로그인 한 사용자
		System.out.println("participantDAO selectOne 로그:"+(participantDAO.selectOne(participantDTO)));
		System.out.println("participantDTO 로그:" +participantDTO);

		try {
			if((participantDAO.selectOne(participantDTO)).getParticipantBoardNumber() >= boardDTO.getBoardLimit()){  //인원수 다 차면
				request.setAttribute("msg", "인원이 다 찼습니다");
				request.setAttribute("flag", false);
				forward.setPath("alert.jsp");	
				forward.setRedirect(false);
				return forward;
			}
			else if(participantDAO.selectOne(participantDTO) != null) {	//사용자가 이미 참가하기 눌렀으면
				request.setAttribute("msg", "이미 참가하기 누르셨습니다");
				request.setAttribute("flag", false);
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
			forward.setPath("alert.jsp");	
			forward.setRedirect(false);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "유효하지 않은 요청입니다");
			request.setAttribute("flag", false);
			forward.setPath("alert.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}
}

