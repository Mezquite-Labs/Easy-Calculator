package com.mezquitelabs.easycalculator.model;

public interface OperationListener {

    void onStart();

//    void calculate(int leftOperand, int rightOperand);

    void onFinishOperation(String result);

    void onError();

}
