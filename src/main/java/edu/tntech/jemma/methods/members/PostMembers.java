package edu.tntech.jemma.methods.members;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import edu.tntech.jemma.JEmma;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostMembers extends MembersApiMethod<Long>{

    @Expose@SerializedName("members")
    private List<HashMap<String, String>> memebers;

    @Expose@SerializedName("automate_field_changes")
    private Boolean automateFieldChanges;

    @Expose@SerializedName("source_filename")
    private String sourceFilename;

    @Expose@SerializedName("add_only")
    private Boolean addOnly;

    @Expose@SerializedName("group_ids")
    private Set<Integer> groupIds;

    public PostMembers(JEmma jemma, List<HashMap<String, String>> members) {
        super(jemma);
        this.memebers = members;
    }

    public PostMembers automateFieldChanges() {
        this.automateFieldChanges = true;
        return this;
    }

    public PostMembers setSourceFilename(String filename) {
        this.sourceFilename = filename;
        return this;
    }

    public PostMembers setAddOnly() {
        this.addOnly = true;
        return this;
    }

    public PostMembers addToGroups(int[] groupIds){
        if (this.groupIds == null)
            this.groupIds = new HashSet<>();
        for (int i : groupIds) {
            this.groupIds.add(i);
        }
        return this;
    }

    public PostMembers addToGroup(int groupID) {
        if (groupIds == null)
            groupIds = new HashSet<>();
        groupIds.add(groupID);
        return this;
    }

    public Long execute() {
        String json = jemma.getGson().toJson(this);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .post(body).build();
        return execute(request, Response.class).importID;
    }

    private static class Response {
        @Expose@SerializedName("import_id")
        private Long importID;
    }

}
