package com.mezquitelabs.easycalculator.model;

public enum BasicOperations {
    ADD("+"),
    SUBTRACT("-"),
    PRODUCT("*"),
    DIVISION("/");

    private String mOperator;

    BasicOperations(String operator) {
        mOperator = operator;
    }

    public static BasicOperations fromString(String operator) {
        if (operator != null) {
            for (BasicOperations basicOperations : BasicOperations.values()) {
                if (operator.equalsIgnoreCase(basicOperations.getOperator())) {
                    return basicOperations;
                }
            }
        }
        return null;
    }

    public String getOperator() {
        return mOperator;
    }
}
