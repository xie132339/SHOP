package com.kuke.web.user.demo;

public class FactoryTest {
	public static void main(String[] args) {
		SendFactory sf = new SendFactory();
		Sender sender = sf.produce("sms");
		sender.Send();
	}
}
