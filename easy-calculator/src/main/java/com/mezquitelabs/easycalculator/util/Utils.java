package com.mezquitelabs.easycalculator.util;

import com.mezquitelabs.easycalculator.R;

import android.support.v4.util.SparseArrayCompat;

import java.util.Collection;
import java.util.HashMap;


public class Utils {

    /**
     * We don't care about decimals, if there is a decimal value we return false.
     * @param digits Digits to verify if they are integers only
     * @return true is digits, false if decimal
     */
    public static boolean isDigitsOnly(String digits) {
        // This should use TextUtils but for now, we can't UnitTest that
        // according to http://tools.android.com/tech-docs/unit-testing-support
         for (char c : digits.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }


    public static class NameLocator {

        private static final SparseArrayCompat<Integer> digitsLocator = new SparseArrayCompat<Integer>() {{
            put(0, R.id.zero_btn);
            put(1, R.id.one_btn);
            put(2, R.id.two_btn);
            put(3, R.id.three_btn);
            put(4, R.id.four_btn);
            put(5, R.id.five_btn);
            put(6, R.id.six_btn);
            put(7, R.id.seven_btn);
            put(8, R.id.eight_btn);
            put(9, R.id.nine_btn);
        }};

        private static final HashMap<String, Integer> operandsLocator = new HashMap<String, Integer>() {{
            put("+", R.id.plus_btn);
            put("-", R.id.minus_btn);
            put("*", R.id.prod_btn);
            put("/", R.id.divider_btn);
            //put("=", R.id.equals_btn);
        }};

        public static int getResourceIdForDigit(int digit) {
            int resourceId = digitsLocator.get(digit);
            return resourceId;
        }

        public static int getResourceIdForOperand(String operand) {
            int resourceId = operandsLocator.get(operand);
            return resourceId;
        }

        public static Collection<Integer> getResourceIdOperandList() {
            return operandsLocator.values();
        }

    }

}
