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

import java.util.ArrayList;

public class ParticipantBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("ParticipantBoardAction!");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();
		ParticipantDTO participantDTO = new ParticipantDTO();
		ParticipantDAO participantDAO = new ParticipantDAO();

		int boardNum = Integer.parseInt(request.getParameter("boardNumber"));	//이벤트 번호 받음
		boardDTO.setBoardNumber(boardNum);
		System.out.println("boardNumber: ["+boardNum+"]");
		String userEmail = (String)session.getAttribute("userEmail");	//로그인 한 사용자 이메일
		System.out.println("userEmail: ["+userEmail+"]");

		participantDTO.setParticipantUserEmail(userEmail);
		ArrayList<ParticipantDTO> datas = participantDAO.selectAll(participantDTO);
		participantDTO.setParticipantBoardNumber(boardNum);
		System.out.println("participantDTO: ["+participantDTO+"]");
		System.out.println("participantDAO.selectOne(participantDTO)).getParticipantBoardNumber(): ["+participantDAO.selectOne(participantDTO).getParticipantBoardNumber()+"]");
		System.out.println("boardDTO.getBoardLimit(): ["+boardDAO.selectOne(boardDTO).getBoardLimit()+"]");

		for(ParticipantDTO v: datas){	//이미 참가 신청한 이벤트 버튼 다시 누르면 참가 취소
			if (v.getParticipantBoardNumber() == boardNum) {
				participantDTO.setCondition("DELETE");
				participantDAO.delete(participantDTO);
				System.out.println("v.getParticipantBoardNumber: ["+v.getParticipantBoardNumber()+"]");
				System.out.println("v.getParticipantUserEmail: "+v.getParticipantUserEmail()+"]");
				request.setAttribute("msg", "참가 취소 되었습니다");
				request.setAttribute("url", "boardPage.do");
				request.setAttribute("flag", true);
				forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
				forward.setRedirect(false);
				return forward;
			}
		}
		//인원 다 찼으면 참가 못함
		if((participantDAO.selectOne(participantDTO)).getParticipantBoardNumber() >= boardDAO.selectOne(boardDTO).getBoardLimit()){  //인원수 다 차면
			request.setAttribute("msg", "인원이 다 찼습니다");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
			forward.setRedirect(false);
			return forward;
		}
		if(participantDAO.insert(participantDTO)) {	//참가 성공
			request.setAttribute("msg", "이벤트 참가 성공");
			request.setAttribute("flag", true);
			request.setAttribute("url", "boardPage.do");
		}
		else {										//참가 실패
			request.setAttribute("msg", "참가 실패");
			request.setAttribute("flag", false);
		}
		forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}

