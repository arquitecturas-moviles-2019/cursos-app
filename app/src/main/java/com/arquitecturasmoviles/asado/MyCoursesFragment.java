package com.arquitecturasmoviles.asado;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.arquitecturasmoviles.asado.model.Curso;
import com.arquitecturasmoviles.asado.model.Evento;
import com.arquitecturasmoviles.asado.network.RemoteApi;
import com.arquitecturasmoviles.asado.network.RetrofitClientInstance;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCoursesFragment extends Fragment {

    ArrayList<Curso> listadoCursosDelEvento = new ArrayList<>();
    Evento evento;
    View view;

    private Retrofit mRestAdapter;
    private RemoteApi remoteApi;

    public MyCoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_courses, container, false);
        // Inflate the layout for this fragment


        remoteApi = RetrofitClientInstance.getRetrofitInstance().create(RemoteApi.class);

        //int courseId = getIntent().getIntExtra("COURSE_ID", 0);

        //Sentecia para obtener los cursos
        //Por ahora hardcodeo - Inicio hardcodeo
        evento = new Evento();
        evento.setLugar("UTN - Facultad Regional San Francisco");

        Curso curso1 = new Curso();
        curso1.setId("1");
        curso1.setNombre("Curso de Vue.js");
        curso1.setDiaHora("1/5/19");
        Curso curso2 = new Curso();
        curso2.setId("2");
        curso2.setNombre("Curso de Redis");
        curso2.setDiaHora("22/5/19");
        Curso curso3 = new Curso();
        curso3.setId("3");
        curso3.setNombre("Curso de Laravel from Scratch");
        curso3.setDiaHora("12/6/19");

        listadoCursosDelEvento.add(curso1);
        listadoCursosDelEvento.add(curso2);
        listadoCursosDelEvento.add(curso3);

        String [] myCorusesString = {
                curso1.getNombre(),
                curso2.getNombre(),
                curso3.getNombre()
        };
        //Fin hardcodeo

        Call<List<Curso>> allCoursesCall = remoteApi.getAllCourses();
        allCoursesCall.enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
                String asd = response.body().toString();
                Snackbar.make(view.findViewById(R.id.myCoursesListView), "OK", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                for (Curso curso:
                        response.body()) {
                    listadoCursosDelEvento.add(curso);
                }
            }

            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {
                String asd = t.getMessage();
                Snackbar.make(view.findViewById(R.id.myCoursesListView), "ERROR", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




        ListView lv = view.findViewById(R.id.myCoursesListView);

        ArrayAdapter<String> lva = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, myCorusesString
        );

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Snackbar.make(view, "Se ha presionado el curso de la posición "+id, Snackbar.LENGTH_LONG)
//                        .show();
                /*
                Intent intent = new Intent(getContext(), CursosActivity.class);
                intent.putExtra("COURSE_ID", position);
                startActivity(intent);
                */


                Intent goToDetail = new Intent(getContext(), CourseDetailActivity.class);

                Curso selectedCourse = listadoCursosDelEvento.get(position);

                goToDetail.putExtra(selectedCourse.KEY_NOMBRE, selectedCourse.getNombre());
                goToDetail.putExtra(selectedCourse.KEY_DESCRIPCION, selectedCourse.getDescripcion());
                goToDetail.putExtra(selectedCourse.KEY_DIA_HORA, selectedCourse.getDiaHora());


                goToDetail.putExtra(evento.KEY_LUGAR, evento.getLugar());

                startActivity(goToDetail);
            }
        });

        lv.setAdapter(lva);
        return view;
    }

}