package edu.tntech.jemma.methods.members;

import edu.tntech.jemma.JEmma;
import edu.tntech.jemma.exceptions.ApiException;
import edu.tntech.jemma.models.Member;
import okhttp3.Request;

import java.util.Optional;

public class GetMemberByID extends MembersApiMethod<Optional<Member>> {

    public GetMemberByID(JEmma jemma, long memberID) {
        super(jemma);
        urlBuilder.addPathSegment(Long.toString(memberID));
    }

    public GetMemberByID includeDeleted() {
        urlBuilder.addQueryParameter("deleted", "true");
        return this;
    }

    public Optional<Member> execute() {
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get().build();
        try {
            Member member = execute(request, Member.class);
            return Optional.of(member);
        } catch (ApiException e) {
            System.out.println(e);
            return Optional.empty();
        }
    }

}
