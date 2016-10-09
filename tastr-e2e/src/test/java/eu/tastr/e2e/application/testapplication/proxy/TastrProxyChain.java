package eu.tastr.e2e.application.testapplication.proxy;

import eu.tastr.e2e.application.testapplication.proxy.rules.common.TastrProxyRule;
import eu.tastr.e2e.application.testapplication.proxy.rules.*;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class TastrProxyChain {

    private static final HttpResponse CONTINUE = null;
    private final List<TastrProxyRule> proxyRules;

    public TastrProxyChain() {
        this.proxyRules = newArrayList(
            new IndexProxyRule(),
            new AppJsProxyRule(),
            new AppJsMapProxyRule(),
            new VendorJsProxyRule(),
            new ApiProxyRule(),
            new DefaultProxyRule()
        );
    }

    public HttpResponse proxy(DefaultHttpRequest httpRequest) {
        for (TastrProxyRule proxyRule : proxyRules) {
            if (proxyRule.canProxy(httpRequest)) {
                return proxyRule.proxy(httpRequest);
            }
        }
        return CONTINUE;
    }
}
