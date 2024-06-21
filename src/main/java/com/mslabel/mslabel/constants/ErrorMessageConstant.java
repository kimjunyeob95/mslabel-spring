package com.mslabel.mslabel.constants;

public class ErrorMessageConstant {
    
    private static final String DEFAULT_MSG          = "(을)를 전달해주세요.";
    private static final String DEFAULT_TYPE_MSG     = "의 타입형식이 올바르지 않습니다.";
    private static final String DEFAULT_NOT_HAVE_MSG = "(은)는 없습니다.";
    private static final String DEFAULT_FIT_MSG      = "Error";

    public static final String ERROR_MESSAGE_ENDPOINT = "endPoint";
    public static final String ERROR_MESSAGE_SERVER   = "server";

    public static String getErrorMessageNotDefault(String constantName) {
        return getConstantValue(constantName);
    }

    public static String getErrorMessage(String constantName) {
        return getConstantValue(constantName) + DEFAULT_MSG;
    }

    public static String getTypeErrorMessage(String constantName) {
        return getConstantValue(constantName) + DEFAULT_TYPE_MSG;
    }

    public static String getNotHaveErrorMessage(String constantName) {
        return "해당 " + getConstantValue(constantName) + DEFAULT_NOT_HAVE_MSG;
    }

    public static String getFitErrorMessage(String constantName) {
        return DEFAULT_FIT_MSG + " " + getConstantValue(constantName);
    }

    private static String getConstantValue(String constantName) {
        try {
            return (String) ErrorMessageConstant.class.getDeclaredField("ERROR_MESSAGE_" + constantName).get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("Invalid constant name: " + constantName);
        }
    }
}
