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
import com.arquitecturasmoviles.asado.model.Evento;
import com.arquitecturasmoviles.asado.model.LoginBody;
import com.arquitecturasmoviles.asado.model.LoginResponse;
import com.arquitecturasmoviles.asado.network.RemoteApi;

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

        // Crear conexión al servicio REST
        mRestAdapter = new Retrofit.Builder()
                .baseUrl("http://testing.nexoserver.com.ar/bootcampmobile/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        remoteApi = mRestAdapter.create(RemoteApi.class);

        listadoDondeSeVisualiza = findViewById(R.id.myCoursesListView);
        Intent intencion = getIntent();
        Bundle extras = intencion.getExtras();
        //Evento evento = (Evento)extras.get("Evento");
        Evento evento = new Evento();
        evento.setLugar("utn");
        cargarCursosDelEvento(evento);
    }

    private void cargarCursosDelEvento(final Evento evento){

        Call<ArrayList<Curso>> allCoursesCall = remoteApi.getAllCourses();
        allCoursesCall.enqueue(new Callback<ArrayList<Curso>>() {
            @Override
            public void onResponse(Call<ArrayList<Curso>> call, Response<ArrayList<Curso>> response) {
                Snackbar.make(findViewById(R.id.myCoursesListView), "OK", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                for (Curso curso:
                     response.body()) {
                    listadoCursosDelEvento.add(curso);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Curso>> call, Throwable t) {
                Snackbar.make(findViewById(R.id.myCoursesListView), "ERROR", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String ubicacion = evento.getLugar();

        //Sentecia para obtener los cursos
        //Por ahora hardcodeo - Inicio hardcodeo
        Curso curso1 = new Curso();
        curso1.setNombre("curso1");
        curso1.setDiaHora("1/5/19");
        Curso curso2 = new Curso();
        curso2.setNombre("curso2");
        curso2.setDiaHora("2/6/19");
        Curso curso3 = new Curso();
        curso3.setNombre("curso3");
        curso3.setDiaHora("3/7/19");

        listadoCursosDelEvento.add(curso1);
        listadoCursosDelEvento.add(curso2);
        listadoCursosDelEvento.add(curso3);
        //Fin hardcodeo

        AdaptCurseListActivity adaptador = new AdaptCurseListActivity(listadoCursosDelEvento, getApplicationContext(), evento.getLugar());
        listadoDondeSeVisualiza.setAdapter(adaptador);

        listadoDondeSeVisualiza.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goToDetail = new Intent(getApplicationContext(), CourseDetailActivity.class);

                Curso selectedCourse = listadoCursosDelEvento.get(position);

                goToDetail.putExtra(selectedCourse.KEY_NOMBRE, selectedCourse.getNombre());
                goToDetail.putExtra(selectedCourse.KEY_DESCRIPCION, selectedCourse.getDescripcion());
                goToDetail.putExtra(selectedCourse.KEY_DIA_HORA, selectedCourse.getDiaHora());


                goToDetail.putExtra(evento.KEY_LUGAR, evento.getLugar());

                startActivity(goToDetail);
            }
        });

    }
}
