package be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.proxy.rules;

import be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.proxy.rules.common.FileSystemProxyRule;
import io.netty.handler.codec.http.DefaultHttpRequest;

public class AppJsProxyRule extends FileSystemProxyRule {

    private static final String APP_JS = "app/app.min.js";
    private static final String APP_JS_PATH = "/app/app.min.js";

    @Override
    public boolean canProxy(DefaultHttpRequest httpRequest) {
        return APP_JS_PATH.equals(httpRequest.getUri());
    }

    @Override
    protected String fileName(String uri) {
        return APP_JS;
    }
}
