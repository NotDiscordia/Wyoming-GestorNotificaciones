package Wyoming.WyomingGestor.Controller;

import Wyoming.WyomingGestor.Servicio.CorreoServiceSendGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificacionController {

    @Autowired
    private CorreoServiceSendGrid correoService;

    @PostMapping("/enviar-correo")
    public String enviarCorreo(
            @RequestParam String destinatario,
            @RequestParam String asunto,
            @RequestParam String mensaje) {

        return correoService.enviarCorreo(destinatario, asunto, mensaje);
    }
}
