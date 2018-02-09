package dinosaur.arch.remotedata;

import io.reactivex.Observable;

public interface RemoteBlock {
    Observable execute();
}
