package com.example.Like_and_Unlike_feature.Controller;

import com.example.Like_and_Unlike_feature.Model.EMails;
import com.example.Like_and_Unlike_feature.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController
{

    @Autowired
    private EmailService emailService;

    //this api send simple email
    @PostMapping("/sendingemail")
        public ResponseEntity<?> sendEmail(@RequestBody EMails request)
        {

            System.out.println(request);

            boolean result = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
            if(result)
            {
                return  ResponseEntity.ok("Email Properly Sent Successfully... ");
            }else
            {
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("email sending fail");
            }
        }
        //this api send email with file
        @PostMapping("/sendemailattachement")
        public ResponseEntity<?> sendEmailWithAttachment(@RequestBody EMails request)
        {
            System.out.println(request);

            boolean result = this.emailService.sendWithAttachment(request.getSubject(), request.getMessage(), request.getTo());

            if(result)
            {

                return  ResponseEntity.ok("Sent Email With Attchment Successfully... ");

            }else
            {

                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("attachment email sending fail");
            }

        }
}
