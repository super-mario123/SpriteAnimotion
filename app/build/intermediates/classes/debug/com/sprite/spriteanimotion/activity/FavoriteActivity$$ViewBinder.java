// Generated code from Butter Knife. Do not modify!
package com.sprite.spriteanimotion.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FavoriteActivity$$ViewBinder<T extends com.sprite.spriteanimotion.activity.FavoriteActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492982, "field 'mRefreshLayout'");
    target.mRefreshLayout = finder.castView(view, 2131492982, "field 'mRefreshLayout'");
    view = finder.findRequiredView(source, 2131492983, "field 'mRecyclerView'");
    target.mRecyclerView = finder.castView(view, 2131492983, "field 'mRecyclerView'");
    view = finder.findRequiredView(source, 2131492981, "field 'mToolbar'");
    target.mToolbar = finder.castView(view, 2131492981, "field 'mToolbar'");
    view = finder.findRequiredView(source, 2131492984, "field 'mEmptyView'");
    target.mEmptyView = finder.castView(view, 2131492984, "field 'mEmptyView'");
  }

  @Override public void unbind(T target) {
    target.mRefreshLayout = null;
    target.mRecyclerView = null;
    target.mToolbar = null;
    target.mEmptyView = null;
  }
}
