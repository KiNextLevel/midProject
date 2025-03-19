package controller.asyn;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

@WebServlet("/check")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서버 도착 성공 ["+request.getParameter("m_id")+"]");
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setCondition("SELECTONE_CHECK");
		memberDTO.setM_id(request.getParameter("m_id"));
		memberDTO = memberDAO.selectOne(memberDTO);
	
		
		PrintWriter out = response.getWriter();
		if(memberDTO == null) {  // 사용자가 입력한 아이디가 중복되지 않으면 ? 
			out.print("true");
		}
		else {
			out.print("false");
		}		
	}

}
