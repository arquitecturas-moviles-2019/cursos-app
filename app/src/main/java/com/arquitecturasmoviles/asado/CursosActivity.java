package com.arquitecturasmoviles.asado;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.arquitecturasmoviles.asado.model.Curso;
import com.arquitecturasmoviles.asado.model.Evento;
import com.arquitecturasmoviles.asado.model.Curso;
import com.arquitecturasmoviles.asado.model.Evento;

import java.util.ArrayList;

public class CursosActivity extends AppCompatActivity {

    ListView listadoDondeSeVisualiza;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.content_cursos);

        //listadoDondeSeVisualiza = findViewById(R.id.lv_cursos);

        Intent intencion = getIntent();
        Bundle extras = intencion.getExtras();
        Evento evento = (Evento)extras.get("Evento");

        cargarCursosDelEvento(evento);
    }

    private void cargarCursosDelEvento(Evento evento){
        ArrayList<Curso>listadoCursosDelEvento = new ArrayList<>();

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

        //AdaptCurseListActivity adaptador = new AdaptCurseListActivity(listadoCursosDelEvento, getApplicationContext());
        //listadoDondeSeVisualiza.setAdapter(adaptador);

        listadoDondeSeVisualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aca se construye la intencion para ir al curso seleccionado
            }
        });

    }
}
