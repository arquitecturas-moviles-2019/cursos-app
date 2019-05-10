package com.arquitecturasmoviles.asado.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class RegisterBody {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("apellido")
    private String apellido;
    @SerializedName("email")
    private String email;
    @SerializedName("contrasenia")
    private String contrasenia;
    @SerializedName("contraseniaConfirmacion")
    private String contraseniaConfirmacion;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public RegisterBody(String nombre, String apellido, String email, String contrasenia, String contraseniaConfirmacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.contraseniaConfirmacion = contraseniaConfirmacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getContraseniaConfirmacion() {
        return contraseniaConfirmacion;
    }

    public void setContraseniaConfirmacion(String contraseniaConfirmacion) {
        this.contraseniaConfirmacion = contraseniaConfirmacion;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}