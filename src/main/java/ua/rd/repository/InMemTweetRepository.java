package ua.rd.repository;

import org.springframework.stereotype.Repository;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("tweetRepository")
public class InMemTweetRepository implements TweetRepository {
	private static Long idCounter = Long.valueOf(0);
	private Map<Long, Tweet> tweets;

	public InMemTweetRepository(Map<Long, Tweet> tweets) {
		this.tweets = tweets;
	}
	
	@PostConstruct
	private void init() {
		User user = new User("aaa");
		put(new Tweet("", user));
		put(new Tweet("", user));
		put(new Tweet("", user));
		User otherUser = new User("bbb");
		put(new Tweet("", otherUser));
	}

	@Override
	public Collection<Tweet> allTweets() {
		return tweets.values();

	}

	@Override
	public Optional<Tweet> getTweet(Long id) {
		return Optional.ofNullable(tweets.get(id));
	}
	
	@Override
	public Tweet save(Tweet tweet) {
		if (tweet.getId() == null) {
			tweet.setId(idCounter++);
		}
		put(tweet);
		return tweet;
	}

	private Tweet put(Tweet tweet) {
		return tweets.put(tweet.getId(), tweet);
	}
}
