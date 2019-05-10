package com.arquitecturasmoviles.asado;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.arquitecturasmoviles.asado.model.Curso;
import com.arquitecturasmoviles.asado.model.CursosResponse;
import com.arquitecturasmoviles.asado.model.Evento;
import com.arquitecturasmoviles.asado.model.LoginBody;
import com.arquitecturasmoviles.asado.model.LoginResponse;
import com.arquitecturasmoviles.asado.network.RemoteApi;
import com.arquitecturasmoviles.asado.network.RetrofitClientInstance;

import com.arquitecturasmoviles.asado.model.Curso;
import com.arquitecturasmoviles.asado.model.Evento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CursosActivity extends AppCompatActivity {

    ListView listadoDondeSeVisualiza;
    Curso cursoAPasar;
    ArrayList<Curso> listadoCursosDelEvento = new ArrayList<>();

    private Retrofit mRestAdapter;
    private RemoteApi remoteApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_courses);

        remoteApi = RetrofitClientInstance.getRetrofitInstance().create(RemoteApi.class);

        int courseId = getIntent().getIntExtra("COURSE_ID", 0);

        Toast.makeText(this, "El id del curso es: " + courseId, Toast.LENGTH_LONG).show();

        listadoDondeSeVisualiza = findViewById(R.id.myCoursesListView);
        Intent intencion = getIntent();
        Bundle extras = intencion.getExtras();
        //Evento evento = (Evento)extras.get("Evento");
        Evento evento = new Evento();
        evento.setLugar("utn");
        cargarCursosDelEvento(evento);
    }

    private void cargarCursosDelEvento(final Evento evento){

        Call<CursosResponse> allCoursesCall = remoteApi.getAllCourses();
        allCoursesCall.enqueue(new Callback<CursosResponse>() {
            @Override
            public void onResponse(Call<CursosResponse> call, Response<CursosResponse> response) {
                Snackbar.make(findViewById(R.id.myCoursesListView), "OK", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                listadoCursosDelEvento = response.body().getCursos();

                AdaptCurseListActivity adaptador = new AdaptCurseListActivity(listadoCursosDelEvento, getApplicationContext(), evento.getLugar());
                listadoDondeSeVisualiza.setAdapter(adaptador);
                listadoDondeSeVisualiza.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent goToDetail = new Intent(getApplicationContext(), CourseDetailActivity.class);

                        Curso selectedCourse = listadoCursosDelEvento.get(position);

                        goToDetail.putExtra(selectedCourse.KEY_ID, selectedCourse.getId());
                        goToDetail.putExtra(selectedCourse.KEY_NOMBRE, selectedCourse.getNombre());
                        goToDetail.putExtra(selectedCourse.KEY_DESCRIPCION, selectedCourse.getDescripcion());
                        goToDetail.putExtra(selectedCourse.KEY_DIA_HORA, selectedCourse.getDiaHora());

                        goToDetail.putExtra(evento.KEY_LUGAR, evento.getLugar());

                        startActivity(goToDetail);
                    }
                });

            }

            @Override
            public void onFailure(Call<CursosResponse> call, Throwable t) {
                Snackbar.make(findViewById(R.id.myCoursesListView), "ERROR", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}
