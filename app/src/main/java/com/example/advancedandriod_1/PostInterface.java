package com.example.advancedandriod_1;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface PostInterface {
    @GET("posts")
        // here instead of posts u can write whole url,so the base url here will override the original.(if they differ)
    Call<List<Post>> getPosts();

    @GET("posts/{id}/comments")
        // in these url id can be given dynamically using @path.
    Call<List<Comment>> getComments(@Path("id") int postId); // we can pass multiple paths also.

    @GET
    Call<List<Comment>> getComments(@Url String url);


    // note in uRL if there are parameters after ? they are queries.
    // example /posts/?userId=1&_sort=id & _order=desc. In these userId,sort,order are  Query parameters.


    /*  @GET("posts")
      Call<List<Post>> getPosts(
              @Query("userId") Integer userId,
              @Query("userId") Integer userId2,
              @Query("_sort") String sort,
              @Query("_order") String order
      );*/
    // for multiple input for single query.
    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId,  // we are using Integer class because to support null input.
            @Query("_sort") String sort,
            @Query("_order") String order
    );

// you can do the above using below code also.but the drawback u cant use a key as duplicate.like userId

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);

    // **************************** NOTE **************************
    /* if base url is something like .com/x/
       then @Get should be like @Get("posts") it will append to base
       but if you pass like this-> @Get("/posts") ,it will remove x and append to .com/posts
       so thats the reason retrofit forces to write url that ends with /.
     */

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text  // key is not text beacuse on server it is called as body
    );

    @FormUrlEncoded
    // means data will be send as url type.  like useeId=23&tittle=New%20Tittle&body=New%20Text. it removes empty spaces ,special characters.
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    @PUT("posts/{id}")
        //PUT MEANS COMPLTEE UPDATE /REPLACEMENT OF OBJECT.
    Call<Post> putPost(@Path("id") int id, @Body Post post);

    @PATCH("posts/{id}")
        // PATCH MEANS PARTIAL UPDATE
    Call<Post> patchPost(@Path("id") int id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
