package org.example.webapp.model.dao;

import org.example.webapp.model.common.JDBCUtil;
import org.example.webapp.model.dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {

    // 상품 구매 페이지에서 상품 출력 - 상품명, 상품 설명, 상품 가격
    private final String SELECTALL_PRODUCTS_BUY = "SELECT PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE FROM PRODUCT";

    // 00유저 마이페이지에서 상품결제 내역 보기 - 상품명, 결제 내역, 가격
    private final String SELECTALL_PAYMENT_HISTORY = "SELECT p.PRODUCT_NAME, pm.PAYMENT_DATE, pm.PAYMENT_PRICE " +
                                             "FROM PAYMENT pm " +
                                             "JOIN PRODUCT p ON pm.PRODUCT_NUM = p.PRODUCT_NUM " +
                                             "WHERE pm.PAYMENT_USER_EMAIL = ?";


    // 상품번호로 상품명, 상품 설명, 상품 가격 가져오기(추가)
    private final String SELECTONE = "SELECT PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE FROM PRODUCT WHERE PRODUCT_NUM = ?";

    // 관리자용 상품 추가하기 쿼리문 - 상품 이름, 설명, 가격 추가하기(상품번호는 AUTO)
    private final String INSERT = "INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE) VALUES (?, ?, ?)";

    // 관리자용 상품 정보 업데이트 - 상품명, 설명, 가격 변경 하기
    private final String UPDATE = "UPDATE PRODUCT SET PRODUCT_NAME = ?, PRODUCT_DESCRIPTION = ?, PRODUCT_PRICE = ? WHERE PRODUCT_NUM = ?";

    // 관리자용 상품 삭제하기 (상품명, 설명, 가격 삭제 하기)
    // final String DELETE="DELETE FROM PRODUCT WHERE PRODUCT_NUM = ?;"
    private final String DELETE = "UPDATE PRODUCT SET PRODUCT_STATUS = 'INACTIVE' WHERE PRODUCT_NUM = ?";

    public ArrayList<ProductDTO> selectAll(ProductDTO productDTO) {
        ArrayList<ProductDTO> datas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        System.out.println("ProductDAO 로그:");
        try {
            conn = JDBCUtil.connect();
            // 만약, 유저 마이페이지에서 상품 결제 내역 보기라면? (상품명, 결제 내역, 가격)
            if (productDTO != null && productDTO.getCondition().equals("SELECTALL_PRODUCTS_BUY")) {
                pstmt = conn.prepareStatement(SELECTALL_PRODUCTS_BUY);

            } else if (productDTO.getCondition().equals("SELECTALL_PAYMENT_HISTORY")) {
                pstmt = conn.prepareStatement(SELECTALL_PAYMENT_HISTORY);
                pstmt.setString(1, productDTO.getUserEmail()); // 추가
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ProductDTO data = new ProductDTO();
                // 상품 구매 페이지에서 상품 출력(상품명, 상품 설명, 상품 가격)
                if (productDTO.getCondition().equals("SELECTALL_PRODUCTS_BUY")) {
                    data.setProductName(rs.getString("PRODUCT_NAME"));   // 상품 이름
                    data.setProductDescription(rs.getString("PRODUCT_DESCRIPTION"));  //상품 설명
                    data.setProductPrice(rs.getInt("PRODUCT_PRICE"));   // 상품 가격
                    datas.add(data);
                    // 유저 마이페이지에서 상품결제 내역 보기(상품명, 결제 내역, 가격)
                } else if (productDTO.getCondition().equals("SELECTALL_PAYMENT_HISTORY")) {
                    data.setProductName(rs.getString("PRODUCT_NAME"));   // 상품 이름
                    data.setPaymentDate(rs.getDate("PAYMENT_DATE"));     // 결제 날짜
                    data.setPaymentPrice(rs.getInt("PAYMENT_PRICE"));    // 결제한 금액
                    datas.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
        return datas;
    }

    public ProductDTO selectOne(ProductDTO productDTO) {
        ProductDTO data = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTONE);
            pstmt.setInt(1, productDTO.getProductNumber()); // 상품번호 설정

            rs = pstmt.executeQuery();

            if (rs.next()) {
                data = new ProductDTO();
                data.setProductName(rs.getString("PRODUCT_NAME"));  // 상품 이름
                data.setProductDescription(rs.getString("PRODUCT_DESCRIPTION"));  // 상품 설명
                data.setProductPrice(rs.getInt("PRODUCT_PRICE"));  // 상품 가격
                data.setProductNumber(productDTO.getProductNumber()); // 요청한 상품번호도 설정
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.disconnect(conn, pstmt, rs);
        }

        return data;
    }


    public boolean insert(ProductDTO productDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(INSERT);

            pstmt.setString(1, productDTO.getProductName());  // 상품 이름
            pstmt.setString(2, productDTO.getProductDescription());  // 상품 설명
            pstmt.setInt(3, productDTO.getProductPrice());   //상품 가격

            int result = pstmt.executeUpdate(); // 실행 후 적용된 행 개수 반환
            return result > 0; // 1개 이상 변경되었으면 true 반환

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    public boolean update(ProductDTO productDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setString(1, productDTO.getProductName());    // 상품 이름
            pstmt.setString(2, productDTO.getProductDescription());  // 상품 설명
            pstmt.setInt(3, productDTO.getProductPrice());    // 상품 가격
            pstmt.setInt(4, productDTO.getProductNumber()); // WHERE 조건 - 특정 상품 수정

            int result = pstmt.executeUpdate(); // 실행 후 적용된 행 개수 반환
            return result > 0; // 1개 이상 변경되었으면 true 반환

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    public boolean delete(ProductDTO productDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(DELETE);

            pstmt.setInt(1, productDTO.getProductNumber()); // WHERE 조건 - 특정 상품 삭제

            int result = pstmt.executeUpdate(); // 실행 후 적용된 행 개수 반환
            return result > 0; // 1개 이상 변경되었으면 true 반환

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }
}
