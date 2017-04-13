package com.infosys.weixin.web.exception;

public class TimeoutException extends Exception {

    /**    
     *    
     * @since Ver 1.1    
     */    
    
    private static final long serialVersionUID = 1L;

    public TimeoutException() {
    }

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(Throwable cause) {
        super(cause);
    }

    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
