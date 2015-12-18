package com.andyiac.arch.module;

import android.content.Context;

import com.andyiac.arch.ClientApplication;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(
        injects = {ClientApplication.class},
        includes = {DataModule.class},
        library = true
)

public class AppModule {
    Context ctx;

    public AppModule(Context context) {
        this.ctx = context;
    }

    @Provides
    Context provideContexts() {
        return this.ctx;
    }

    @Provides
    ClientApplication provideClientApplication() {
        return ClientApplication.getInstance();

    }

    @Provides
    @Singleton
    @Named("Root")
    File provideCacheDir(Context ctx) {
        return ctx.getCacheDir();
    }

    @Provides
    @Singleton
    @Named("Http")
    File provideHttpCacheDir(@Named("Root") File root) {
        return new File(root, "http");
    }

    @Provides
    @Singleton
    @Named("Data")
    File provideDataCacheDir(@Named("Root") File data) {
        return new File(data, "data");
    }


}
