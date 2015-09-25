package test.com.mezquitelabs.easycalculator;

import com.mezquitelabs.easycalculator.model.Calculator;
import com.mezquitelabs.easycalculator.model.OperationListener;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorSubBehaviorTest {

    private Calculator mCalculator;
    private OperationListener mOperationListener;
    private String mResult;
    private String mLeftOperand;
    private String mRightOperand;
    private String mOperator;

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUpMethods() {
        mCalculator = new Calculator();
        givenOperationListener(mOperationListener);
    }

    @Test
    public void shouldHaveMinusOperandAfterAddingOneNumberAndPlusOperand() {
        givenFirstOperand("1");
        whenCheckingLeftOperand();
        thenLeftOperandWillBe("1");
        givenOperator("-");
        whenCheckingOperator();
        thenOperatorShouldBe("-");
        whenCheckingRightOperand();
        thenRightOperandShouldBeEmpty();
    }

    @Test
    public void subOperationShouldOneMinusOneReturnZero() {
        givenFirstOperand("1");
        givenOperator("-");
        givenSecondOperand("1");
        whenPerformsOperation();
        thenResultWillBe("0");
    }

    @Test
    public void subOperationShouldOneMinusNegativeTwoReturnThree() {
        givenFirstOperand("1");
        givenOperator("-");
        givenSecondOperand("-2");
        whenPerformsOperation();
        thenResultWillBe("3");
    }

    @Test
    public void subOperationShouldTenMinusTweleveReturnMinusTwo(){
        givenFirstOperand("10");
        givenOperator("-");
        givenSecondOperand("12");
        whenPerformsOperation();
        thenResultWillBe("-2");
    }

    @Test
    public void subOperationShouldPiNumberMinusOneReturn2Dot1416() {
        givenFirstOperand("3.1416");
        givenOperator("-");
        givenSecondOperand("1");
        whenPerformsOperation();
        thenResultWillBe("2.1416");
    }

    @Test
    public void subOperationShouldReturnMinusThreeGivenTwoOperatorsAnd3OneOperands() {
        givenOperation(new String[]{"0", "-", "1", "-", "1", "-", "1"});
        whenPerformsOperation();
        thenResultWillBe("-3");
    }

    @Test
    public void subOperationShouldReturnTwoGivenThreeOperatorsAnd4OneOperands() {
        givenOperation(new String[]{"10", "-", "1", "-", "2", "-", "5"});
        whenPerformsOperation();
        thenResultWillBe("2");
    }

    private void givenOperation(String[] inputs) {
        boolean firstOperator = true;
        int counter = 0;
        for (String input : inputs) {

            if (counter % 2 == 0) {

                if (firstOperator) {
                    givenFirstOperand(input);
                    firstOperator = false;
                } else {
                    givenSecondOperand(input);
                    firstOperator = true;
                }
            } else {
                givenOperator(input);
            }

            ++counter;
        }
    }


    private void thenRightOperandShouldBe(String expectedValue) {
        assertEquals(expectedValue, mRightOperand);
    }

    private void whenCheckingOperator() {
        mOperator = mCalculator.getOperator();
    }

    private void thenLeftOperandWillBe(String expectedLeftOperandValue) {
        assertEquals(expectedLeftOperandValue, mLeftOperand);
    }

    private void thenOperatorShouldBe(String expectedOperator) {
        assertEquals(expectedOperator, mOperator);
    }

    private void thenRightOperandShouldBeEmpty() {
        assertEquals(new StringBuilder().toString(), mRightOperand);
    }

    private void whenCheckingLeftOperand() {
        mLeftOperand = mCalculator.getLeftOperand().toString();
    }

    private void thenResultWillBe(String expectedResult) {
        assertEquals(expectedResult, mResult);
    }

    private void givenOperationListener(OperationListener operationListener) {
        mOperationListener = new OperationListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinishOperation(String result) {
                mResult = result;
            }

            @Override
            public void onError() {

            }
        };

        mCalculator.setOperationListener(mOperationListener);
    }

    private void whenPerformsOperation() {
        mCalculator.performOperation();
    }

    private void givenSecondOperand(String secondOperand) {
        mCalculator.appendCurrentOperand(secondOperand);
    }

    private void givenOperator(String operator) {
        mCalculator.appendCurrentOperator(operator);
    }

    private void givenFirstOperand(String firstOperand) {
        mCalculator.appendCurrentOperand(firstOperand);
    }

    private void whenCheckingRightOperand() {
        mRightOperand = mCalculator.getRightOperand().toString();
    }
}
