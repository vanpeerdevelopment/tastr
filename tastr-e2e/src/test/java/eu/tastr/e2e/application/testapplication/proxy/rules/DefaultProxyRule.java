package eu.tastr.e2e.application.testapplication.proxy.rules;

import eu.tastr.e2e.application.testapplication.proxy.rules.common.TomcatProxyRule;
import io.netty.handler.codec.http.DefaultHttpRequest;

public class DefaultProxyRule extends TomcatProxyRule {

    @Override
    public boolean canProxy(DefaultHttpRequest httpRequest) {
        return true;
    }
}
