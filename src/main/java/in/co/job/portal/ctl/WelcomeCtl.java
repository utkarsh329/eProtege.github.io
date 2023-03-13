package in.co.job.portal.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeCtl 
{

	 @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	    public String welcome(Model model) 
	    {
	        return "welcome";
	    }	
}
