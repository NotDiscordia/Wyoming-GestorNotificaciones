package Wyoming.WyomingGestor.Servicio;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service  // Anotación necesaria para que Spring registre la clase como un bean
public class CorreoServiceSendGrid {

    @Value("${spring.sendgrid.api-key}")
    private String sendGridApiKey;

    public String enviarCorreo(String destinatario, String asunto, String mensaje) {
        Email from = new Email("tucorreo@uabc.edu.mx");
        Email to = new Email(destinatario);
        Content content = new Content("text/plain", mensaje);
        Mail mail = new Mail(from, asunto, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            if (response.getStatusCode() == 202) {
                return "Correo enviado exitosamente a: " + destinatario;
            } else {
                return "Error al enviar el correo. Código: " + response.getStatusCode();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return "Error al enviar el correo: " + ex.getMessage();
        }

    }
    @PostConstruct
    public void init() {
        System.out.println("Clave de API SendGrid: " + sendGridApiKey);
    }

}
