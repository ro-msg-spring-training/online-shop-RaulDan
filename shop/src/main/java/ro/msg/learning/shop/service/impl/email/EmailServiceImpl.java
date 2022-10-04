package ro.msg.learning.shop.service.impl.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.service.EmailService;
import ro.msg.learning.shop.service.MessageSourceService;
import ro.msg.learning.shop.service.PDFService;
import ro.msg.learning.shop.utils.EmailType;
import ro.msg.learning.shop.utils.Status;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${app.email}")
    private EmailType emailType;
    private String subject;
    private String mainMessage;
    private String createdAt;
    private String orderDetails;
    private String endMessage;
    private String billMessage;
    private static final String FROM="rauldan216@gmail.com";
    private DateTimeFormatter formatter;


    private final MessageSource messageSource;
    private final MessageSourceService messageSourceService;
    private final PDFService pdfService;

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @PostConstruct
    private void init(){
        subject=messageSource.getMessage("message.subject",null, Locale.ENGLISH);
        mainMessage=messageSource.getMessage("message.mainMessage",null, Locale.ENGLISH);
        endMessage=messageSource.getMessage("message.endMessage",null, Locale.ENGLISH);
        orderDetails=messageSource.getMessage("message.orderDetails",null, Locale.ENGLISH);
        billMessage=messageSource.getMessage("message.bill",null,Locale.ENGLISH);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    }

    @Override
    public Status sendEmail(String to, String createdAt, List<OrderDetail> orderDetails) {
        switch (emailType){
            case TEXT: return sendPlainTextEmail(to,createdAt,orderDetails);
            case HTML:
                try {
                    return sendHTMLEmail(to,createdAt, orderDetails);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            case PDF:sendPDFEmail(to,createdAt,orderDetails);
            default:return Status.ERROR;
        }
    }

    private Status sendPlainTextEmail(String to,String createdAt, List<OrderDetail> orderDetails){
        String text=this.mainMessage+this.orderDetails;
        for(OrderDetail orderDetail:orderDetails){
            text+=orderDetail.getProduct().getName()+"\n";
        }
        this.createdAt=messageSourceService.getMessage("message.createdAt", List.of(createdAt));
        text+=createdAt;
        text+="\n\n"+this.endMessage;
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(FROM);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        return Status.SUCCESS;
    }

    private Status sendHTMLEmail(String to,String createdAt, List<OrderDetail> orderDetails) throws MessagingException {
        MimeMessage mimeMessage=emailSender.createMimeMessage();
        MimeMessageHelper helper=computeHelper(to,mimeMessage,false);
        this.createdAt=messageSourceService.getMessage("message.createdAt", List.of(createdAt));
        Context context=new Context();
        context.setVariable("orderDetails",orderDetails);
        context.setVariable("createdAt",this.createdAt);
        String htmlContent=templateEngine.process("email",context);
        helper.setText(htmlContent,true);
        emailSender.send(mimeMessage);
        return Status.SUCCESS;
    }

    private void sendPDFEmail(String to,String createdAt,List<OrderDetail> orderDetails){
        MimeMessage mimeMessage=emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper=computeHelper(to,mimeMessage,true);
            mimeMessageHelper.setText(this.mainMessage+"\n"+this.billMessage);
            DataSource dataSource= pdfService.computePDF(orderDetails);
            mimeMessageHelper.addAttachment("bill.pdf",dataSource);
            emailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private MimeMessageHelper computeHelper(String to,MimeMessage mimeMessage,boolean multipart){
        try {
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,multipart);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(FROM);
            mimeMessageHelper.setSubject(this.subject);
            return mimeMessageHelper;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
