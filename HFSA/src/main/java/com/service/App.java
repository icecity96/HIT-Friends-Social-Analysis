package com.service;

import java.io.UnsupportedEncodingException;

public class App {

	public static void main(String[] args) {
		String password = "lmy19960521";
		try {
			String md5string = PassWord.md5encode(password);
			System.out.println(md5string);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
