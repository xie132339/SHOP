package com.kuke.web.user.demo;

public class SmsSender implements Sender{

	@Override
	public void Send() {
		System.out.println("this is sms sender!");
	}

}
