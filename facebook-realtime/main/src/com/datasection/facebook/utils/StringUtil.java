package com.datasection.facebook.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static String escapeNonUnicode(String content) {
		return escapeNonUnicode(content, " ");
	}

	public static String escapeNonUnicode(String content, String replacement) {
		if(content == null)
			content = "";
		content = content.replaceAll("\\p{C}", replacement);
		content = content.replaceAll("\\p{InEmoticons}", replacement);
		return content;
	}

	public static String md5(String input) {

		String md5 = null;

		if (null == input)
			return null;

		try {

			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");

			// Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return md5;
	}

	public static boolean isUsername(String username) {

		String pattern = "([a-zA-Z_.0-9])+";
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(username);
		if(m.matches())
			return true;
		return false;
	}
	
	public static String getStringIgnoreAccent(String str){
		
		str = escapeNonUnicode(str);
		
		String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replace("Ð", "D")
				.replace("đ", "d").toLowerCase();
		
	}
}
