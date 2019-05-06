package com.arquitecturasmoviles.asado;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCoursesFragment extends Fragment {


    public MyCoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_courses, container, false);
        // Inflate the layout for this fragment
        String [] myCorusesString = {
                "MI CURSO 1",
                "MI CURSO 2",
                "MI CURSO 3"
        };

        ListView lv = (ListView) view.findViewById(R.id.myCoursesListView);

        ArrayAdapter<String> lva = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, myCorusesString
        );

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "Se ha presionado el curso de la posición "+id, Snackbar.LENGTH_LONG)
                        .show();
            }
        });

        lv.setAdapter(lva);
        return view;
    }

}