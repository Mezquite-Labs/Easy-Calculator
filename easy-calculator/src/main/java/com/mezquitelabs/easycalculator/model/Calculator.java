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
    private boolean mInvalidOperation;
    private String mErrorMsg;

    public Calculator() {
        mCurrentIsLeftOperand = true;
        mInvalidOperation = false;
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
        BigInteger tempResult = leftOperand.add(rightOperand);
        BigInteger limit = new BigInteger("99999999999999");
        String result;
        if(tempResult.compareTo(limit)!=1) {
            mInvalidOperation =false;
            if(checkValidLength(String.valueOf(tempResult))){
                result = new String(String.valueOf(tempResult));
            }
            else {
                result = new String(String.valueOf(tempResult).toCharArray(), 0, 14);
            }
            finishOperation(result);
        }
        else{
            mInvalidOperation =true;
            mErrorMsg = "ERROR: Out of boundary";
            finishOperation("0");
        }
    }

    private void sumTwoNumbers(BigDecimal leftOperand, BigDecimal rightOperand) {
        BigDecimal tempResult = leftOperand.add(rightOperand);
        BigDecimal limit = new BigDecimal("99999999999999");
        String result;
        if(tempResult.compareTo(limit)!=1) {
            mInvalidOperation =false;
            if(checkValidLength(String.valueOf(tempResult))){
                result = new String(String.valueOf(tempResult));
            }
            else {
                result = new String(String.valueOf(tempResult).toCharArray(), 0, 14);
            }
            finishOperation(result);
        }
        else{
            mInvalidOperation =true;
            mErrorMsg = "ERROR: Out of boundary";
            finishOperation("0");
        }
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
        BigInteger tempResult = leftOperand.subtract(rightOperand);
        BigInteger limit = new BigInteger("99999999999999");
        String result;
        if(tempResult.compareTo(limit)!=1) {
            mInvalidOperation =false;
            if(checkValidLength(String.valueOf(tempResult))){
                result = new String(String.valueOf(tempResult));
            }
            else {
                result = new String(String.valueOf(tempResult).toCharArray(), 0, 14);
            }
            finishOperation(result);
        }
        else{
            mInvalidOperation =true;
            mErrorMsg = "ERROR: Out of boundary";
            finishOperation("0");
        }
    }

    private void subTwoNumbers(BigDecimal leftOperand, BigDecimal rightOperand) {
        BigDecimal tempResult = leftOperand.subtract(rightOperand);
        BigDecimal limit = new BigDecimal("99999999999999");
        String result;
        if(tempResult.compareTo(limit)!=1) {
            mInvalidOperation =false;
            if(checkValidLength(String.valueOf(tempResult))){
                result = new String(String.valueOf(tempResult));
            }
            else {
                result = new String(String.valueOf(tempResult).toCharArray(), 0, 14);
            }
            finishOperation(result);
        }
        else{
            mInvalidOperation =true;
            mErrorMsg = "ERROR: Out of boundary";
            finishOperation("0");
        }
    }

    private void mulTwoNumbers(String leftOperand, String rightOperand){
        if(isDigitsOnly(leftOperand) && isDigitsOnly(rightOperand)){
            mulTwoNumbers(new BigInteger(leftOperand), new BigInteger(rightOperand));
        }else{
            mulTwoNumbers(new BigDecimal(leftOperand), new BigDecimal(rightOperand));
        }
    }

    private void mulTwoNumbers(BigInteger leftOperand, BigInteger rightOperand){
        BigInteger tempResult = leftOperand.multiply(rightOperand);
        BigInteger limit = new BigInteger("99999999999999");
        if(tempResult.compareTo(limit)!=1) {
            mInvalidOperation =false;
            String result = new String(String.valueOf(tempResult));
            finishOperation(result);
        }
        else{
            mInvalidOperation =true;
            mErrorMsg = "ERROR: Out of boundary";
            finishOperation("0");
        }
    }

    private void mulTwoNumbers(BigDecimal leftOperand, BigDecimal rightOperand) {
        BigDecimal tempResult = leftOperand.multiply(rightOperand);
        BigDecimal limit = new BigDecimal("99999999999999");
        String result;
        if(tempResult.compareTo(limit)!=1) {
            mInvalidOperation =false;
            if(checkValidLength(String.valueOf(tempResult))){
                result = new String(String.valueOf(tempResult));
            }
            else {
                result = new String(String.valueOf(tempResult).toCharArray(), 0, 14);
            }
            finishOperation(result);
        }
        else{
            mInvalidOperation =true;
            mErrorMsg = "ERROR: Out of boundary";
            finishOperation("0");
        }
    }

    public void divTwoNumbers(String leftOperand, String rightOperand){
        divTwoNumbers(new BigDecimal(leftOperand), new BigDecimal(rightOperand));
    }

    private void divTwoNumbers(BigDecimal leftOperand, BigDecimal rightOperand) {
        //DECIMAL64 is a precision setting matching the IEEE 754R Decimal64 format, it's size is 16 digits
        if(rightOperand.intValue()!=0) {
            BigDecimal tempResult = leftOperand.divide(rightOperand, MathContext.DECIMAL64);
            BigDecimal limit = new BigDecimal("99999999999999");
            String result;
            if (tempResult.compareTo(limit) != 1) {
                mInvalidOperation = false;
                if (checkValidLength(String.valueOf(tempResult))) {
                    result = new String(String.valueOf(tempResult));
                } else {
                    result = new String(String.valueOf(tempResult).toCharArray(), 0, 14);
                }
                finishOperation(result);
            } else {
                mInvalidOperation = true;
                mErrorMsg = "ERROR: Out of boundary";
                finishOperation("0");
            }
        }
        else{
            mInvalidOperation = true;
            mErrorMsg = "ERROR: Invalid Operation";
            finishOperation("0");
        }
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

    public void setOperationListener(OperationListener operationListener) {
        mOperationListener = operationListener;
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

    public void appendCurrentOperand(CharSequence nextInputText) {
        if (mCurrentIsLeftOperand) {
            if(mLeftOperand.length()<14)
                mLeftOperand.append(nextInputText);
        } else {
            if(mRightOperand.length()<14)
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
        mOperator = nextInputText.toString();


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

    private boolean checkValidLength(String temp){
        if(temp.length()<14){
            return true;
        }
        else {
            return false;
        }
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

    public boolean ismInvalidOperation() {
        return mInvalidOperation;
    }

    public String getmErrorMsg() {
        return mErrorMsg;
    }
}
