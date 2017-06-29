// Generated code from Butter Knife. Do not modify!
package com.example.shinelon.demo.demo6;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Demo6Activity$$ViewBinder<T extends com.example.shinelon.demo.demo6.Demo6Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492968, "field 'request' and method 'onClick'");
    target.request = finder.castView(view, 2131492968, "field 'request'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
    view = finder.findRequiredView(source, 2131492969, "field 'content'");
    target.content = finder.castView(view, 2131492969, "field 'content'");
  }

  @Override public void unbind(T target) {
    target.request = null;
    target.content = null;
  }
}
