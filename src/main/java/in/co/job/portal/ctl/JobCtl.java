package in.co.job.portal.ctl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.job.portal.dto.JobDTO;
import in.co.job.portal.dto.UserDTO;
import in.co.job.portal.exception.DuplicateRecordException;
import in.co.job.portal.form.JobForm;
import in.co.job.portal.service.JobServiceInt;

@Controller
@RequestMapping("/ctl/job")
public class JobCtl extends BaseCtl {

	@Autowired
	private JobServiceInt service;
	
	
	@ModelAttribute
	public void preload(Model model) {		

		HashMap<String,String> map=new HashMap<String, String>();
		map.put("Full Time","Full Time");
		map.put("Part Time","Part Time");
		model.addAttribute("type",map);
		
	}
	
	@GetMapping
	public String display(@RequestParam(required = false) Long id,@ModelAttribute("form") JobForm form, Model model) {
		if (form.getId() > 0) {
			JobDTO bean=service.findBypk(id);
			form.populate(bean);
		}
		return "job";
	}
	
	
	
	@PostMapping
	public String submit(HttpSession session,@Valid @ModelAttribute("form")  JobForm form, BindingResult bindingResult,
			Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/job";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) 
			{
				if (bindingResult.hasErrors()) {
					return "job";
				}
				
				JobDTO bean = (JobDTO) form.getDto();
				UserDTO uDto=(UserDTO)session.getAttribute("user");
				bean.setRecruiterId(uDto.getId());
				boolean flag= true;
				 List<JobDTO> x = service.list();
				 for(JobDTO m:x)
				 {
			          if(m.getCompanyName().equals(bean.getCompanyName()))	    		  
			          {
			        	        long l=m.getId();
//			  				{
			        	        bean.setId(l);
			  					service.update(bean);
			  					flag=false;
			  					model.addAttribute("success", "Job updated Successfully!!!!");
			  					break;        	  
				      }
				}
				if(flag) 
				{
				service.add(bean);
				model.addAttribute("success", "Job Added Successfully!!!!");
				}
			
				return "job";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "job";
		}
		return "";
	}
	
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(HttpSession session,@ModelAttribute("form") JobForm form,
			@RequestParam(required = false) String operation, Model model) 
	{

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/job/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) 
		{
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation))
		{
			pageNo--;
		}else if (OP_NEW.equals(operation))
		{
			return "redirect:/ctl/job";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation))
		{
			pageNo = 1;
			if (form.getIds() != null)
			{
				for (long id : form.getIds()) 
				{
					System.out.println(id);
				JobDTO x =	service.findBypk(id);
					System.out.println("Hi");
					service.delete(x);
					System.out.println("deleted");
				}
				model.addAttribute("success","Deleted Successfully!!!");
			} else 
			{
				model.addAttribute("error","Select at least one record");
			}
		}
		JobDTO dto=(JobDTO)form.getDto();
		
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		if(uDto.getRoleId()==2)
		{
			dto.setRecruiterId(uDto.getId());
		}
		
		
		List<JobDTO> list =service.search(dto, pageNo, pageSize);
		List<JobDTO> totallist =service.search(dto);
		model.addAttribute("list", list);
		
		

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation))
		{
			model.addAttribute("error","Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		
		return "jobList";
	}
	
}
