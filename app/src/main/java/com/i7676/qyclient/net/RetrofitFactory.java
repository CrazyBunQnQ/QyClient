package com.i7676.qyclient.net;

import android.util.Log;
import com.i7676.qyclient.util.CommUtil;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by Administrator on 2016/9/5.
 */
public class RetrofitFactory {

  private static String cachePath;
  // FIXME how to get a valid path
  private static final String DEFAULT_CACHE_PATH = "";

  public static <S> S createService(String baseURL, Class<S> service, String cachePath) {
    interfaceCheck(service);
    RetrofitFactory.cachePath = cachePath;
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
  private static File cacheDir;
  private static long defaultCacheSize = 10 * 1024;

  private static void ensureHttpClient() {
    if (mHttpClient == null) mHttpClient = initHttpClient();
  }

  private static OkHttpClient initHttpClient() {

    if (CommUtil.validString(cachePath)) {
      cacheDir = new File(cachePath);
    } else {
      cacheDir = new File(DEFAULT_CACHE_PATH);
    }

    final Cache simpleCache = new Cache(cacheDir, defaultCacheSize);
    mHttpClient = new OkHttpClient.Builder()
        // 链接打印拦截器
        .addInterceptor(LoggerInterceptor.create())
        .readTimeout(10000, TimeUnit.MILLISECONDS)
        .connectTimeout(15000, TimeUnit.MILLISECONDS)
        .cache(simpleCache)
        .build();
    return mHttpClient;
  }

  public static class LoggerInterceptor implements Interceptor {
    private static final String TAG = ">>> reqURL: ";

    public static LoggerInterceptor create() {
      return new LoggerInterceptor();
    }

    @Override public Response intercept(Chain chain) throws IOException {
      Log.i(TAG, chain.request().toString());
      return chain.proceed(chain.request());
    }
  }
}
