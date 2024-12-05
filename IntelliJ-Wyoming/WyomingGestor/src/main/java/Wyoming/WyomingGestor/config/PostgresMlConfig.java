package Wyoming.WyomingGestor.config;// src/main/java/wyoming/wyominggestor/config/PostgresMlConfig.java
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class PostgresMlConfig {
    private static final Logger logger = LoggerFactory.getLogger(PostgresMlConfig.class);

    private final DataSource dataSource;

    @Autowired
    public PostgresMlConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE EXTENSION IF NOT EXISTS pgml VERSION '1.0'"); // <-- Añadir una versión explícita como '1.0' si sabes cuál es válida.
            logger.info("pgml extension created successfully.");

        } catch (SQLException e) {
            logger.error("Error creating pgml extension while obtaining connection or executing statement: " + e.getMessage(), e);
        }
    }
}