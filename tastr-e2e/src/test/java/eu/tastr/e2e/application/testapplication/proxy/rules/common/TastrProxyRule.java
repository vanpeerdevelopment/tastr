package eu.tastr.e2e.application.testapplication.proxy.rules.common;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpResponse;

public interface TastrProxyRule {

    boolean canProxy(DefaultHttpRequest httpRequest);

    HttpResponse proxy(DefaultHttpRequest httpRequest);
}
