package com.arquitecturasmoviles.asado.TestsUtilities;

import com.arquitecturasmoviles.asado.model.Curso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RemoteApiTest {

    @GET("eventos/todos")
    Call<EventosResponse> getAllEvents();

    @GET("cursos/todos")
    Call<Curso> getAllCourses();
}
