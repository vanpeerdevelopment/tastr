package eu.tastr.e2e.application.testapplication.tomcat;

import eu.tastr.infrastructure.spring.InfrastructureTestPropertiesConfig;
import eu.tastr.fatjar.spring.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class TastrTomcat {

    private static TastrTomcat instance;

    private final int port = 8282;
    private final String contextPath = "";
    private boolean started = false;

    private TastrTomcat(){
    }

    public int port() {
        return port;
    }

    public String contextPath() {
        return contextPath;
    }

    public String baseUri() {
        return "http://localhost:" + port();
    }

    public void start() {
        if (!started) {
            startTomcat();
            started = true;
        }
    }

    private void startTomcat() {
        new SpringApplicationBuilder()
            .sources(
                Application.class,
                InfrastructureTestPropertiesConfig.class)
            .properties("server.port:" + port)
            .properties("server.contextPath:" + contextPath)
            .run();
    }

    public static TastrTomcat getInstance() {
        if (instance == null)
            instance = new TastrTomcat();
        return instance;
    }
}
