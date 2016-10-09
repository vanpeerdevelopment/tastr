package eu.tastr.e2e.application.testapplication.proxy.rules;

import eu.tastr.e2e.application.testapplication.proxy.rules.common.TomcatProxyRule;
import eu.tastr.e2e.application.testapplication.tomcat.TastrTomcat;
import io.netty.handler.codec.http.DefaultHttpRequest;

public class ApiProxyRule extends TomcatProxyRule {

    private static final String API_PATH = TastrTomcat.getInstance().contextPath() + "/api";

    @Override
    public boolean canProxy(DefaultHttpRequest httpRequest) {
        return httpRequest.getUri().startsWith(API_PATH);
    }
}
