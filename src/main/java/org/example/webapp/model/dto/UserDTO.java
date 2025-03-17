package org.example.webapp.model.dto;

import java.util.Date;

public class UserDTO {
    // 사용자 이메일
    private String USER_EMAIL;
    // 사용자 비밀번호
    private String USER_PASSWORD;
    // 사용자 닉네임
    private String USER_NICKNAME;
    // 회원가입 날짜
    private Date USER_REGDATE;
    // 성별
    private boolean USER_GENDER;
    // 생년월일
    private String USER_BIRTH;
    // 사용자 키
    private int USER_HEIGHT;
    // 사용자 체형
    private String USER_BODY;
    // MBTI
    private String USER_MBTI;
    // 회원 사진
    private String USER_PROFILE;
    // 학력
    private String USER_EDUCATION;
    // 종교
    private String USER_RELIGION;
    // 음주
    private int USER_DRINK;
    // 흡연
    private boolean USER_SMOKE;
    // 직업
    private String USER_JOB;
    // 역할
    private String USER_ROLE;
    // 구독 여부
    private boolean USER_PREMINUM;
    // 토큰 개수
    private int USER_TOKEN;
    // 지역
    private String USER_REGION;
    // 자기소개
    private String USER_DESCRIPTION;
    // 이름
    private String USER_NAME;
    // 컨디션
    private String condition;
    // Getter, Setter 메서드
    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public void setUSER_PASSWORD(String USER_PASSWORD) {
        this.USER_PASSWORD = USER_PASSWORD;
    }

    public String getUSER_NICKNAME() {
        return USER_NICKNAME;
    }

    public void setUSER_NICKNAME(String USER_NICKNAME) {
        this.USER_NICKNAME = USER_NICKNAME;
    }

    public Date getUSER_REGDATE() {
        return USER_REGDATE;
    }

    public void setUSER_REGDATE(Date USER_REGDATE) {
        this.USER_REGDATE = USER_REGDATE;
    }

    public boolean isUSER_GENDER() {
        return USER_GENDER;
    }

    public void setUSER_GENDER(boolean USER_GENDER) {
        this.USER_GENDER = USER_GENDER;
    }

    public String getUSER_BIRTH() {
        return USER_BIRTH;
    }

    public void setUSER_BIRTH(String USER_BIRTH) {
        this.USER_BIRTH = USER_BIRTH;
    }

    public int getUSER_HEIGHT() {
        return USER_HEIGHT;
    }

    public void setUSER_HEIGHT(int USER_HEIGHT) {
        this.USER_HEIGHT = USER_HEIGHT;
    }

    public String getUSER_BODY() {
        return USER_BODY;
    }

    public void setUSER_BODY(String USER_BODY) {
        this.USER_BODY = USER_BODY;
    }

    public String getUSER_MBTI() {
        return USER_MBTI;
    }

    public void setUSER_MBTI(String USER_MBTI) {
        this.USER_MBTI = USER_MBTI;
    }

    public String getUSER_PROFILE() {
        return USER_PROFILE;
    }

    public void setUSER_PROFILE(String USER_PROFILE) {
        this.USER_PROFILE = USER_PROFILE;
    }

    public String getUSER_EDUCATION() {
        return USER_EDUCATION;
    }

    public void setUSER_EDUCATION(String USER_EDUCATION) {
        this.USER_EDUCATION = USER_EDUCATION;
    }

    public String getUSER_RELIGION() {
        return USER_RELIGION;
    }

    public void setUSER_RELIGION(String USER_RELIGION) {
        this.USER_RELIGION = USER_RELIGION;
    }

    public int getUSER_DRINK() {
        return USER_DRINK;
    }

    public void setUSER_DRINK(int USER_DRINK) {
        this.USER_DRINK = USER_DRINK;
    }

    public boolean isUSER_SMOKE() {
        return USER_SMOKE;
    }

    public void setUSER_SMOKE(boolean USER_SMOKE) {
        this.USER_SMOKE = USER_SMOKE;
    }

    public String getUSER_JOB() {
        return USER_JOB;
    }

    public void setUSER_JOB(String USER_JOB) {
        this.USER_JOB = USER_JOB;
    }

    public String getUSER_ROLE() {
        return USER_ROLE;
    }

    public void setUSER_ROLE(String USER_ROLE) {
        this.USER_ROLE = USER_ROLE;
    }

    public boolean isUSER_PREMINUM() {
        return USER_PREMINUM;
    }

    public void setUSER_PREMINUM(boolean USER_PREMINUM) {
        this.USER_PREMINUM = USER_PREMINUM;
    }

    public int getUSER_TOKEN() {
        return USER_TOKEN;
    }

    public void setUSER_TOKEN(int USER_TOKEN) {
        this.USER_TOKEN = USER_TOKEN;
    }

    public String getUSER_REGION() {
        return USER_REGION;
    }

    public void setUSER_REGION(String USER_REGION) {
        this.USER_REGION = USER_REGION;
    }

    public String getUSER_DESCRIPTION() {
        return USER_DESCRIPTION;
    }

    public void setUSER_DESCRIPTION(String USER_DESCRIPTION) {
        this.USER_DESCRIPTION = USER_DESCRIPTION;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "UserDTO{" +
                "USER_EMAIL='" + USER_EMAIL + '\'' +
                ", USER_PASSWORD='" + USER_PASSWORD + '\'' +
                ", USER_NICKNAME='" + USER_NICKNAME + '\'' +
                ", USER_REGDATE=" + USER_REGDATE +
                ", USER_GENDER=" + USER_GENDER +
                ", USER_BIRTH='" + USER_BIRTH + '\'' +
                ", USER_HEIGHT=" + USER_HEIGHT +
                ", USER_BODY='" + USER_BODY + '\'' +
                ", USER_MBTI='" + USER_MBTI + '\'' +
                ", USER_PROFILE='" + USER_PROFILE + '\'' +
                ", USER_EDUCATION='" + USER_EDUCATION + '\'' +
                ", USER_RELIGION='" + USER_RELIGION + '\'' +
                ", USER_DRINK=" + USER_DRINK +
                ", USER_SMOKE=" + USER_SMOKE +
                ", USER_JOB='" + USER_JOB + '\'' +
                ", USER_ROLE='" + USER_ROLE + '\'' +
                ", USER_PREMINUM=" + USER_PREMINUM +
                ", USER_TOKEN=" + USER_TOKEN +
                ", USER_REGION='" + USER_REGION + '\'' +
                ", USER_DESCRIPTION='" + USER_DESCRIPTION + '\'' +
                ", USER_NAME='" + USER_NAME + '\'' +
                '}';
    }
}
