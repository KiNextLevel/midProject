package org.example.webapp.model.dto;

public class PreferenceDTO {
	private String userEmail; // 이메일(PK)
	private String userName;
	private int preferenceHeight; // 선호 키
	private String preferenceBody; // 선호 체형
	private String preferenceAge; // 선호 나이
	private String condition; // 컨디션
	private String searchKeyword; // SearchKeyword

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPreferenceHeight() {
		return preferenceHeight;
	}

	public void setPreferenceHeight(int preferenceHeight) {
		this.preferenceHeight = preferenceHeight;
	}

	public String getPreferenceBody() {
		return preferenceBody;
	}

	public void setPreferenceBody(String preferenceBody) {
		this.preferenceBody = preferenceBody;
	}

	public String getPreferenceAge() {
		return preferenceAge;
	}

	public void setPreferenceAge(String preferenceAge) {
		this.preferenceAge = preferenceAge;
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

	@Override
	public String toString() {
		return "PreferenceDTO [userEmail=" + userEmail + ", userName=" + userName + ", preferenceHeight="
				+ preferenceHeight + ", preferenceBody=" + preferenceBody + ", preferenceAge=" + preferenceAge
				+ ", condition=" + condition + ", searchKeyword=" + searchKeyword + "]";
	}

}
