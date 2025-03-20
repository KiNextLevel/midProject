package controller.common;

import java.util.HashMap;
import java.util.Map;

import controller.action.AdminAddBlackAction;
import controller.action.AdminAddBoardAction;
import controller.action.AdminAddBoardPageAction;
import controller.action.AdminDeleteBlackAction;
import controller.action.AdminDeleteBoardAction;
import controller.action.AdminModifyBoardAction;
import controller.action.AdminModifyBoardPageAction;
import controller.action.AdminPaymentListPageAction;
import controller.action.AdminReportPageAction;
import controller.action.AdminSendWarningAction;
import controller.action.BoardPageAction;
import controller.action.ModifyMyPageAction;
import controller.action.MyPageAction;
import controller.action.ParticipantBoardAction;


public class ActionFactory {
	
	private Map<String, Action>factory;
	
	public ActionFactory() {
		factory = new HashMap<String, Action>(); //command 넣으면 Action 반환
		factory.put("/adminDeleteBoard", new AdminDeleteBoardAction());
		factory.put("/adminPaymentListPage", new AdminPaymentListPageAction());
		factory.put("/adminAddBlack", new AdminAddBlackAction());
		factory.put("/adminAddBoard", new AdminAddBoardAction());
		factory.put("/adminDeleteBlack", new AdminDeleteBlackAction());
		factory.put("/adminReportPage", new AdminReportPageAction());
		factory.put("/boardPage", new BoardPageAction());
		factory.put("/modifyMyPage", new ModifyMyPageAction());
		factory.put("/myPage", new MyPageAction());
		factory.put("/participantBoard", new ParticipantBoardAction());
		factory.put("/adminModifyBoardPage", new AdminModifyBoardPageAction());
		factory.put("/adminModifyBoard", new AdminModifyBoardAction());
		factory.put("/adminAddBoardPage", new AdminAddBoardPageAction());
		factory.put("/adminSendWarning", new AdminSendWarningAction());
	}
	public Action getAction(String command) {
		System.out.println("팩토리 로그"+this.factory.get(command));
		System.out.println("팩토리 로그"+this.factory);
		return this.factory.get(command);
	}
}
