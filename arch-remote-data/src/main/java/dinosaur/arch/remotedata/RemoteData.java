package dinosaur.arch.remotedata;

public class RemoteData<T> {

    private OnGotRemoteDataListener<T> onGotRemoteDataListener;
    private OnLoseConnectionListener onLoseConnectionListener;
    private OnRemoteErrorListener onRemoteErrorListener;

    public RemoteData<T> access(String action) {
        return this;
    }

    public RemoteData<T> got(OnGotRemoteDataListener<T> listener) {
        onGotRemoteDataListener = listener;
        return this;
    }

    public RemoteData<T> onLoseConnection(OnLoseConnectionListener listener) {
        onLoseConnectionListener = listener;
        return this;
    }

    public RemoteData<T> onError(OnRemoteErrorListener listener) {
        onRemoteErrorListener = listener;
        return this;
    }

    static class Config {
        static String BASE_URL;
        static void setBaseUrl(String url) {
            BASE_URL = url;
        }
    }

    String getBaseUrl() {
        return Config.BASE_URL;
    }
}
