package com.am.framework.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequests {

    ApiRequests apiService = APIClient.getClient().create(ApiRequests.class);

    @GET("competitions/{id}/stages")
    Call<Void> getRequest(@Path("id") String competitionId,
                          @Query("season") String seasonId,
                          @Header("Signature") String signature,
                          @Header("Token") String Token);

    @POST("login")
    @FormUrlEncoded
    Call<Void> postRequst(@Field("ClientSecret") String secret,
                          @Header("Signature") String signature,
                          @Header("Token") String Token);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "user/{user_id}/favComps", hasBody = true)
    Call<Void> deleteRequest(@Path("user_id") String userId,
                             @Field("comp_id") String compId,
                             @Header("Signature") String signature,
                             @Header("Token") String token);

    @FormUrlEncoded
    @HTTP(method = "PUT", path = "comments/{comment_id}", hasBody = true)
    Call<Void> putRequest(@Path("comment_id") String comment_id,
                          @Field("new_text") String new_text,
                          @Field("page") String page,
                          @Header("Signature") String signature,
                          @Header("Token") String token);


}
