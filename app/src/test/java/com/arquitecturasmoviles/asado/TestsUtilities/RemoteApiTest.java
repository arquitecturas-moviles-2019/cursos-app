package com.arquitecturasmoviles.asado.TestsUtilities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RemoteApiTest {

    @GET("eventos/todos")
    Call<EventosResponse> getAllEvents();
}
