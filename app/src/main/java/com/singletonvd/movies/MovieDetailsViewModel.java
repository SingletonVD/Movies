package com.singletonvd.movies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailsViewModel extends AndroidViewModel {

    private int movieId;
    MutableLiveData<List<Review>> reviews = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MovieDao movieDao;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<Movie> getFavoriteMovie(int movieId) {
        return movieDao.getFavoriteMovie(movieId);
    }

    public void addMovie(Movie movie) {
        Disposable disposable = movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void removeMovie(int moveId) {
        Disposable disposable = movieDao.removeMovie(moveId)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void loadReviews(int movieId) {
        if (movieId == this.movieId) {
            return;
        }
        this.movieId = movieId;

        Disposable disposable = ApiFactory.apiService.loadReviews(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> isLoading.setValue(true))
                .map(ReviewsResponse::getReviews)
                .doOnSuccess(reviews::setValue)
                .doOnError(e -> reviews.setValue(new ArrayList<>()))
                .doAfterTerminate(() -> isLoading.setValue(false))
                .subscribe();
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
