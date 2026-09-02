package io.fogcloud.sdk.easylink.jetty;

import com.stub.StubApp;
import io.fogcloud.sdk.easylink.helper.ComHelper;
import io.fogcloud.sdk.easylink.helper.EasyLinkCallBack;
import io.fogcloud.sdk.easylink.helper.EasyLinkErrCode;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class EasyServlet extends HttpServlet {
    private static final long serialVersionUID = 1;
    private ComHelper comfunc = new ComHelper();
    private EasyLinkCallBack elcb;

    public EasyServlet(EasyLinkCallBack easyLinkCallBack) {
        this.elcb = easyLinkCallBack;
    }

    @Override // javax.servlet.http.HttpServlet
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setCharacterEncoding(StubApp.getString2(567));
        httpServletResponse.setContentType(StubApp.getString2(4019));
        PrintWriter writer = httpServletResponse.getWriter();
        writer.println(StubApp.getString2(13461));
        writer.flush();
        this.comfunc.successCBEasyLink(EasyLinkErrCode.CALLBACK_CODE, readFully(httpServletRequest.getInputStream(), StubApp.getString2(32550)), this.elcb);
    }

    public String readFully(InputStream inputStream, String str) throws IOException {
        return new String(readFully(inputStream), str);
    }

    private byte[] readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
