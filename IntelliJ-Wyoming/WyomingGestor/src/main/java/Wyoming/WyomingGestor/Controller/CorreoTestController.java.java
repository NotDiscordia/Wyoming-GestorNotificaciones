package Wyoming.WyomingGestor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorreoTestController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/enviar-correo-test")
    public String enviarCorreo(@RequestParam String destinatario) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(destinatario);
            mensaje.setSubject("Prueba de Envío de Correo");
            mensaje.setText("Este es un correo de prueba enviado desde Spring Boot.");

            mailSender.send(mensaje);
            return "Correo enviado exitosamente a " + destinatario;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al enviar el correo: " + e.getMessage();
        }
    }
}
