package ua.rd.config;

import java.util.function.BiPredicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;

@Configuration
public class TimelineFilterConfig {

	@Bean
	public BiPredicate<Tweet, User> ownTweetFilter() {
		return (tweet, user) -> tweet.getUser().equals(user);
	}

	@Bean
	public BiPredicate<Tweet, User> subscriptionsTweetFilter() {
		return (tweet, user) -> user.getSubscriptions().contains(tweet.getUser());
	}

	@Bean
	public BiPredicate<Tweet, User> ownRetweetFilter() {
		return (tweet, user) -> user.getRetweets().contains(tweet);
	}

	@Bean
	public BiPredicate<Tweet, User> subscriptionsRetweetFilter() {
		return (tweet, user) -> user.getSubscriptions().stream()
				.flatMap(targetUser -> targetUser.getRetweets().stream()).anyMatch(item -> item.equals(tweet));
	}
}
