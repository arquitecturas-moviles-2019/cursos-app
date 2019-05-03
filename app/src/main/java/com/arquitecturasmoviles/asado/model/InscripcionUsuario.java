package com.arquitecturasmoviles.asado.model;

import java.util.HashMap;
import java.util.Map;

public class InscripcionUsuario {

    private String cursosId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCursosId() {
        return cursosId;
    }

    public void setCursosId(String cursosId) {
        this.cursosId = cursosId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
