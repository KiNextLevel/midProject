package org.example.webapp.model.dto;

import java.util.Date;

public class UserDTO {
    // 사용자 이메일
    private String userEmail;
    // 사용자 비밀번호
    private String userPassword;
    // 사용자 닉네임
    private String userNickname;
    // 전화번호
    private String userPhone;
    // 회원가입 날짜
    private Date userRegdate;
    // 성별
    private boolean userGender;
    // 성별 변경 여부를 나타내는 플래그
    private boolean userGenderChanged = false;
    // 생년월일
    private String userBirth;
    // 사용자 키
    private int userHeight;
    // 사용자 체형
    private String userBody;
    // MBTI
    private String userMbti;
    // 회원 사진
    private String userProfile;
    // 학력
    private String userEducation;
    // 종교
    private String userReligion;
    // 음주
    private int userDrink;
    // 흡연
    private boolean userSmoke;
    // 흡연 변경 여부를 나타내는 플래그
    private boolean userSmokeChanged = false;
    // 직업
    private String userJob;
    // 역할
    private Integer userRole;
    // 구독 여부
    private boolean userPreminum;
    // 토큰 개수
    private int userToken;
    // 지역
    private String userRegion;
    // 자기소개
    private String userDescription;
    // 이름
    private String userName;
    // 컨디션
    private String condition;
    // 키워드 검색
    private String searchKeyword;
    // 위도
    private double userLatitude;
    // 경도
    private double userLongitude;
    // 회원가입 타입
    private String socialType;

    // Getter, Setter 메서드

    public String getSocialType() {
        return socialType;
    }

    public void setSocialType(String socialType) {
        this.socialType = socialType;
    }



    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Date getUserRegdate() {
        return userRegdate;
    }

    public void setUserRegdate(Date userRegdate) {
        this.userRegdate = userRegdate;
    }

    public boolean getUserGender() {
        return userGender;
    }

    // 추가: 일관성을 위한 isUserGender() 메소드
    public boolean isUserGender() {
        return userGender;
    }

    public void setUserGender(boolean userGender) {
        this.userGender = userGender;
        this.userGenderChanged = true;
    }

    public boolean isUserGenderChanged() {
        return userGenderChanged;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public int getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(int userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserBody() {
        return userBody;
    }

    public void setUserBody(String userBody) {
        this.userBody = userBody;
    }

    public String getUserMbti() {
        return userMbti;
    }

    public void setUserMbti(String userMbti) {
        this.userMbti = userMbti;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserEducation() {
        return userEducation;
    }

    public void setUserEducation(String userEducation) {
        this.userEducation = userEducation;
    }

    public String getUserReligion() {
        return userReligion;
    }

    public void setUserReligion(String userReligion) {
        this.userReligion = userReligion;
    }

    public int getUserDrink() {
        return userDrink;
    }

    public void setUserDrink(int userDrink) {
        this.userDrink = userDrink;
    }

    public boolean isUserSmoke() {
        return userSmoke;
    }

    public void setUserSmoke(boolean userSmoke) {
        this.userSmoke = userSmoke;
        this.userSmokeChanged = true;  // 변경 플래그 설정
    }

    public boolean isUserSmokeChanged() {
        return userSmokeChanged;
    }

    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public boolean isUserPreminum() {
        return userPreminum;
    }

    public void setUserPreminum(boolean userPreminum) {
        this.userPreminum = userPreminum;
    }

    public int getUserToken() {
        return userToken;
    }

    public void setUserToken(int userToken) {
        this.userToken = userToken;
    }

    public String getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(String userRegion) {
        this.userRegion = userRegion;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(double userLongitude) {
        this.userLongitude = userLongitude;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userRegdate=" + userRegdate +
                ", userGender=" + userGender +
                ", userGenderChanged=" + userGenderChanged +
                ", userBirth='" + userBirth + '\'' +
                ", userHeight=" + userHeight +
                ", userBody='" + userBody + '\'' +
                ", userMbti='" + userMbti + '\'' +
                ", userProfile='" + userProfile + '\'' +
                ", userEducation='" + userEducation + '\'' +
                ", userReligion='" + userReligion + '\'' +
                ", userDrink=" + userDrink +
                ", userSmoke=" + userSmoke +
                ", userSmokeChanged=" + userSmokeChanged +
                ", userJob='" + userJob + '\'' +
                ", userRole=" + userRole +
                ", userPreminum=" + userPreminum +
                ", userToken=" + userToken +
                ", userRegion='" + userRegion + '\'' +
                ", userDescription='" + userDescription + '\'' +
                ", userName='" + userName + '\'' +
                ", condition='" + condition + '\'' +
                ", searchKeyword='" + searchKeyword + '\'' +
                ", socialType='" + socialType + '\'' +
                ", userLatitude=" + userLatitude + '\'' +    //위도 추가
                ", userLongitude=" + userLongitude + '\'' + //경도 추가
                '}';
    }
}