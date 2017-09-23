package com.mvp.demo.sortmvp;

import com.mvp.demo.sortmvp.methods.SortMethod;

/**
 * Created by jatempa on 9/23/17.
 */

public interface SortMethodPresenter {
    void setView(SortMethodView view);
    void sortValues(String method, String values);
}
