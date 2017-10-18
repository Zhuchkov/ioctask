package ua.rd.web.controller;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.rd.web.validators.StoValidator;

@Controller
@RequestMapping("/new")
//@SessionAttributes("sessionObj")
public class NewController {

	@GetMapping("/test")
	public String test(Model model, SessionStatus status) {
		System.out.println("__test start__________");
		model.asMap().entrySet().forEach(System.out::println);
		System.out.println("__test end__________");
		return "test";
	}
	

	@PostMapping("/createTweet")
	public String process(
			@ModelAttribute("sessionObj") TestObj test,
			BindingResult result,
			@ModelAttribute(name = "secondTestObject") SecondTestObject test2, 
			RedirectAttributes redirectAttributes,
			Model model) {
		System.out.println(result.hasErrors());
		System.out.println("__createTweet start________________");
		model.asMap().entrySet().forEach(System.out::println);
		System.out.println("__createTweet end________________");
		redirectAttributes.addFlashAttribute("sessionObj", test);
		redirectAttributes.addFlashAttribute("secondTestObject", test2);
		return "redirect:test";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// binder.addValidators(new StoValidator());

	}

//	@ModelAttribute("sessionObj")
//	SecondTestObject prepare(Model model) {
//		System.out.println("********************************************");
//		SecondTestObject t = new SecondTestObject("tyui", "ghjk");
//		t.setId(1);
//		if (counter++ > 2) {
//			t.setSecondId(99);
//		}
//		System.out.println("prep:" + t);
//		model.addAttribute("sessionObj", t);
//		return t;
//	}
//
//	@ModelAttribute
//	SecondTestObject prepareSec(Model model) {
//		System.out.println("22222222222222222222222222222222222222222222222");
//		SecondTestObject t = new SecondTestObject("p[ipio", "op[iop");
//		t.setId(12);
//		if (counter++ > 2) {
//			t.setSecondId(1399);
//		}
//		System.out.println("prep2:" + t);
//		model.addAttribute(t);
//		return t;
//	}
//
//	@ModelAttribute
//	SecondTestObject prepareTheard(Model model) {
//		System.out.println("33333333333333333333333333");
//		SecondTestObject t = new Successor("12342134", "1234234[iop");
//		t.setId(12);
//		if (counter++ > 2) {
//			t.setSecondId(13);
//		}
//		System.out.println("prep3:" + t);
//		model.addAttribute(t);
//		return t;
//	}
	// @InitBinder
	// protected void initBinder(WebDataBinder binder) {
	// PropertyEditor pe = new PropertyEditorSupport () {
	//
	// };
	// binder.registerCustomEditor(TestObj.class,pe);
	// }
}
