package eu.tastr.e2e.application.testapplication.proxy;

import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

public class TastrProxy {

    private static TastrProxy instance = null;

    private int port = 8181;
    private boolean started = false;

    private TastrProxy() {
    }

    public int port() {
        return port;
    }

    public void start() {
        if (!started) {
            startProxy();
            started = true;
        }
    }

    private void startProxy() {
        DefaultHttpProxyServer.bootstrap()
            .withPort(port)
            .withFiltersSource(new TastrFiltersSource())
            .start();
    }

    public static TastrProxy getInstance() {
        if (instance == null)
            instance = new TastrProxy();
        return instance;
    }
}
