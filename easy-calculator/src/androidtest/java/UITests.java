import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.mezquitelabs.easycalculator.CalculatorActivity;

/**
 * Created by burni on 3/17/15.
 */
public class UITests extends ActivityInstrumentationTestCase2<CalculatorActivity> {

    private Solo solo;

    public UITests(){

        super(CalculatorActivity.class);
    }

    @Override
    public void setUp() throws Exception{
        solo=new Solo(getInstrumentation(),getActivity());
    }

    @Override
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }

    public void testAllNumbers() throws Exception{
        solo.clickOnButton("0");
        solo.clickOnButton("1");
        solo.clickOnButton("2");
        solo.clickOnButton("3");
        solo.clickOnButton("4");
        solo.clickOnButton("5");
        solo.clickOnButton("6");
        solo.clickOnButton("7");
        solo.clickOnButton("8");
        solo.clickOnButton("9");
        assertEquals("0123456789",solo.getText(0).getText().toString());
    }

    public void testOnePlusOne() throws Exception{
        solo.clickOnButton("1");
        solo.clickOnButton("+");
        solo.clickOnButton("1");
        solo.clickOnButton("=");
        assertEquals("2",solo.getText(0).getText().toString());

    }

    public void testClearResult() throws Exception{
        solo.clickOnButton("3");
        solo.clickOnButton("5");
        solo.clickOnButton("-");
        solo.clickOnButton("1");
        solo.clickOnButton("2");
        solo.clickOnButton("=");
        assertEquals("23",solo.getText(0).getText().toString());
        solo.clickOnButton("C");
        assertEquals("0",solo.getText(0).getText().toString());
    }

    public void testLimit() throws Exception{
        for(int i=0;i<20;i++)
            solo.clickOnButton("9");
        solo.clickOnButton("+");
        solo.clickOnButton("1");
        solo.clickOnButton("=");
        assertEquals("ERROR: Out of boundary",solo.getText(0).getText().toString());
    }

    public void testOneDivBy3() throws Exception{
        solo.clickOnButton("1");
        solo.clickOnButton("/");
        solo.clickOnButton("3");
        solo.clickOnButton("=");
        assertEquals("0.333333333333",solo.getText(0).getText().toString());
    }

    public void testMultipleOperations() throws Exception{
        solo.clickOnButton("5");
        solo.clickOnButton("8");
        solo.clickOnButton("+");
        solo.clickOnButton("1");
        solo.clickOnButton("7");
        solo.clickOnButton("-");
        assertEquals("75",solo.getText(0).getText().toString());
        solo.clickOnButton("5");
        solo.clickOnButton("*");
        assertEquals("70",solo.getText(0).getText().toString());
        solo.clickOnButton("9");
        solo.clickOnButton("/");
        assertEquals("630",solo.getText(0).getText().toString());
        solo.clickOnButton("1");
        solo.clickOnButton("2");
        solo.clickOnButton("=");
        assertEquals("52.5",solo.getText(0).getText().toString());
    }

    public void testDivByZero() throws Exception{
        solo.clickOnButton("9");
        solo.clickOnButton("/");
        solo.clickOnButton("0");
        solo.clickOnButton("=");
        assertEquals("ERROR: Invalid Operation",solo.getText(0).getText().toString());
    }

    public void testMultiplyDecimalValues() throws Exception{
        solo.clickOnButton("4");
        solo.clickOnButton(13); //dot
        solo.clickOnButton("7");
        solo.clickOnButton("9");
        solo.clickOnButton("*");
        solo.clickOnButton("8");
        solo.clickOnButton(13); //dot
        solo.clickOnButton("3");
        solo.clickOnButton("2");
        solo.clickOnButton("=");
        assertEquals("39.8528",solo.getText(0).getText().toString());
    }




}
