// Generated by data binding compiler. Do not edit!
package com.crysapp.student.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.crysapp.student.R;
import com.crysapp.student.models.Attachment;
import com.crysapp.student.presenter.ItemPresenter;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class CardAttachmentsBinding extends ViewDataBinding {
  @NonNull
  public final TextView corse;

  @NonNull
  public final View guideline;

  @NonNull
  public final FrameLayout image;

  @NonNull
  public final ImageView images;

  @NonNull
  public final CardView midCard;

  @NonNull
  public final TextView times;

  @Bindable
  protected ItemPresenter mPresenter;

  @Bindable
  protected Attachment mItem;

  protected CardAttachmentsBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView corse, View guideline, FrameLayout image, ImageView images, CardView midCard,
      TextView times) {
    super(_bindingComponent, _root, _localFieldCount);
    this.corse = corse;
    this.guideline = guideline;
    this.image = image;
    this.images = images;
    this.midCard = midCard;
    this.times = times;
  }

  public abstract void setPresenter(@Nullable ItemPresenter presenter);

  @Nullable
  public ItemPresenter getPresenter() {
    return mPresenter;
  }

  public abstract void setItem(@Nullable Attachment item);

  @Nullable
  public Attachment getItem() {
    return mItem;
  }

  @NonNull
  public static CardAttachmentsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.card_attachments, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static CardAttachmentsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<CardAttachmentsBinding>inflateInternal(inflater, R.layout.card_attachments, root, attachToRoot, component);
  }

  @NonNull
  public static CardAttachmentsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.card_attachments, null, false, component)
   */
  @NonNull
  @Deprecated
  public static CardAttachmentsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<CardAttachmentsBinding>inflateInternal(inflater, R.layout.card_attachments, null, false, component);
  }

  public static CardAttachmentsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static CardAttachmentsBinding bind(@NonNull View view, @Nullable Object component) {
    return (CardAttachmentsBinding)bind(component, view, R.layout.card_attachments);
  }
}
