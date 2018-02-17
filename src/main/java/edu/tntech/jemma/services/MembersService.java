package edu.tntech.jemma.services;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import edu.tntech.jemma.methods.members.PostMembers;
import edu.tntech.jemma.models.Member;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MembersService {

    @GET("members")
    Call<List<Member>> fetchAllMembers(@Nullable @Query("start") Integer start,
                                       @Nullable @Query("end") Integer end,
                                       @Nullable @Query("deleted") Boolean deleted);

    @GET("members/{id}")
    Call<Member> fetchMemberById(@Path("id") long memberID,
                             @Nullable @Query("deleted") Boolean deleted);


    @GET("members/email/{email}")
    Call<Member> fetchMemberByEmail(@NotNull @Path("email") String email,
                             @Nullable @Query("deleted") Boolean deleted);


    @POST("members/add")
    Call<Integer> addOrUpdate(@Body Member.Builder member);


    @POST("members")
    Call<PostMembers.Response> addOrUpdate(@Body PostMembers members);
}
