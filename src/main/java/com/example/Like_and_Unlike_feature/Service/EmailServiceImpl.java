package com.example.Like_and_Unlike_feature.Service;

import com.example.Like_and_Unlike_feature.Model.TransactionCounter;
import com.example.Like_and_Unlike_feature.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService
{
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TransactionRepo transactionRepo;

    public boolean sendEmail(String subject, String message, String to)
    {
        boolean foo = false; // Set the false, default variable "foo", we will allow it after sending code process email

        String senderEmail ="saveitasap1@gmail.com"; // your gmail email id
        String senderPassword = "qwertyuiop1234@"; // your gmail id password

        // Properties class enables us to connect to the host SMTP server
        Properties properties = new Properties();

        // Setting necessary information for object property

        // Setup host and mail server
        properties.put("mail.smtp.auth", "true"); // enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // enable TLS-protected connection
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Mention the SMTP server address. Here Gmail's SMTP server is being used to send email
        properties.put("mail.smtp.port", "587"); // 587 is TLS port number

        // get the session object and pass username and password
        Session session = Session.getDefaultInstance(properties, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {

                return new PasswordAuthentication(senderEmail,senderPassword);
            }
        });

        try {

            MimeMessage msg = new MimeMessage(session); // Create a default MimeMessage object for compose the message

            msg.setFrom(new InternetAddress(senderEmail)); // adding sender email id to msg object

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // adding recipient to msg object

            msg.setSubject(subject); // adding subject to msg object
            msg.setText(message); // adding text to msg object

            Transport.send(msg); // Transport class send the message using send() method
            System.out.println("Email Sent Successfully...");

            TransactionCounter counter =new TransactionCounter();
            Long id = transactionRepo.findByMailCounter(counter.getMailCounter());
            System.out.println(id);
            if(id==null)
            {
                transactionRepo.insertTwo();
            }
            else if (id>=1)
            {
//                       id+=id;
                counter.setMailCounter(transactionRepo.MailCounter(id));
                transactionRepo.save(counter);
                System.out.println("Here it is");
            }
            foo = true; // Set the "foo" variable to true after successfully sending emails

        }catch(Exception e) {

            System.out.println("EmailServiceImpl File Error"+ e);
        }

        return foo; // and return foo variable
    }



    public boolean sendWithAttachment(String subject, String message, String to)
    {
        boolean foo = false; // Set the false, default variable "foo", we will allow it after sending code process email

        String senderEmail = "saveitasap1@gmail.com"; // your gmail email id
        String senderPassword = "qwertyuiop1234@"; // your gmail id password

        //Properties class enables us to connect to the host SMTP server
        Properties properties = new Properties();

        //Setting necessary information for object property

        //Setup host and mail server
        properties.put("mail.smtp.auth", "true"); // enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // enable TLS-protected connection
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Mention the SMTP server address. Here Gmail's SMTP server is being used to send email
        properties.put("mail.smtp.port", "587"); // 587 is TLS port number

        // get the session object and pass username and password
        Session session = Session.getDefaultInstance(properties, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication(){

                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {

            MimeMessage msg = new MimeMessage(session); // Create a default MimeMessage object for compose the message

            msg.setFrom(new InternetAddress(senderEmail)); // adding sender email id to msg object

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // adding recipient to msg object

            msg.setSubject(subject); // adding subject to msg object

            // sets file location
            String path = "/home/sabyasachi/Agent.txt";

            MimeMultipart mimeMultipart = new MimeMultipart(); // create MimeMultipart object

            MimeBodyPart textMime = new MimeBodyPart(); // create first MimeBodyPart object textMime for containing the message

            MimeBodyPart fileMime = new MimeBodyPart(); //create second MimeBodyPart object fileMime for containing the file

            textMime.setText(message); //sets message to textMime object

            File file = new File(path); //Initialize the File and Move Path variable
            fileMime.attachFile(file); //attach file to fileMime object

            //The mimeMmultipart adds textMime and fileMime to the
            mimeMultipart.addBodyPart(textMime);
            mimeMultipart.addBodyPart(fileMime);

            msg.setContent(mimeMultipart); // Sets the mimeMultipart the contents of the msg

            Transport.send(msg); // Transport class send the message using send() method

            System.out.println("Email Sent With Attachment Successfully...");

            foo = true; // Set the "foo" variable to true after successfully sending emails with attchment

        }catch(Exception e){

            System.out.println("EmailService File Error"+ e);
        }

        return foo; // and return foo variable
    }
}
