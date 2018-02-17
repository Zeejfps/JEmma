package edu.tntech.jemma.methods.members;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import edu.tntech.jemma.JEmma;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.List;

public class PostMembers extends MembersApiMethod<Long>{

    private JsonObject jsonObject;

    public PostMembers(JEmma jemma, List<HashMap<String, String>> members) {
        super(jemma);
        this.jsonObject = new JsonObject();
        jsonObject.add("members", jemma.getGson().toJsonTree(members));
    }

    public PostMembers automateFieldChanges() {
        jsonObject.addProperty("automate_field_changes", true);
        return this;
    }

    public PostMembers setSourceFilename(String filename) {
        jsonObject.addProperty("source_filename", filename);
        return this;
    }

    public PostMembers setAddOnly() {
        jsonObject.addProperty("add_only", true);
        return this;
    }

    public PostMembers addToGroups(int[] groupIds){
        jsonObject.add("group_ids", jemma.getGson().toJsonTree(groupIds));
        return this;
    }

    public Long execute() {
        String json = jsonObject.toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .post(body).build();
        return execute(request, Response.class).getImportID();
    }

    private static class Response {
        @SerializedName("import_id")
        private Long importID;
        Long getImportID() {
            return importID;
        }
    }

}
