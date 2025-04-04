package org.example.webapp.model.dao;

import org.example.webapp.model.common.JDBCUtil;
import org.example.webapp.model.dto.PaymentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PaymentDAO {
    // 결제 번호, 유저 이메일, 이름 , 상품명, 결제금액, 결제 날짜 조회하기
    private final String SELECTALL_ADMIN_PAYMENTS = "SELECT "
            + "p.PAYMENT_NUM, " // 결제 번호
            + "u.USER_EMAIL, " // 유저 이메일
            + "u.USER_NAME, " // 유저 이름
            + "pr.PRODUCT_NAME, " // 상품명
            + "p.PAYMENT_PRICE, " // 결제 가격
            + "p.PAYMENT_DATE " // 결제 날짜
            + "FROM PAYMENT p " + "JOIN USER u ON p.PAYMENT_USER_EMAIL = u.USER_EMAIL "
            + "JOIN PRODUCT pr ON p.PRODUCT_NUM = pr.PRODUCT_NUM ORDER BY p.PAYMENT_DATE DESC";
    // 추가하기(일별, 월별, 연도별 매출 조회

    // 유저 마이페이지 - 결제한 상품명, 결제일, 결제 날짜
    private final String SELECTALL_PRODUCTLIST =
            "SELECT " +
                    "    P.PRODUCT_NAME, " +  // 상품 이름
                    "    P.PRODUCT_PRICE, " +  // 상품 가격
                  //  "    P.PAYMENT_PRICE, " +  // 상품 가격
                    "    M.PAYMENT_DATE " +  // 결제 날짜
                    "FROM PAYMENT M " +
                    "JOIN PRODUCT P ON M.PRODUCT_NUM = P.PRODUCT_NUM " +
                    "WHERE M.PAYMENT_USER_EMAIL = ? ORDER BY M.PAYMENT_DATE DESC;";


    // 사용자 결제 내역 저장하기
    // 유저 이메일, 금액, 결제 날짜, 결제 방법, 상품 번호
    private final String INSERT = "INSERT INTO PAYMENT "
            + "(PAYMENT_USER_EMAIL, PAYMENT_PRICE, PAYMENT_DATE, PAYMENT_TYPE, PRODUCT_NUM)"
            + "VALUES (?, ?, NOW(), ?, ?)";

    private final String UPDATE = "";
    private final String DELETE = "";

    public ArrayList<PaymentDTO> selectAll(PaymentDTO paymentDTO) {
        ArrayList<PaymentDTO> datas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.connect();

            // 조건 분기
            if ("SELECTALL_ADMIN_PAYMENTS".equals(paymentDTO.getCondition())) {
                pstmt = conn.prepareStatement(SELECTALL_ADMIN_PAYMENTS);
            } else if ("SELECTALL_PRODUCTLIST".equals(paymentDTO.getCondition())) {
                pstmt = conn.prepareStatement(SELECTALL_PRODUCTLIST);
                pstmt.setString(1, paymentDTO.getUserEmail());
            } else {
                throw new IllegalArgumentException("알 수 없는 condition입니다: " + paymentDTO.getCondition());
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                PaymentDTO data = new PaymentDTO();

                // 관리자 전용 데이터 컬럼들
                if ("SELECTALL_ADMIN_PAYMENTS".equals(paymentDTO.getCondition())) {
                    data.setPaymentNumber(rs.getInt("PAYMENT_NUM"));
                    data.setUserEmail(rs.getString("USER_EMAIL"));
                    data.setUserName(rs.getString("USER_NAME"));
                    data.setProductName(rs.getString("PRODUCT_NAME"));
                    data.setPaymentPrice(rs.getInt("PAYMENT_PRICE"));
                    data.setPaymentDate(rs.getDate("PAYMENT_DATE"));
                }
                if ("SELECTALL_PRODUCTLIST".equals(paymentDTO.getCondition())) {
                    data.setProductName(rs.getString("PRODUCT_NAME"));
                    data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
                 //   data.setPaymentPrice(rs.getInt("PAYMENT_PRICE"));
                    data.setPaymentDate(rs.getTimestamp("PAYMENT_DATE"));
                }

                datas.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }

        return datas;
    }

//    public ArrayList<PaymentDTO> selectAll(PaymentDTO paymentDTO) {
//        ArrayList<PaymentDTO> datas = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        try {
//            conn = JDBCUtil.connect();
//            if (paymentDTO.getCondition().equals("SELECTALL_ADMIN_PAYMENTS"))
//                pstmt = conn.prepareStatement(SELECTALL_ADMIN_PAYMENTS);
//            else if(paymentDTO.getCondition().equals("SELECTONE_CHECK")){
//                pstmt = conn.prepareStatement(SELECTALL_PRODUCTLIST);
//                pstmt.setString(1, paymentDTO.getUserEmail());
//            }
//
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                PaymentDTO data = new PaymentDTO();
//
//                data.setPaymentNumber(rs.getInt("PAYMENT_NUM"));
//                data.setUserEmail(rs.getString("USER_EMAIL"));
//                data.setUserName(rs.getString("USER_NAME"));
//                data.setProductName(rs.getString("PRODUCT_NAME"));
//                data.setPaymentPrice(rs.getInt("PAYMENT_PRICE"));
//                data.setPaymentDate(rs.getDate("PAYMENT_DATE"));
//                datas.add(data);
//            }
//            return datas;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            JDBCUtil.disconnect(conn, pstmt);
//        }
//
//    }

//    public ArrayList<PaymentDTO> selectOne(PaymentDTO paymentDTO) {
//        ArrayList<PaymentDTO> list = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            conn = JDBCUtil.connect();
//            pstmt = conn.prepareStatement(SELECTONE);
//            pstmt.setString(1, paymentDTO.getUserEmail());
//
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                PaymentDTO data = new PaymentDTO();
//                data.setProductName(rs.getString("PRODUCT_NAME"));
//                data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
//                data.setPaymentPrice(rs.getInt("PAYMENT_PRICE"));
//                data.setPaymentDate(rs.getDate("PAYMENT_DATE"));
//                list.add(data);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.disconnect(conn, pstmt);
//        }
//
//        return list;
//    }


    public boolean insert(PaymentDTO paymentDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtil.connect();

            pstmt = conn.prepareStatement(INSERT);
            pstmt.setString(1, paymentDTO.getUserEmail());
            pstmt.setInt(2, paymentDTO.getPaymentPrice());
            pstmt.setString(3, paymentDTO.getPaymentType());
            pstmt.setInt(4, paymentDTO.getProductNumber());

            int result = pstmt.executeUpdate();
            if (result > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    private boolean update(PaymentDTO paymentDTO) {
        return false;
    }

    private boolean delete(PaymentDTO paymentDTO) {
        return false;
    }
}