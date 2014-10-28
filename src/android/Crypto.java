//
//  Crypto.h
//  Crypto
//
//  Created by kumar on 27/10/14.
//  Copyright (c) 2014 nixplay. All rights reserved.
//

package com.nixplay.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import 	android.net.Uri;

public class Crypto extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
		if (action.equals("md5")) {
			String fileName = data.getString(0);
			System.out.println("[CordovaLog] File Name: " + fileName);
			if(fileName!=null && !"".equals(fileName)) {
		    	try {

		    		if(fileName.startsWith("content://")) {
		    			Uri uri = Uri.parse(fileName);
			    		System.out.println("[CordovaLog] Uri: " + uri);
			    		String md5 = getMD5Checksum(uri);
			    		System.out.println("[CordovaLog] MD5: " + md5);
						callbackContext.success(md5);
		    		} else {
		    			if(fileName.startsWith("file://")) {
							fileName = fileName.replaceAll("file://", "");
						}
		    			File file = new File(fileName);
	                    System.out.println("[CordovaLog] File: " + file);
	                    String md5 = getMD5Checksum(file);
	                    System.out.println("[CordovaLog] MD5: " + md5);
	                    callbackContext.success(md5);
		    		}
				} catch(Exception ioe) {
					System.out.println("[CordovaLog] Error when generating md5: " + fileName);
					callbackContext.error("Error when generating md5: " + fileName);
					return false;
				}
			} else {
				System.out.println("[CordovaLog] File name cannot be empty: " + fileName);
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	public byte[] createChecksum(File file) throws Exception {
		 InputStream fis =  new FileInputStream(file);
		//InputStream fis = cordova.getActivity().getContentResolver().openInputStream(file);
		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;
		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);
		fis.close();
		return complete.digest();
	}

	// a byte array to a HEX string
	public String getMD5Checksum(File file) throws Exception {
		byte[] b = createChecksum(file);
		String result = "";

		for (int i=0; i < b.length; i++) {
			result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		return result;
	}

	public byte[] createChecksum(Uri uri) throws Exception {
		// InputStream fis =  new FileInputStream(fileName);
		InputStream fis = cordova.getActivity().getContentResolver().openInputStream(uri);
		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;
		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);
		fis.close();
		return complete.digest();
	}

	// a byte array to a HEX string
	public String getMD5Checksum(Uri uri) throws Exception {
		byte[] b = createChecksum(uri);
		String result = "";

		for (int i=0; i < b.length; i++) {
			result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		return result;
	}
}
