package be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.proxy.rules;

import be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.proxy.rules.common.TomcatProxyRule;
import be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.tomcat.TastrTomcat;
import io.netty.handler.codec.http.DefaultHttpRequest;

public class ApiProxyRule extends TomcatProxyRule {

    private static final String API_PATH = TastrTomcat.getInstance().contextPath() + "/api";

    @Override
    public boolean canProxy(DefaultHttpRequest httpRequest) {
        return httpRequest.getUri().startsWith(API_PATH);
    }
}
