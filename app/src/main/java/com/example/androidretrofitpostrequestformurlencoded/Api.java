package com.example.androidretrofitpostrequestformurlencoded;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @POST("posts")
    Call<Post>createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post>createPost(
            @Field("userId")int userId,
            @Field("title")String title,
            @Field("body")String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post>createPost(@FieldMap Map<String,String> field);
}
