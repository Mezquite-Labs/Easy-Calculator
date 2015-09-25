package com.mezquitelabs.easycalculator;

import com.mezquitelabs.easycalculator.model.Calculator;
import com.mezquitelabs.easycalculator.model.OperationListener;
import com.mezquitelabs.easycalculator.util.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CalculatorActivity extends Activity {

    private Calculator mCalculator;
    private TextView mResultTextView;
    private List<Button> mDigitButtons;
    private List<Button> mOperatorButtons;
    private OnClickNumberOrOperator mOnClickNumberOrOperatorListener;
    private OperationListener mOperationListener = new OperationListener() {
        @Override
        public void onStart() {
            // Disable other buttons?
        }

        @Override
        public void onFinishOperation(String result) {
            mResultTextView.setText(result);
        }

        @Override
        public void onError() {
            mResultTextView.setText(getString(R.string.error));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        mCalculator = new Calculator();
        mDigitButtons = new ArrayList<>(10);
        mOperatorButtons = new ArrayList<>(4); // +, -, *, /
        mOnClickNumberOrOperatorListener = new OnClickNumberOrOperator();
        mCalculator.setOperationListener(mOperationListener);

        final WatchViewStub stub = (WatchViewStub)findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mResultTextView = (TextView)stub.findViewById(R.id.resultTextView);
                setDigitsListeners(stub);
                setOperatorListeners(stub);
                setEqualsListener(stub);
                setDotListener(stub);
                setClearListener(stub);
            }
        });
    }

    private void setEqualsListener(WatchViewStub stub) {
        Button equalsButton = (Button)stub.findViewById(R.id.equals_btn);
        equalsButton.setOnClickListener(new OnEqualsButtonListener());
    }

    private void setDotListener(WatchViewStub stub) {
        Button dotButton = (Button)stub.findViewById(R.id.dot_btn);
        dotButton.setOnClickListener(new OnDotButtonListener());
    }

    private void setClearListener(WatchViewStub stub) {
        Button clearButton = (Button)stub.findViewById(R.id.clear_btn);
        clearButton.setOnClickListener(new OnClearButtonListener());
    }

    private void setDigitsListeners(WatchViewStub stub) {
        // we loop through all the digits we support (0-9)
        for (int i = 0; i <= 9; ++i) {
            int resourceId = Utils.NameLocator.getResourceIdForDigit(i);
            Button digitButton = (Button)stub.findViewById(resourceId);
            digitButton.setOnClickListener(mOnClickNumberOrOperatorListener);
            mDigitButtons.add(digitButton);
        }
    }

    private void setOperatorListeners(WatchViewStub stub) {
        for (int resourceId : Utils.NameLocator.getResourceIdOperandList()) {
            Button operandButton = (Button)stub.findViewById(resourceId);
            operandButton.setOnClickListener(mOnClickNumberOrOperatorListener);
            mOperatorButtons.add(operandButton);
        }
    }


    private class OnClickNumberOrOperator implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            TextView textView = (TextView)v;
            CharSequence nextInputText = textView.getText();
            boolean isDigit = TextUtils.isDigitsOnly(nextInputText);

            if (isDigit) {
                mCalculator.appendCurrentOperand(nextInputText);

                if(mCalculator.getIsCurrentLeft()==true)
                    mResultTextView.setText(mCalculator.getLeftOperand());
                else
                    mResultTextView.setText(mCalculator.getRightOperand());
            } else {
                if(mCalculator.getIsAnOperandEmpty() == false) {
                    if (mCalculator.getOperator() == null) {
                        mCalculator.appendCurrentOperator(nextInputText);
                    } else {
                        mCalculator.performOperation();
                        mCalculator.appendCurrentOperator(nextInputText);
                    }
                }
            }

        }
    }


    private class OnEqualsButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // can't perform operation without operator, we do nothing
            if (mCalculator.getOperator() != null) {
                mCalculator.performOperation();
                mResultTextView.setText(mCalculator.getResult());
                if(mCalculator.ismInvalidOperation()==true)
                    Toast.makeText(getApplicationContext(), mCalculator.getmErrorMsg(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class OnDotButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (TextUtils.isDigitsOnly(mResultTextView.getText())) {
                TextView textView = (TextView)v;
                CharSequence nextInputText = textView.getText();
                mCalculator.appendCurrentOperand(nextInputText);
                if(mCalculator.getIsCurrentLeft()==true)
                    mResultTextView.setText(mCalculator.getLeftOperand());
                else
                    mResultTextView.setText(mCalculator.getRightOperand());
            }
        }
    }

    private class OnClearButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mResultTextView.setText("0");
            mCalculator.clear();
        }
    }
}
