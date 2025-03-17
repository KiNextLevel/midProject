package model.dto;

public class StyleDTO {
	private int styleNumber; // 스타일 번호 (PK)
	private String styleType; // 스타일
	private String userEmail; // 스타일 유저 이메일 (FK)
	private String condition; // 컨디션
	private String searchKeyword; // SearchKeyword

	public int getStyleNumber() {
		return styleNumber;
	}

	public void setStyleNumber(int styleNumber) {
		this.styleNumber = styleNumber;
	}

	public String getStyleType() {
		return styleType;
	}

	public void setStyleType(String styleType) {
		this.styleType = styleType;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
		return "StyleDTO [styleNumber=" + styleNumber + ", styleType=" + styleType + ", userEmail=" + userEmail
				+ ", condition=" + condition + ", searchKeyword=" + searchKeyword + "]";
	}
	
	

}
