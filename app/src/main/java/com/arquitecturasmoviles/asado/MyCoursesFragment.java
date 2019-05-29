package com.arquitecturasmoviles.asado;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.arquitecturasmoviles.asado.model.Curso;
import com.arquitecturasmoviles.asado.model.CursosIdResponse;
import com.arquitecturasmoviles.asado.model.CursosResponse;
import com.arquitecturasmoviles.asado.model.User;
import com.arquitecturasmoviles.asado.model.UserInscriptionsResponse;
import com.arquitecturasmoviles.asado.network.RemoteApi;
import com.arquitecturasmoviles.asado.network.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCoursesFragment extends Fragment {

    ListView coursesListView;
    ArrayList<Curso> coursesList = new ArrayList<>();
    View view;

    private Retrofit mRestAdapter;
    private RemoteApi remoteApi;

    public MyCoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_courses, container, false);

        remoteApi = RetrofitClientInstance.getRetrofitInstance().create(RemoteApi.class);

        User user = new User();
        String userId = getActivity().getIntent().getStringExtra(user.KEY_ID);
        Call<UserInscriptionsResponse> userInscriptionsCall = remoteApi.getCoursesByUserId(userId);
        userInscriptionsCall.enqueue(new Callback<UserInscriptionsResponse>() {
            @Override
            public void onResponse(Call<UserInscriptionsResponse> call, Response<UserInscriptionsResponse> response) {
                ArrayList<CursosIdResponse> cursosIdResponse = null;
                if (response.body() != null) {
                    cursosIdResponse = response.body().getCursos();
                }
                final ArrayList<String> cursosId = new ArrayList<>();

                if (cursosIdResponse == null) {
                    return;
                }

                //Obtener todos los ids de cursos a los que estoy inscripto
                for (CursosIdResponse cursoIdResponse: cursosIdResponse) {
                    cursosId.add(cursoIdResponse.getCursos_id());
                }

                //Obtener todos los cursos, y filtrar solo en los que estoy inscripto
                Call<CursosResponse> allCoursesCall = remoteApi.getAllCourses();
                allCoursesCall.enqueue(new Callback<CursosResponse>() {
                    @Override
                    public void onResponse(Call<CursosResponse> call, Response<CursosResponse> response) {

                        //Solo agregar los que se haya inscripto el usuario
                        for (Curso curso:
                                response.body().getCursos()) {
                            if (cursosId.contains(curso.getId())) {
                                coursesList.add(curso);
                            }
                        }

                        AdaptCurseListActivity adaptador = new AdaptCurseListActivity(coursesList, getContext(), "");
                        coursesListView = getActivity().findViewById(R.id.myCoursesListView);
                        coursesListView.setAdapter(adaptador);
                        coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent goToDetail = new Intent(getActivity().getApplicationContext(), CourseDetailActivity.class);

                                Curso selectedCourse = coursesList.get(position);

                                goToDetail.putExtra(selectedCourse.KEY_ID, selectedCourse.getId());
                                goToDetail.putExtra(selectedCourse.KEY_NOMBRE, selectedCourse.getNombre());
                                goToDetail.putExtra(selectedCourse.KEY_DESCRIPCION, selectedCourse.getDescripcion());
                                goToDetail.putExtra(selectedCourse.KEY_DIA_HORA, selectedCourse.getDiaHora());

                                startActivity(goToDetail);

                            }

                        });
                    }

                    @Override
                    public void onFailure(Call<CursosResponse> call, Throwable t) {
                        Snackbar.make(view.findViewById(R.id.myCoursesListView), "ERROR", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<UserInscriptionsResponse> call, Throwable t) {
                Snackbar.make(view.findViewById(R.id.myCoursesListView), "ERROR", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });



        ListView lv = view.findViewById(R.id.myCoursesListView);

        return view;
    }

}