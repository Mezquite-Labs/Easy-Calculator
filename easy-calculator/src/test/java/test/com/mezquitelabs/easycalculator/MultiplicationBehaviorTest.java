package test.com.mezquitelabs.easycalculator;

import com.mezquitelabs.easycalculator.model.Calculator;
import com.mezquitelabs.easycalculator.model.OperationListener;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by burni on 2/24/15.
 */
public class MultiplicationBehaviorTest {
    private Calculator mCalculator;
    private OperationListener mOperationListener;
    private String mResult;

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUpMethods() {
        mCalculator = new Calculator();
        givenOperationListener(mOperationListener);
    }


    @Test
    public void multiplyOperation2Times5ShouldReturn10(){
        givenOperand("2");
        givenOperator("*");
        givenOperand("5");
        performOperation();
        resultShouldBe("10");
    }

    @Test
    public void multiplyOperationMinus1Times10ShouldReturnMinus10(){
        givenOperand("-1");
        givenOperator("*");
        givenOperand("10");
        performOperation();
        resultShouldBe("-10");
    }

    @Test
    public void multiplyOperationMinus7TimesMinus23ShouldReturn161(){
        givenOperand("-7");
        givenOperator("*");
        givenOperand("-23");
        performOperation();
        resultShouldBe("161");
    }

    @Test
    public void multiplyOperation333Dot333Times43Dot123ShouldReturn14374Dot318959(){
        givenOperand("333.333");
        givenOperator("*");
        givenOperand("43.123");
        performOperation();
        resultShouldBe("14374.318959");
    }

    @Test
    public void operationShouldReturn256With8OperandsAnd7Operators(){
        givenOperation(new String[]{"2","*","2","*","2","*","2","*","2","*","2","*","2","*","2"});
        performOperation();
        resultShouldBe("256");
    }

    @Test
    public void operationShouldReturn100With2DifferentOperatorsAnd3Operands(){
        givenOperation(new String[]{"2", "+", "3", "*", "20"});
        performOperation();
        resultShouldBe("100");
    }



    public void givenOperand(String operand){
        mCalculator.appendCurrentOperand(operand);
    }

    public void givenOperator(String operator){
        mCalculator.appendCurrentOperator(operator);
    }

    public void resultShouldBe(String expected) { assertEquals(expected, mResult);}

    public void performOperation(){ mCalculator.performOperation();}

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

    private void givenOperation(String[] inputs) {
        boolean firstOperator = true;
        int counter = 0;
        for (String input : inputs) {

            if (counter % 2 == 0) {

                if (firstOperator) {
                    givenOperand(input);
                    firstOperator = false;
                } else {
                    givenOperand(input);
                    firstOperator = true;
                }
            } else {
                givenOperator(input);
            }

            ++counter;
        }
    }
}
