package com.mvp.demo.sortmvp.di;

import android.app.Application;

/**
 * Created by jatempa on 9/23/17.
 */

public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .presenterModule(new PresenterModule())
                .build();
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
