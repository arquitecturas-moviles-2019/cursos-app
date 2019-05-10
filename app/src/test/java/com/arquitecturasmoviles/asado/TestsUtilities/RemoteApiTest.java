package com.arquitecturasmoviles.asado.TestsUtilities;

import com.arquitecturasmoviles.asado.model.Curso;
import com.arquitecturasmoviles.asado.model.Evento;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RemoteApiTest {

    @GET("eventos/todos")
    Call<Evento> getAllEvents();

    @GET("cursos/todos")
    Call<Curso> getAllCourses();
}
