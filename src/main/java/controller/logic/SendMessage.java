package controller.logic;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
public class SendMessage {
    final DefaultMessageService messageService;
    public SendMessage() {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize("NCSLFOCVDKQN2IJW",
                "YOUR7XFOD6LZ69EC9PWZ6UWFPL9EUQNR",
                "https://api.coolsms.co.kr");
    }
    public void sendOne() {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 010
        // 12345678 형태로 입력되어야 합니다.
        message.setFrom("098");
        message.setTo("01");
        message.setText("테스트");
        SingleMessageSentResponse response =
                this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);
    }
    public void sendPay(String phoneNumber, String userName, int price, String productName, String orderId) {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom(phoneNumber);
        message.setTo(phoneNumber);
        message.setText(userName + "님, " + productName + "을(를) 구매해 주셔서 감사합니다.\n" +
                "결제 금액: " + price + "원\n" +
                "주문 번호: \n" + orderId);
        SingleMessageSentResponse response =
                this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);
    }
}
