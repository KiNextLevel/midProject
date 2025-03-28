package controller.common;

import java.util.HashMap;
import java.util.Map;

import controller.action.*;


public class ActionFactory {

	private Map<String, Action>factory;

	public ActionFactory() {
		factory = new HashMap<>(); //command 넣으면 Action 반환
		factory.put("adminDeleteBoard.do", new AdminDeleteBoardAction());
		factory.put("adminPaymentListPage.do", new AdminPaymentListPageAction());
		factory.put("adminAddBlack.do", new AdminAddBlackAction());
		factory.put("adminAddBoard.do", new AdminAddBoardAction());
		factory.put("adminDeleteBlack.do", new AdminDeleteBlackAction());
		factory.put("adminReportPage.do", new AdminReportPageAction());
		factory.put("boardPage.do", new BoardPageAction());
		factory.put("modifyMyPage.do", new ModifyMyPageAction());
		factory.put("myPage.do", new MyPageAction());
		factory.put("participantBoard.do", new ParticipantBoardAction());
		factory.put("adminModifyBoardPage.do", new AdminModifyBoardPageAction());
		factory.put("adminModifyBoard.do", new AdminModifyBoardAction());
		factory.put("adminAddBoardPage.do", new AdminAddBoardPageAction());
		factory.put("adminSendWarning.do", new AdminSendWarningAction());
		factory.put("loginPage.do", new LoginPageAction());
		factory.put("login.do", new LoginAction());
		factory.put("logout.do", new LogoutAction());
		factory.put("join.do", new JoinAction());
		factory.put("joinPage.do", new JoinNextAction());
		factory.put("mainPage.do",new MainPageAction());
		factory.put("paymentPage.do",new PaymentPageAction());
		factory.put("payment.do",new PaymentAction());
		factory.put("productPage.do",new ProductPageAction());
		factory.put("report.do",new ReportAction());
		factory.put("reportPage.do",new ReportPageAction());
		factory.put("userDetailPage.do",new UserDetailPageAction());
		factory.put("naverCallback.do", new NaverCallBackAction());
		factory.put("kakaoCallBack.do", new KakaoCallBackAction());
		factory.put("adminPage.do", new AdminMainPageAction());
		factory.put("tossPaymentPage.do", new TossPaymentPageAction());
		factory.put("updateProfile.do", new UpdateProfileAction());
		factory.put("updateProfilePage.do", new UpdateProfilePageAction());
		factory.put("updateProfileImage.do", new UpdateProfileImageAction());
		factory.put("deleteUser.do", new DeleteUserAction());
		factory.put("deleteUserAlert.do", new DeleteUserAlertAction());
		factory.put("userPreferencePage.do", new UserPreferencePageAction());
		factory.put("userPreference.do", new UserPreferenceAction());
	}
	public Action getAction(String command) {
		System.out.println("팩토리 로그"+this.factory.get(command));
		System.out.println("팩토리 로그"+this.factory);
		return this.factory.get(command);
	}
}
