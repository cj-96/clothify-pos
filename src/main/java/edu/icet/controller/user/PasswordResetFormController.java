package edu.icet.controller.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.UserBo;
import edu.icet.util.BoType;
import edu.icet.util.CrudUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

public class PasswordResetFormController implements Initializable {
    public JFXTextField txtEmail;
    public JFXTextField txtOtp;
    public JFXButton btnSave;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtConfirmPassword;


    String otp;

    UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPassword.setDisable(true);
        txtConfirmPassword.setDisable(true);
        btnSave.setDisable(true);

    }

    public void btnSendOtpOnAction(ActionEvent actionEvent) {
        Boolean exist = userBo.isExist(txtEmail.getText());
        if(exist){
            sendEmail(txtEmail.getText());
        }else{
            new Alert(Alert.AlertType.ERROR, "Email is not registered !").show();
        }
    }

    private void sendEmail(String recipient) {
        String sender = "chamfer1996@gmail.com";

        otp = getOtp();
        String pw = "qmfc jyis xpwk jgkp";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, pw);
                    }
                }
        );
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("change your password For this is OTP mail");
            message.setText(otp + " is your authentication setup code.");
            Transport.send(message);
            new Alert(Alert.AlertType.INFORMATION, "otp has been send please check your email !").show();
            txtOtp.setDisable(false);
            btnSave.setDisable(false);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public String getOtp() {
        String numbers = "0123456789";
        Random rNd = new Random();
        String otpString = "";
        for (int i = 0; i < 6; i++) {
            otpString += numbers.charAt(rNd.nextInt(numbers.length()));

        }
        System.out.println(otpString);
        return otpString;
    }

    public void btnVerifyOnAction(ActionEvent actionEvent) {
        if (txtOtp.getText().equals(otp)) {
            new Alert(Alert.AlertType.INFORMATION, "OTP Matched :)").show();
            txtPassword.setDisable(false);
            txtConfirmPassword.setDisable(false);


        } else {
            txtPassword.setDisable(true);
            txtConfirmPassword.setDisable(true);
            btnSave.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "invalid OTP :(").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        boolean isUpdate= userBo.updatePassword(txtConfirmPassword.getText(),txtEmail.getText());

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION, "Your password change Success !").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Your password change Failed !").show();

        }


    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }


    public void txtConfirmPwdOnKeyReleased(KeyEvent keyEvent) {
        if(txtPassword.getText().equals(txtConfirmPassword.getText())){
            btnSave.setDisable(false);
            txtConfirmPassword.setFocusColor(Paint.valueOf("4059a9"));

        }else{
            btnSave.setDisable(true);
            txtConfirmPassword.setFocusColor(Paint.valueOf("c80815"));
        }
    }


}
