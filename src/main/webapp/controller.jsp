<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "controller.common.Action ,controller.common.ActionForward, controller.common.ActionFactory"%>
<jsp:useBean id="factory" class="controller.common.ActionFactory" scope="application"></jsp:useBean>

<%
   String command = request.getParameter("command");
   System.out.println("CTRL로그 - 액션: ["+command+"]");
   
   Action action = factory.getAction(command);	//command 넣으면 action 객체 반환
   ActionForward forward = action.execute(request); //action이 execute 실행하고
   													// Actionforward 반환
   
   if(forward == null){
		// 에러페이지로 이동
   }
	if(forward.isRedirect()){
		response.sendRedirect(forward.getPath());
	}
	else{
		pageContext.forward(forward.getPath());
	}
%>
</body>
</html>