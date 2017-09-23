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
    public void sortValues(String method, String values) {
        view.showProgressBar();
        // Validate parameters
        if (method != null && values != null) {
            // Parse input values from string to int array
            int[] inputValues = convertInputValues(values);
            // Get sort method
            switch (method) {
                case "BubbleSort":
                    sortMethod = new BubbleSort();
                    break;
                case "MergeSort":
                    sortMethod = new MergeSort();
                    break;
                case "QuickSort":
                    sortMethod = new QuickSort();
                    break;
            }
            // Get result
            int[] outputValues = sortMethod.sort(inputValues);
            // Return the result
            view.showResultLabel();
            view.showResult(convertOutputValues(outputValues));
            view.hideProgressBar();
        }
    }

    private int[] convertInputValues(String values) {
        String[] inputValues = values.split(",");
        int[] n = new int[inputValues.length];

        for (int i = 0; i < inputValues.length; i++) {
            n[i] = Integer.parseInt(inputValues[i]);
        }

        return n;
    }

    private String convertOutputValues(int[] values) {
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
