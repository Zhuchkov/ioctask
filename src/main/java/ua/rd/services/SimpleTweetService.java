package ua.rd.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ua.rd.domain.TimelineFilter;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.TweetRepository;

public class SimpleTweetService implements TweetService {

	private TweetRepository tweetRepository;
	private TimelineFilter timelineFilter;
	
	public SimpleTweetService(TweetRepository tweetRepository,TimelineFilter timelineFilter) {
		this.tweetRepository=tweetRepository;
		this.timelineFilter=timelineFilter;
	}
	
	//for tests
	SimpleTweetService(TweetRepository tweetRepository){
		this.tweetRepository=tweetRepository;
		this.timelineFilter= new TimelineFilter();
	}
	
	@Override
	public Iterable<Tweet> allUsersTweets(User user) {
		Collection<Tweet> tweets = tweetRepository.allTweets();
		return tweets.stream().filter(tweet -> tweet.getUser().equals(user)).collect(Collectors.toList());
	}

	@Override
	public Tweet newTweet(User user) {
		Tweet tweet = createNewTweet();
		tweet.setUser(user);
		return tweetRepository.save(tweet);
		 
	}
	
	@Override
	public Tweet createNewTweet() {
		return new Tweet();
	}

	@Override
	public Iterable<Tweet> userTimeline(User user) {
		Collection<Tweet> tweets = tweetRepository.allTweets();
		return tweets.stream()
				.filter(tweet->timelineFilter.test(tweet,user) )
				.collect(Collectors.toList());
	}

	@Override
	public boolean likeTweet(Long tweetId) {
		Optional<Tweet> tweet = tweetRepository.getTweet(tweetId);
		return tweet.map(item -> {
			item.like();
			tweetRepository.save(item);
			return true;
		}).orElse(false);
	}

}
