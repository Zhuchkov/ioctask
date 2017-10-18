package ua.rd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.rd.config.RepoConfig;
import ua.rd.config.ServiceConfig;
import ua.rd.config.TimelineFilterConfig;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.services.TweetService;
import ua.rd.services.UserService;

public class JavaBasedConfigRunner {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext repoContext = new AnnotationConfigApplicationContext(RepoConfig.class);
		AnnotationConfigApplicationContext serviceContext = new AnnotationConfigApplicationContext();
		serviceContext.setParent(repoContext);
		serviceContext.register(ServiceConfig.class,TimelineFilterConfig.class);
		serviceContext.refresh();
		UserService userService = serviceContext.getBean(UserService.class);
		TweetService tweetService = serviceContext.getBean(TweetService.class);
		//Registered user
		User currentUser = userService.saveUser(userService.createNewUser("user1"));
		
		//User createed 2 tweets
		tweetService.newTweet(currentUser,"");
		tweetService.newTweet(currentUser,"");
		System.out.println("User's timline:\n"+tweetService.userTimeline(currentUser)+"\n");
		//user 2
		User user2 = userService.saveUser(userService.createNewUser("user2"));
		tweetService.newTweet(user2,"");
		tweetService.newTweet(user2,"");
		//user 3
		User user3 = userService.saveUser(userService.createNewUser("user3"));
		tweetService.newTweet(user3,"");
		
		Tweet targetTweet = tweetService.newTweet(user3,"");
		targetTweet.setMaxTextLength(20L);
		targetTweet.setTxt("targetTweet");
		
		//subscribe registered user on user with id 2
		userService.createSubscription(currentUser, user2.getId());
		System.out.println("User's timline after subscription ou user 2:\n"+tweetService.userTimeline(currentUser)+"\n");
		
		//User 2 retweeted user3's tweet, so user 1 can see targetTweet in his timeline;
		userService.retweet(user2, targetTweet.getId());
		System.out.println("User's timline after user2 retweet targetTweet:\n"+tweetService.userTimeline(currentUser)+"\n");
		
		
		//User1 sets likes to all tweets in his timeline
		for(Tweet tweet :tweetService.userTimeline(currentUser)) {
			tweetService.likeTweet(tweet.getId());
		}
		System.out.println("User's timline after likes:\n"+tweetService.userTimeline(currentUser));
	}
}
