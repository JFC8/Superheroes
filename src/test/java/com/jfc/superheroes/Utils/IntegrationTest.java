package com.jfc.superheroes.Utils;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT
)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@Testcontainers
public class IntegrationTest
{
    public static final String DEFAULT_PROTOCOL = "http";
    public static final String DEFAULT_HOST = "localhost";
    @LocalServerPort
    protected int port;
    //@Value("${server.servlet.context-path}")
    protected String context;
    @Autowired
    protected TestRestTemplate testRestTemplate;
    @Autowired
    protected MockMvc mockMvc;

    protected IntegrationTest() {
    }

    protected String getProtocol() {
        return "http";
    }

    protected String getHost() {
        return "localhost";
    }

    protected String getPort() {
        return Integer.toString(this.port);
    }

    protected String getContext() {
        return this.context;
    }

    private String getTrimNotNull(String str) {
        return str != null ? str.trim() : "";
    }

    private boolean isValid(String str) {
        return str != null && !str.trim().isEmpty();
    }

    private String getValidPath(String path) {
        String validContext = this.getTrimNotNull(this.getContext());
        String validPath = this.getTrimNotNull(path);
        if (!validContext.startsWith("/")) {
            validContext = "/" + validContext;
        }

        if (validContext.endsWith("/") && validPath.startsWith("/")) {
            validPath = validPath.substring(1);
        } else if (!validContext.endsWith("/") && !validPath.startsWith("/")) {
            validPath = "/" + validPath;
        }

        return validContext + validPath;
    }

    protected String getUrl(String path, String... pathParams) {
        StringBuilder sb = (new StringBuilder()).append(this.getProtocol()).append("://").append(this.getHost()).append(":").append(this.getPort()).append(this.getValidPath(path));
        if (pathParams != null) {
            String[] var4 = pathParams;
            int var5 = pathParams.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String pathParam = var4[var6];
                if (this.isValid(pathParam)) {
                    sb.append("/").append(pathParam);
                }
            }
        }

        return sb.toString();
    }
}
