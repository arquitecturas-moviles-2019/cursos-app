package com.arquitecturasmoviles.asado;

import android.util.Log;

import com.arquitecturasmoviles.asado.model.LoginBody;
import com.arquitecturasmoviles.asado.model.LoginResponse;
import com.arquitecturasmoviles.asado.model.User;
import com.arquitecturasmoviles.asado.network.RemoteApi;
import com.arquitecturasmoviles.asado.network.RetrofitClientInstance;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

public class LoginTest {
    private static RemoteApi remoteApi;
    private boolean testSuccess;
    private CountDownLatch countDownLatch;
    private String email = "jack@correo.com";
    private String password = "123456";

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
    public void testServiceLogin() throws InterruptedException {
        Call<LoginResponse> loginCall = remoteApi.login(email, password);
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() == 200) {
                    testSuccess = true;
                } else {
                    testSuccess = false;
                }
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                testSuccess = false;
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        assertEquals(true, testSuccess);
    }

    @Test
    public void testUserLogin() throws InterruptedException {
        Call<LoginResponse> loginCall = remoteApi.login(email, password);
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() == 200) {
                    try {
                        LoginResponse responseBody = response.body();
                        Boolean error = responseBody.getError();
                        if (error.equals(false)) {
                            testSuccess = true;
                        } else {
                            testSuccess = false;
                        }
                    } catch (Exception e) {
                        testSuccess = false;
                    }
                } else {
                    testSuccess = false;
                }
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                testSuccess = false;
                countDownLatch.countDown();
            }

        });
        countDownLatch.await();
        assertEquals(true, testSuccess);
    }


    @Test
    public void testUserLoginBody() throws InterruptedException {
        Call<LoginResponse> loginCall = remoteApi.login(email, password);
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() == 200) {
                    try {
                        LoginResponse responseBody = response.body();
                        if (
                                responseBody.getMensaje().equals("Exito") &&
                                        responseBody.getError().equals(false)
                        ) {
                            testSuccess = true;
                        } else {
                            testSuccess = false;
                        }

                    } catch (Exception e) {
                        testSuccess = false;
                    }
                } else {
                    testSuccess = false;
                }
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                testSuccess = false;
                countDownLatch.countDown();
            }

        });
        countDownLatch.await();
        assertEquals(true, testSuccess);


    }
}