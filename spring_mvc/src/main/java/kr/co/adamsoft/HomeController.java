package kr.co.adamsoft;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.adamsoft.domain.Article;
import kr.co.adamsoft.domain.Item;
import kr.co.adamsoft.domain.ItemReport;
import kr.co.adamsoft.domain.Member;
import kr.co.adamsoft.domain.ReportCommand;
import kr.co.adamsoft.service.ItemService;
import kr.co.adamsoft.service.ViewService;
import kr.co.adamsoft.validator.MemberValidator;


@Controller
public class HomeController {
	
	@RequestMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hello");
		mav.addObject("greeting", "안녕하세요 Spring MVC Test 입니다.");
		return mav;
	}

	@RequestMapping("/message/detail/{num}")
	public ModelAndView detail(@PathVariable int num) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("detail");
		mav.addObject("num", num);
		return mav;
	}

	@RequestMapping("/newarticle")
	public ModelAndView newArticle() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("newarticleform");
		return mav;
	}
	
	/*
	@RequestMapping("/form")
	public ModelAndView articleSave(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		System.out.println("제목:" + request.getParameter("title"));
		System.out.println("내용:" + request.getParameter("content"));
		return mav;
	}
	*/
	
	
	/*
	@RequestMapping("/form")
	public ModelAndView articleSave(
		@RequestParam("title")String title, 
		@RequestParam("content") String content) {
			ModelAndView mav = new ModelAndView();
			System.out.println("제목:" + title);
			System.out.println("내용:" + content);
			return mav;
	}
	*/
	
	@RequestMapping("/form")
	public ModelAndView formProcessing(Article command) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("form");
		return mav;
	}

	@RequestMapping("/redirect")
	public String redirect(RedirectAttributes rttr) {
		rttr.addFlashAttribute("msg", "임시 데이터 전달");
		return "redirect:disp";
	}

	@RequestMapping("/disp")
	public String disp(@ModelAttribute String msg) {
		return "redirect";
	}
	
	@RequestMapping("/header")
	public ResponseEntity<String> header() {
		String msg = "{\"name\" : \"박문석\"}";
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/json;charset=UTF-8");
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("list", itemService.getAll());
		return "home";
	}

	@RequestMapping("/detail.html")
	public String detail(@RequestParam("itemid") int itemid, Model model){
		Item item = itemService.getItem(itemid);
		//데이터를 저장
		model.addAttribute("item", item);
		return "item";
	}	
	
	@Autowired
	private ViewService viewService;

	@RequestMapping("/fileview")
	public String fileview(Model model, HttpServletRequest request){
		List<String>list = viewService.fileview(request);
		model.addAttribute("list", list);
		return "fileview";
	}
	
	@RequestMapping("/download")
	public String download(@RequestParam("filename") String filename, Model model, HttpServletRequest request){
		File downloadFile = new File(request.getServletContext().getRealPath("/img") + "/" + filename);
		model.addAttribute("downloadFile", downloadFile);
		return "download";
	}
	
	@RequestMapping("/item.xls")	
	public String excel(Model model) {
		List<Item> list = itemService.getAll();
		model.addAttribute("list", list);
		return "excel";
	}

	@RequestMapping("/excelread")
	public String excelread(Model model, HttpServletRequest request) {
		List<Map<String, Object>> list = viewService.excelRead(request);
		System.out.println(list);
		model.addAttribute("list", list);
		return "excelread";
	}
	
	@RequestMapping("/pdf")
	public String pdfReport(Model model) {
		List<Item> list = itemService.getAll();
		model.addAttribute("list", list);
		return "pdf";
	}

	@RequestMapping("/itemlist.json")
	public String jsonReport(Model model) {
		List<Item> list = itemService.getAll();
		model.addAttribute("list", list);
		return "itemlist";
	}

	@RequestMapping("/item.xml")
	public String xmlReport(Model model) {
		List<Item> list = itemService.getAll();
		model.addAttribute("list", new ItemReport(list));
		return "itemreport"; 
	}
	
	@RequestMapping(value = "/exception", method = RequestMethod.GET)
	public String input(Model model) {
		return "input";
	}

	@RequestMapping(value = "/cal", method = RequestMethod.GET)
	public String cal(
		@RequestParam("dividend") int dividend , @RequestParam("divisor") int divisor, Model model) {
		model.addAttribute("result", dividend/divisor);
		return "result";
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error/exception");
		System.out.println(e.getMessage());
		mav.addObject("result", e.getMessage());
		return mav;
	}
	
	/*
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String form() {
		return "loginform";
	}
	*/

	/*
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String form(@ModelAttribute("member")Member member) {
		return "loginform";
	}
	*/
	
	@ModelAttribute("loginTypes")
	public List<String> loginTypes(){
		List<String>list = new ArrayList<String>();
		list.add("일반회원");
		list.add("기업회원");
		list.add("비회원");
		return list;
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String form(@ModelAttribute("command") Member member) {
		return "loginform";
	}

	/*
	@RequestMapping(value = "/message",method = RequestMethod.POST)
	public String created(@ModelAttribute("command") Member member,
			BindingResult result) {
		new MemberValidator().validate(member, result);
		if (result.hasErrors()) {
			return "loginform";
		}
		return "redirect:/";
	}
	*/
	
	@RequestMapping(value = "/message",method = RequestMethod.POST)
	public String created(@Valid @ModelAttribute("command") Member member,
		Errors errors) {
			if (errors.hasErrors()) {
				return "loginform";
			}
			return "redirect:/";
	}
	
	@RequestMapping(value = "fileupload", method = RequestMethod.GET)
	public String form() {
		return "submissionform";
	}
	
	private void printInfo(String studentNumber, MultipartFile report) {
		System.out.println(studentNumber + "가 업로드 한 파일: " + report.getOriginalFilename());
	}
	
	@RequestMapping(value = "fileupload1.action", method = RequestMethod.POST)
	public String submitReport1(@RequestParam("studentNumber") String studentNumber,
			@RequestParam("report") MultipartFile report) {
		printInfo(studentNumber, report);
		return "submissioncomplete";
	}
	
	@RequestMapping(value = "fileupload2.action", method = RequestMethod.POST)
	public String submitReport2(MultipartHttpServletRequest request) {
		String studentNumber = request.getParameter("studentNumber");
		MultipartFile report = request.getFile("report");
		printInfo(studentNumber, report);
		return "submissioncomplete";
	}
	
	@RequestMapping(value = "fileupload3.action", method = RequestMethod.POST)
	public String submitReport3(ReportCommand reportCommand) {
		printInfo(reportCommand.getStudentNumber(), reportCommand.getReport());
		if (reportCommand.getReport().isEmpty()) {
			System.out.println("업로드한 파일이 없습니다.");
		} else {
			//파일 업로드를 위힌 디렉토리 - 자신의 디렉토리로 수정
			String filePath = "/Users/adam/Documents/data/";
			filePath += UUID.randomUUID() + reportCommand.getReport().getOriginalFilename();
			System.out.println("filePath:" + filePath);
			File file = new File(filePath);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e1) {
			}
			
			try {
				fos.write(reportCommand.getReport().getBytes());
				System.out.println("fos:" + fos);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("전송 실패");
			}
		}
		return "submissioncomplete";
	}



}
