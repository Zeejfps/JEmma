package edu.tntech.jemma.methods.members;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import edu.tntech.jemma.JEmma;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.*;

public class PostMember extends MembersApiMethod<Long> {

    @Expose@SerializedName("email")
    private String email;

    @Expose@SerializedName("fields")
    private HashMap<String, String> fields;

    @Expose@SerializedName("group_ids")
    private Set<Integer> groupIds;

    @Expose@SerializedName("field_triggers")
    private Boolean fireFieldTriggers;

    public PostMember(JEmma jemma, String email) {
        super(jemma);
        this.email = email;
        urlBuilder.addPathSegment("add");
    }

    public PostMember addField(String field, String value) {
        if (fields == null)
            fields = new HashMap<>();
        fields.put(field, value);
        return this;
    }

    public PostMember addToGroup(int groupID) {
        if (groupIds == null)
            groupIds = new HashSet<>();
        groupIds.add(groupID);
        return this;
    }

    public PostMember addToGroups(int[] groupIds) {
        if (this.groupIds == null)
            this.groupIds = new HashSet<>();
        for (int i : groupIds) {
            this.groupIds.add(i);
        }
        return this;
    }

    public PostMember fireFieldTriggers() {
        fireFieldTriggers = true;
        return this;
    }

    @Override
    public Long execute() {
        String json = jemma.getGson().toJson(this);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .post(body)
                .build();
        return execute(request, Response.class).memberID;
    }

    private static class Response {
        @Expose@SerializedName("status")
        private String status;
        @Expose@SerializedName("member_id")
        private Long memberID;
    }

}
