// Generated code from Butter Knife. Do not modify!
package com.example.shinelon.demo;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.example.shinelon.demo.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492958, "field 'demo1' and method 'onClick1'");
    target.demo1 = finder.castView(view, 2131492958, "field 'demo1'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick1();
        }
      });
    view = finder.findRequiredView(source, 2131492959, "field 'demo2' and method 'onClick2'");
    target.demo2 = finder.castView(view, 2131492959, "field 'demo2'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick2();
        }
      });
    view = finder.findRequiredView(source, 2131492960, "field 'demo3' and method 'onClick3'");
    target.demo3 = finder.castView(view, 2131492960, "field 'demo3'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick3();
        }
      });
    view = finder.findRequiredView(source, 2131492961, "field 'demo4' and method 'onClick4'");
    target.demo4 = finder.castView(view, 2131492961, "field 'demo4'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick4();
        }
      });
    view = finder.findRequiredView(source, 2131492962, "field 'demo5' and method 'onClick5'");
    target.demo5 = finder.castView(view, 2131492962, "field 'demo5'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick5();
        }
      });
    view = finder.findRequiredView(source, 2131492963, "field 'demo6' and method 'onClick6'");
    target.demo6 = finder.castView(view, 2131492963, "field 'demo6'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick6();
        }
      });
    view = finder.findRequiredView(source, 2131492964, "field 'mDemo7' and method 'onClick7'");
    target.mDemo7 = finder.castView(view, 2131492964, "field 'mDemo7'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick7();
        }
      });
    view = finder.findRequiredView(source, 2131492965, "field 'mDemo8' and method 'onClick8'");
    target.mDemo8 = finder.castView(view, 2131492965, "field 'mDemo8'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick8();
        }
      });
  }

  @Override public void unbind(T target) {
    target.demo1 = null;
    target.demo2 = null;
    target.demo3 = null;
    target.demo4 = null;
    target.demo5 = null;
    target.demo6 = null;
    target.mDemo7 = null;
    target.mDemo8 = null;
  }
}
