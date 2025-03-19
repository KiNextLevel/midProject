package controller.asyn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.dto.ProductDTO;
import model.dao.ProductDAO;

@WebServlet("/sortListServlet")
public class SortList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SortList() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String condition = request.getParameter("condition");
        
        // 여기에 데이터베이스에서 상품 데이터를 가져오는 코드 (쿼리 처리)
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = new ProductDTO();
        dto.setCondition(condition);
        ArrayList<ProductDTO> sortedProducts = dao.selectAll(dto);

        // 응답 헤더 설정
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // 정렬된 상품 목록을 HTML 테이블 형식으로 응답
        out.println("<h3>상품목록</h3>");
        out.println("<table border='1'>");
        out.println("<tr><th>상품 번호</th><th>상품 명</th><th>가격</th><th>재고</th><th>등록날짜</th><th>좋아요갯수</th></tr>");
        
        if (sortedProducts.isEmpty()) {
            out.println("<tr><td colspan='5'>상품이 없습니다!</td></tr>");
        } else {
            for (ProductDTO product : sortedProducts) {
                out.println("<tr>");
                out.println("<td>" + product.getP_num() + "</td>");
                out.println("<td><a href='controller.jsp?action=PRODUCTDETAILPAGE&p_num=" + product.getP_num() + "'>" + product.getP_name() + "</a></td>");
                out.println("<td>" + product.getP_price() + "</td>");
                out.println("<td>" + product.getP_stock() + "</td>");
                out.println("<td>" + product.getP_regdate() + "</td>");
                out.println("<td>" + product.getLikes() + "</td>");
                out.println("</tr>");
            }
        }
        
        out.println("</table>");
    }
}
