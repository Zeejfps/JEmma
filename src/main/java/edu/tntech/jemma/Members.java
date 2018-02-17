package edu.tntech.jemma;

import com.sun.istack.internal.NotNull;
import edu.tntech.jemma.methods.members.GetMemberByEmail;
import edu.tntech.jemma.methods.members.GetMemberByID;
import edu.tntech.jemma.methods.members.GetMembers;
import edu.tntech.jemma.methods.members.PostMembers;
import edu.tntech.jemma.services.MembersService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Members {

    private final MembersService service;

    Members(MembersService service) {
        this.service = service;
    }

    public Factory create(String email) {
        return new Factory(email);
    }

    public GetMembers listAll() {
        return new GetMembers(service);
    }

    public GetMembers list(int length) {
        return new GetMembers(service, length);
    }

    public GetMemberByID get(long memberID) {
        if (memberID < 0)
            throw new IllegalArgumentException("'memberID' can not be negative");
        return new GetMemberByID(service, memberID);
    }

    public GetMemberByEmail get(@NotNull String memberEmail) {
        if (memberEmail == null || memberEmail.isEmpty())
            throw new IllegalArgumentException("'memberEmail' can not be null or empty");
        return new GetMemberByEmail(service, memberEmail);
    }

    public PostMembers save(List<Factory> members) {
        List<HashMap<String, String>> membersToAdd = new ArrayList<>();
        for (Factory member : members) {
            membersToAdd.add(member.toMap());
        }
        return new PostMembers(service, membersToAdd);
    }

    public class Factory {

        private final HashMap<String, String> fields;

        public Factory(String email) {
            fields = new HashMap<>();
            fields.put("email", email);
        }

        public Factory addField(String field, String value) {
            fields.put(field, value);
            return this;
        }

        protected HashMap<String, String> toMap() {
            return fields;
        }

    }

}