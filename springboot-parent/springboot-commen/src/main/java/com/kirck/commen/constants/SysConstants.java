package com.kirck.commen.constants;

public class SysConstants {
	public interface Token {
		/**
		 * 缓存设置为1小时
		 */
		public static final int CACHE_TIME_HOUR = 1;
		/**
		 * 缓存设置为 3600 秒
		 */
		public static final int CACHE_TIME_SECOND = 3600;
	}

	// 特殊符号
	public interface Symbol {
		/* 垂线 */
		public static final String VERTICAL = "|";
		/* 反斜线 */
		public static final String SLASH_OPPOSITE = "\\";
		/* 双反斜线 */
		public static final String SLASH_OPPOSITE_DOUBLE = "\\\\";
		/* 双斜线 */
		public static final String SLASH = "/";
		/* 中杠 */
		public static final String DASH = "-";
		/* 中杠 */
		public static final String UNDERLINE = "_";
		/* 逗号 */
		public static final String COMMA = ",";
		/* and 符 */
		public static final String STR_AND = "&";
		/* 双引号 */
		public static final String DOUBLE_QUOTATION = "\"";
		/* 句号 */
		public static final String POINT = ".";
		/* 分号 */
		public static final String SEMI = ";";
		/* 分号 */
		public static final String PLUS = "+";

		public static final char CHAR_WHITE_SPACE = ' ';

		public static final String STRING_WHITE_SPACE = " ";

		public static final String STRING_QUESTION = "?";

		public static final String STRING_DENG = "=";

		public static final String COLON = ":";

	}

}
