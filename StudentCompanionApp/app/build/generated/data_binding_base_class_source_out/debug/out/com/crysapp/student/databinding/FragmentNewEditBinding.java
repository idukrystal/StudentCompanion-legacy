// Generated by view binder compiler. Do not edit!
package com.crysapp.student.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.crysapp.student.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentNewEditBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppBarLayout appbar;

  @NonNull
  public final TextInputLayout codeInput;

  @NonNull
  public final TextInputEditText codeInputField;

  @NonNull
  public final TextInputLayout dayInput;

  @NonNull
  public final AutoCompleteTextView dayInputField;

  @NonNull
  public final LinearLayout ll;

  @NonNull
  public final CheckBox remind;

  @NonNull
  public final Button saveb;

  @NonNull
  public final ConstraintLayout sheet;

  @NonNull
  public final TextInputLayout startInput;

  @NonNull
  public final TextInputEditText startInputField;

  @NonNull
  public final TextInputLayout stopInput;

  @NonNull
  public final TextInputEditText stopInputField;

  @NonNull
  public final MaterialToolbar toolbar;

  @NonNull
  public final TextView war;

  private FragmentNewEditBinding(@NonNull ConstraintLayout rootView, @NonNull AppBarLayout appbar,
      @NonNull TextInputLayout codeInput, @NonNull TextInputEditText codeInputField,
      @NonNull TextInputLayout dayInput, @NonNull AutoCompleteTextView dayInputField,
      @NonNull LinearLayout ll, @NonNull CheckBox remind, @NonNull Button saveb,
      @NonNull ConstraintLayout sheet, @NonNull TextInputLayout startInput,
      @NonNull TextInputEditText startInputField, @NonNull TextInputLayout stopInput,
      @NonNull TextInputEditText stopInputField, @NonNull MaterialToolbar toolbar,
      @NonNull TextView war) {
    this.rootView = rootView;
    this.appbar = appbar;
    this.codeInput = codeInput;
    this.codeInputField = codeInputField;
    this.dayInput = dayInput;
    this.dayInputField = dayInputField;
    this.ll = ll;
    this.remind = remind;
    this.saveb = saveb;
    this.sheet = sheet;
    this.startInput = startInput;
    this.startInputField = startInputField;
    this.stopInput = stopInput;
    this.stopInputField = stopInputField;
    this.toolbar = toolbar;
    this.war = war;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentNewEditBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentNewEditBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_new_edit, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentNewEditBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appbar;
      AppBarLayout appbar = ViewBindings.findChildViewById(rootView, id);
      if (appbar == null) {
        break missingId;
      }

      id = R.id.code_input;
      TextInputLayout codeInput = ViewBindings.findChildViewById(rootView, id);
      if (codeInput == null) {
        break missingId;
      }

      id = R.id.code_input_field;
      TextInputEditText codeInputField = ViewBindings.findChildViewById(rootView, id);
      if (codeInputField == null) {
        break missingId;
      }

      id = R.id.day_input;
      TextInputLayout dayInput = ViewBindings.findChildViewById(rootView, id);
      if (dayInput == null) {
        break missingId;
      }

      id = R.id.day_input_field;
      AutoCompleteTextView dayInputField = ViewBindings.findChildViewById(rootView, id);
      if (dayInputField == null) {
        break missingId;
      }

      id = R.id.ll;
      LinearLayout ll = ViewBindings.findChildViewById(rootView, id);
      if (ll == null) {
        break missingId;
      }

      id = R.id.remind;
      CheckBox remind = ViewBindings.findChildViewById(rootView, id);
      if (remind == null) {
        break missingId;
      }

      id = R.id.saveb;
      Button saveb = ViewBindings.findChildViewById(rootView, id);
      if (saveb == null) {
        break missingId;
      }

      ConstraintLayout sheet = (ConstraintLayout) rootView;

      id = R.id.start_input;
      TextInputLayout startInput = ViewBindings.findChildViewById(rootView, id);
      if (startInput == null) {
        break missingId;
      }

      id = R.id.start_input_field;
      TextInputEditText startInputField = ViewBindings.findChildViewById(rootView, id);
      if (startInputField == null) {
        break missingId;
      }

      id = R.id.stop_input;
      TextInputLayout stopInput = ViewBindings.findChildViewById(rootView, id);
      if (stopInput == null) {
        break missingId;
      }

      id = R.id.stop_input_field;
      TextInputEditText stopInputField = ViewBindings.findChildViewById(rootView, id);
      if (stopInputField == null) {
        break missingId;
      }

      id = R.id.toolbar;
      MaterialToolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.war;
      TextView war = ViewBindings.findChildViewById(rootView, id);
      if (war == null) {
        break missingId;
      }

      return new FragmentNewEditBinding((ConstraintLayout) rootView, appbar, codeInput,
          codeInputField, dayInput, dayInputField, ll, remind, saveb, sheet, startInput,
          startInputField, stopInput, stopInputField, toolbar, war);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
