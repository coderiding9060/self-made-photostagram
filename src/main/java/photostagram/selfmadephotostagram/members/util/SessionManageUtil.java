package photostagram.selfmadephotostagram.members.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class SessionManageUtil {

    private HttpSession httpSession;

    @Autowired
    public SessionManageUtil(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public String createSession(String sessionName, String sessionValue) {
        String result = "";
        httpSession.setAttribute(sessionName,sessionValue);
        String checkSession = httpSession.getAttribute(sessionName).toString();
        if(checkSession.length()>0){
            result = checkSession;
        }
        return result;
    }



}
