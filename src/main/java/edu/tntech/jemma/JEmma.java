package edu.tntech.jemma;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import edu.tntech.jemma.services.MembersService;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JEmma {

    private final Members members;

    public JEmma(String accountID, String publicKey, String privateKey){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .authenticator((route, response) -> {
                    String credentials = Credentials.basic(publicKey, privateKey);
                    return response.request().newBuilder().header("Authorization", credentials).build();
                }).build();

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
            String dateTimeStr = json.getAsJsonPrimitive().getAsString();
            return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("'@D:'yyyy-MM-dd'T'HH:mm:ss"));
        }).create();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.e2ma.net/" + accountID + "/")
                .client(okHttpClient)
                .build();

        MembersService membersService = retrofit.create(MembersService.class);
        members = new Members(membersService);
    }

    public Members members() {
        return members;
    }

}
