package optionalgetter.processor;

import io.toolisticon.aptk.tools.corematcher.*;

/**
 * Messages used by annotation processors.
 */
public enum OptionalGetterProcessorMessages implements ValidationMessage {

    // TODO: Replace this by your own error messages
    ERROR_COULD_NOT_CREATE_CLASS("true_ERROR_001", "Could not create class ${0} : ${1}"),
    ERROR_VALUE_MUST_NOT_BE_EMPTY("true_ERROR_002", "Value must not be empty");


    /**
     * the message code.
     */
    private final String code;
    /**
     * the message text.
     */
    private final String message;

    /**
     * Constructor.
     *
     * @param code    the message code
     * @param message the message text
     */
    OptionalGetterProcessorMessages(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets the code of the message.
     *
     * @return the message code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the message text.
     *
     * @return the message text
     */
    public String getMessage() {
        return message;
    }


}
