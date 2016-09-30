package com.i7676.qyclient.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Api Connection class used to retrieve data from the cloud.
 * Implements {@link java.util.concurrent.Callable} so when executed asynchronously can return a
 * value.
 *
 * @author heqi
 * @version 1.0.0
 * @create 2016/7/19
 * @modify 2016/7/19 by HCol
 *
 * FIXME we should using retrofit to connected the cloud server, because retrofit has good cache
 * component and the others h_col.com.farbox.hgank.utils
 */
public class ApiConnection implements Callable<String> {

    private URL url;
    private String response;
    private static final int HTTP_CONNECT_TIMEOUT = 15000;
    private static final int HTTP_READ_TIMEOUT = 10000;
    public static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";
    //public static final String CONTENT_TYPE_VALUE_IMAGE = "image/*";
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    //public static String CONTENT_TYPE_VALUE = CONTENT_TYPE_VALUE_JSON;

    private ApiConnection(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public static ApiConnection createGET(String url) throws MalformedURLException {
        return new ApiConnection(url);
    }

    /**
     * Do a request to an api synchronously.
     * It should not be executed in the main thread of the application.
     *
     * @return A string response
     */
    public String requestSyncCall() {
        connectToApi();
        return response;
    }

    private void connectToApi() {
        OkHttpClient okHttpClient = this.createHttpClient();
        final Request request = new Request.Builder().url(this.url)
            .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
            .get()
            .build();

        try {
            this.response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OkHttpClient createHttpClient() {
        final OkHttpClient okHttpClient =
            new OkHttpClient.Builder().connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();

        return okHttpClient;
    }

    @Override public String call() throws Exception {
        return requestSyncCall();
    }
}