package com.mvp.demo.sortmvp;

import com.mvp.demo.sortmvp.methods.BubbleSort;
import com.mvp.demo.sortmvp.methods.MergeSort;
import com.mvp.demo.sortmvp.methods.QuickSort;
import com.mvp.demo.sortmvp.methods.SortMethod;

/**
 * Created by jatempa on 9/23/17.
 */

public class SortMethodPresenterImpl implements SortMethodPresenter {
    private SortMethodView view;
    private SortMethod sortMethod;

    @Override
    public void setView(SortMethodView view) {
        this.view = view;
    }

    @Override
    public void setSortMethod(String method) {
        // Get sort method
        switch (method) {
            case "BubbleSort":
                this.sortMethod = new BubbleSort();
                break;
            case "MergeSort":
                this.sortMethod = new MergeSort();
                break;
            case "QuickSort":
                this.sortMethod = new QuickSort();
                break;
        }
    }

    @Override
    public SortMethod getSortMethod() {
        return sortMethod;
    }

    @Override
    public void sortValues(String values) {
        // Validate parameters
        if ((view != null)) {
            view.showProgressBar();
            if ((getSortMethod() != null) && (values != null)) {
                // Parse input values from string to int array
                int[] inputValues = convertInputValues(values);
                // Get result
                int[] outputValues = getSortMethod().sort(inputValues);
                // Return the result
                view.hideProgressBar();
                view.showResultLabel();
                view.showResult(setFormatOutputValues(outputValues));
                view.showBtnClear();
                view.resetValues();
            }
        }
    }

    @Override
    public int[] convertInputValues(String values) {
        String[] inputValues = values.split(",");
        int[] n = new int[inputValues.length];

        for (int i = 0; i < inputValues.length; i++) {
            String val = inputValues[i].trim();
            try {
                if (!val.equals("")) {
                    n[i] = Integer.parseInt(val);
                }
            } catch (NumberFormatException nfe) {
                val = val.replaceAll("(?<=\\d) +(?=\\d)", "");
                if (val.matches("\\d+")) {
                    n[i] = Integer.parseInt(val);
                }
            }
        }

        return n;
    }

    @Override
    public String setFormatOutputValues(int[] values) {
        String result = "";

        for (int i = 0; i < values.length; i++) {
            if (i < (values.length - 1)) {
                result += values[i] + ", ";
            } else {
                result += values[i];
            }
        }

        return result;
    }
}
