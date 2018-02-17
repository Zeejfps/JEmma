package edu.tntech.jemma.methods.members;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import edu.tntech.jemma.services.MembersService;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PostMembers {

    @SerializedName("members")
    private List<HashMap<String, String>> members;

    @SerializedName("automate_field_changes")
    private Boolean automateFieldChanges;

    @SerializedName("source_filename")
    private String sourceFilename;

    @SerializedName("add_only")
    private Boolean addOnly;

    @SerializedName("group_ids")
    private int[] groupIds;

    private transient final MembersService service;

    public PostMembers(MembersService service, List<HashMap<String, String>> members) {
        this.service = service;
        this.members = members;
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
        this.groupIds = groupIds;
        return this;
    }

    public int execute() {
        try {
            retrofit2.Response<Response> response = service.addOrUpdate(this).execute();
            Response data = response.body();
            if (response.isSuccessful() && data != null)
                return data.getImportID();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public class Response {

        @SerializedName("import_id")
        private int importID;

        public int getImportID() {
            return importID;
        }

    }

}
