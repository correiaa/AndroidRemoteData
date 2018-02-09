package arch.active.remotedata.retrofit.endpoint;

import arch.active.remotedata.retrofit.response.App;
import io.reactivex.Observable;
import retrofit2.http.GET;

// RemoteData.class
public interface AppEndpoint {
    // RemoteData.Action
    String ACTION_APP_NAME = "/success/true/app/event";

    // RemoteData.Method
    @GET(ACTION_APP_NAME)
    Observable<App> execute();
}
