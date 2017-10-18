package ua.rd.web.rest;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import ua.rd.domain.Tweet;
import ua.rd.services.TweetService;

@Controller
public class RestController {
	
	@Autowired
	TweetService tweetService;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello from rest";
	}
	@RequestMapping(value = "/all")
	@ResponseBody
	public Iterable<Tweet> tweets() {
		return tweetService.allTweets();
	}
	@RequestMapping(value = "/get/{tweetId}")
	@ResponseBody
	public ResponseEntity<Tweet> getTweet(@PathVariable Long tweetId) {
		Optional<Tweet> tweet =  StreamSupport.stream(tweetService.allTweets().spliterator(), false).filter(localtweet->localtweet.getId().equals(tweetId)).findFirst();
		//ResponseEntity<Tweet> result = new ResponseEntity<>(tweet.get(),HttpStatus.FOUND);
		//ResponseEntity<Tweet> result = new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		return tweet.isPresent()?
				new ResponseEntity<>(tweet.get(),HttpStatus.FOUND) :
				new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST,consumes="application/json",produces="application/json")
	@ResponseBody
	public ResponseEntity<Tweet> tweets(@RequestBody Tweet tweet,UriComponentsBuilder builder) {
		Tweet newTweet = tweetService.saveTweet(tweet);
		ResponseEntity<Tweet> res = new ResponseEntity<>(tweet,HttpStatus.CREATED);
		URI uri= builder.path("/{tweetId}").buildAndExpand(newTweet.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
