package com.francis.byteworkstest.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/*
 * Mail Service
 */
@Service
public class EmailService {

    @Autowired
    private Configuration freemarkerConfig;

    public void sendSimpleMessage(Mail mail) throws MessagingException, IOException, TemplateException {
    	
    	//mail username and password
    	final String username = "260424e5b9dc172bb6aab1267ed0f560";
		final String password = "46eb58d0ece23c9763ced569fb158378";
	    
		//smtp config
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "in-v3.mailjet.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		//Load html template from resource/templates directory
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
        Template t = freemarkerConfig.getTemplate(mail.getTemplate());
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

		Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username, " "));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
		message.setSubject(mail.getSubject());
		message.setContent(html, "text/html");
		
		//Send mail
		Transport.send(message);
        
    }
    

}
