package com.arquitecturasmoviles.asado.model;

import java.util.HashMap;
import java.util.Map;

public class RegisterResponse {

    private Boolean error;
    private String mensaje;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}