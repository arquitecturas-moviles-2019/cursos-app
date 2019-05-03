package com.arquitecturasmoviles.asado.model;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String id;
    private String nombre;
    private String apellido;
    private Object dni;
    private Object domicilio;
    private Object telefono;
    private String idlocalidad;
    private String localidad;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Object getDni() {
        return dni;
    }

    public void setDni(Object dni) {
        this.dni = dni;
    }

    public Object getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Object domicilio) {
        this.domicilio = domicilio;
    }

    public Object getTelefono() {
        return telefono;
    }

    public void setTelefono(Object telefono) {
        this.telefono = telefono;
    }

    public String getIdlocalidad() {
        return idlocalidad;
    }

    public void setIdlocalidad(String idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
