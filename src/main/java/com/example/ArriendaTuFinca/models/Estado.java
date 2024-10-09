package com.example.ArriendaTuFinca.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Estado {

    ACTIVE("Activo"),
    INACTIVE("Inactivo");

    private String value;

    Estado(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
