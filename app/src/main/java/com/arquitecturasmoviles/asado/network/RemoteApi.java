package com.arquitecturasmoviles.asado.network;

import android.util.Log;

import com.arquitecturasmoviles.asado.model.LoginBody;
import com.arquitecturasmoviles.asado.model.LoginResponse;
import com.arquitecturasmoviles.asado.model.RegisterBody;
import com.arquitecturasmoviles.asado.model.RegisterResponse;
import com.arquitecturasmoviles.asado.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
