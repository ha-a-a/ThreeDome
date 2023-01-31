package com.example.demoneo4j.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.DatabaseSelection;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;

import java.util.Date;

/**
 * @author tangmengyue
 * @ClassName DatabaseConfig.java
 * @Description TODO
 * @createTime 2023年01月31日 11:27:00
 */
@Configuration
@Slf4j
public class DatabaseConfig {
    @Bean
    DatabaseSelectionProvider databaseSelectionProvider(@Value("${spring.data.neo4j.database}") String database) {
        return () -> {
            String neo4jVersion = System.getenv("NEO4J_VERSION");
            log.info("neo4jVersion={}", neo4jVersion);
            if (neo4jVersion == null || neo4jVersion.startsWith("4")) {
                return DatabaseSelection.byName(database);
            }
            return DatabaseSelection.undecided();
        };
    }
}
