package com.example.advancedandriod_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConcept extends AppCompatActivity {
    TextView textViewResult;
    long cachesize = 10 * 1024 * 1024;//10MB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_concept);
        textViewResult = findViewById(R.id.text_view_result);
        // getConcept();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public void getConcept(View view) {
        textViewResult.setText("");
        //File file=new File(getD)
        final Cache cache = new Cache(new File(getCacheDir(), "MYFILE"), cachesize);

        //   Cache cache1=new Cache(new File(path,"myfile"),cachesize); you have to give the path.
        // you can do this by if (NO INTERNET) and fetch data from cache. or based on maxage/stale.
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!isNetworkConnected()) {


                    CacheControl cacheControl = new CacheControl.Builder().maxStale(7, TimeUnit.DAYS).build();
                    request = request.newBuilder().cacheControl(cacheControl).removeHeader("Pragma")
                            .build();
                    //  return chain.proceed(request);
                }
                //use it for 7days.
                okhttp3.Response response = chain.proceed(request);

                return response;
            }
        };
        Interceptor interceptor1 = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                okhttp3.Response response = chain.proceed(request);



                    CacheControl cacheControls = new CacheControl.Builder().maxAge(10, TimeUnit.SECONDS).build();
                    // if user fetches same data within less tha 5 minutes ,data will be retrived from cahe.
                    Log.i("data", "STORED"+response.cacheControl().noStore());
                    return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", cacheControls.toString()).
                            build();



            }
        };


        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(30, TimeUnit.SECONDS).cache(cache).
                addNetworkInterceptor(interceptor1).addInterceptor(interceptor).
                build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        PostInterface jsonPlaceHolderApi = retrofit.create(PostInterface.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse
                    (Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                Log.i("data", response.raw().cacheControl().toString());
               /* if (response.raw().cacheResponse() != null) {
                    Log.i("response", "FROM CACHE");
                } else*/
                if (response.raw().cacheResponse() != null && response.raw().networkResponse() == null) {
                    Log.i("response", "FROM CACHE");
                } else if (response.raw().networkResponse() != null) {
                    Log.i("response", "FROM NETWORK");

                }
                // int maxstale=response.headers().
                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void pathConcept(View view) {
        textViewResult.setText("");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostInterface jsonPlaceHolderApi = retrofit.create(PostInterface.class);
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3);
        /// u can use @URL ALso
        // Call<List<Comment>> call = jsonPlaceHolderApi.getComments("https://jsonplaceholder.typicode.com/posts/3/comments");
// the base url in above line will override the original url if they differ.

        // and instead of whole url you can pass endpoint url.


        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Comment> comments = response.body();
                for (Comment comment : comments) {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void queryConcept(View view) {
        textViewResult.setText("");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostInterface jsonPlaceHolderApi = retrofit.create(PostInterface.class);
        // Call<List<Post>> call = jsonPlaceHolderApi.getPosts(1,4,"id",null);
        //  Call<List<Post>> call = jsonPlaceHolderApi.getPosts(new Integer[]{1,2,3,4},null,"desc");
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void postConcept(View view) {
        textViewResult.setText("");
        Post post = new Post(23, "New Title", "New Text");
        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "25");
        fields.put("title", "SAI KALYAN");
        fields.put("body", "ANDROID");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostInterface jsonPlaceHolderApi = retrofit.create(PostInterface.class);

        //    Call<Post> call = jsonPlaceHolderApi.createPost(25,"sai kalyan","android");
        // Call<Post> call = jsonPlaceHolderApi.createPost(post);

        Call<Post> call = jsonPlaceHolderApi.createPost(fields);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                Post postResponse = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";  // if response http code is 201,200 it means task is success.
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";
                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void updateConcept(View view) {
        Post post = new Post(12, null, "New Text");

// to see how the procees is happening in logcat we use okhttp httploggginginterceptor.
        Gson gson = new GsonBuilder().serializeNulls().create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        // CUSTOM HTTP LOGGER
        //HttpLoggingInterceptor logging = new HttpLoggingInterceptor( new Logger()
        //{
        //    @Override public void log(String message)
        //    {
        //        Timber.tag("OkHttp").d(message);
        //    }
        //});
        // custom interceptor,add before logging interceptor.
  /*      Interceptor interceptor=new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                Request.Builder builder=request.newBuilder().addHeader("Accept-Languge","EN").addHeader("key","value")
                        ;
                okhttp3.Response response=chain.proceed(builder.build());

                return response;
            }
        };*/
        PostInterface jsonPlaceHolderApi = retrofit.create(PostInterface.class);

        Call<Post> call = jsonPlaceHolderApi.putPost(5, post);

        // Call<Post> call = jsonPlaceHolderApi.patchPost(5, post); // note here gson will ignore null,means in ouput tittle willbe same as orginal.
        // Gson gson = new GsonBuilder().serializeNulls().create();
        // TO MAKE GSON DONOT IGNORE NULLS WESHOULD PASS ABOVE OJECT IN GsonConverterFactory.create(gson).
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                //  int maxstale=response.g
                Post postResponse = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";
                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void deleteConcept(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostInterface jsonPlaceHolderApi = retrofit.create(PostInterface.class);
        Call<Void> call = jsonPlaceHolderApi.deletePost(5);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewResult.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}

