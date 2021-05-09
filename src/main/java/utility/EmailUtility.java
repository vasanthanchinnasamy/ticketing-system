package utility;

import java.io.IOException;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class EmailUtility {
	
	  public static Mail buildResponseEmail(Long ticketId, String responseText,String emailAddress) {
		    Email from = new Email("vasanthantamilan@gmail.com");
		    String subject = "New response has been added to your ticket "+ticketId;
		    Email to = new Email(emailAddress);
		    Content content = new Content("text/plain", "Response added is "+responseText);
		    Mail mail = new Mail(from, subject, to, content);
		    return mail;
		  }
	  
	  
	  public static void sendResponseMail(Long ticketId, String responseText,String emailAddress) throws IOException {
		    final Mail resposeMail = buildResponseEmail(ticketId, responseText,emailAddress);
		    send(resposeMail);
		  }

		  private static void send(final Mail mail) throws IOException {
		    final SendGrid sg = new SendGrid("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");

		    final Request request = new Request();
		    request.setMethod(Method.POST);
		    request.setEndpoint("mail/send");
		    request.setBody(mail.build());

		    final Response response = sg.api(request);
		  }

}
