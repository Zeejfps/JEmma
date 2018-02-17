package edu.tntech.jemma.methods.members;

import edu.tntech.jemma.JEmma;
import edu.tntech.jemma.models.Member;

import java.util.List;

public class GetAllMembers extends MembersApiMethod<List<Member>> {

    private int start;
    private boolean includeDeleted;

    public GetAllMembers(JEmma jemma) {
        super(jemma);
        this.start = 0;
    }

    public GetAllMembers from(int index) {
        this.start = index;
        return this;
    }

    public GetAllMembers includeDeleted() {
        urlBuilder.addQueryParameter("deleted", "true");
        this.includeDeleted = true;
        return this;
    }

    @Override
    public List<Member> execute() {
        GetMemberCount getCount = jemma.members().count();
        if (includeDeleted)
            getCount.includeDeleted();
        int count = getCount.execute();

        GetMembers getMembers = jemma.members().list(count-start).from(start);
        if (includeDeleted)
            getMembers.includeDeleted();
        return getMembers.execute();
    }
}
