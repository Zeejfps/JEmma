package edu.tntech.jemma;

import com.sun.istack.internal.NotNull;
import edu.tntech.jemma.methods.members.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Members {

    private final JEmma jemma;

    Members(JEmma jemma) {
        this.jemma = jemma;
    }

    public Factory create(String email) {
        return new Factory(email);
    }

    public GetMemberCount count() {
        return new GetMemberCount(jemma);
    }

    public GetAllMembers listAll() {
        return new GetAllMembers(jemma);
    }

    public GetMembers list(int length) {
        return new GetMembers(jemma, length);
    }

    public GetMemberByID get(long memberID) {
        if (memberID < 0)
            throw new IllegalArgumentException("'memberID' can not be negative");
        return new GetMemberByID(jemma, memberID);
    }

    public GetMemberByEmail get(@NotNull String memberEmail) {
        if (memberEmail == null || memberEmail.isEmpty())
            throw new IllegalArgumentException("'memberEmail' can not be null or empty");
        return new GetMemberByEmail(jemma, memberEmail);
    }

    public PostMembers save(List<Factory> members) {
        List<HashMap<String, String>> membersToAdd = new ArrayList<>();
        for (Factory member : members) {
            membersToAdd.add(member.toMap());
        }
        return new PostMembers(jemma, membersToAdd);
    }

    public PostMember save(String email) {
        return new PostMember(jemma, email);
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