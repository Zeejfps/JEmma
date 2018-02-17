package edu.tntech.jemma.methods.members;

import edu.tntech.jemma.JEmma;
import edu.tntech.jemma.models.Member;
import okhttp3.Request;

import java.util.Optional;

public class GetMemberByEmail extends MembersApiMethod<Optional<Member>> {

    public GetMemberByEmail(JEmma jemma, String email) {
        super(jemma);
        urlBuilder.addPathSegment("email");
        urlBuilder.addPathSegment(email);
    }

    public GetMemberByEmail includeDeleted() {
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
        } catch(Exception e) {
            return Optional.empty();
        }
    }

}
