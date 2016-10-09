package be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.proxy.rules;

import be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.proxy.rules.common.TomcatProxyRule;
import io.netty.handler.codec.http.DefaultHttpRequest;

public class DefaultProxyRule extends TomcatProxyRule {

    @Override
    public boolean canProxy(DefaultHttpRequest httpRequest) {
        return true;
    }
}
