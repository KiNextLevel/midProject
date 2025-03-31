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
        int productNum = (Integer)request.getAttribute("productNum");
        System.out.println("productNum: "+productNum);
        UserDTO userDTO = new UserDTO();
        UserDAO userDAO = new UserDAO();
        String email = (String)session.getAttribute("userEmail");
        System.out.println("email: "+email);
        System.out.println("userEmail: "+session.getAttribute("userEmail"));
        userDTO.setCondition("SELECTONE_USERINFO");
        userDTO.setUserEmail((String)session.getAttribute("userEmail"));
        userDTO = userDAO.selectOne(userDTO);
        System.out.println("userDTO: "+userDTO);
        int userToken = userDTO.getUserToken();
        if(productNum == 2){
        userToken += 1;
        userDTO.setUserToken(userToken);
        userDAO.update(userDTO);
        }
        else if(productNum == 3){
            userToken += 5;
            userDTO.setUserToken(userToken);
            userDAO.update(userDTO);
        }
        else if(productNum == 4){
            userToken += 10;
            userDTO.setUserToken(userToken);
            userDAO.update(userDTO);
        }
        return null;
    }
}
