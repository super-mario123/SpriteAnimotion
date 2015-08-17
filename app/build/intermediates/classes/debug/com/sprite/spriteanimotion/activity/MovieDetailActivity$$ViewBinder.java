// Generated code from Butter Knife. Do not modify!
package com.sprite.spriteanimotion.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MovieDetailActivity$$ViewBinder<T extends com.sprite.spriteanimotion.activity.MovieDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492968, "field 'mScrollView'");
    target.mScrollView = finder.castView(view, 2131492968, "field 'mScrollView'");
    view = finder.findRequiredView(source, 2131492969, "field 'mMovieCover'");
    target.mMovieCover = finder.castView(view, 2131492969, "field 'mMovieCover'");
    view = finder.findRequiredView(source, 2131492973, "field 'mCollectButton'");
    target.mCollectButton = finder.castView(view, 2131492973, "field 'mCollectButton'");
    view = finder.findRequiredView(source, 2131492971, "field 'mMovieTitle'");
    target.mMovieTitle = finder.castView(view, 2131492971, "field 'mMovieTitle'");
    view = finder.findRequiredView(source, 2131492972, "field 'mRating'");
    target.mRating = finder.castView(view, 2131492972, "field 'mRating'");
    view = finder.findRequiredView(source, 2131492975, "field 'mDirector'");
    target.mDirector = finder.castView(view, 2131492975, "field 'mDirector'");
    view = finder.findRequiredView(source, 2131492976, "field 'mActors'");
    target.mActors = finder.castView(view, 2131492976, "field 'mActors'");
    view = finder.findRequiredView(source, 2131492977, "field 'mMovieType'");
    target.mMovieType = finder.castView(view, 2131492977, "field 'mMovieType'");
    view = finder.findRequiredView(source, 2131492980, "field 'mMovieDescription'");
    target.mMovieDescription = finder.castView(view, 2131492980, "field 'mMovieDescription'");
    view = finder.findRequiredView(source, 2131492978, "field 'mMovieYears'");
    target.mMovieYears = finder.castView(view, 2131492978, "field 'mMovieYears'");
  }

  @Override public void unbind(T target) {
    target.mScrollView = null;
    target.mMovieCover = null;
    target.mCollectButton = null;
    target.mMovieTitle = null;
    target.mRating = null;
    target.mDirector = null;
    target.mActors = null;
    target.mMovieType = null;
    target.mMovieDescription = null;
    target.mMovieYears = null;
  }
}
