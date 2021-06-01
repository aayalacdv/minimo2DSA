package com.example.minimo2.io;
import com.example.minimo2.responses.Medalla;
import com.example.minimo2.responses.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiService {

//    @GET("pokemon/")
//    Call<PokemonResponse> getPokemons();

//    @GET("characteristic/{id}/")
//    Call<SkillResponse> getSkill(@Path("id") String id);


    @GET("medals/")
    Call<ArrayList<Medalla>> getMedallas();


    @GET("user/{id}/")
    Call<Usuario> getUsuario(@Path("id") String id);
}
