package com.gabrielklein.email.enums;

public enum EIntegrationType {
    OCI, AWS;

    public static EIntegrationType fromString(String value) {
        try {
            return EIntegrationType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de integração desconhecido: " + value);
        }
    }
}
