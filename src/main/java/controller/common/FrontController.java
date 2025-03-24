package controller.common;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionFactory;
import controller.common.ActionForward;

@WebServlet("*.do")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 5 * 1024 * 1024,   // 5MB
		maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActionFactory factory;

    public FrontController() {
        super();
        factory = new ActionFactory();
    }

    private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String command = request.getRequestURI();
    	System.out.println(" CTRL 로그 : command ["+command+"]");
		command = command.replace("/Metronic-Shop-UI-master/theme/", "");
		if(command.contains(("target"))){
			command = command.replace("/target-free-admin-template/", "");
		}
		System.out.println(" CTRL 자른 로그 : command ["+command+"]");

    	Action action = factory.getAction(command); // 팩토리 패턴
    	ActionForward forward = action.execute(request);
    	
    	if(forward == null){
    		// 에러페이지로 이동
    	}
    	
    	if(forward.isRedirect()){
    		response.sendRedirect(forward.getPath());
    	}
    	else{
    		RequestDispatcher dispatcher= request.getRequestDispatcher(forward.getPath());
    		dispatcher.forward(request, response);
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("로그 : GET 요청 호출됨");
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("로그 : POST 요청 호출됨");
		doAction(request, response);
	}
}

