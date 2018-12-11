package com.example.demo.jacoco.exception;

public class FunctionalException extends Exception {

    /**
     * Serial.
     */
    private static final long serialVersionUID = -6172386369816752623L;

    /**
     * Quelle classe remonte l'exception.
     */
    private final String className;

    /**
     * Quelle méthode remonte l'exception.
     */
    private final String methodName;

    /**
     * Constructeur.
     *
     * @param message
     *         Le message d'erreur.
     */
    public FunctionalException(
            final String message) {

        super(message);

        this.className = this.getStackTrace()[0].getClassName();
        this.methodName = this.getStackTrace()[0].getMethodName();
    }

    /**
     * Constructeur.
     *
     * @param cause
     *         Exception racine.
     */
    public FunctionalException(
            final Throwable cause) {

        super(cause);

        this.className = this.getStackTrace()[0].getClassName();
        this.methodName = this.getStackTrace()[0].getMethodName();
    }

    /**
     * Constructeur.
     *
     * @param message
     *         Le message d'erreur.
     * @param cause
     *         Exception racine.
     */
    public FunctionalException(
            final String message, final Throwable cause) {

        super(message, cause);

        this.className = this.getStackTrace()[0].getClassName();
        this.methodName = this.getStackTrace()[0].getMethodName();
    }

    /**
     * @return Renvoie className.
     */
    public String getClassName() {

        return this.className;
    }

    /**
     * @return Renvoie methodName.
     */
    public String getMethodName() {

        return this.methodName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        StringBuffer result = new StringBuffer();
        result.append(this.getClass().getSimpleName());
        result.append(" { className : ");
        result.append(this.className);
        result.append(", methodName : ");
        result.append(this.methodName);
        result.append(", message : ");
        result.append(this.getMessage());
        result.append(", cause : ");
        result.append(this.getCause());
        result.append("}");

        return result.toString();
    }
}