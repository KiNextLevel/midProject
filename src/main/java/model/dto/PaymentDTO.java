package model.dto;

import java.util.Date;

public class PaymentDTO {

	private int paymentNumber; // 결제 번호 (PK)
	private int paymentPrice; // 결제 금액
	private Date paymentDate; // 결제 날짜
	private String paymentType; // 구매 종류
	private String userEmail; // 이메일 (FK)
	private String userName; //유저 이름 
	private int productNumber; // 구매한 상품 번호 (FK)
	private String productName; // 상품명
	private String condition; // 컨디션
	private String searchKeyword; // SearchKeyword
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(int paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public int getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
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
		return "PaymentDTO [paymentNumber=" + paymentNumber + ", paymentPrice=" + paymentPrice + ", paymentDate="
				+ paymentDate + ", paymentType=" + paymentType + ", userEmail=" + userEmail + ", userName=" + userName
				+ ", productNumber=" + productNumber + ", productName=" + productName + ", condition=" + condition
				+ ", searchKeyword=" + searchKeyword + "]";
	}

	

}
