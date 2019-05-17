package com.arquitecturasmoviles.asado;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.arquitecturasmoviles.asado.model.Curso;
import com.arquitecturasmoviles.asado.model.CursosResponse;
import com.arquitecturasmoviles.asado.model.Evento;
import com.arquitecturasmoviles.asado.network.RemoteApi;
import com.arquitecturasmoviles.asado.network.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CursosActivity extends AppCompatActivity {

    ListView coursesListView;
    ArrayList<Curso> cursosList = new ArrayList<>();
    String idEvento;
    String eventoLugar;
    Evento evento = new Evento(); //Evento vacio para usar sus keys

    private RemoteApi remoteApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_courses);

        remoteApi = RetrofitClientInstance.getRetrofitInstance().create(RemoteApi.class);

        coursesListView = findViewById(R.id.myCoursesListView);

        idEvento = getIntent().getStringExtra(evento.KEY_ID);
        eventoLugar = getIntent().getStringExtra(evento.KEY_LUGAR);

        cargarCursosDelEvento();
    }

    private void cargarCursosDelEvento(){

        Call<CursosResponse> allCoursesCall = remoteApi.getAllCourses();
        allCoursesCall.enqueue(new Callback<CursosResponse>() {
            @Override
            public void onResponse(Call<CursosResponse> call, Response<CursosResponse> response) {
                Snackbar.make(findViewById(R.id.myCoursesListView), "OK", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //Solo agregar los que correspondan al evento
                for (Curso curso:
                        response.body().getCursos()) {
                    if (curso.getEventoId().equals(idEvento)) {
                        cursosList.add(curso);
                    }
                }

                AdaptCurseListActivity adaptador = new AdaptCurseListActivity(cursosList, getApplicationContext(), evento.getLugar());
                coursesListView.setAdapter(adaptador);
                coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent goToDetail = new Intent(getApplicationContext(), CourseDetailActivity.class);

                        Curso selectedCourse = cursosList.get(position);

                        goToDetail.putExtra(selectedCourse.KEY_ID, selectedCourse.getId());
                        goToDetail.putExtra(selectedCourse.KEY_NOMBRE, selectedCourse.getNombre());
                        goToDetail.putExtra(selectedCourse.KEY_DESCRIPCION, selectedCourse.getDescripcion());
                        goToDetail.putExtra(selectedCourse.KEY_DIA_HORA, selectedCourse.getDiaHora());

                        goToDetail.putExtra(evento.KEY_LUGAR, eventoLugar);

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
