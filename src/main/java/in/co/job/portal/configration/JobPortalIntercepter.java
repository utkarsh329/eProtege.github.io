package in.co.job.portal.configration;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class JobPortalIntercepter implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
        	
      
        	/*Cookie cookie= WebUtils.getCookie(request, "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE");
        	
        	String locale= cookie.getValue();*/
        	
        	
        	
        	
	        
        	System.out.println("Pre Handle method is Calling");
        	request.setAttribute("error", "Session has been Expaired");
            RequestDispatcher rd = request.getRequestDispatcher("/login");
            rd.forward(request, response);
            return false;
        }
        return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("Post Handle method is Calling");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {

		System.out.println("Request and Response is completed");
	}
}
