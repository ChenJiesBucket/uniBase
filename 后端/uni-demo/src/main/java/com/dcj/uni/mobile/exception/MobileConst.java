package com.dcj.uni.mobile.exception;

public enum MobileConst implements IMobileError {
    /**参数为空*/
    EMPTY_PARAME("A11002","参数为空"),
    SUCCESS("200","处理成功"),
    /**参数错误*/
    ERROR_PARAME("A11002","参数错误");


    private String code;
    private String message;

    private MobileConst() {
    }

    private MobileConst(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return this.code;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}
