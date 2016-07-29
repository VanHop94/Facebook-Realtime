package com.datasection.web.mvc.config;

public class ArgsConstant {

	private int maxThread;
	private long maxFollowingTime;
	private int maxUserIsViewed;
	private int maxCommentIsViewed;
	private int timeToRepeat;
	private int timeCallServer;

	public ArgsConstant(int maxThread, long maxFollowingTime, int maxUserIsViewed, int maxCommentIsViewed,
			int timeToRepeat, int timeCallServer) {
		super();
		this.maxThread = maxThread;
		this.maxFollowingTime = maxFollowingTime;
		this.maxUserIsViewed = maxUserIsViewed;
		this.maxCommentIsViewed = maxCommentIsViewed;
		this.timeToRepeat = timeToRepeat;
		this.timeCallServer = timeCallServer;
	}

	public int getTimeCallServer() {
		return timeCallServer;
	}

	public void setTimeCallServer(int timeCallServer) {
		this.timeCallServer = timeCallServer;
	}

	public int getMaxThread() {
		return maxThread;
	}

	public void setMaxThread(int maxThread) {
		this.maxThread = maxThread;
	}

	public int getTimeToRepeat() {
		return timeToRepeat;
	}

	public void setTimeToRepeat(int timeToRepeat) {
		this.timeToRepeat = timeToRepeat;
	}

	public long getMaxFollowingTime() {
		return maxFollowingTime;
	}

	public void setMaxFollowingTime(long maxFollowingTime) {
		this.maxFollowingTime = maxFollowingTime;
	}

	public int getMaxUserIsViewed() {
		return maxUserIsViewed;
	}

	public void setMaxUserIsViewed(int maxUserIsViewed) {
		this.maxUserIsViewed = maxUserIsViewed;
	}

	public int getMaxCommentIsViewed() {
		return maxCommentIsViewed;
	}

	public void setMaxCommentIsViewed(int maxCommentIsViewed) {
		this.maxCommentIsViewed = maxCommentIsViewed;
	}

}
