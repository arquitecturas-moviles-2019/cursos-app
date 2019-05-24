package com.arquitecturasmoviles.asado.network;

import android.util.Log;

import com.arquitecturasmoviles.asado.model.Curso;
import com.arquitecturasmoviles.asado.model.EventosResponse;
import com.arquitecturasmoviles.asado.model.InscripcionesResponse;
import com.arquitecturasmoviles.asado.model.LocalidadesResponse;
import com.arquitecturasmoviles.asado.model.CursosResponse;
import com.arquitecturasmoviles.asado.model.LoginBody;
import com.arquitecturasmoviles.asado.model.LoginResponse;
import com.arquitecturasmoviles.asado.model.RegisterBody;
import com.arquitecturasmoviles.asado.model.RegisterResponse;
import com.arquitecturasmoviles.asado.model.User;
import com.arquitecturasmoviles.asado.model.UserInscriptionsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RemoteApi {

    @POST("usuarios/login")
    Call<LoginResponse> login(@Body LoginBody loginBody);

    @FormUrlEncoded
    @POST("usuarios/nuevo")
    Call<RegisterResponse> register(@Field("nombre") String nombre,
                                    @Field("apellido") String apellido,
                                    @Field("email") String email,
                                    @Field("contrasenia") String contrasenia,
                                    @Field("contraseniaConfirmacion") String contraseniaConfirmacion);

    @GET("cursos/todos")
    Call<CursosResponse> getAllCourses();

    @GET("inscripciones/{user_id}")
    Call<UserInscriptionsResponse> getCoursesByUserId(@Path("user_id") String user_id);

    @GET("eventos/todos")
    Call<EventosResponse> getAllEvents();

    @GET("localidades/todas")
    Call<LocalidadesResponse> getAllCities();

    @GET("inscripciones/todos")
    Call<InscripcionesResponse> getAllInscriptions();
}
