package be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.proxy.rules;

import be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.proxy.rules.common.FileSystemProxyRule;
import io.netty.handler.codec.http.DefaultHttpRequest;

public class VendorJsProxyRule extends FileSystemProxyRule {

    private static final String JS_PATH = "/vendor/js";

    @Override
    public boolean canProxy(DefaultHttpRequest httpRequest) {
        return httpRequest.getUri().startsWith(JS_PATH);
    }

    @Override
    protected String fileName(String uri) {
        return "vendor/js" + uri.substring(uri.lastIndexOf("/"));
    }
}
