package com.example.actcalcu;

public class Suma extends BaseOperationActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_suma;
    }

    @Override
    protected String getOperationTitle() {
        return getString(R.string.sum_title);
    }

    @Override
    protected String getOperationSymbol() {
        return "+";
    }

    @Override
    protected double resolveOperation(double first, double second) {
        return first + second;
    }

    @Override
    protected void onResultCalculated(double first, double second, double result) {
        showMessage(getString(R.string.result_template, formatNumber(result)));
    }
}
