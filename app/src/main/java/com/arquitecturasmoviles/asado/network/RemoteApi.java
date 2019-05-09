package com.arquitecturasmoviles.asado.network;

import com.arquitecturasmoviles.asado.model.Curso;
import com.arquitecturasmoviles.asado.model.InscripcionesResponse;
import com.arquitecturasmoviles.asado.model.LocalidadesResponse;
import com.arquitecturasmoviles.asado.model.LoginBody;
import com.arquitecturasmoviles.asado.model.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RemoteApi {

    @POST("usuarios/login")
    Call<LoginResponse> login(@Body LoginBody loginBody);

    @GET("cursos/todos")
    Call<List<Curso>> getAllCourses();

    @GET("localidades/todas")
    Call<LocalidadesResponse> getAllCities();

    @GET("inscripciones/todos")
    Call<InscripcionesResponse> getAllInscriptions();
}
