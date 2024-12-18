package Wyoming.WyomingGestor.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CorreoTestController {

    @GetMapping("/enviar-correo-test")
    public String enviarCorreo(@RequestParam String destinatario) {
        String firebaseUrl = "https://REGION-PROJECT_ID.cloudfunctions.net/enviarCorreo";
        String url = firebaseUrl + "?destinatario=" + destinatario;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response.getBody();
    }
}
