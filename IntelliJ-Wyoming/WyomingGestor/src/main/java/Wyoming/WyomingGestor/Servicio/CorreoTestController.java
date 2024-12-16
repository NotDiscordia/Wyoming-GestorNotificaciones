package Wyoming.WyomingGestor.Controller;

import Wyoming.WyomingGestor.Servicio.CorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private CorreoService correoService;

    @PostMapping("/enviar")
    public String enviarNotificacion(@RequestParam String destinatario,
                                     @RequestParam String titulo,
                                     @RequestParam String contenido) {
        try {
            // Validar correo UABC
            if (!destinatario.endsWith("@uabc.edu.mx")) {
                return "Error: El correo debe pertenecer al dominio @uabc.edu.mx";
            }

            // Enviar correo
            correoService.enviarCorreo(destinatario, titulo, contenido);
            return "Notificación enviada exitosamente a " + destinatario;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al enviar la notificación: " + e.getMessage();
        }
    }
}
