package eu.tastr.e2e.application.testapplication.proxy;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.littleshoot.proxy.HttpFiltersAdapter;

public class TastrHttpFilters extends HttpFiltersAdapter {

    private static final HttpResponse CONTINUE = null;
    private final TastrProxyChain chain;

    public TastrHttpFilters(HttpRequest originalRequest) {
        super(originalRequest);
        chain = new TastrProxyChain();
    }

    @Override
    public HttpResponse clientToProxyRequest(HttpObject httpObject) {
        if (httpObject instanceof DefaultHttpRequest) {
            return chain.proxy((DefaultHttpRequest) httpObject);
        }
        return CONTINUE;
    }
}
