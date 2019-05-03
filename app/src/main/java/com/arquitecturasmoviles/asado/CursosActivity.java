package com.arquitecturasmoviles.asado;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.Evento;

import java.io.Serializable;
import java.util.ArrayList;

public class CursosActivity extends AppCompatActivity {

    ListView listadoDondeSeVisualiza;
    Curso cursoAPasar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_courses);

        listadoDondeSeVisualiza = findViewById(R.id.myCoursesListView);
        Intent intencion = getIntent();
        Bundle extras = intencion.getExtras();
        //Evento evento = (Evento)extras.get("Evento");
        Evento evento = new Evento();
        evento.setLugar("utn");
        cargarCursosDelEvento(evento);
    }

    private void cargarCursosDelEvento(final Evento evento){
        final ArrayList<Curso>listadoCursosDelEvento = new ArrayList<>();

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
