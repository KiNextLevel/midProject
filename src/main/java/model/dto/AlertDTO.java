package model.dto;

import java.util.Date;

public class AlertDTO {

	private int alertNum; 	// 알림 번호 (PK)
	private Date alertDate; // 알림 날짜
	private String alertContent; // 알림 내용
	private boolean alertIsWatch; // 열람 여부 (TINYINT → boolean 변환)
	private String userEmail; // 이메일 (FK)
	private String condition; // 컨디션
	private String searchKeyword; // SearchKeyword

	public int getAlertNum() {
		return alertNum;
	}

	public void setAlertNum(int alertNum) {
		this.alertNum = alertNum;
	}

	public Date getAlertDate() {
		return alertDate;
	}

	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}

	public String getAlertContent() {
		return alertContent;
	}

	public void setAlertContent(String alertContent) {
		this.alertContent = alertContent;
	}

	public boolean isAlertIsWatch() {
		return alertIsWatch;
	}

	public void setAlertIsWatch(boolean alertIsWatch) {
		this.alertIsWatch = alertIsWatch;
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
