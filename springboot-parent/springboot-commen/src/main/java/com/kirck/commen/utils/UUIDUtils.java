package com.kirck.commen.utils;

import java.util.UUID;

public class UUIDUtils {
	public static String getNewId(){
		return UUID.randomUUID().toString().replaceAll("-", "")+UUID.randomUUID().toString().substring(0, 4);		
	}
}
