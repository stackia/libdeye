package io.fogcloud.sdk.easylink.jetty;

import com.stub.StubApp;
import io.fogcloud.sdk.easylink.helper.EasyLinkCallBack;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class EasyServer {
    private int mPort;
    public Server mServer;

    public EasyServer(int i) {
        this.mPort = i;
    }

    public synchronized void start(EasyLinkCallBack easyLinkCallBack) {
        Server server = this.mServer;
        if (server == null || !server.isStarted()) {
            if (this.mServer == null) {
                ServletContextHandler servletContextHandler = new ServletContextHandler(1);
                servletContextHandler.addServlet(new ServletHolder(new EasyServlet(easyLinkCallBack)), StubApp.getString2("44626"));
                HandlerList handlerList = new HandlerList();
                handlerList.addHandler(servletContextHandler);
                Server server2 = new Server(this.mPort);
                this.mServer = server2;
                server2.setHandler(handlerList);
            }
            try {
                this.mServer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void stop() {
        Server server = this.mServer;
        if (server == null || server.isStopped()) {
            return;
        }
        try {
            this.mServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean isStarted() {
        Server server = this.mServer;
        if (server == null) {
            return false;
        }
        return server.isStarted();
    }
}
