package com.arquitecturasmoviles.asado;

import com.arquitecturasmoviles.asado.TestsUtilities.RemoteApiTest;
import com.arquitecturasmoviles.asado.model.Curso;
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

public class ApiResponseTests {
    private static RemoteApi remoteApi;
    private static RemoteApiTest remoteApiTest;
    private boolean testSuccess;
    private CountDownLatch countDownLatch;

    @BeforeClass
    public static void init() {
        remoteApi = RetrofitClientInstance.getRetrofitInstance().create(RemoteApi.class);
        remoteApiTest = RetrofitClientInstance.getRetrofitInstance().create(RemoteApiTest.class);

    }

    @Before
    public void setup() {
        testSuccess = false;
        countDownLatch = new CountDownLatch(1);
    }
//
//    @Test
//    public void testGetAllCities() throws InterruptedException {
//        Call<LocalidadesResponse> citiesCall = remoteApi.getAllCities();
//        citiesCall.enqueue(new Callback<LocalidadesResponse>() {
//            @Override
//            public void onResponse(Call<LocalidadesResponse> call, Response<LocalidadesResponse> response) {
//                testSuccess = (response.code() == 200);
//                countDownLatch.countDown();
//            }
//
//            @Override
//            public void onFailure(Call<LocalidadesResponse> call, Throwable t) {
//                testSuccess = false;
//                countDownLatch.countDown();
//            }
//        });
//        countDownLatch.await();
//        assertEquals(true, testSuccess);
//    }
//
//    @Test
//    public void testGetAllInscriptions() throws InterruptedException {
//        Call<InscripcionesResponse> inscriptionsCall = remoteApi.getAllInscriptions();
//        inscriptionsCall.enqueue(new Callback<InscripcionesResponse>() {
//            @Override
//            public void onResponse(Call<InscripcionesResponse> call, Response<InscripcionesResponse> response) {
//                testSuccess = (response.code() == 200);
//                countDownLatch.countDown();
//            }
//
//            @Override
//            public void onFailure(Call<InscripcionesResponse> call, Throwable t) {
//                testSuccess = false;
//                countDownLatch.countDown();
//            }
//        });
//        countDownLatch.await();
//        assertEquals(true, testSuccess);
//    }

    @Test
    public void testGetAllCoursesService() throws InterruptedException {
        Call<Curso> inscriptionCall = remoteApiTest.getAllCourses();
        inscriptionCall.enqueue(new Callback<Curso>() {
            @Override
            public void onResponse(Call<Curso> call, Response<Curso> response) {
                if (response.code() == 200) {
                    testSuccess = true;
                } else {
                    testSuccess = false;
                }
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Call<Curso> call, Throwable t) {
                testSuccess = false;
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        assertEquals(true, testSuccess);
    }

//

}


