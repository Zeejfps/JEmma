package edu.tntech.jemma.methods.members;

import edu.tntech.jemma.JEmma;
import okhttp3.Request;

public class GetMemberCount extends MembersApiMethod<Integer> {

    private Boolean includeDeleted;

    public GetMemberCount(JEmma jemma) {
        super(jemma);
        urlBuilder.addQueryParameter("count", "true");
    }

    public GetMemberCount includeDeleted() {
        urlBuilder.addQueryParameter("deleted", "true");
        return this;
    }

    public Integer execute() {
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get().build();
        return execute(request, Integer.class);
    }

}
