package edu.tntech.jemma.methods;

import edu.tntech.jemma.JEmma;
import edu.tntech.jemma.exceptions.ApiException;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public abstract class ApiMethod<R> {

    protected final JEmma jemma;
    protected final HttpUrl.Builder urlBuilder;

    public ApiMethod(JEmma jemma) {
        this.jemma = jemma;
        this.urlBuilder = new HttpUrl.Builder();
        urlBuilder.scheme(JEmma.SCHEME).host(JEmma.API_URL);
        urlBuilder.addPathSegment(jemma.getAccountID());
    }

    protected <T> T execute(Request request, Class<T> clazz) {

        try {
            Response response = jemma.getHttpClient().newCall(request).execute();
            ResponseBody body = response.body();
            if (response.isSuccessful() && body != null) {
                return jemma.getGson().fromJson(body.charStream(), clazz);
            }
            throw new ApiException(response.code(), response.message());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract R execute();

}
