package com.example.actcalcu;

public class Resta extends BaseOperationActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_resta;
    }

    @Override
    protected String getOperationTitle() {
        return getString(R.string.resta_title);
    }

    @Override
    protected String getOperationSymbol() {
        return "-";
    }

    @Override
    protected double resolveOperation(double first, double second) {
        return first - second;
    }

    @Override
    protected void onResultCalculated(double first, double second, double result) {
        showMessage(getString(R.string.result_template, formatNumber(result)));
    }
}
