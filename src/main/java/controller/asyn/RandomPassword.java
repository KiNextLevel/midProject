package controller.asyn;

import java.util.UUID;

public class RandomPassword {
    // 랜덤 비밀번호 생성 메서드
    public static String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}
