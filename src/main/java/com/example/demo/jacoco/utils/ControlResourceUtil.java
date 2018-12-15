package com.example.demo.jacoco.utils;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.jacoco.exception.ParametreRestInvalideException;

public class ControlResourceUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ControlResourceUtil.class);

    private ControlResourceUtil() {
        throw new IllegalAccessError("ControlResourceUtil : Utility class");
    }

    public static int controlId(String id) {
        // Validation des entrées
        try {
            Validate.notNull(id, "Le paramètre ne doit pas être null");
            return controlPositiveIdVersion(id);
        } catch (IllegalArgumentException e) {
            LOG.error(e.getMessage());
            throw new ParametreRestInvalideException(e.getMessage(), e);
        }
    }

    private static int controlPositiveIdVersion(String idVersion) {
        try {
            int idVersionInt = Integer.parseInt(idVersion);
            Validate.isTrue(idVersionInt > 0, "Le paramètre doit être strictement positif");
            return idVersionInt;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Le paramètre doit être un nombre entier");
        }
    }

}
