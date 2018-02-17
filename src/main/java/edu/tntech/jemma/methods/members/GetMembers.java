package edu.tntech.jemma.methods.members;

import edu.tntech.jemma.models.Member;
import edu.tntech.jemma.services.MembersService;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class GetMembers {

    private Integer start, end;
    private Boolean showDeleted;
    private int length;

    private final MembersService service;

    public GetMembers(MembersService service) {
        this.service = service;
        length = 0;
    }

    public GetMembers(MembersService service, int length) {
        if (length < 0)
            throw new IllegalArgumentException("'length' can not be negative");
        this.service = service;
        this.start = 0;
        this.length = length;
    }

    public GetMembers from(int start) {
        if (start < 0)
            throw new IllegalArgumentException("'start' can not be negative");
        this.start = start;
        return this;
    }

    public GetMembers includeDeleted() {
        showDeleted = true;
        return this;
    }

    public List<Member> execute() {
        try {
            if (length > 0)
                end = start + length;
            Response<List<Member>> response =
                    service.fetchAllMembers(start, end, showDeleted).execute();
            if (response.isSuccessful())
                return response.body();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return Collections.emptyList();
    }

}
