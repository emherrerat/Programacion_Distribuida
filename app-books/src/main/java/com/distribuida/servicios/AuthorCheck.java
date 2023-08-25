package com.distribuida.servicios;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import java.time.LocalDateTime;

@ApplicationScoped
@Liveness
public class AuthorCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call (){
        return HealthCheckResponse
                .named("app-authors")
                .withData("time", LocalDateTime.now().toString())
                .down()
                .build();
    }
}
