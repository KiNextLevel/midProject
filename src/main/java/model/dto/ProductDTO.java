package model.dto;

public class ProductDTO {
	private int productNumber; // 상품 번호 (PK)
	private String productName; // 상품 이름
	private String productDescription; // 상품 설명
	private int productPrice; // 상품 가격
	private String condition; // 컨디션
	private String searchKeyword; // SearchKeyword

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
				+ productDescription + ", productPrice=" + productPrice + ", condition=" + condition
				+ ", searchKeyword=" + searchKeyword + "]";
	}
	
	
	

}