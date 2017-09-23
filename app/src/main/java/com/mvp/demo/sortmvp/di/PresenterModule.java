package com.mvp.demo.sortmvp.di;

import com.mvp.demo.sortmvp.SortMethodPresenter;
import com.mvp.demo.sortmvp.SortMethodPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jatempa on 9/23/17.
 */
@Module
public class PresenterModule {

    @Provides
    public SortMethodPresenter provideSortMethodPresenter(){
        return new SortMethodPresenterImpl();
    }
}
