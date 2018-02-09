package dinosaur.arch.remotedata;

public interface RemoteAction<T, E extends RemoteBlock> {
    String path();
    T response();
    Class<E> definition();
}
