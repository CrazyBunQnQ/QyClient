package com.i7676.qyclient.net;

import android.text.TextUtils;
import com.i7676.qyclient.QyClient;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by Administrator on 2016/9/5.
 */
public final class RetrofitFactory {

    public static <S> S createService(String baseURL, Class<S> service) {
        interfaceCheck(service);
        //this.cacheDir = cacheDir;
        return createRetrofitClient(baseURL).create(service);
    }

    public static <S> S createService(String baseURL, Class<S> service, File cacheDir) {
        interfaceCheck(service);
        //this.cacheDir = cacheDir;
        return createRetrofitClient(baseURL).create(service);
    }

    private static void interfaceCheck(Class clz) {
        if (!clz.isInterface()) {
            throw new IllegalArgumentException(clz.getSimpleName() + " may not be an interface.");
        }
    }

    private static Retrofit createRetrofitClient(String baseURL) {

        ensureHttpClient();

        final Retrofit mRetrofitClient = new Retrofit.Builder().client(mHttpClient)
            .baseUrl(baseURL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(FastJsonConverterFactory.create())
            .build();

        return mRetrofitClient;
    }

    private static OkHttpClient mHttpClient;
    //private File cacheDir;
    //private long defaultCacheSize = 10 * 1024 * 1024;

    private static void ensureHttpClient() {
        if (mHttpClient == null) mHttpClient = initHttpClient();
    }

    private static OkHttpClient initHttpClient() {
        //final Cache simpleCache = new Cache(cacheDir, defaultCacheSize);

        mHttpClient = new OkHttpClient.Builder()
            // 请求结果拦截器
            .addInterceptor(new HttpLoggingInterceptor())
            // Token 拦截器
            .addInterceptor(mTokenInterceptor)
            // api认证 401 拒绝服务拦截器
            //.authenticator(new QyAuthenticator())
            // 常规设置
            .readTimeout(10000, TimeUnit.MILLISECONDS).connectTimeout(15000, TimeUnit.MILLISECONDS)
            //.cache(simpleCache)
            .build();
        return mHttpClient;
    }

    //private class QyAuthenticator implements Authenticator {
    //    @Override public Request authenticate(Route route, Response response) throws IOException {
    //        return null;
    //    }
    //}

    private static Interceptor mTokenInterceptor = new Interceptor() {

        final String authHeader = "Authorization";

        boolean checkTokenValidity(Request originalRequest) {
            String authToken = originalRequest.header(authHeader);
            return TextUtils.isEmpty(authToken);
        }

        @Override public Response intercept(Chain chain) throws IOException {
            // 获取原始请求
            Request originalRequest = chain.request();
            // 有 token 之后每个请求必须带上 token
            if (checkTokenValidity(originalRequest)) {
                originalRequest =
                    originalRequest.newBuilder().header(authHeader, QyClient.mToken).build();
            }
            // FIXME cache有风险，过期需谨慎啊，确定了过期方案再 cache 吧
            return chain.proceed(originalRequest);
        }
    };
}
