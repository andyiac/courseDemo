package com.andyiac.arch.module;

import android.content.Context;
import android.net.Uri;

import com.google.gson.Gson;
import com.andyiac.arch.BuildConfig;
import com.andyiac.arch.R;
import com.andyiac.arch.api.ApiService;
import com.andyiac.arch.utils.L;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.internal.DiskLruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;


@Module(
        library = true,
        complete = false
)

public class DataModule {
    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Picasso providePicasso(OkHttpClient okHttpClient, Context ctx) {
        Picasso.Builder builder = new Picasso.Builder(ctx);
        builder.downloader(new OkHttpDownloader(okHttpClient))
                .listener(new Picasso.Listener() {
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        L.e(exception + "Picasso load image failed: " + uri.toString());
                    }
                })
                .indicatorsEnabled(false)
                .loggingEnabled(false);
        return builder.build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttp(Cache cache) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCache(cache);
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        return okHttpClient;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(@Named("Http") File httpCacheDir) {
        //100M;
        int cacheSize = 1024 * 1024 * 100;
        try {
            return new Cache(httpCacheDir, cacheSize);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            L.e("install http cache false");
        }

        return null;
    }


    @Provides
    @Singleton
    DiskLruCache provideDataCache(@Named("Data") File cacheDir) {
        DiskLruCache cache = null;
        try {
            //10M
            cache = DiskLruCache.open(cacheDir, BuildConfig.VERSION_CODE, 1, 1024 * 1024 * 10);
        } catch (IOException e) {
            e.printStackTrace();
            L.e(e + "install data cache failed");
        }
        return cache;
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter(Gson gson, OkHttpClient okHttpClient, ErrorHandler errorHandler) {
        return new RestAdapter.Builder()
                .setErrorHandler(errorHandler)
                .setClient(new OkClient(okHttpClient))
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setEndpoint("https://api.coursera.org")
                .build();
    }


    @Provides
    @Singleton
    ErrorHandler provideErrorHandler(final Context ctx) {
        return new ErrorHandler() {
            @Override
            public Throwable handleError(RetrofitError retrofitError) {
                L.e(retrofitError + "请求出现错误:%s" + retrofitError.getUrl());
                RetrofitError.Kind kind = retrofitError.getKind();
                String message;
                if (RetrofitError.Kind.NETWORK.equals(kind)) {
                    message = ctx.getString(R.string.network_error);
                } else if (RetrofitError.Kind.HTTP.equals(kind)) {
                    message = ctx.getString(R.string.http_error);
                } else if (RetrofitError.Kind.CONVERSION.equals(kind)) {
                    message = ctx.getString(R.string.conversion_error);
                } else {
                    message = ctx.getString(R.string.unexpected_error);
                }
                return new Exception(message);
            }
        };
    }

    @Provides
    @Singleton
    ApiService provideApiService(RestAdapter restAdapter) {
        return restAdapter.create(ApiService.class);
    }
}
