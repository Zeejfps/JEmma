package edu.tntech.jemma;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;

public class JEmma {

    private final String accountID;

    private final Members members;

    public JEmma(String accountID, String publicKey, String privateKey){
        this.accountID = accountID;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .authenticator((route, response) -> {
                    String credentials = Credentials.basic(publicKey, privateKey);
                    return response.request().newBuilder().header("Authorization", credentials).build();
                }).build();
        members = new Members(this);
    }

    public String getAccountID() {
        return accountID;
    }

    public Members members() {
        return members;
    }

}
