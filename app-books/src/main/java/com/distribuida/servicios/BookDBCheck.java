package com.distribuida.servicios;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.sql.DataSource;
import java.sql.SQLException;
@ApplicationScoped
@Readiness
public class BookDBCheck implements HealthCheck {
    @Inject DataSource dataSource;

    @Override
    public HealthCheckResponse call() {
        try {
            // Verifica el estado de la base de datos
            boolean isUp = dataSource.getConnection().isValid(1000);
            if (isUp) {
                return HealthCheckResponse.up("La base de datos se encuentra conectada");
            } else {
                return HealthCheckResponse.down("La base de datos no se encuentra conectada");
            }
        } catch (SQLException e) {
            return HealthCheckResponse.down(e.getMessage());
        }
    }
}
