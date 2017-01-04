package bitcamp.java89.ems2.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      // 클라이언트가 요청한 서블릿 경로를 알아낸다.
      String servletPath = request.getServletPath();
      
      // 스프링 IoC 컨테이너에서 서블릿 경로에 해당하는 객체를 찾는다.
      PageController pageController = 
          (PageController)ContextLoaderListener.applicationContext.getBean(servletPath);
      
      // 페이지 컨트롤러를 호출하여 작업을 실행시킨다.
      String viewUrl = pageController.service(request, response);
      
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
      } else {
        // 페이지 컨트롤러가 리턴한 뷰 컴포넌트(JSP)로 화면 출력을 위임한다.
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
        rd.include(request, response);
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
      rd.forward(request, response);
      return;
    }
  } 
}









