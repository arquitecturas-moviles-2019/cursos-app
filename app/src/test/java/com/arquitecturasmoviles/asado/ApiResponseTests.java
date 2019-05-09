package com.arquitecturasmoviles.asado;

import static org.junit.Assert.assertEquals;

public class ApiResponseTests {
//    private static RemoteApi remoteApi;
//    private static RemoteApiTest remoteApiTest;
//    private boolean testSuccess;
//    private CountDownLatch countDownLatch;
//
//    @BeforeClass
//    public static void init() {
//        remoteApi = RetrofitClientInstance.getRetrofitInstance().create(RemoteApi.class);
//        remoteApiTest = RetrofitClientInstance.getRetrofitInstance().create(RemoteApiTest.class);
//
//    }
//
//    @Before
//    public void setup() {
//        testSuccess = false;
//        countDownLatch = new CountDownLatch(1);
//    }
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

//    @Test
//    public void testInscriptionService() throws InterruptedException {
//        Call<List<Curso>> inscriptionCall = remoteApi.getAllCourses();
//        inscriptionCall.enqueue(new Callback<List<Curso>>() {
//            @Override
//            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
//                if (response.code() == 200) {
//                    testSuccess = true;
//                } else {
//                    testSuccess = false;
//                }
//                countDownLatch.countDown();
//            }
//
//            @Override
//            public void onFailure(Call<List<Curso>> call, Throwable t) {
//                testSuccess = false;
//                countDownLatch.countDown();
//            }
//        });
//        countDownLatch.await();
//        assertEquals(true, testSuccess);
//    }

//

}


