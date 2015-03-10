package com.mezquitelabs.easycalculator.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import static com.mezquitelabs.easycalculator.util.Utils.isDigitsOnly;


public class Calculator {

    private OperationListener mOperationListener;
    private boolean mCurrentIsLeftOperand;
    private StringBuilder mLeftOperand;
    private StringBuilder mRightOperand;
    private String mOperator;
    private String mResult;

    public Calculator() {
        mCurrentIsLeftOperand = true;
        mLeftOperand = new StringBuilder();
        mRightOperand = new StringBuilder();
    }

    public void sumTwoNumbers(String leftOperand, String rightOperand) {
        // One of the numbers is decimal, we need to perform a decimal operation
        if (isDigitsOnly(leftOperand) && isDigitsOnly(rightOperand)) {
            sumTwoNumbers(new BigInteger(leftOperand), new BigInteger(rightOperand));
        } else {
            sumTwoNumbers(new BigDecimal(leftOperand), new BigDecimal(rightOperand));
        }
    }

    private void sumTwoNumbers(BigInteger leftOperand, BigInteger rightOperand) {
        String result = String.valueOf(leftOperand.add(rightOperand));
        finishOperation(result);
    }

    private void sumTwoNumbers(BigDecimal leftOperand, BigDecimal rightOperand) {
        String result = String.valueOf(leftOperand.add(rightOperand));
        finishOperation(result);
    }

    public void subTwoNumbers(String leftOperand, String rightOperand) {
        // If one of the numbers is decimal, we nee to perform a decimal operation
        if(isDigitsOnly(leftOperand) && isDigitsOnly(rightOperand)) {
            subTwoNumbers(new BigInteger(leftOperand), new BigInteger(rightOperand));
        }
        else {
            subTwoNumbers(new BigDecimal(leftOperand), new BigDecimal(rightOperand));
        }
    }

    private void subTwoNumbers(BigInteger leftOperand, BigInteger rightOperand) {
        String result = String.valueOf(leftOperand.subtract(rightOperand));
        finishOperation(result);
    }

    private void subTwoNumbers(BigDecimal leftOperand, BigDecimal rightOperand) {
        String result = String.valueOf(leftOperand.subtract(rightOperand));
        finishOperation(result);
    }

    private void mulTwoNumbers(String leftOperand, String rightOperand){
        if(isDigitsOnly(leftOperand) && isDigitsOnly(rightOperand)){
            mulTwoNumbers(new BigInteger(leftOperand), new BigInteger(rightOperand));
        }else{
            mulTwoNumbers(new BigDecimal(leftOperand), new BigDecimal(rightOperand));
        }
    }

    private void mulTwoNumbers(BigInteger leftOperand, BigInteger rightOperand){
        String result = String.valueOf(leftOperand.multiply(rightOperand));
        finishOperation(result);
    }

    private void mulTwoNumbers(BigDecimal leftOperand, BigDecimal rightOperand) {
        String result = String.valueOf(leftOperand.multiply(rightOperand));
        finishOperation(result);
    }

    public void divTwoNumbers(String leftOperand, String rightOperand){
        divTwoNumbers(new BigDecimal(leftOperand), new BigDecimal(rightOperand));
    }

    private void divTwoNumbers(BigDecimal leftOperand, BigDecimal rightOperand) {
        //DECIMAL64 is a precision setting matching the IEEE 754R Decimal64 format, it's size is 16 digits
        String result = String.valueOf(leftOperand.divide(rightOperand, MathContext.DECIMAL64));
        finishOperation(result);
    }


    public void setOperationListener(OperationListener operationListener) {
        mOperationListener = operationListener;
    }

    public void appendCurrentOperand(CharSequence nextInputText) {
        if (mCurrentIsLeftOperand) {
            mLeftOperand.append(nextInputText);
        } else {
            mRightOperand.append(nextInputText);
        }
    }

    public void appendCurrentOperator(CharSequence nextInputText) {
        if (canPerformOperation()) {
            performOperation();
            savePreviousResultOnLeftOperand(nextInputText);
        } else {
            mCurrentIsLeftOperand = false;
            mOperator = nextInputText.toString();
        }


    }

    private boolean canPerformOperation() {
        boolean rightOperandHasValue = mRightOperand != null && !(mRightOperand.toString().isEmpty());
        return !mCurrentIsLeftOperand || rightOperandHasValue;
    }


    public String getOperator() {
        return mOperator;
    }

    public void performOperation() {
        String leftOperand = getLeftOperand().toString();
        String rightOperand = getRightOperand().toString();

        switch (BasicOperations.fromString(mOperator)) {
            case ADD:
                sumTwoNumbers(leftOperand, rightOperand);
                break;
            case SUBTRACT:
                subTwoNumbers(leftOperand, rightOperand);
                break;
            case DIVISION:
                divTwoNumbers(leftOperand, rightOperand);
                break;
            case PRODUCT:
                mulTwoNumbers(leftOperand, rightOperand);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private void savePreviousResultOnLeftOperand(CharSequence nextInputText) {
        mCurrentIsLeftOperand = false;
        mOperator = nextInputText.toString();
    }

    private void finishOperation(String result) {
        mCurrentIsLeftOperand = true;
        mRightOperand.setLength(0); // Resets the value
        mLeftOperand.setLength(0);
        mOperationListener.onFinishOperation(result);
        mOperator = null;
        mResult = result;
        mLeftOperand.append(mResult);
    }

    public void clear(){
        mLeftOperand.setLength(0);
        mRightOperand.setLength(0);
        mOperator = null;
        mCurrentIsLeftOperand = true;
    }

    public boolean getIsAnOperandEmpty(){
        boolean empty=true;
        if(getIsCurrentLeft()==true){
            if(mLeftOperand.length()!=0){
                empty=false;
            }
        } else{
            if(mRightOperand.length()!=0){
                empty=false;
            }
        }
        return empty;
    }

    public StringBuilder getLeftOperand() {
        return mLeftOperand;
    }

    public StringBuilder getRightOperand() {
        return mRightOperand;
    }

    public boolean getIsCurrentLeft() {
        return mCurrentIsLeftOperand;
    }

    public String getResult() { return  mResult;}
}
