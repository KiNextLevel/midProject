package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

public class AddTokenAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("AddTokenAction log!");
        HttpSession session = request.getSession();
        int productNum = (Integer)request.getAttribute("productNum");   //구매한 상품
        System.out.println("productNum: "+productNum);
        UserDTO userDTO = new UserDTO();
        UserDAO userDAO = new UserDAO();
        String email = (String)session.getAttribute("userEmail"); //로그인 한 사용자 이메일
        System.out.println("email: "+email);
        System.out.println("userEmail: "+session.getAttribute("userEmail"));
        userDTO.setCondition("SELECTONE_USERINFO"); //selectOne 한 번 하는게 서버비용 아낄 수 있음
        userDTO.setUserEmail(email);
        userDTO = userDAO.selectOne(userDTO);
        System.out.println("userDTO: "+userDTO);
        int userToken = userDTO.getUserToken(); //로그인 한 사용자의 토큰 개수
        System.out.println("userToken: ["+userToken+"]");

        //프리미엄 결제
        if(productNum == 1){
            userDTO.setCondition("UPDATE_PREMIUM");
            if(userDAO.update(userDTO)) {
                System.out.println("update successs");
                session.setAttribute("userPremium", userDTO.isUserPremium());
                System.out.println("userPremium: ["+session.getAttribute("userPremium")+"]");
            }
            else{
                System.out.println("update fail");
            }
        }
        else if(productNum == 2){   //토큰 1개 구매
        userToken += 1;
        userDTO.setUserToken(userToken);
        userDTO.setCondition("UPDATE_ADD_TOKEN");
        userDAO.update(userDTO);
        }
        else if(productNum == 3){   //토큰 5개 구매
            userToken += 5;
            userDTO.setUserToken(userToken);
            userDTO.setCondition("UPDATE_ADD_TOKEN");
            userDAO.update(userDTO);
        }
        else if(productNum == 4){   //토큰 10개 구매
            userToken += 10;
            userDTO.setUserToken(userToken);
            userDTO.setCondition("UPDATE_ADD_TOKEN");
            userDAO.update(userDTO);
        }
        return null;
    }
}
