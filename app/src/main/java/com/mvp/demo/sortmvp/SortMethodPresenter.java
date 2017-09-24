package com.mvp.demo.sortmvp;

import com.mvp.demo.sortmvp.methods.SortMethod;

/**
 * Created by jatempa on 9/23/17.
 */

public interface SortMethodPresenter {
    void setView(SortMethodView view);
    void setSortMethod(String method);
    SortMethod getSortMethod();
    void sortValues(String values);
    int[] convertInputValues(String values);
    String setFormatOutputValues(int[] values);
}
