package com.mvp.demo.sortmvp;

/**
 * Created by jatempa on 9/23/17.
 */

public interface SortMethodView {
    void getValues();
    void showProgressBar();
    void hideProgressBar();
    void showBtnClear();
    void hideBtnClear();
    void showResult(String result);
    void resetValues();
}
