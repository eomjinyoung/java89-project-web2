package bitcamp.java89.ems2.control.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;

@Controller("/auth/loginform.do")
public class LoginFormControl implements PageController {
  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("title", "로그인");
    request.setAttribute("contentPage", "/auth/loginform.jsp");
    return "/main.jsp";
  }
}








