package com.example.prac15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardActivity extends AppCompatActivity {
    ApiInterface apiInterface;

    String name;
    String img;
    String fio;
    String detail;
    int score;

    ImageView cover;
    TextView title;
    TextView authorName;
    TextView scoreValue;
    TextView description;
    AppCompatButton del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        cover = findViewById(R.id.cover);
        title = findViewById(R.id.title);
        authorName = findViewById(R.id.author_name);
        scoreValue = findViewById(R.id.score_value);
        description = findViewById(R.id.description);
        del = findViewById(R.id.delete);

        Bundle extras = getIntent().getExtras();
        /*if (extras.containsKey("item")) {
            Log.d("newitem", "yes");
            String s = extras.getString("item");
            Anime anim = new Gson().fromJson(s, Anime.class);
            name = anim.getName();
            title.setText(name);
            img = anim.getImg();
            Picasso.with(CardActivity.this).load(img).into(cover);
            fio = anim.getFio();
            authorName.setText(fio);
            score = anim.getScore();
            scoreValue.setText(Integer.toString(score));
            detail = anim.getDetail();
            description.setText(detail);
        } */
        int currentId = extras.getInt("id");
        apiInterface = ServiceBuilder.requestBuilder().create(ApiInterface.class);
        Call<Anime> currentItem = apiInterface.getItem(currentId);
        currentItem.enqueue(new Callback<Anime>() {
            @Override
            public void onResponse(Call<Anime> call, Response<Anime> response) {
                if (response.isSuccessful()){
                    Anime anime = response.body();
                    if (anime != null) {
                        name = anime.getName();
                        title.setText(name);
                        img = anime.getImg();
                        Picasso.with(CardActivity.this).load(img).into(cover);
                        fio = anime.getFio();
                        authorName.setText(fio);
                        score = anime.getScore();
                        scoreValue.setText(Integer.toString(score));
                        detail = anime.getDetail();
                        description.setText(detail);
                    }
                }
            }

            @Override
            public void onFailure(Call<Anime> call, Throwable t) {

            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Anime> delItem = apiInterface.deleteItem(currentId);
                delItem.enqueue(new Callback<Anime>() {
                    @Override
                    public void onResponse(Call<Anime> call, Response<Anime> response) {
                        if (response.isSuccessful()){

                        }
                    }

                    @Override
                    public void onFailure(Call<Anime> call, Throwable t) {

                    }
                });
                Intent intent=new Intent(CardActivity.this, MainActivity.class);
                CardActivity.this.startActivity(intent);
            }
        });
    }
}