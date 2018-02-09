package arch.active.remotedata.retrofit.response;

public class App {
    private String success;
    private String app;

    public String getSuccess() {
        return success;
    }

    public App setSuccess(String success) {
        this.success = success;
        return this;
    }

    public String getApp() {
        return app;
    }

    public App setApp(String app) {
        this.app = app;
        return this;
    }
}
