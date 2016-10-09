package be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.proxy.rules;

import be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.proxy.rules.common.FileSystemProxyRule;
import io.netty.handler.codec.http.DefaultHttpRequest;

public class IndexProxyRule extends FileSystemProxyRule {

    private static final String INDEX = "index.html";
    private static final String INDEX_HTML_PATH = "/";

    @Override
    public boolean canProxy(DefaultHttpRequest httpRequest) {
        return INDEX_HTML_PATH.equals(httpRequest.getUri());
    }

    @Override
    protected String fileName(String uri) {
        return INDEX;
    }
}
