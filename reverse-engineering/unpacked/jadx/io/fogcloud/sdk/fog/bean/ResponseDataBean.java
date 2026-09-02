package io.fogcloud.sdk.fog.bean;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class ResponseDataBean {
    private ImgPath data;
    private Msg meta;

    public Msg getMeta() {
        return this.meta;
    }

    public void setMeta(Msg msg) {
        this.meta = msg;
    }

    public ImgPath getData() {
        return this.data;
    }

    public void setData(ImgPath imgPath) {
        this.data = imgPath;
    }

    public class Msg {
        int code;
        String message;

        public Msg() {
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        public int getCode() {
            return this.code;
        }

        public void setCode(int i) {
            this.code = i;
        }
    }

    public class ImgPath {
        String image_path;

        public ImgPath() {
        }

        public String getImage_path() {
            return this.image_path;
        }

        public void setImage_path(String str) {
            this.image_path = str;
        }
    }
}
