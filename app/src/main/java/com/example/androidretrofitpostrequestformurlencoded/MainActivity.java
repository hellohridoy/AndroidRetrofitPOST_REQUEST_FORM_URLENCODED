package com.example.androidretrofitpostrequestformurlencoded;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView textViewResult;
private Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api= retrofit.create(Api.class);
        createPost();
    }

    private void createPost() {

        Post post = new Post(31,"New Title","New Text");

        Map<String,String> map= new HashMap<>();
        map.put("userId","25");
        map.put("Title","New Title");
        map.put("Text","new Text");
        Call<Post>call=api.createPost(map);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Post postresponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + post.getId() + "\n";
                content += "User ID: " + post.getUserId() + "\n";
                content += "Title: " + post.getTitle() + "\n";
                content += "Text: " + post.getText() + "\n\n";
                textViewResult.setText(content);

            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}