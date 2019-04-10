package com.zzg.encrypt.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
	/**
	 * 
	 * @Title: getEncodeValue   
	 * @Description: 根据指定图片路径，获取Base64.encode的值  
	 * @param: @param imagePath
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getEncodeValue(String imagePath){
		String content = null;
		InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imagePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        content = Base64.encodeBase64String(data);
		return content;
	}

}
