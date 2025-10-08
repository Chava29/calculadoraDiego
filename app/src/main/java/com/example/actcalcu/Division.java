package com.example.actcalcu;

public class Division extends BaseOperationActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_division;
    }

    @Override
    protected String getOperationTitle() {
        return getString(R.string.division_title);
    }

    @Override
    protected String getOperationSymbol() {
        return "÷";
    }

    @Override
    protected double resolveOperation(double first, double second) {
        if (Math.abs(second) < 1e-9) {
            throw new IllegalArgumentException(getString(R.string.error_division_by_zero));
        }
        return first / second;
    }

    @Override
    protected void onResultCalculated(double first, double second, double result) {
        showMessage(getString(R.string.result_template, formatNumber(result)));
    }
}
