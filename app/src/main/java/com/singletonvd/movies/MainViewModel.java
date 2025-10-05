package com.singletonvd.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private static final String LOG_TAG = "MainViewModel";

    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isError = new MutableLiveData<>(false);
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final ApiService apiService = ApiFactory.apiService;

    private int page = 1;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadMovies() {
        loadMovies(page++);
    }

    public void loadMovies(int page) {
        Disposable disposable = apiService.loadMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> {
                    isLoading.setValue(true);
                    isError.setValue(false);
                })
                .doAfterTerminate(() -> isLoading.setValue(false))
                .subscribe(
                        moviesResponse -> {
                            List<Movie> loadedMovies = movies.getValue();
                            if (loadedMovies != null) {
                                loadedMovies.addAll(moviesResponse.getMovies());
                                movies.setValue(loadedMovies);
                            } else {
                                movies.setValue(moviesResponse.getMovies());
                            }
                        },
                        error -> {
                            isError.setValue(true);
                            Log.d(
                                    LOG_TAG,
                                    String.format("Error loading movies: %s", error.toString())
                            );
                        }
                );
        compositeDisposable.add(disposable);
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<Boolean> getIsError() {
        return isError;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
