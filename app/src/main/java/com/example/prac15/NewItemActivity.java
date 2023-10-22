package com.example.prac15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewItemActivity extends AppCompatActivity {
    EditText name;
    EditText link;
    EditText description;
    EditText fio;
    EditText score;
    AppCompatButton save;
    Anime newItem;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        name = findViewById(R.id.edit_name);
        link = findViewById(R.id.edit_link);
        description = findViewById(R.id.edit_description);
        fio = findViewById(R.id.edit_fio);
        score = findViewById(R.id.edit_score);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newItem = new Anime(name.getText().toString(), description.getText().toString(), link.getText().toString(), fio.getText().toString(), Integer.parseInt(score.getText().toString()));
                Intent intent=new Intent(NewItemActivity.this, MainActivity.class);
                //String s = new Gson().toJson(newItem);
                //intent.putExtra("item", s);
                apiInterface = ServiceBuilder.requestBuilder().create(ApiInterface.class);
                Call<Anime> currentItem = apiInterface.addItem(newItem);
                currentItem.enqueue(new Callback<Anime>() {
                    @Override
                    public void onResponse(Call<Anime> call, Response<Anime> response) {
                        if (response.isSuccessful()){

                        }
                    }

                    @Override
                    public void onFailure(Call<Anime> call, Throwable t) {

                    }
                });
                NewItemActivity.this.startActivity(intent);
            }
        });
    }
}