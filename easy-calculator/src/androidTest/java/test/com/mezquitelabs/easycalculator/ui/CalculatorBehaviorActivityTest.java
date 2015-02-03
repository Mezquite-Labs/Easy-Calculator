package test.com.mezquitelabs.easycalculator.ui;

import com.mezquitelabs.easycalculator.CalculatorActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;


@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class CalculatorBehaviorActivityTest {

    @Test
    public void shouldInstantiateCalculatorActivity() throws Exception {
        assertTrue(Robolectric.buildActivity(CalculatorActivity.class).create().get() != null);
    }

}
