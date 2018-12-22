package com.kirck.commen.exception;

public class BusinessException extends BaseException {

    /**
     */
    private static final long serialVersionUID = -5631834713040791029L;

    public BusinessException() {
        super();
    }

    public BusinessException(String code, String logMsg, Object[] messageArgs, String defaultFriendlyMessage) {
        super(code, logMsg, messageArgs, defaultFriendlyMessage);
    }

    public BusinessException(String code, String logMsg, Object[] messageArgs) {
        super(code, logMsg, messageArgs);
    }

    public BusinessException(String code, String logMsg, String defaultFriendlyMessage) {
        super(code, logMsg, defaultFriendlyMessage);
    }

    public BusinessException(String code, String logMsg, Throwable cause, Object[] messageArgs,
            String defaultFriendlyMessage) {
        super(code, logMsg, cause, messageArgs, defaultFriendlyMessage);
    }

    public BusinessException(String code, String logMsg, Throwable cause, Object[] messageArgs) {
        super(code, logMsg, cause, messageArgs);
    }

    public BusinessException(String code, String logMsg, Throwable cause, String defaultFriendlyMessage) {
        super(code, logMsg, cause, defaultFriendlyMessage);
    }

    public BusinessException(String code, String logMsg, Throwable cause) {
        super(code, logMsg, cause);
    }

    public BusinessException(String code, String logMsg) {
        super(code, logMsg);
    }

    public BusinessException(String code, Throwable cause, Object[] messageArgs, String defaultFriendlyMessage) {
        super(code, cause, messageArgs, defaultFriendlyMessage);
    }

    public BusinessException(String code, Throwable cause, Object[] messageArgs) {
        super(code, cause, messageArgs);
    }

    public BusinessException(String code, Throwable cause, String defaultFriendlyMessage) {
        super(code, cause, defaultFriendlyMessage);
    }

    public BusinessException(String code, Throwable cause) {
        super(code, cause);
    }

    public BusinessException(String logMsg) {
        super(logMsg);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
