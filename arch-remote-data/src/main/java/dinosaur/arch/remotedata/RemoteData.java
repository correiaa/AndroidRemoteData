package dinosaur.arch.remotedata;

import dinosaur.arch.remotedata.retrofit.RetrofitHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RemoteData<T, I extends RemoteBlock> {

    private OnGotRemoteDataListener<T> onGotRemoteDataListener;
    private OnLoseConnectionListener onLoseConnectionListener;
    private OnRemoteErrorListener onRemoteErrorListener;

    private RemoteAction<T, I> action;

    public RemoteData<T, I> access(RemoteAction<T, I> action) {
        this.action = action;
        return this;
    }

    public RemoteData<T, I> got(OnGotRemoteDataListener<T> listener) {
        onGotRemoteDataListener = listener;
        I service = RetrofitHelper.createService(action.definition(), getBaseUrl());
        service.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        results-> {
                            onGotRemoteDataListener.onGotData(results);
                        }, error -> {
                            onRemoteErrorListener.onError(error);
                        }
                );

        return this;
    }

    public RemoteData<T, I> onLoseConnection(OnLoseConnectionListener listener) {
        onLoseConnectionListener = listener;
        return this;
    }

    public RemoteData<T, I> onError(OnRemoteErrorListener listener) {
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
