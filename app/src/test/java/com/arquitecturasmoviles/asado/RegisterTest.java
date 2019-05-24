package com.arquitecturasmoviles.asado;

import com.arquitecturasmoviles.asado.model.RegisterBody;
import com.arquitecturasmoviles.asado.model.RegisterResponse;
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

public class RegisterTest {

    private static RemoteApi remoteApi;
    private boolean testSuccess;
    private CountDownLatch countDownLatch;
    private String email = "hectorbarbosa@correo.com";
    private String password = "1234567";
    private String name = "Hector";
    private String surname = "Barbosa";
    private String passwordOK = "1234567";


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
    public void testServiceRegister() throws InterruptedException {
        final RegisterBody registerBody = new RegisterBody(name, surname, email, password, passwordOK);
        remoteApi.register(registerBody.getNombre(), registerBody.getApellido(), registerBody.getEmail(), registerBody.getContrasenia(), registerBody.getContraseniaConfirmacion()).enqueue(new Callback<RegisterResponse>() {

            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.code() == 200) {
                    testSuccess = true;
                } else {
                    testSuccess = false;
                }
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                testSuccess = false;
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        assertEquals(true, testSuccess);
    }

    @Test
    public void testServicesExistentUser() throws InterruptedException {
        final RegisterBody registerBody = new RegisterBody(name, surname, email, password, passwordOK);
        remoteApi.register(registerBody.getNombre(), registerBody.getApellido(), registerBody.getEmail(), registerBody.getContrasenia(), registerBody.getContraseniaConfirmacion()).enqueue(new Callback<RegisterResponse>() {

            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.code() == 200) {
                    try {
                        RegisterResponse responseBody = response.body();
                        Boolean error = responseBody.getError();
                        if (error.equals(true)) {
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
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                testSuccess = false;
                countDownLatch.countDown();
            }

        });
        countDownLatch.await();
        assertEquals(true, testSuccess);
    }

}