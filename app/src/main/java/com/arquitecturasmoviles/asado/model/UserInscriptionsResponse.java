package com.arquitecturasmoviles.asado.model;

import java.util.ArrayList;

public class UserInscriptionsResponse {
    private String error;
    private String mensaje;
    private ArrayList<CursosIdResponse> cursos;

    public ArrayList<CursosIdResponse> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<CursosIdResponse> cursos) {
        this.cursos = cursos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}