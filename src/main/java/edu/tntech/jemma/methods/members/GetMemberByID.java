package edu.tntech.jemma.methods.members;

import edu.tntech.jemma.models.Member;
import edu.tntech.jemma.services.MembersService;
import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;

public class GetMemberByID {

    private long memberID;

    private Boolean includeDeleted;

    private final MembersService service;

    public GetMemberByID(MembersService service, long memberID) {
        this.service = service;
        this.memberID = memberID;
    }

    public GetMemberByID includeDeleted() {
        includeDeleted = true;
        return this;
    }

    public Optional<Member> execute() {
        try {
            Response<Member> response = service.fetchMemberById(memberID, includeDeleted).execute();
            Member data = response.body();
            if (response.isSuccessful() && data != null)
                return Optional.of(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

}
