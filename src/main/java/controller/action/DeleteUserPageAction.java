package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteUserPageAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        ActionForward forward = new ActionForward();

        forward.setPath("/Metronic-Shop-UI-master/theme/AccountDelete.jsp");
        forward.setRedirect(true);

        return forward;
    }
}
