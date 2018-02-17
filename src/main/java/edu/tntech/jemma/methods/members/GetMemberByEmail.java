package edu.tntech.jemma.methods.members;

import edu.tntech.jemma.models.Member;
import edu.tntech.jemma.services.MembersService;
import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;

public class GetMemberByEmail {

    private String email;

    private Boolean includeDeleted;

    private final MembersService service;

    public GetMemberByEmail(MembersService service, String email) {
        this.service = service;
        this.email = email;
    }

    public GetMemberByEmail includeDeleted() {
        includeDeleted = true;
        return this;
    }

    public Optional<Member> execute() {
        try {
            Response<Member> response = service.fetchMemberByEmail(email, includeDeleted).execute();
            Member data = response.body();
            if (response.isSuccessful() && data != null)
                return Optional.of(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

}
