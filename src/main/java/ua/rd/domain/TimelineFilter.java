package ua.rd.domain;

import java.util.Set;
import java.util.function.BiPredicate;

public class TimelineFilter {
	private BiPredicate<Tweet,User> filter;
	
	public TimelineFilter() {
		 filter = defaultFilter();
	}
	public TimelineFilter(BiPredicate<Tweet,User> filter) {
		 this.filter = filter;
	}
	public TimelineFilter(Set<BiPredicate<Tweet,User>> filters) {
		this();
		for(BiPredicate<Tweet,User> filter : filters) {
			this.filter=this.filter.or(filter);
		}
	}
	
	private BiPredicate<Tweet, User> defaultFilter() {
		return (t,u)->false;
	}
	public boolean test(Tweet tweet, User user) {
		return filter.test(tweet, user);
	}

	
}
