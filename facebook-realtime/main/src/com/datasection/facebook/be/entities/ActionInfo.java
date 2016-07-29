package com.datasection.facebook.be.entities;

public class ActionInfo {

	private long id;
	private long post_id;
	private long date_time;
	private long like_count;
	private long comment_count;
	private long share_count;
	private int requestTimes;
	private int update_count;
	private ListFixedSize<ActionObservation> storage;

	public ActionInfo(long id, long post_id, long date_time, long like_count, long comment_count, long share_count,
			ListFixedSize<ActionObservation> listFixedSize, int update_count) {
		this.id = id;
		this.post_id = post_id;
		this.date_time = date_time;
		this.like_count = like_count;
		this.comment_count = comment_count;
		this.share_count = share_count;
		this.storage = listFixedSize;
		this.update_count = update_count;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ActionInfo() {
		storage = new ListFixedSize<>();
		update_count = 0;
	}

	public int getUpdate_count() {
		return update_count;
	}

	public void setUpdate_count(int update_count) {
		this.update_count = update_count;
	}

	public int getRequestTimes() {
		return requestTimes;
	}

	public void setRequestTimes(int requestTimes) {
		this.requestTimes = requestTimes;
	}

	public ListFixedSize<ActionObservation> getStorage() {
		return storage;
	}

	public void setStorage(ListFixedSize<ActionObservation> storage) {
		this.storage = storage;
	}

	public long getPost_id() {
		return post_id;
	}

	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}

	public long getDate_time() {
		return date_time;
	}

	public void setDate_time(long date_time) {
		this.date_time = date_time;
	}

	public long getLike_count() {
		return like_count;
	}

	public void setLike_count(long like_count) {
		this.like_count = like_count;
	}

	public long getComment_count() {
		return comment_count;
	}

	public void setComment_count(long comment_count) {
		this.comment_count = comment_count;
	}

	public long getShare_count() {
		return share_count;
	}

	public void setShare_count(long share_count) {
		this.share_count = share_count;
	}

}
