// Generated code from Butter Knife. Do not modify!
package com.example.shinelon.demo.demo7;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Demo7Activity$$ViewBinder<T extends com.example.shinelon.demo.demo7.Demo7Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492970, "field 'mWrite' and method 'onClick'");
    target.mWrite = finder.castView(view, 2131492970, "field 'mWrite'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492971, "field 'mRead' and method 'onClick'");
    target.mRead = finder.castView(view, 2131492971, "field 'mRead'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492972, "field 'mChange' and method 'onClick'");
    target.mChange = finder.castView(view, 2131492972, "field 'mChange'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.mWrite = null;
    target.mRead = null;
    target.mChange = null;
  }
}
