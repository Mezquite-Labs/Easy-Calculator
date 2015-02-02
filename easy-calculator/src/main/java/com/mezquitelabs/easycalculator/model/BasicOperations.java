package com.mezquitelabs.easycalculator.model;

import com.mezquitelabs.easycalculator.CalculatorActivityApplication;
import com.mezquitelabs.easycalculator.R;


public enum BasicOperations {
    ADD(CalculatorActivityApplication.retrieveString(R.string.plus)),
    SUBSTRACT(CalculatorActivityApplication.retrieveString(R.string.minus)),
    PRODUCT(CalculatorActivityApplication.retrieveString(R.string.product)),
    DIVISION(CalculatorActivityApplication.retrieveString(R.string.division));

    private String mOperator;

    BasicOperations(String operator) {
        mOperator = operator;
    }

    public String getOperator() {
        return mOperator;
    }
}
