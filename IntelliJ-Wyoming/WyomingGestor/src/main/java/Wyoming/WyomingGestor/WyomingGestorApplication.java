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
	}

	@Bean
	public ServletRegistrationBean<LoginServlet> loginServletRegistration() {
		ServletRegistrationBean<LoginServlet> registrationBean = new ServletRegistrationBean<>(new LoginServlet(), "/login");
		return registrationBean;
	}
}