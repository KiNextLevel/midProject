package controller.common;

import java.util.HashMap;
import java.util.Map;

import controller.action.AdminAddBlackAction;
import controller.action.AdminAddBoardAction;
import controller.action.AdminAddBoardPageAction;
import controller.action.AdminDeleteBlackAction;
import controller.action.AdminDeleteBoardAction;
import controller.action.AdminMainPageAction;
import controller.action.AdminModifyBoardPageAction;
import controller.action.AdminPaymentListPageAction;
import controller.action.AdminReportPageAction;
import controller.action.BoardPageAction;
import controller.action.ModifyMyPageAction;
import controller.action.MyPageAction;
import controller.action.ParticipantBoardAction;


public class ActionFactory {
	
	private Map<String, Action>factory;
	
	public ActionFactory() {
		factory = new HashMap<String, Action>(); //command 넣으면 Action 반환
		factory.put("ADMINDELETEBOARD", new AdminDeleteBoardAction());
		factory.put("ADMINPAYMENTLISTPAGE", new AdminPaymentListPageAction());
		factory.put("ADMINADDBLACK", new AdminAddBlackAction());
		factory.put("ADMINADDBOARD", new AdminAddBoardAction());
		factory.put("ADMINDELETEBLACK", new AdminDeleteBlackAction());
		factory.put("ADMINREPORTPAGE", new AdminReportPageAction());
		factory.put("BOARDPAGE", new BoardPageAction());
		factory.put("MODIFYMYPAGE", new ModifyMyPageAction());
		factory.put("MYPAGE", new MyPageAction());
		factory.put("PARTICIPANTBOARD", new ParticipantBoardAction());
		factory.put("ADMINMODITYBOARDPAGE", new AdminModifyBoardPageAction());
		factory.put("ADMINADDBOARDPAGE", new AdminAddBoardPageAction());
	}
	public Action getAction(String command) {
		System.out.println("팩토리 로그"+this.factory.get(command));
		System.out.println("팩토리 로그"+this.factory);
		return this.factory.get(command);
	}
}
