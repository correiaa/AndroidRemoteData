package dinosaur.arch.remotedata.retrofit;

import android.text.TextUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitHelper {

    private static final String TAG = RetrofitHelper.class.getSimpleName();

    private static final int CONNECT_TIMEOUT = 15;
    private static final int READ_TIMEOUT = 60;

    private static OkHttpClient.Builder newOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).addInterceptor(interceptor);

        return clientBuilder;
    }

    public static Retrofit newRetrofit(final String endpoint, final String cookie) {
        OkHttpClient.Builder clientBuilder = newOkHttpClient();
        if (!TextUtils.isEmpty(cookie)) {
            clientBuilder.addInterceptor(new AddCookiesInterceptor(cookie));
        }
        OkHttpClient client = clientBuilder.build();
        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(endpoint)
                .client(client);

        return builder.build();
    }

    public static <T> T createService(String url, Class<T> service, final String cookie) {
        return newRetrofit(url, cookie).create(service);
    }

    public static <T> T createService(Class<T> service, String url) {
        return createService(url, service, null);
    }

    static class AddCookiesInterceptor implements Interceptor {
        String mCookie;

        AddCookiesInterceptor(String cookie) {
            mCookie = cookie;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            if (!TextUtils.isEmpty(mCookie))
                builder.addHeader("Cookie", mCookie);

            return chain.proceed(builder.build());
        }
    }
}
