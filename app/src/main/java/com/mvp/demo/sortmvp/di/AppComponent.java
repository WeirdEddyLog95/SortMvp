package com.mvp.demo.sortmvp.di;

import com.mvp.demo.sortmvp.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jatempa on 9/23/17.
 */
@Singleton
@Component(modules = {AppModule.class, PresenterModule.class})
public interface AppComponent {
    void inject(MainActivity target);
}
