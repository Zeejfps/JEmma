package edu.tntech.jemma.methods.members;

import edu.tntech.jemma.JEmma;
import edu.tntech.jemma.models.Member;
import okhttp3.Request;

import java.util.Arrays;
import java.util.List;

public class GetMembers extends MembersApiMethod<List<Member>> {

    private int start;
    private int length;

    public GetMembers(JEmma jemma, int length) {
        super(jemma);
        this.length = length;
        this.start = 0;
    }

    public GetMembers from(int index) {
        this.start = index;
        return this;
    }

    public GetMembers includeDeleted() {
        urlBuilder.addQueryParameter("deleted", "true");
        return this;
    }

    @Override
    public List<Member> execute() {
        urlBuilder.addQueryParameter("start", Integer.toString(start));
        urlBuilder.addQueryParameter("end", Integer.toString(start+length));
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get().build();
        return Arrays.asList(execute(request, Member[].class));
    }
}
