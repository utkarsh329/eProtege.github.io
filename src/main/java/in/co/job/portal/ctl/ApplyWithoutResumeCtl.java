package in.co.job.portal.ctl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import in.co.job.portal.dao.EducationDAOImpl;
import in.co.job.portal.dao.EducationDAOInt;
import in.co.job.portal.dto.ApplicationDTO;
import in.co.job.portal.dto.ApplyJobDTO;
import in.co.job.portal.dto.EducationDTO;
import in.co.job.portal.dto.ExprienceDTO;
import in.co.job.portal.dto.JobDTO;
import in.co.job.portal.dto.UserDTO;
import in.co.job.portal.exception.DuplicateRecordException;
import in.co.job.portal.form.ApplicationForm;
import in.co.job.portal.form.ExprienceForm;
import in.co.job.portal.service.ApplicationServiceInt;
import in.co.job.portal.service.ApplyJobServiceInt;
import in.co.job.portal.service.EducationServiceInt;
import in.co.job.portal.service.ExprienceServiceImpl;
import in.co.job.portal.service.ExprienceServiceInt;
import in.co.job.portal.service.JobServiceInt;
import in.co.job.portal.util.DataUtility;

@Controller
@RequestMapping("/ctl/apwresume")
public class ApplyWithoutResumeCtl extends BaseCtl {

	@Autowired
	private ApplicationServiceInt service;

	@Autowired
	private EducationServiceInt educationService;

	@Autowired
	private ExprienceServiceInt exprienceService;

	@Autowired
	private ApplyJobServiceInt applyJobService;
	
	@Autowired
	private JobServiceInt jobService;

	private List<EducationDTO> eduList = null;

	private List<ExprienceDTO> expList = null;

	@GetMapping
	public String display(HttpSession session, @RequestParam(required = false) Long id,Long jId,
			@ModelAttribute("form") ApplicationForm form, Model model) {
		if (form.getId() > 0) {
			ApplicationDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		if(DataUtility.getLong(String.valueOf(jId))>0) {
			session.setAttribute("jobId",jId);
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
		return "awresume";
	}

	@PostMapping
	public String submit(HttpSession session, @Valid @ModelAttribute("form") ApplicationForm form,
			BindingResult bindingResult, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/apwresume";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				ApplicationDTO bean = (ApplicationDTO) form.getDto();
				System.out.println("Application Id"+bean.getId());
				UserDTO uDto = (UserDTO) session.getAttribute("user");
				bean.setUserId(uDto.getId());
				String path=ResumeCreate(bean);
				long jId=DataUtility.getLong(String.valueOf(session.getAttribute("jobId")));
				JobDTO jDto=jobService.findBypk(jId);
				ApplyJobDTO apDto=new ApplyJobDTO();
				apDto.setApDate(new Date());
				apDto.setCompanyName(jDto.getCompanyName());
				apDto.setJobId(jId);
				apDto.setJobName(jDto.getName());
				apDto.setRecruiterId(jDto.getRecruiterId());
				apDto.setResumeFile(DataUtility.openFile(path));
				apDto.setFileName(uDto.getFirstName()+".pdf");
				apDto.setUserName(uDto.getFirstName()+" "+uDto.getLastName());
				apDto.setUserId(uDto.getId());
				applyJobService.add(apDto);
				model.addAttribute("success", "Job Applyed Successfully!!!!");
				return "awresume";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public  String ResumeCreate(ApplicationDTO bean) throws FileNotFoundException, DocumentException {
		String path = "E://JobPortalSpringboot//JobPortal//src//main//webapp//resources//file//" + DataUtility.removeSpace(bean.getName()) + ".pdf";
		try {
			

			OutputStream file = new FileOutputStream(new File(path.toString()));
			Document document = new Document();
			PdfWriter.getInstance(document, file);

			// Now Insert Every Thing Into PDF Document
			document.open();// PDF document opened........
			// ------------------------------------------------------------------------------------------
			Font font = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLUE);
			Chunk chunk = new Chunk("Name : " + bean.getName(), font);
			document.add(new Paragraph(chunk));

			Font emailfont = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLUE);
			Chunk emailchunk = new Chunk("Email : " + bean.getEmail(), emailfont);
			document.add(new Paragraph(emailchunk));

			Font mobilefont = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLUE);
			Chunk mobilechunk = new Chunk("Mobile : " + bean.getMobile(), mobilefont);
			document.add(new Paragraph(mobilechunk));
			document.add(Chunk.NEWLINE); // Something like in HTML 🙂

			// ------------------------------------------------------------------------------------------
			Font objfont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk objchunk = new Chunk("Objective", objfont);
			objchunk.setUnderline(+1f, -2f);
			document.add(objchunk);

			document.add(Chunk.NEWLINE);
			Font objdfont = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
			Chunk objdchunk = new Chunk(bean.getObjective(), objdfont);
			document.add(objdchunk);

			// ------------------------------------------------------------------------------------------
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Font edfont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk edchunk = new Chunk("Education And Qualification", edfont);
			edchunk.setUnderline(+1f, -2f);
			document.add(edchunk);

			// ------------------------------------------------------------------------------------------
			PdfPTable table = new PdfPTable(3);

			/*
			 * PdfPCell cell = new PdfPCell(new Paragraph("Java4s.com"));
			 * 
			 * cell.setColspan(3); cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 * cell.setPadding(10.0f); cell.setBackgroundColor(new BaseColor(140, 221, 8));
			 * 
			 * table.addCell(cell);
			 */
			

			EducationDTO edDto = new EducationDTO();
			edDto.setApplicationId(bean.getId());
			System.out.println("dbvicsdbuv------------"+edDto.getApplicationId());
			List<EducationDTO> edList = educationService.search(edDto);
			Iterator<EducationDTO> edIt = edList.iterator();
			while (edIt.hasNext()) {
				EducationDTO educationDTO = (EducationDTO) edIt.next();
				table.addCell(educationDTO.getCourseName());
				table.addCell(educationDTO.getInsName());
				table.addCell(educationDTO.getPercentage() + "%");
			}

			table.setSpacingAfter(30.0f);
			document.add(table);
			// ------------------------------------------------------------------------------------------
			Font skillfont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk skillchunk = new Chunk("Programming Skill", skillfont);
			skillchunk.setUnderline(+1f, -2f);
			document.add(skillchunk);

			document.add(Chunk.NEWLINE);
			Font pskillfont = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
			Chunk pskillchunk = new Chunk(bean.getSkill(), pskillfont);
			document.add(pskillchunk);

			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Font hobfont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk hobchunk = new Chunk("Hobbies and Intrest", hobfont);
			hobchunk.setUnderline(+1f, -2f);
			document.add(hobchunk);

			document.add(Chunk.NEWLINE);
			Font hbfont = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
			Chunk hbchunk = new Chunk(bean.getHobbies(), hbfont);
			document.add(hbchunk);

			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Font pxfont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk pxchunk = new Chunk("Past Exprience", pxfont);
			pxchunk.setUnderline(+1f, -2f);
			document.add(pxchunk);

			PdfPTable table2 = new PdfPTable(3);

			ExprienceDTO exDto = new ExprienceDTO();
			exDto.setApplicationId(bean.getId());
			List<ExprienceDTO> exList = exprienceService.search(exDto);
			Iterator<ExprienceDTO> exIt = exList.iterator();

			while (exIt.hasNext()) {
				ExprienceDTO exprienceDTO = (ExprienceDTO) exIt.next();
				table2.addCell(exprienceDTO.getCompanyName());
				table2.addCell(exprienceDTO.getDuration());
				table2.addCell(exprienceDTO.getTechnology());
			}

			table2.setSpacingAfter(30.0f);
			document.add(table2);

			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Font pfont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk pchunk = new Chunk("Personal Detail", pfont);
			pchunk.setUnderline(+1f, -2f);
			document.add(pchunk);

			document.add(Chunk.NEWLINE);
			Font pdfont = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
			Chunk pdchunk = new Chunk(bean.getpDetail(), pdfont);
			document.add(pdchunk);

			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Font defont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk dechunk = new Chunk("Declaration", defont);
			dechunk.setUnderline(+1f, -2f);
			document.add(dechunk);

			document.add(Chunk.NEWLINE);
			Font decfont = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
			Chunk decchunk = new Chunk(bean.getDeclaration(), decfont);
			document.add(decchunk);

			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);

			Font datefont = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
			Chunk datechunk = new Chunk("Date : " + DataUtility.getDateString(bean.getDate()).toString(), datefont);
			document.add(new Paragraph(datechunk));

			Font plfont = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
			Chunk plchunk = new Chunk("Place : " + bean.getPlace(), plfont);
			document.add(new Paragraph(plchunk));

			document.newPage(); // Opened new page
			document.close();
			file.close();

			System.out.println("Pdf created successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return path;
	}

}
