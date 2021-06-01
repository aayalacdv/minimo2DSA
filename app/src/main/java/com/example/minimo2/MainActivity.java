package com.example.minimo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.minimo2.io.MyApiAdapter;
import com.example.minimo2.responses.Medalla;
import com.example.minimo2.responses.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.example.minimo2.MESSAGE";
    private Button userbtn;
    private Button medalsbtn;
    private EditText editText;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.username);
        userbtn = (Button)findViewById(R.id.usuariobtn);
        medalsbtn = (Button)findViewById(R.id.medalsbtn);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);

        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                intent.putExtra(EXTRA_NAME, name);

                //hacer  consulta a api y ver si existe el usuario

                Call<Usuario> call = MyApiAdapter.getApiService().getUsuario(name);
                progressBar.setVisibility(View.VISIBLE);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if(response.code() != 404){
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(intent);
                        }
                        else {
                            Toast errorToast = Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT);
                            errorToast.show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast errorToast = Toast.makeText(getApplicationContext(), "Error, pls chech your internet connection and try again!", Toast.LENGTH_SHORT);
                        errorToast.show();

                    }
                });

            }
        });


        medalsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MainActivity3.class);
                Call<ArrayList<Medalla>> call2 = MyApiAdapter.getApiService().getMedallas();
                progressBar.setVisibility(View.VISIBLE);
                call2.enqueue(new Callback<ArrayList<Medalla>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Medalla>> call, Response<ArrayList<Medalla>> response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        startActivity(intent2);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Medalla>> call, Throwable t) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast errorToast = Toast.makeText(getApplicationContext(), "Error, pls chech your internet connection and try again!", Toast.LENGTH_SHORT);
                        errorToast.show();
                    }
                });
            }
        });


    }
}