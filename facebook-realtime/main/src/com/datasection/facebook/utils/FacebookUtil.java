package com.datasection.facebook.utils;

import org.apache.log4j.Logger;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

public class FacebookUtil {
	public final static String ACCESS_TOKEN = "";
	public final static String APP_ID = "1694956460738257";
	public final static String APP_SECRET = "b983dfd25532e30b8d573aa60578cae7";
	private static FacebookClient facebookClient = null;

	final static Logger logger = Logger.getLogger(FacebookUtil.class);

	private FacebookUtil() {

	}

	public static String getPageID(String url) {

		String str = url.substring(url.indexOf("facebook.com") + "facebook.com".length() + 1, url.length());
		return str.split("/")[0];

	}

	public static String getPostID(String url) {
		String postID = null;
		String str = url.replace("https://www.facebook.com/", "");
		String[] tokens = str.split("/");
		if (str.contains("posts"))
			postID = tokens[tokens.length - 1];
		if (str.contains("videos")) {
			if (tokens.length == 3) {
				postID = tokens[tokens.length - 1];
			} else {
				postID = tokens[tokens.length - 2];
			}
		}
		if (str.contains("photos"))
			postID = tokens[tokens.length - 2];
		if (postID == null)
			logger.error("Url is invalidation:\t" + url);
		return postID;
	}

	public static FacebookClient getClient() {
		if (facebookClient == null) {

			facebookClient = new DefaultFacebookClient("1694956460738257|-Qy-3ef-DvyjsBOao0ze__5jqKU",
					Version.VERSION_2_5);
			// accessToken.getAccessToken());
		}
		return facebookClient;
	}

}
