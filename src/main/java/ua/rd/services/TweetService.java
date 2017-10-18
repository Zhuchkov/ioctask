package ua.rd.services;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;

public interface TweetService {
	Iterable<Tweet> allTweets();
    Iterable<Tweet> allUsersTweets(User user);
    Iterable<Tweet> userTimeline(User user);
    Tweet newTweet(User user,String text);
    Tweet saveTweet(Tweet tweet);
    Tweet createNewTweet();
	boolean likeTweet(Long teweetId);
}
