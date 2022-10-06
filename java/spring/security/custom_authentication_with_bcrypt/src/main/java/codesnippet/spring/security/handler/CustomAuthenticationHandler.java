package codesnippet.spring.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import codesnippet.spring.security.model.UserRegisterInfo;
import codesnippet.spring.security.service.UserService;

@Component
public class CustomAuthenticationHandler implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    private UserService userService;

    @Autowired
    public CustomAuthenticationHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName();

        LOGGER.info("Authenticate the user[{}]", username);

        UserRegisterInfo info = this.userService.findByUserName(username);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("user", info);

        response.sendRedirect(request.getContextPath() + "/");
    }
}
