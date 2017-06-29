// Generated code from Butter Knife. Do not modify!
package com.example.shinelon.demo.demo3;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Demo3Activity$$ViewBinder<T extends com.example.shinelon.demo.demo3.Demo3Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492951, "field 'demo3Click' and method 'onClick'");
    target.demo3Click = finder.castView(view, 2131492951, "field 'demo3Click'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
    view = finder.findRequiredView(source, 2131492952, "field 'demo3Read' and method 'onClick2'");
    target.demo3Read = finder.castView(view, 2131492952, "field 'demo3Read'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick2();
        }
      });
    view = finder.findRequiredView(source, 2131492906, "field 'image'");
    target.image = finder.castView(view, 2131492906, "field 'image'");
    view = finder.findRequiredView(source, 2131492953, "field 'demo3Remove' and method 'onClick3'");
    target.demo3Remove = finder.castView(view, 2131492953, "field 'demo3Remove'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick3();
        }
      });
    view = finder.findRequiredView(source, 2131492954, "field 'demo3Delete' and method 'onClick4'");
    target.demo3Delete = finder.castView(view, 2131492954, "field 'demo3Delete'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick4();
        }
      });
    view = finder.findRequiredView(source, 2131492955, "field 'demo3Size' and method 'onClick5'");
    target.demo3Size = finder.castView(view, 2131492955, "field 'demo3Size'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick5();
        }
      });
  }

  @Override public void unbind(T target) {
    target.demo3Click = null;
    target.demo3Read = null;
    target.image = null;
    target.demo3Remove = null;
    target.demo3Delete = null;
    target.demo3Size = null;
  }
}
