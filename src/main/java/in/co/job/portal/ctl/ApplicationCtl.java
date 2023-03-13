package in.co.job.portal.ctl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import in.co.job.portal.dto.ApplicationDTO;
import in.co.job.portal.dto.EducationDTO;
import in.co.job.portal.dto.ExprienceDTO;
import in.co.job.portal.dto.UserDTO;
import in.co.job.portal.exception.DuplicateRecordException;
import in.co.job.portal.form.ApplicationForm;
import in.co.job.portal.form.ExprienceForm;
import in.co.job.portal.service.ApplicationServiceInt;
import in.co.job.portal.service.EducationServiceInt;
import in.co.job.portal.service.ExprienceServiceInt;
import in.co.job.portal.util.DataUtility;
import in.co.job.portal.util.DataValidator;

@Controller
@RequestMapping("/ctl/application")
public class ApplicationCtl extends BaseCtl {

	@Autowired
	private ApplicationServiceInt service;

	@Autowired
	private EducationServiceInt educationService;

	@Autowired
	private ExprienceServiceInt exprienceService;

	private List<EducationDTO> eduList = null;

	private List<ExprienceDTO> expList = null;

	@GetMapping
	public String display(HttpSession session, @RequestParam(required = false) Long id,
			@ModelAttribute("form") ApplicationForm form, Model model) {
		if (form.getId() > 0) {
			ApplicationDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		UserDTO uDto = (UserDTO) session.getAttribute("user");
		ApplicationDTO dto = service.findByUserId(uDto.getId());
		eduList = new ArrayList<EducationDTO>();
		expList = new ArrayList<ExprienceDTO>();
		eduList.add(new EducationDTO("", "", ""));
		form.setEduForm(eduList);
		form.setExpForm(expList);
		if (dto != null) {
			form.populate(dto);
			EducationDTO eduDto = new EducationDTO();
			eduDto.setApplicationId(dto.getId());
			form.setEduForm(educationService.search(eduDto));
			ExprienceDTO expDto = new ExprienceDTO();
			expDto.setApplicationId(dto.getId());
			form.setExpForm(exprienceService.search(expDto));
		}
		return "application";
	}

	@PostMapping
	public String submit(HttpSession session, @Valid @ModelAttribute("form") ApplicationForm form,
			BindingResult bindingResult, Model model)
	{

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/application";
		}

		if (OP_ADD_MORE.equalsIgnoreCase(form.getOperation())) {
			System.out.println("In Add More------------------------------");
			eduList = form.getEduForm();
			eduList.add(new EducationDTO("", "", ""));
			form.setEduForm(eduList);
			return "application";
		}
		if (OP_ADD_FIELD.equalsIgnoreCase(form.getOperation())) {
			System.out.println("In Add Fiels------------------------------");
			if (form.getExpForm() == null) {
				expList = new ArrayList<ExprienceDTO>();
			} else {
				expList = form.getExpForm();
			}
			expList.add(new ExprienceDTO("", "", ""));
			form.setExpForm(expList);
			return "application";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				ApplicationDTO bean = (ApplicationDTO) form.getDto();
				if(!DataValidator.isName(bean.getDeclaration())) {
					model.addAttribute("jsCode",bean.getDeclaration());
					return "application";
				}
				
				/*
				 * if (bindingResult.hasErrors()) {
				 * System.out.println(bindingResult.getAllErrors()); return "application"; }
				 */
				
				System.out.println("In Save 1 -----------------");
				
				UserDTO uDto = (UserDTO) session.getAttribute("user");
				bean.setUserId(uDto.getId());
				if (bean.getId() > 0) {
					service.update(bean);
					EducationDTO eDto = new EducationDTO();
					eDto.setApplicationId(bean.getId());
					List edList = educationService.search(eDto);
					Iterator<EducationDTO> edIt = edList.iterator();
					while (edIt.hasNext()) {
						EducationDTO educationDTO = (EducationDTO) edIt.next();
						educationService.delete(educationDTO);
					}
					List edList1 = form.getEduForm();
					Iterator<EducationDTO> edIt1 = edList1.iterator();
					while (edIt1.hasNext()) {
						EducationDTO edDto = (EducationDTO) edIt1.next();
						edDto.setApplicationId(bean.getId());
						educationService.add(edDto);
					}

					ExprienceDTO exDto = new ExprienceDTO();
					exDto.setApplicationId(bean.getId());
					List exList = exprienceService.search(exDto);
					Iterator<ExprienceDTO> exIt = exList.iterator();
					while (exIt.hasNext()) {
						ExprienceDTO expDTO = (ExprienceDTO) exIt.next();
						exprienceService.delete(expDTO);
					}
					List exList1 = form.getExpForm();
					Iterator<ExprienceDTO> exIt1 = exList1.iterator();
					while (exIt1.hasNext()) {
						ExprienceDTO expDto = (ExprienceDTO) exIt1.next();
						expDto.setApplicationId(bean.getId());
						exprienceService.add(expDto);
					}

					model.addAttribute("success", "Application update Successfully!!!!");
				} else {
					System.out.println("In Save 2-----------------");
					long pk = service.add(bean);
					List edList = form.getEduForm();
					Iterator<EducationDTO> edIt = edList.iterator();
					while (edIt.hasNext()) {
						EducationDTO edDto = (EducationDTO) edIt.next();
						edDto.setApplicationId(pk);
						educationService.add(edDto);
					}
					List exList = form.getEduForm();
					if (exList != null) {
						Iterator<ExprienceDTO> exIt = exList.iterator();
						while (edIt.hasNext()) {
							ExprienceDTO exDto = (ExprienceDTO) exIt.next();
							exDto.setApplicationId(pk);
							exprienceService.add(exDto);
						}
					}
					System.out.println("In Save 3 -----------------");
					model.addAttribute("success", "Application Added Successfully!!!!");
				}
				return "application";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "application";
		}
		return "";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") ApplicationForm form,
			@RequestParam(required = false) String operation, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/application/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/application";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					ApplicationDTO dto = new ApplicationDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		ApplicationDTO dto = (ApplicationDTO) form.getDto();
		List<ApplicationDTO> list = service.search(dto, pageNo, pageSize);
		List<ApplicationDTO> totallist = service.search(dto);
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
		return "applicationList";
	}

}
