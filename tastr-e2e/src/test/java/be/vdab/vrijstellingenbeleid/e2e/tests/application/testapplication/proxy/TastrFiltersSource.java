package be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.proxy;

import io.netty.handler.codec.http.HttpRequest;
import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;

public class TastrFiltersSource extends HttpFiltersSourceAdapter {

    @Override
    public HttpFilters filterRequest(HttpRequest originalRequest) {
        return new TastrHttpFilters(originalRequest);
    }
}
