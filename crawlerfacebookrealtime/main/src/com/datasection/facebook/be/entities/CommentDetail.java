package com.datasection.facebook.be.entities;

import com.datasection.facebook.utils.StringUtil;

public class CommentDetail {
	private long like_count;
	private String content;
	private String content_2;
	private String from_id;
	private long id;
	private String from_name;
	private long create_time;
	private String url;
	private String comment_id;
	private long post_id;
	private int status;
	public final static int DEFAULT_COMMENT_STATUS = 0;

	public CommentDetail() {
	}

	public CommentDetail(long id, String comment_id, long like_count, String content, String content_2, String from_id,
			String from_name, long create_time, String url, long post_id, int status) {
		this.like_count = like_count;
		this.content = StringUtil.escapeNonUnicode(content);
		this.content_2 = content_2;
		this.from_id = from_id;
		this.from_name = from_name;
		this.create_time = create_time;
		this.url = url;
		this.post_id = post_id;
		this.id = id;
		this.status = status;
		this.comment_id = comment_id;
	}

	public String getContent_2() {
		return content_2;
	}

	public void setContent_2(String content_2) {
		this.content_2 = content_2;
	}

	public int getStatus() {
		return status;
	}

	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPost_id() {
		return post_id;
	}

	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}

	public long getLike_count() {
		return like_count;
	}

	public void setLike_count(long like_count) {
		this.like_count = like_count;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = StringUtil.escapeNonUnicode(content);
	}

	public String getFrom_id() {
		return from_id;
	}

	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}

	public String getFrom_name() {
		return from_name;
	}

	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
