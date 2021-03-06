package eu.tastr.e2e.application.testapplication.proxy.rules.common;

import eu.tastr.e2e.application.testapplication.tomcat.TastrTomcat;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpResponse;

public abstract class TomcatProxyRule implements TastrProxyRule {

    public static final String TOMCAT_BASE_URL = TastrTomcat.getInstance().baseUri();

    @Override
    public HttpResponse proxy(DefaultHttpRequest httpRequest) {
        httpRequest.setUri(TOMCAT_BASE_URL + httpRequest.getUri());
        return null;
    }
}
