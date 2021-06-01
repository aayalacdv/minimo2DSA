package com.example.minimo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.minimo2.io.MyApiAdapter;
import com.example.minimo2.responses.Medalla;
import com.example.minimo2.responses.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private MedalsListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (TextView)findViewById(R.id.username2);
        imageView = (ImageView)findViewById(R.id.imageView);
        recyclerView = (RecyclerView)findViewById(R.id.userMedals);
        listAdapter = new MedalsListAdapter(MainActivity2.this);
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        textView.setText(name);


        Call<Usuario> call = MyApiAdapter.getApiService().getUsuario(name);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario user = response.body();
                ArrayList<Medalla> medallas = user.getMedallaList();
                Log.d("usuario", "onResponse:  " + user.getMedallaList());
                Glide.with(getApplicationContext()).load(user.getImgUrl()).into(imageView);
                loadData(medallas);
                setAdapter();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast errorToast = Toast.makeText(getApplicationContext(), "Error, pls chech your internet connection and try again!", Toast.LENGTH_SHORT);
                errorToast.show();

            }
        });


    }

    private void loadData(ArrayList<Medalla> medallas){
        this.listAdapter.setMedallas(medallas);
    }


    private void setAdapter(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        this.recyclerView.setLayoutManager(manager);
        this.recyclerView.setAdapter(this.listAdapter);
    }

}