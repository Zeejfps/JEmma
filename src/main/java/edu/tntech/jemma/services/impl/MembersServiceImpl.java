package edu.tntech.jemma.services.impl;

import com.google.gson.Gson;
import edu.tntech.jemma.models.Member;
import edu.tntech.jemma.models.Optout;
import edu.tntech.jemma.services.MembersService;
import okhttp3.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MembersServiceImpl implements MembersService {

    private static MediaType JSON = MediaType.parse("application/json");

    private final OkHttpClient httpClient;
    private final Gson gson;

    public MembersServiceImpl(OkHttpClient httpClient, Gson gson) {
        this.httpClient = httpClient;
        this.gson = gson;
    }

    @Override
    public List<Member> fetchAll(HttpUrl url) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (response.isSuccessful() && responseBody != null) {
                Member[] members = gson.fromJson(responseBody.string(), Member[].class);
                return Arrays.asList(members);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<Member> fetch(HttpUrl url) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (response.isSuccessful() && responseBody != null) {
                return Optional.of(gson.fromJson(responseBody.string(), Member.class));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public List<Optout> fetchOptouts(HttpUrl url) {
        System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (response.isSuccessful() && responseBody != null) {
                Optout[] optouts = gson.fromJson(responseBody.string(), Optout[].class);
                System.out.println(optouts.length);
                return Arrays.asList(optouts);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean optout(HttpUrl url) {
        Request request = new Request.Builder()
                .url(url)
                .put(RequestBody.create(JSON, "{}"))
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (response.isSuccessful() && responseBody != null) {
                return gson.fromJson(responseBody.string(), Boolean.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
