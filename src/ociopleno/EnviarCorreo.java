
package ociopleno;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


public class EnviarCorreo{
    // Constantes de configuración del correo
    private final String remite="semientornos@gmail.com";//email remitente
    private final String passremite="acces0entornos";// contraseña del email remitente
    private final String asunto = "Remitiendo contraseña de acceso a Ocio Pleno";//Asunto del correo
    private final String mensaje = "Su contraseña de acceso a Ocio Pleno es: ";//Asunto del correo
    
    private String pass;// contraseña a enviar
    private String destinatario;// email destinatario
    
    /**
     * CONSTRUCTOR
     */
    public EnviarCorreo(String pass, String destinatario){

        this.pass=pass;
        this.destinatario=destinatario;
        
        enviar();
    }
            
    private void enviar(){
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
 
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        //return new PasswordAuthentication(Username, PassWord);
                        return new PasswordAuthentication(remite, passremite);
                    }
                });
 
        try {
 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remite));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje + pass);
 
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "La contraseña se ha enviado a su email");
 
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}