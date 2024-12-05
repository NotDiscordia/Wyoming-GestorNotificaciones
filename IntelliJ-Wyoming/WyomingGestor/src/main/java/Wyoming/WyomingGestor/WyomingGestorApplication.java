package Wyoming.WyomingGestor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import Wyoming.WyomingGestor.Web.Servlet.LoginServlet;

@SpringBootApplication
public class WyomingGestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WyomingGestorApplication.class, args);

		System.out.println("La aplicación está corriendo en: http://localhost:8080/");
	}

	@Bean
	public ServletRegistrationBean<LoginServlet> loginServletRegistration() {
		ServletRegistrationBean<LoginServlet> registrationBean = new ServletRegistrationBean<>(new LoginServlet(), "/login");
		return registrationBean;
	}
}