package com.dcj.uni.mobile.exception;

public class MobileRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -6938187021751397947L;

    private String code;
    private String message;
    private IMobileError mobileError;



    public MobileRuntimeException(IMobileError iMobileError){
        super();
        this.mobileError =iMobileError;
        this.code = iMobileError.getErrorCode();
        this.message = iMobileError.getErrorMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
