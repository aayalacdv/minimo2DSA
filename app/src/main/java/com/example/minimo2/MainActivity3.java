package com.example.minimo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.minimo2.io.MyApiAdapter;
import com.example.minimo2.responses.Medalla;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity3 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MedalsListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerView = (RecyclerView)findViewById(R.id.recycler3);
        listAdapter = new MedalsListAdapter(getApplicationContext());

        Call<ArrayList<Medalla>> call = MyApiAdapter.getApiService().getMedallas();
        call.enqueue(new Callback<ArrayList<Medalla>>() {
            @Override
            public void onResponse(Call<ArrayList<Medalla>> call, Response<ArrayList<Medalla>> response) {
                    ArrayList<Medalla> medallas = response.body();
                    Log.d("xd",""+medallas);
                    loadData(medallas);
                    setAdapter();

            }

            @Override
            public void onFailure(Call<ArrayList<Medalla>> call, Throwable t) {

            }
        });

    }

    private void loadData(ArrayList<Medalla> medallas){
        this.listAdapter.setMedallas(medallas);
    }

    private void setAdapter(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setLayoutManager(manager);
        this.recyclerView.setAdapter(this.listAdapter);
    }




}