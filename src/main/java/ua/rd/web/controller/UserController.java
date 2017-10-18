package ua.rd.web.controller;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.services.TweetService;
import ua.rd.services.UserService;

@Controller
@RequestMapping("/user")
//@SessionAttributes(types=User.class)
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	TweetService tweetService;

	@RequestMapping("/hello")
	public String hello() {
		System.out.println("hello");
		return "helloPage";
	}
	@GetMapping("/{userId}")
	public String getUser(@PathVariable("userId") User user, Model model) {
		model.addAttribute(user);
		return "users";
	}
	
	@PostMapping("/register")
	public String register(@RequestParam("username") User registeredUser,RedirectAttributes atributs) {
		
		System.out.println(registeredUser.getId());
		System.out.println(registeredUser.getName());
		atributs.addFlashAttribute("registeredUser",registeredUser);
		return "redirect:hello";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username, Model model,SessionStatus status) {
		System.out.println(status.isComplete());
		userService.getUserByName(username).map(user->{
			System.out.println("username:"+username+";user:"+user);
			model.addAttribute("user", user);
			return user;
		});
		return "redirect:timeline";
	}
	
	@GetMapping("/timeline")
	public String timeline(@ModelAttribute User currentUser, Model model) {
		System.out.println("timeline:"+currentUser.getName());
		model.addAttribute("tweets", tweetService.userTimeline(currentUser)) ;
		return "timeline";
	}
	@GetMapping("/createTweet")
	public String getCreateTweetPage(@ModelAttribute User currentUser) {
		return "createTweet";
		
	}
	@PostMapping("/createTweet")
	public String reateTweet(@ModelAttribute User currentUser, @RequestParam String tweetText, RedirectAttributes redirectAttributes) {
		System.out.println("createTweet:"+currentUser);
		Tweet tweet = tweetService.newTweet(currentUser,tweetText);
		redirectAttributes.addFlashAttribute(tweet);
		return "redirect:timeline";
	}
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:hello";
		
	}
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(User.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				System.out.println("in customBinder:"+text);
				setValue(new User(Long.parseLong(text)));
				setValue(userService.saveUser(userService.createNewUser(text)));
			}
		});
		

	}

}
