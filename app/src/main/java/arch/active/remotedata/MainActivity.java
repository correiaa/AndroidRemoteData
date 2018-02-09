package arch.active.remotedata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import arch.active.remotedata.retrofit.RetrofitHelper;
import arch.active.remotedata.retrofit.endpoint.AppEndpoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppEndpoint appEndpoint = RetrofitHelper.createService(AppEndpoint.class);
        appEndpoint.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        app -> {
                            Log.d(TAG, "Got app: " + app);
                        }, error -> {
                            Log.e(TAG, "GotAppError", error);
                        }
                );
    }
}
