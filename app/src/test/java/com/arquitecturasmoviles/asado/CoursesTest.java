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

public class CoursesTest {
    //    private static RemoteApi remoteApi;
    private static RemoteApiTest remoteApiTest;
    private boolean testSuccess;
    private CountDownLatch countDownLatch;

    @BeforeClass
    public static void init() {
//        remoteApi = RetrofitClientInstance.getRetrofitInstance().create(RemoteApi.class);
        remoteApiTest = RetrofitClientInstance.getRetrofitInstance().create(RemoteApiTest.class);

    }

    @Before
    public void setup() {
        testSuccess = false;
        countDownLatch = new CountDownLatch(1);
    }

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
}