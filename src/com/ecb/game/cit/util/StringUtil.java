package com.ecb.game.cit.util;

public class StringUtil {
	public static final String EMPTY = "";

	// 空文字もしくはnullであるかの判定
	public static boolean isEmpty(String str) {
		return (str == null || StringUtil.EMPTY.equals(str));
	}

	public static boolean isNotEmpty(String str) {
		return !StringUtil.isEmpty(str);
	}

	public static String htmlSpecialChars(String str) {
		return str.replace("&", "&amp;")
				.replace("<", "&lt;")
				.replace(">", "&gt;")
				.replace("\"", "&quot;")
				.replace("'", "&#39;")
				.replace("&lt;&gt;", "<>");
	}
}
