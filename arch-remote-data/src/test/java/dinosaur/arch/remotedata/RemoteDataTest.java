package dinosaur.arch.remotedata;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class RemoteDataTest {

    private static final String URL_BASE = "https://www.google.com";

    @Before public void setup() {
        RemoteData.Config.setBaseUrl(URL_BASE);
    }

    @Test public void testConfigRemoteBaseUrl() {
        RemoteData remoteData = new RemoteData();
        assertEquals(URL_BASE, remoteData.getBaseUrl());
    }

    @Test public void test() {
        RemoteData<List<Event>> remoteData = new RemoteData<>();
        String action = "/events";
        remoteData.access(action)
                .got(data -> {
                    // when got the data in async mode
                })
                .onLoseConnection(() -> {
                    // when lose network connection
                })
                .onError(throwable -> {
                    // when cache a throwable
                });
    }
}
