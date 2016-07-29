package com.datasection.facebook.be.entities;

public class ActionObservation {

	private long like;
	private long comments;
	private long share;
	private long time;

	public ActionObservation() {
		super();
	}

	public ActionObservation(long like, long comments, long share, long time) {
		this.like = like;
		this.comments = comments;
		this.share = share;
		this.time = time;
	}

	public long getLike() {
		return like;
	}

	public void setLike(long like) {
		this.like = like;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getComments() {
		return comments;
	}

	public void setComments(long comments) {
		this.comments = comments;
	}

	public long getShare() {
		return share;
	}

	public void setShare(long share) {
		this.share = share;
	}

}
