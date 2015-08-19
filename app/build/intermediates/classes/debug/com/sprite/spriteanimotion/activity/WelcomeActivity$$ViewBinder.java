// Generated code from Butter Knife. Do not modify!
package com.sprite.spriteanimotion.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WelcomeActivity$$ViewBinder<T extends com.sprite.spriteanimotion.activity.WelcomeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492987, "field 'mWelcomeView'");
    target.mWelcomeView = finder.castView(view, 2131492987, "field 'mWelcomeView'");
    view = finder.findRequiredView(source, 2131492988, "field 'mTvPass'");
    target.mTvPass = finder.castView(view, 2131492988, "field 'mTvPass'");
  }

  @Override public void unbind(T target) {
    target.mWelcomeView = null;
    target.mTvPass = null;
  }
}
