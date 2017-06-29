// Generated code from Butter Knife. Do not modify!
package com.example.shinelon.demo.demo8;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class demo8Activity$$ViewBinder<T extends com.example.shinelon.demo.demo8.demo8Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492973, "field 'mShow' and method 'onClick'");
    target.mShow = finder.castView(view, 2131492973, "field 'mShow'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
    view = finder.findRequiredView(source, 2131492906, "field 'mImage'");
    target.mImage = finder.castView(view, 2131492906, "field 'mImage'");
  }

  @Override public void unbind(T target) {
    target.mShow = null;
    target.mImage = null;
  }
}
