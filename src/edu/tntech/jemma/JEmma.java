package edu.tntech.jemma;

import edu.tntech.jemma.models.Member;
import okhttp3.Call;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.List;

public class JEmma {

    private static final MediaType JSON = MediaType.parse("application/json");

    private final String accountID;
    private final String publicKey;
    private final String privateKey;

    private final OkHttpClient httpClient;

    private final Members members = new Members();

    public JEmma(String accountID, String publicKey, String privateKey){
        this.accountID = accountID;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        httpClient = new OkHttpClient.Builder()
                .authenticator((route, response) -> {
                    String credentials = Credentials.basic(publicKey, privateKey);
                    return response.request().newBuilder().header("Authorization", credentials).build();
                }).build();
    }

    public Members members() {
        return members;
    }

    public class Members {

        private Members() {}

        public Call bulkAddOrUpdate(List<Member> members) {
            return null;
        }

    }

}
