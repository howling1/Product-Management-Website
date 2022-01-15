package org.example.utils;

import java.util.UUID;

public class FileNameUtil {

	//Generate random file name based on UUID
	public static String getUUIDFileName() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	//Extract the file name from the request header
	public static String getRealFileName(String context) {
		// Content-Disposition: form-data; name="myfile"; filename="a_left.jpg"
		int index = context.lastIndexOf("=");
		String filename = context.substring(index + 2, context.length() - 1);
		return filename;
	}

	//Extract file type
	public static String getFileType(String fileName){
		//9527s.jpg
		int index = fileName.lastIndexOf(".");
		return fileName.substring(index);
	}
}
