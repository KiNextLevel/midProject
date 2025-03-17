package model.dto;

public class StyleDTO {
	private int styleNum;       // 스타일 번호 (PK)
    private String styleType;     // 스타일
    private String userEmail;    // 스타일 유저 이메일 (FK)
    private String condition;      // 컨디션
    private String searchKeyword;  // SearchKeyword
    
	public int getStyleNum() {
		return styleNum;
	}
	public void setStyleNum(int styleNum) {
		this.styleNum = styleNum;
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

}
