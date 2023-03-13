package in.co.job.portal.ctl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.co.job.portal.dto.ApplyJobDTO;
import in.co.job.portal.dto.JobDTO;
import in.co.job.portal.dto.UserDTO;
import in.co.job.portal.exception.DuplicateRecordException;
import in.co.job.portal.form.ApplyJobForm;
import in.co.job.portal.service.ApplyJobServiceInt;
import in.co.job.portal.service.JobServiceInt;
import in.co.job.portal.util.DataUtility;

@Controller
@RequestMapping("/ctl/apply")
public class ApplyJobCtl extends BaseCtl {

	@Autowired
	private ApplyJobServiceInt service;

	@Autowired
	private JobServiceInt jobService;
	
	

	@GetMapping
	public String display(HttpSession session, @RequestParam(required = false) Long id, Long jId,
			@ModelAttribute("form") ApplyJobForm form, Model model) {
		if (form.getId() > 0) {
			ApplyJobDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		if (DataUtility.getLong(String.valueOf(jId)) > 0) {
			form.setJobDto(jobService.findBypk(jId));
			session.setAttribute("jobId", jId);
		}
		return "apply";
	}

	@PostMapping
	public String submit(@RequestParam("file") MultipartFile file,HttpSession session, @Valid @ModelAttribute("form") ApplyJobForm form,
			BindingResult bindingResult, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/apply";
		}

		try {
			if (OP_SUBMIT.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "apply";
				}

				ApplyJobDTO bean = (ApplyJobDTO) form.getDto();
				long jId = DataUtility.getLong(String.valueOf(session.getAttribute("jobId")));
				JobDTO jDto = jobService.findBypk(jId);
				bean.setJobId(jDto.getId());
				bean.setJobName(jDto.getName());
				bean.setCompanyName(jDto.getCompanyName());
				bean.setRecruiterId(jDto.getRecruiterId());
				UserDTO uDto=(UserDTO)session.getAttribute("user");
				bean.setUserId(uDto.getId());
				bean.setUserName(uDto.getFirstName()+" "+uDto.getLastName());
				bean.setApDate(new Date());
				bean.setResumeFile(file.getBytes());
				bean.setFileName(DataUtility.removeSpace(uDto.getFirstName()+uDto.getEmail())+".pdf");
			    //System.out.println(bean.getJobId());
			    //System.out.println(bean.getUserId());
				 List<ApplyJobDTO> x =	service.list();
				 boolean flag=true;
                 for(ApplyJobDTO m: x)
        	     {
			     if((m.getJobId()==bean.getJobId()) && (m.getUserId()==bean.getUserId()))
			      {
			       long i=m.getId();
			       bean.setId(i);
                   service.update(bean);
                   flag=false;
					      model.addAttribute("success", "Already applied for this Job!!!!");
					      break;
			    	  }
        	     }
				 if(flag) 
				 {
					   service.add(bean);
					   model.addAttribute("success", "Job Applied Successfully!!!!");
				 }
        	     
				return "apply";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "apply";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") ApplyJobForm form,
			@RequestParam(required = false) String operation, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/apply/search";
		}                   //redirect to new page
		
		
		
		

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/apply";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					ApplyJobDTO dto = new ApplyJobDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		ApplyJobDTO dto = (ApplyJobDTO) form.getDto();
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		if(uDto.getRoleId()==2) {
			dto.setRecruiterId(uDto.getId());
		}if(uDto.getRoleId()==3) {
			dto.setUserId(uDto.getId());
		}
		
		if(dto.getFileName()!=null) {
			try {
				DataUtility.cmdCommand(dto.getFileName().trim());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		List<ApplyJobDTO> list = service.search(dto, pageNo, pageSize);
		List<ApplyJobDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
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
		return "applyList";
	}
	
	@GetMapping("/getFile/{id}")
	public void getStudentPhoto(HttpServletResponse response, @PathVariable("id") long id) throws Exception {
		response.setContentType("application/pdf");

		Blob blb=service.getFileById(id);
		
		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}

}
