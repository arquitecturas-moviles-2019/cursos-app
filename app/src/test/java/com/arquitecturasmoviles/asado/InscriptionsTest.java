package com.arquitecturasmoviles.asado;

import com.arquitecturasmoviles.asado.model.InscripcionesResponse;
import com.arquitecturasmoviles.asado.network.RemoteApi;
import com.arquitecturasmoviles.asado.network.RetrofitClientInstance;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

public class InscriptionsTest {
    private static RemoteApi remoteApi;
    private boolean testSuccess;
    private CountDownLatch countDownLatch;

    @BeforeClass
    public static void init() {
        remoteApi = RetrofitClientInstance.getRetrofitInstance().create(RemoteApi.class);
    }

    @Before
    public void setup() {
        testSuccess = false;
        countDownLatch = new CountDownLatch(1);
    }
    @Test
    public void testGetAllInscriptionsService() throws InterruptedException {
        Call<InscripcionesResponse> inscriptionsCall = remoteApi.getAllInscriptions();
        inscriptionsCall.enqueue(new Callback<InscripcionesResponse>() {
            @Override
            public void onResponse(Call<InscripcionesResponse> call, Response<InscripcionesResponse> response) {
                testSuccess = (response.code() == 200);
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Call<InscripcionesResponse> call, Throwable t) {
                testSuccess = false;
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        assertEquals(true, testSuccess);
    }
}
