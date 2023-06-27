// Generated by view binder compiler. Do not edit!
package com.crysapp.student.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.crysapp.student.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomeBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final AppBarLayout appbar;

  @NonNull
  public final BottomNavigationView bottom;

  @NonNull
  public final CollapsingToolbarLayout collapse;

  @NonNull
  public final ViewPager2 currentPager;

  @NonNull
  public final FloatingActionButton fab;

  @NonNull
  public final FragmentContainerView navHostFragment;

  @NonNull
  public final CoordinatorLayout snacks;

  @NonNull
  public final MaterialToolbar toolbar;

  private ActivityHomeBinding(@NonNull CoordinatorLayout rootView, @NonNull AppBarLayout appbar,
      @NonNull BottomNavigationView bottom, @NonNull CollapsingToolbarLayout collapse,
      @NonNull ViewPager2 currentPager, @NonNull FloatingActionButton fab,
      @NonNull FragmentContainerView navHostFragment, @NonNull CoordinatorLayout snacks,
      @NonNull MaterialToolbar toolbar) {
    this.rootView = rootView;
    this.appbar = appbar;
    this.bottom = bottom;
    this.collapse = collapse;
    this.currentPager = currentPager;
    this.fab = fab;
    this.navHostFragment = navHostFragment;
    this.snacks = snacks;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appbar;
      AppBarLayout appbar = ViewBindings.findChildViewById(rootView, id);
      if (appbar == null) {
        break missingId;
      }

      id = R.id.bottom;
      BottomNavigationView bottom = ViewBindings.findChildViewById(rootView, id);
      if (bottom == null) {
        break missingId;
      }

      id = R.id.collapse;
      CollapsingToolbarLayout collapse = ViewBindings.findChildViewById(rootView, id);
      if (collapse == null) {
        break missingId;
      }

      id = R.id.current_pager;
      ViewPager2 currentPager = ViewBindings.findChildViewById(rootView, id);
      if (currentPager == null) {
        break missingId;
      }

      id = R.id.fab;
      FloatingActionButton fab = ViewBindings.findChildViewById(rootView, id);
      if (fab == null) {
        break missingId;
      }

      id = R.id.nav_host_fragment;
      FragmentContainerView navHostFragment = ViewBindings.findChildViewById(rootView, id);
      if (navHostFragment == null) {
        break missingId;
      }

      id = R.id.snacks;
      CoordinatorLayout snacks = ViewBindings.findChildViewById(rootView, id);
      if (snacks == null) {
        break missingId;
      }

      id = R.id.toolbar;
      MaterialToolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new ActivityHomeBinding((CoordinatorLayout) rootView, appbar, bottom, collapse,
          currentPager, fab, navHostFragment, snacks, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}