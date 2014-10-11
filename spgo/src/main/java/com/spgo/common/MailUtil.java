package com.spgo.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailUtil {
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String from, String to, String subject, String msg) {
		try {

			long t1 = System.currentTimeMillis();
			SimpleMailMessage message = new SimpleMailMessage();
	
			message.setFrom(from);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(msg);
			mailSender.send(message);
			long t2 = System.currentTimeMillis();
			System.out.println((t2-t1)/1000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"dispatcher-servlet.xml");

		long t1 = System.currentTimeMillis();
		MailUtil mm = (MailUtil) context.getBean("mailMail");
		mm.sendMail("test@gmail.com", "duanhv@gmail.com", "Testing Email",
				"Testing only \n\n Hello Spring Email Sender ");
		long t2 = System.currentTimeMillis();
		System.out.println(" XXX " + (t2-t1)/1000);
		
	}
}