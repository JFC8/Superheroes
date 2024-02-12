package com.jfc.superheroes;

import com.jfc.superheroes.Utils.IntegrationTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

public class AbstractIntegrationTest extends IntegrationTest
{
    private static final String DOCKER_ENTRYPOINT_INIT_DB = "/docker-entrypoint-initdb.d";

    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0"));

    static
    {
        mySQLContainer
                .withDatabaseName( "superheroes" )
                .withUsername( "hero" )
                .withPassword( "hero" )
                .withCopyFileToContainer(MountableFile.forHostPath("./src/db"), DOCKER_ENTRYPOINT_INIT_DB)
                .start();
    }

    @DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry)
    {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.datasource.driverClassName", mySQLContainer::getDriverClassName);

    }


    protected AbstractIntegrationTest() {}

}
