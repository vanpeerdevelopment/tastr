package be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.proxy.rules.common;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpResponse;

public interface TastrProxyRule {

    boolean canProxy(DefaultHttpRequest httpRequest);

    HttpResponse proxy(DefaultHttpRequest httpRequest);
}
