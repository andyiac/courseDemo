package com.andyiac.arch;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.andyiac.arch.module.AppModule;
import com.andyiac.arch.module.Injector;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;


public class ClientApplication extends Application implements Injector {

    private static final String LOGGER_TAG = "<<=TAG=>>";

    private ObjectGraph objectGraph;

    private static ClientApplication mClientApplication;

    public void onCreate() {
        super.onCreate();
        mClientApplication = this;
        initStetho();
        initLogger();

        initDagger();
    }

    public static ClientApplication getInstance() {
        return mClientApplication;
    }

    private void initDagger() {
        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);
    }

    private void initLogger() {
        Logger.init(LOGGER_TAG)               // default PRETTYLOGGER or use just init()
                .setMethodCount(3)            // default 2
                .setLogLevel(LogLevel.FULL)   // default LogLevel.FULL
                .setMethodOffset(2);          // default 0
//              .hideThreadInfo()             // default shown
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    public List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
    }

    @Override
    public void inject(Object target) {
        this.objectGraph.inject(target);
    }

    @Override
    public ObjectGraph plus(Object[] modules) {
        return objectGraph.plus(modules);
    }

    public ObjectGraph plus(Injector injector) {
        return this.objectGraph.plus(injector.getModules().toArray());
    }
}
