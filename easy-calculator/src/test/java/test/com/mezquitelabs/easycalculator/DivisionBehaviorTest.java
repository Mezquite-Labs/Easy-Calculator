package test.com.mezquitelabs.easycalculator;

import com.mezquitelabs.easycalculator.model.Calculator;
import com.mezquitelabs.easycalculator.model.OperationListener;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by burni on 2/26/15.
 */
public class DivisionBehaviorTest {
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
    public void divideOperation99DividedBy9ShouldReturn11(){
        givenOperand("99");
        givenOperator("/");
        givenOperand("9");
        performOperation();
        resultShouldBe("11");
    }

    @Test
    public void divideOperation100DividedBy3ShouldReturn33Dot333DotDotDot(){
        givenOperand("100");
        givenOperator("/");
        givenOperand("3");
        performOperation();
        resultShouldBe("33.33333333333333");
    }

    @Test
    public void divideOperation18240DividedByMinus12ShouldReturnMinus1520(){
        givenOperand("18240");
        givenOperator("/");
        givenOperand("-12");
        performOperation();
        resultShouldBe("-1520");
    }

    @Test
    public void divideOperation1Dot23456DividedBy6Dot54321ShouldReturn0Dot18867803417589(){
        givenOperand("1.23456");
        givenOperator("/");
        givenOperand("6.54321");
        performOperation();
        resultShouldBe("0.1886780341758861");
    }

    @Test
    public void divideOperationMinus64DividedByMinus8ShouldReturn8(){
        givenOperand("-64");
        givenOperator("/");
        givenOperand("-8");
        performOperation();
        resultShouldBe("8");
    }

    @Test
    public void OperationShouldReturn7WhenGiven5OperandsAnd4Operators(){
        givenOperation(new String[]{"100","/","10","/","5","/","2","+","6"});
        performOperation();
        resultShouldBe("7");
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
