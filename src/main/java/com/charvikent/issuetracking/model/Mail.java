package com.charvikent.issuetracking.model;

import java.util.List;
import java.util.Map;

public class Mail {
	    private String mailFrom;
	 
	    private String mailTo;
	 
	    private String mailCc;
	 
	    private String mailBcc;
	 
	    private String mailSubject;
	 
	    private String mailContent;
	    private String templateName;

	 
	    private String contentType;
	 
	   
	 
	    public String getTemplateName() {
			return templateName;
		}

		public void setTemplateName(String templateName) {
			this.templateName = templateName;
		}

		public Mail() {
	        contentType = "text/plain";
	    }

		public String getMailFrom() {
			return mailFrom;
		}

		public void setMailFrom(String mailFrom) {
			this.mailFrom = mailFrom;
		}

		public String getMailTo() {
			return mailTo;
		}

		public void setMailTo(String mailTo) {
			this.mailTo = mailTo;
		}

		public String getMailCc() {
			return mailCc;
		}

		public void setMailCc(String mailCc) {
			this.mailCc = mailCc;
		}

		public String getMailBcc() {
			return mailBcc;
		}

		public void setMailBcc(String mailBcc) {
			this.mailBcc = mailBcc;
		}

		public String getMailSubject() {
			return mailSubject;
		}

		public void setMailSubject(String mailSubject) {
			this.mailSubject = mailSubject;
		}

		public String getMailContent() {
			return mailContent;
		}

		public void setMailContent(String mailContent) {
			this.mailContent = mailContent;
		}

		public String getContentType() {
			return contentType;
		}

		public void setContentType(String contentType) {
			this.contentType = contentType;
		}

		
		
	    

	
	
}
	