package com.arquitecturasmoviles.asado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.Curso;

import org.w3c.dom.Text;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        //Get TextView's
        TextView dateTextView = (TextView)this.findViewById(R.id.dateLabel);
        TextView locationTextView = (TextView)this.findViewById(R.id.locationLabel);
        TextView descriptionTextView = (TextView)this.findViewById(R.id.descriptionLabel);

        //Curso curso = (Curso) getIntent().getExtras().getSerializable("CURSO");

        /*HARDCODE - DELETE WHEN RECEIVING CORRECT OBJECT*/
        Curso curso = new Curso();
        curso.setDiaHora("15:30 20/01/1997");
        curso.setDescripcion("Esta es una descripcion hardcodeada");

        //Set Textview's values
        dateTextView.setText(curso.getDiaHora());
        locationTextView.setText("Ubicacion por defecto");
        descriptionTextView.setText(curso.getDescripcion());

        final Button inscribeButton = (Button) findViewById(R.id.inscribeButton);
        inscribeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Realizar la inscripcion onClick()

                //Volver a la Main Activity
                //Intent mainActivity = new Intent(getApplicationContext(), );
                //startActivity(mainActivity);
            }
        });

    }
}
