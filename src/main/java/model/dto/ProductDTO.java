package model.dto;

import java.util.Date;

public class ProductDTO {
	private int productNumber; // 상품 번호 (PK)
	private String productName; // 상품 이름
	private String productDescription; // 상품 설명
	private int productPrice; // 상품 가격
	private Date paymentDate;  //결제 날짜
	private int paymentPrice; //결제 금액 
	private String userEmail; //유저 이메일 
	private String condition; // 컨디션
	private String searchKeyword; // SearchKeyword

	
	

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
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
		return "ProductDTO [productNumber=" + productNumber + ", productName=" + productName + ", productDescription="
				+ productDescription + ", productPrice=" + productPrice + ", paymentDate=" + paymentDate
				+ ", paymentPrice=" + paymentPrice + ", userEmail=" + userEmail + ", condition=" + condition
				+ ", searchKeyword=" + searchKeyword + "]";
	}

	
	
	
	

}
