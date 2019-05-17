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

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        String [] myEventsString = {
                "EVENTO 1",
                "EVENTO 2",
                "EVENTO 3",
                "EVENTO 4",
                "EVENTO 5",
                "EVENTO 6",
                "EVENTO 7",
                "EVENTO 8",
        };

        ListView lv = (ListView) view.findViewById(R.id.eventsListView);

        ArrayAdapter<String> lva = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, myEventsString
        );

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Snackbar.make(view, "Se ha presionado el evento de la posición: "+position, Snackbar.LENGTH_LONG)
                //        .show();

                Intent goToCourse = new Intent(getContext(), CursosActivity.class);
                goToCourse.putExtra("idEvento", String.valueOf(position));
                startActivity(goToCourse);
            }
        });

        lv.setAdapter(lva);
        // Inflate the layout for this fragment
        return view;
    }

}
