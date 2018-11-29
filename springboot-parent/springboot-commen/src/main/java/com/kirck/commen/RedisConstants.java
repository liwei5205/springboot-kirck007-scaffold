package com.kirck.commen;

public class RedisConstants {
	/*
	 * 缓存时间一个小时
	 */
	public static int EXPIRE3600 = 3600;
	/**
	 * 缓存时间一分钟
	 */
	public static int EXPIRE60 = 60;

	/**
	 * 分隔符
	 */
	public static String SPLITTER = ":";

	public interface KEYPRE {
		static String KIRCK = "kirck" + SPLITTER;
		static String KIRCK007 = "KIRCK007" + SPLITTER;
	}

	public interface OBJTYPE {
		static String USER = "user" + SPLITTER;
		static String TOPIC = "topic" + SPLITTER;
		static String TOKEN = "token" + SPLITTER;
		static String SIGN = "sign" + SPLITTER;
		static String ACCOUNT = "account" + SPLITTER;
		static String ORGAN = "organ" + SPLITTER;
	}
}
