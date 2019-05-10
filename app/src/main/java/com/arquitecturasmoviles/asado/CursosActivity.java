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
import com.arquitecturasmoviles.asado.model.Evento;
import com.arquitecturasmoviles.asado.model.LoginBody;
import com.arquitecturasmoviles.asado.model.LoginResponse;
import com.arquitecturasmoviles.asado.network.RemoteApi;
import com.arquitecturasmoviles.asado.network.RetrofitClientInstance;

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
    String idEvento;
    String ubicacion;

    private Retrofit mRestAdapter;
    private RemoteApi remoteApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_courses);

        remoteApi = RetrofitClientInstance.getRetrofitInstance().create(RemoteApi.class);

        //int courseId = getIntent().getIntExtra("COURSE_ID", 0);

        //Toast.makeText(this, "El id del curso es: " + courseId, Toast.LENGTH_LONG).show();

        listadoDondeSeVisualiza = findViewById(R.id.myCoursesListView);
        Intent intencion = getIntent();
        Bundle extras = intencion.getExtras();
        //Evento evento = (Evento)extras.get("Evento");
        idEvento = getIntent().getStringExtra("idEvento");
        //Evento evento = new Evento();
        //evento.setLugar("utn");
        cargarCursosDelEvento();
    }

    private void cargarCursosDelEvento(){

        Call<List<Curso>> allCoursesCall = remoteApi.getAllCourses();
        allCoursesCall.enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
                String asd = response.body().toString();
                Snackbar.make(findViewById(R.id.myCoursesListView), "OK", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                for (Curso curso:
                     response.body()) {
                    listadoCursosDelEvento.add(curso);
                }
            }

            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {
                String asd = t.getMessage();
                Snackbar.make(findViewById(R.id.myCoursesListView), "ERROR", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        //Sentecia para obtener los cursos
        //Por ahora hardcodeo - Inicio hardcodeo
        Curso curso1 = new Curso();
        Curso curso2 = new Curso();
        Curso curso3 = new Curso();
        switch (idEvento){
            case "0":
                curso1.setNombre("Curso de React");
                curso1.setDiaHora("1/5/19");
                curso2.setNombre("Curso de MongoDB");
                curso2.setDiaHora("22/5/19");
                curso3.setNombre("Curso de Express");
                curso3.setDiaHora("12/6/19");
                ubicacion = "UTN - Facultad Regional San Francisco";
                break;
            case "1":
                curso1.setNombre("Curso de Vue.js");
                curso1.setDiaHora("1/5/19");
                curso2.setNombre("Curso de Redis");
                curso2.setDiaHora("22/5/19");
                curso3.setNombre("Curso de Laravel from Scratch");
                curso3.setDiaHora("12/6/19");
                ubicacion = "UTN - Facultad Regional San Francisco";
                break;
            case "2":
                curso1.setNombre("Curso de Node.js");
                curso1.setDiaHora("1/5/19");
                curso2.setNombre("Curso de HTML 5");
                curso2.setDiaHora("22/5/19");
                curso3.setNombre("Curso de PHP 7");
                curso3.setDiaHora("12/6/19");
                ubicacion = "UTN - Facultad Regional Santa Fe";
                break;
            case "3":
                curso1.setNombre("Curso de MariaDB");
                curso1.setDiaHora("1/5/19");
                curso2.setNombre("Curso de Seguridad Informatica");
                curso2.setDiaHora("22/5/19");
                curso3.setNombre("Curso de Android desde 0");
                curso3.setDiaHora("12/6/19");
                ubicacion = "UTN - Facultad Regional Cordoba";
                break;
            case "4":
                curso1.setNombre("Curso de IOs desde 0");
                curso1.setDiaHora("1/5/19");
                curso2.setNombre("Curso de IOs Avanzado");
                curso2.setDiaHora("22/5/19");
                curso3.setNombre("Curso de Queues en Js");
                curso3.setDiaHora("12/6/19");
                ubicacion = "UTN - Facultad Regional Esperanza";
                break;
            case "5":
                curso1.setNombre("Curso de trabajos asincrónicos");
                curso1.setDiaHora("1/5/19");
                curso2.setNombre("Curso de Modelado 3D");
                curso2.setDiaHora("22/5/19");
                curso3.setNombre("Curso de Juegos en Unity");
                curso3.setDiaHora("12/6/19");
                ubicacion = "UTN - Facultad Regional San Francisco";
                break;
            case "6":
                curso1.setNombre("Curso de TDD");
                curso1.setDiaHora("1/5/19");
                curso2.setNombre("Curso de Travis");
                curso2.setDiaHora("22/5/19");
                curso3.setNombre("Curso de Simphony");
                curso3.setDiaHora("12/6/19");
                ubicacion = "UTN - Facultad Regional San Francisco";
                break;
            case "7":
                curso1.setNombre("Curso de BlockChain");
                curso1.setDiaHora("1/5/19");
                curso2.setNombre("Curso de PostMan");
                curso2.setDiaHora("22/5/19");
                curso3.setNombre("Curso de Ruby");
                curso3.setDiaHora("12/6/19");
                ubicacion = "UTN - Facultad Regional Buenos Aires";
                break;
            case "8":
                curso1.setNombre("Curso de GO");
                curso1.setDiaHora("1/5/19");
                curso2.setNombre("Curso de Inteligencia Artificial");
                curso2.setDiaHora("22/5/19");
                curso3.setNombre("Curso de Sistemas expertos");
                curso3.setDiaHora("12/6/19");
                ubicacion = "UTN - Facultad Regional San Francisco";
                break;
        }


        listadoCursosDelEvento.add(curso1);
        listadoCursosDelEvento.add(curso2);
        listadoCursosDelEvento.add(curso3);
        //Fin hardcodeo

        AdaptCurseListActivity adaptador = new AdaptCurseListActivity(listadoCursosDelEvento, getApplicationContext(), ubicacion);
        listadoDondeSeVisualiza.setAdapter(adaptador);

        listadoDondeSeVisualiza.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goToDetail = new Intent(getApplicationContext(), CourseDetailActivity.class);

                Curso selectedCourse = listadoCursosDelEvento.get(position);

                goToDetail.putExtra(selectedCourse.KEY_NOMBRE, selectedCourse.getNombre());
                goToDetail.putExtra(selectedCourse.KEY_DESCRIPCION, selectedCourse.getDescripcion());
                goToDetail.putExtra(selectedCourse.KEY_DIA_HORA, selectedCourse.getDiaHora());


                goToDetail.putExtra("KEY_EVENTO_LUGAR", ubicacion);

                startActivity(goToDetail);
            }
        });

    }
}
