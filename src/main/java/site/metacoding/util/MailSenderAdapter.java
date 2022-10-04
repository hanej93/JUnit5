package site.metacoding.util;

// @Component
public class MailSenderAdapter implements MailSender {

    // private Mail mail;

    // public MailSenderAdapter() {
    //     this.mail = new Mail();
    // }

    @Override
    public boolean send() {
        // return mail.sendMail();
        return true;
    }
    
}
