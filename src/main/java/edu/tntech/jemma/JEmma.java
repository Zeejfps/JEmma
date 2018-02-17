package edu.tntech.jemma;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class JEmma {

    public static final String SCHEME = "https";
    public static final String API_URL = "api.e2ma.net";

    private final String accountID;

    private final OkHttpClient httpClient;
    private final Gson gson;

    private final Members members;

    public JEmma(String accountID, String publicKey, String privateKey){

        this.accountID = accountID;

        this.httpClient = new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .authenticator((route, response) -> {
                    String credentials = Credentials.basic(publicKey, privateKey);
                    return response.request().newBuilder().header("Authorization", credentials).build();
                }).build();

        this.gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
            String dateTimeStr = json.getAsJsonPrimitive().getAsString();
            return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("'@D:'yyyy-MM-dd'T'HH:mm:ss"));
        }).create();

        members = new Members(this);
    }

    public Members members() {
        return members;
    }

    public String getAccountID() {
        return accountID;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public Gson getGson() {
        return gson;
    }

}
