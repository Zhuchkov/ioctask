package ua.rd.domain;

public class Tweet {

	private Long id;
	private String txt;
	private User user;
	private long likeCounter;
	private long retweetCounter;
	private long maxTextLength;

	public Tweet() {

	}

	public Tweet(String txt, User user) {
		setTxt(txt);
		this.user = user;
	}
	public Tweet(Long tweetId, String txt, User user) {
		this(txt,user);
		this.id = tweetId;
	}

	public Long getId() {
		return id==null? null: new Long(id);
	}

	public void setId(Long tweetId) {
		this.id = new Long(tweetId);
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		if (txt.length() <= maxTextLength) {
			this.txt = txt;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getLikeCounter() {
		return likeCounter;
	}

	public void like() {
		this.likeCounter++;
	}

	public void dislike() {
		this.likeCounter--;
	}

	public long getMaxTextLength() {
		return maxTextLength;
	}

	public void setMaxTextLength(long maxTextLength) {
		this.maxTextLength = maxTextLength;
	}

	public void addRetweeter(User retweeter) {
		// TODO Auto-generated method stub

	}

	public long getRetweetCounter() {
		return retweetCounter;
	}

	void incrementRetweetCounter() {
		retweetCounter++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tweet{" + "tweetId=" + id + 
				", txt='" + txt + '\'' + 
				", author=" + user.getName() + 
				", likes=" + likeCounter + 
				'}';
	}

}
