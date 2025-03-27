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
        this.messageService = NurigoApp.INSTANCE.initialize("NCSLFOCVDKQN2IJW", "YOUR7XFOD6LZ69EC9PWZ6UWFPL9EUQNR", "https://api.coolsms.co.kr");
    }
    public void sendOne() {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01095534998");
        message.setTo("01095534998");
        message.setText("테스트문자 제발");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);
    }
}
