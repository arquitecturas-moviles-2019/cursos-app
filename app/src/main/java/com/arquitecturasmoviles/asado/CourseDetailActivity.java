package com.arquitecturasmoviles.asado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.arquitecturasmoviles.asado.model.*;

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

        Curso curso = new Curso();
        curso.setDiaHora(getIntent().getStringExtra(curso.KEY_DIA_HORA));
        curso.setDescripcion(getIntent().getStringExtra(curso.KEY_DESCRIPCION));

        //Set Textview's values
        dateTextView.setText(curso.getDiaHora());
        locationTextView.setText(getIntent().getStringExtra(new Evento().KEY_LUGAR));
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
