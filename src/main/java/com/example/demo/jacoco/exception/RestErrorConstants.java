package com.example.demo.jacoco.exception;

/**
 * Constantes pour la remontees des erreurs des services REST
 */
public final class RestErrorConstants {

    public static final String ERR_ACCESS_DENIED = "error.accessDenied";

    public static final String ERR_AUTH_REQUIRED = "error.authenticationRequired";

    public static final String ERR_VALIDATION = "error.validation";

    public static final String ERR_ILLEGAL_ARGUMENT = "error.illegalArgumentException";

    public static final String ERR_METHOD_NOT_SUPPORTED = "error.methodNotSupported";

    public static final String ERR_INTERNAL_SERVER_ERROR = "error.internalServerError";

    public static final String ERR_DATA_NOT_FOUND = "error.dataNotFound";

    private RestErrorConstants() {
    }

}
