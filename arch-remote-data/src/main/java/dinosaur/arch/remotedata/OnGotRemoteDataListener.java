package dinosaur.arch.remotedata;

public interface OnGotRemoteDataListener<T> {
    void onGotData(T data);
}
