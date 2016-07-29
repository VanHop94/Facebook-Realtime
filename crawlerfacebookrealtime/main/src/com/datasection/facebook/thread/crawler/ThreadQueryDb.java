package com.datasection.facebook.thread.crawler;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.datasection.facebook.be.dao.ActionDao;
import com.datasection.facebook.be.dao.CommentDao;
import com.datasection.facebook.be.dao.PostDao;
import com.datasection.facebook.be.dao.UserDao;
import com.datasection.facebook.be.entities.PostDetail;

public class ThreadQueryDb extends Thread {

	@Autowired
	private PostDao postDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ActionDao actionDao;

	@Override
	public void run() {
		
		HashMap<Long,String> listPost = new HashMap<Long,String>();
		
		while (true) {
			
			List<PostDetail> listPostDetail = postDao.queryAllPostIsRunning();
			if (listPostDetail.size() > 0) {
				for (PostDetail postDetail : listPostDetail) {
					
					if(listPost.get(postDetail.getId()) != null)
						continue;
					CrawlerCommentOnPost crawler = new CrawlerCommentOnPost(postDetail, commentDao, userDao, postDao,
							actionDao);
					crawler.start();
					listPost.put(postDetail.getId(), postDetail.getPostId());
					
				}
			}

			sleep(1);
		}

	}

	private void sleep(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
