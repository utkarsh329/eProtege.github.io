package in.co.job.portal.ctl;



import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController implements ErrorController 
{

    private final static String ERROR_PATH = "/error";

	@Override
	@RequestMapping(value = ERROR_PATH, produces = "text/html")
	public String getErrorPath() {
		return "error";
	}

   

}
