package ua.rd.config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ua.rd.domain.TimelineFilter;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.TweetRepository;
import ua.rd.services.SimpleTweetService;
import ua.rd.services.TweetService;

public class TimelineFilterConfigTest {

	@Test
	public void testOwnTweetFilter() {
		User user = new User("user");
		TimelineFilterConfig filterConfig = new TimelineFilterConfig();
		TimelineFilter filter = new TimelineFilter(filterConfig.ownTweetFilter());

		TweetService service = prepareTweetService(user, filter);

		Iterable<Tweet> result = service.userTimeline(user);
		int itemCounter = 0;
		for (Tweet t : result) {
			itemCounter++;
		}
		assertEquals(3, itemCounter);
	}

	@Test
	public void testSubscriptionsTweetFilter() {
		User user = new User("user");

		TimelineFilterConfig filterConfig = new TimelineFilterConfig();
		TimelineFilter filter = new TimelineFilter(filterConfig.subscriptionsTweetFilter());

		TweetService service = prepareTweetService(user, filter);

		Iterable<Tweet> result = service.userTimeline(user);
		int itemCounter = 0;
		for (Tweet t : result) {
			itemCounter++;
		}
		assertEquals(1, itemCounter);
	}

	@Test
	public void testOwnRetweetFilter() {
		User user = new User("user");

		TimelineFilterConfig filterConfig = new TimelineFilterConfig();
		TimelineFilter filter = new TimelineFilter(filterConfig.ownRetweetFilter());

		TweetService service = prepareTweetService(user, filter);

		Iterable<Tweet> result = service.userTimeline(user);
		int itemCounter = 0;
		for (Tweet t : result) {
			itemCounter++;
		}
		assertEquals(3, itemCounter);
	}

	@Test
	public void testSubscriptionsRetweetFilter() {
		User user = new User("user");

		TimelineFilterConfig filterConfig = new TimelineFilterConfig();
		TimelineFilter filter = new TimelineFilter(filterConfig.subscriptionsRetweetFilter());

		TweetService service = prepareTweetService(user, filter);

		Iterable<Tweet> result = service.userTimeline(user);
		int itemCounter = 0;
		for (Tweet t : result) {
			itemCounter++;
		}
		assertEquals(2, itemCounter);
	}

	private TweetService prepareTweetService(User user, TimelineFilter filter) {
		List<Tweet> tweets = new ArrayList<>();
		tweets.add(new Tweet(1L,"1", user));
		tweets.add(new Tweet(2L,"2", user));
		tweets.add(new Tweet(3L,"3", user));

		User subscriptionTargetUser = new User("subscriptionTarget");
		tweets.add(new Tweet(4L,"4", subscriptionTargetUser));
		user.subscribe(subscriptionTargetUser);

		User retweetTargetUser = new User("retweetTargetUser");
		Tweet retweetTarget = new Tweet(5L,"5", retweetTargetUser);
		Tweet retweetTarget2 = new Tweet(6L,"6", retweetTargetUser);
		Tweet retweetTarget3 = new Tweet(7L,"7", retweetTargetUser);
		
		tweets.add(retweetTarget);
		tweets.add(retweetTarget2);
		tweets.add(retweetTarget3);

		user.setRetweet(retweetTarget);
		user.setRetweet(retweetTarget2);
		user.setRetweet(retweetTarget3);
		
		subscriptionTargetUser.setRetweet(retweetTarget);
		subscriptionTargetUser.setRetweet(retweetTarget2);

		User standaloneUser = new User("standaloneUser");
		tweets.add(new Tweet(8L,"8", standaloneUser));
		tweets.add(new Tweet(9L,"9", standaloneUser));
		tweets.add(new Tweet(10L,"10", standaloneUser));

		TweetRepository tweetRepository = mock(TweetRepository.class);
		TweetService service = new SimpleTweetService(tweetRepository, filter);
		when(tweetRepository.allTweets()).thenReturn(tweets);
		return service;
	}

}
