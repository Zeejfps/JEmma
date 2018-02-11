package edu.tntech.jemma.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Map;

public class Member {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("confirmed_opt_in")
    @Expose
    private Object confirmedOptIn;
    @SerializedName("account_id")
    @Expose
    private int accountId;
    @SerializedName("fields")
    @Expose
    private Map<String, String> fields;
    @SerializedName("member_id")
    @Expose
    private long memberId;
    @SerializedName("last_modified_at")
    @Expose
    private LocalDateTime lastModifiedAt;
    @SerializedName("member_status_id")
    @Expose
    private String memberStatusId;
    @SerializedName("plaintext_preferred")
    @Expose
    private boolean plaintextPreferred;
    @SerializedName("email_error")
    @Expose
    private Object emailError;
    @SerializedName("member_since")
    @Expose
    private String memberSince;
    @SerializedName("bounce_count")
    @Expose
    private int bounceCount;
    @SerializedName("deleted_at")
    @Expose
    private LocalDateTime deletedAt;
    @SerializedName("email")
    @Expose
    private String email;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getConfirmedOptIn() {
        return confirmedOptIn;
    }

    public void setConfirmedOptIn(Object confirmedOptIn) {
        this.confirmedOptIn = confirmedOptIn;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getMemberStatusId() {
        return memberStatusId;
    }

    public void setMemberStatusId(String memberStatusId) {
        this.memberStatusId = memberStatusId;
    }

    public boolean isPlaintextPreferred() {
        return plaintextPreferred;
    }

    public void setPlaintextPreferred(boolean plaintextPreferred) {
        this.plaintextPreferred = plaintextPreferred;
    }

    public Object getEmailError() {
        return emailError;
    }

    public void setEmailError(Object emailError) {
        this.emailError = emailError;
    }

    public String getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(String memberSince) {
        this.memberSince = memberSince;
    }

    public int getBounceCount() {
        return bounceCount;
    }

    public void setBounceCount(int bounceCount) {
        this.bounceCount = bounceCount;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}