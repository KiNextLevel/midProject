package controller.asyn;

import java.io.IOException;

import org.json.simple.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.LikesDAO;
import model.dto.LikesDTO;

/**
 * Servlet implementation class Likeservlet
 */
@WebServlet("/Likeservlet")
public class Likeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Likeservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			String userId = (String)session.getAttribute("userId");
		    int productNum = Integer.parseInt(request.getParameter("p_num"));
		    
		    LikesDTO likesDTO=new LikesDTO();
		    LikesDAO likesDAO=new LikesDAO();

		    likesDTO.setL_m_id(userId);
		    likesDTO.setL_p_num(productNum);

		    boolean liked;
		    if (likesDAO.selectOne(likesDTO) != null) { // 이미 좋아요 상태라면
		    	likesDAO.delete(likesDTO);	//좋아요 취소
		        liked = false;	
		    } else {
		        likesDAO.insert(likesDTO); // 좋아요 추가
		        System.out.println("좋아요 추가");
		        liked = true;
		    }

		    JSONObject result = new JSONObject();	// JSON 객체 생성, JSON 반환할거니까
		    result.put("liked", liked);

		    response.getWriter().print(result);	// JSON 타입 반환	
	}
}
