package org.example.webapp.model.dao;

import org.example.webapp.model.common.JDBCUtil;
import org.example.webapp.model.dto.PaymentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PaymentDAO {
    // 결제 번호, 유저 이메일, 이름 , 상품명, 결제금액, 결제 날짜 조회하기
    final String SELECTALL_ADMIN_PAYMENTS = "SELECT "
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
    final String SELECTONE =
            "SELECT " +
                    "    P.PRODUCT_NAME, " +
                    "    P.PRODUCT_PRICE, " +
                    "    M.PAYMENT_PRICE, " +
                    "    M.PAYMENT_DATE " +
                    "FROM PAYMENT M " +
                    "JOIN PRODUCT P ON M.PRODUCT_NUM = P.PRODUCT_NUM " +
                    "WHERE M.PAYMENT_USER_EMAIL = ?";


    // 사용자 결제 내역 저장하기
    // 유저 이메일, 금액, 결제 날짜, 결제 방법, 상품 번호
    final String INSERT = "INSERT INTO PAYMENT "
            + "(PAYMENT_USER_EMAIL, PAYMENT_PRICE, PAYMENT_DATE, PAYMENT_TYPE, PRODUCT_NUM)"
            + "VALUES (?, ?, CURRENT_DATE, ?, ?)";

    final String UPDATE = "";
    final String DELETE = "";

    public ArrayList<PaymentDTO> selectAll(PaymentDTO paymentDTO) {
        ArrayList<PaymentDTO> datas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtil.connect();
            if (paymentDTO.getCondition().equals("SELECTALL_ADMIN_PAYMENTS"))
                pstmt = conn.prepareStatement(SELECTALL_ADMIN_PAYMENTS);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PaymentDTO data = new PaymentDTO();

                data.setPaymentNumber(rs.getInt("PAYMENT_NUM"));
                data.setUserEmail(rs.getString("USER_EMAIL"));
                data.setUserName(rs.getString("USER_NAME"));
                data.setProductName(rs.getString("PRODUCT_NAME"));
                data.setPaymentPrice(rs.getInt("PAYMENT_PRICE"));
                data.setPaymentDate(rs.getDate("PAYMENT_DATE"));
                datas.add(data);
            }
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }

    }

    public ArrayList<PaymentDTO> selectOne(PaymentDTO paymentDTO) {
        ArrayList<PaymentDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTONE);
            pstmt.setString(1, paymentDTO.getUserEmail());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                PaymentDTO data = new PaymentDTO();
                data.setProductName(rs.getString("PRODUCT_NAME"));
                data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
                data.setPaymentPrice(rs.getInt("PAYMENT_PRICE"));
                data.setPaymentDate(rs.getDate("PAYMENT_DATE"));
                list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }

        return list;
    }


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

    public boolean update(PaymentDTO paymentDTO) {
        return false;
    }

    public boolean delete(PaymentDTO paymentDTO) {
        return false;
    }
}