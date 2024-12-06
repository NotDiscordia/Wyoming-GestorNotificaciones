package Wyoming.WyomingGestor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import Wyoming.WyomingGestor.Web.Servlet.GestorUsuariosServlet;
import Wyoming.WyomingGestor.Web.Servlet.LoginServlet;
import Wyoming.WyomingGestor.Web.Servlet.RegistroServlet;

@SpringBootApplication
public class WyomingGestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WyomingGestorApplication.class, args);

		System.out.println("La aplicación está corriendo en: http://localhost:8080/");
	}

	// Registro del LoginServlet
	@Bean
	public ServletRegistrationBean<LoginServlet> loginServletRegistration() {
		ServletRegistrationBean<LoginServlet> registrationBean = new ServletRegistrationBean<>(new LoginServlet(), "/login");
		return registrationBean;
	}

	// Registro del GestorUsuariosServlet
	@Bean
	public ServletRegistrationBean<GestorUsuariosServlet> gestorUsuariosServletRegistration() {
		ServletRegistrationBean<GestorUsuariosServlet> registrationBean = new ServletRegistrationBean<>(new GestorUsuariosServlet(), "/GestorUsuarios");
		return registrationBean;
	}

	// Registro del RegistroServlet
	@Bean
	public ServletRegistrationBean<RegistroServlet> registroServletRegistration() {
		ServletRegistrationBean<RegistroServlet> registrationBean = new ServletRegistrationBean<>(new RegistroServlet(), "/ResgistroUsuarios");
		return registrationBean;
	}
}
