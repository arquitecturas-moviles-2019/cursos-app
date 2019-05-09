package com.arquitecturasmoviles.asado;

import com.arquitecturasmoviles.asado.model.LocalidadesResponse;
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

public class CitiesTest {
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
    public void testGetAllCitiesService() throws InterruptedException {
        Call<LocalidadesResponse> citiesCall = remoteApi.getAllCities();
        citiesCall.enqueue(new Callback<LocalidadesResponse>() {
            @Override
            public void onResponse(Call<LocalidadesResponse> call, Response<LocalidadesResponse> response) {
                testSuccess = (response.code() == 200);
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Call<LocalidadesResponse> call, Throwable t) {
                testSuccess = false;
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        assertEquals(true, testSuccess);
    }
}
