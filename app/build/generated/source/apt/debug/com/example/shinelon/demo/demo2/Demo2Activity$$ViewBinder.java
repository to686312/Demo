// Generated code from Butter Knife. Do not modify!
package com.example.shinelon.demo.demo2;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Demo2Activity$$ViewBinder<T extends com.example.shinelon.demo.demo2.Demo2Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492950, "field 'xrecyclerview'");
    target.xrecyclerview = finder.castView(view, 2131492950, "field 'xrecyclerview'");
  }

  @Override public void unbind(T target) {
    target.xrecyclerview = null;
  }
}
