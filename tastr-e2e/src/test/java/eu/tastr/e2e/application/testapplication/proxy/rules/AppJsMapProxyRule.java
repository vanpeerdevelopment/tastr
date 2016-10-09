package eu.tastr.e2e.application.testapplication.proxy.rules;

import eu.tastr.e2e.application.testapplication.proxy.rules.common.FileSystemProxyRule;
import io.netty.handler.codec.http.DefaultHttpRequest;

public class AppJsMapProxyRule extends FileSystemProxyRule {

    private static final String APP_JS_MAP = "app/app.min.js.map";
    private static final String APP_JS_MAP_PATH = "/app/app.min.js.map";

    @Override
    public boolean canProxy(DefaultHttpRequest httpRequest) {
        return APP_JS_MAP_PATH.equals(httpRequest.getUri());
    }

    @Override
    protected String fileName(String uri) {
        return APP_JS_MAP;
    }
}
