package com.crysapp.student.databinding;
import com.crysapp.student.R;
import com.crysapp.student.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class AddActivityCardBindingImpl extends AddActivityCardBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appbar, 6);
        sViewsWithIds.put(R.id.toolbar, 7);
        sViewsWithIds.put(R.id.saveb, 8);
        sViewsWithIds.put(R.id.type_group, 9);
        sViewsWithIds.put(R.id.code_input, 10);
        sViewsWithIds.put(R.id.date_input, 11);
        sViewsWithIds.put(R.id.start_input, 12);
        sViewsWithIds.put(R.id.pre_text, 13);
        sViewsWithIds.put(R.id.freq_input, 14);
        sViewsWithIds.put(R.id.pre_date_input, 15);
        sViewsWithIds.put(R.id.pre_day_input_field, 16);
        sViewsWithIds.put(R.id.pre_start_input, 17);
        sViewsWithIds.put(R.id.pre_start_input_field, 18);
        sViewsWithIds.put(R.id.remind, 19);
        sViewsWithIds.put(R.id.war, 20);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener classSwitchandroidCheckedAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of temp.duringClass
            //         is temp.setDuringClass((boolean) callbackArg_0)
            boolean callbackArg_0 = classSwitch.isChecked();
            // localize variables for thread safety
            // temp
            com.crysart.student.models.Activities temp = mTemp;
            // temp.duringClass
            boolean tempDuringClass = false;
            // temp != null
            boolean tempJavaLangObjectNull = false;



            tempJavaLangObjectNull = (temp) != (null);
            if (tempJavaLangObjectNull) {




                temp.setDuringClass(((boolean) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener codeInputFieldandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of temp.title
            //         is temp.setTitle((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(codeInputField);
            // localize variables for thread safety
            // temp.title
            java.lang.String tempTitle = null;
            // temp
            com.crysart.student.models.Activities temp = mTemp;
            // temp != null
            boolean tempJavaLangObjectNull = false;



            tempJavaLangObjectNull = (temp) != (null);
            if (tempJavaLangObjectNull) {




                temp.setTitle(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener freqInputFieldandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of temp.frequency
            //         is temp.setFrequency((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(freqInputField);
            // localize variables for thread safety
            // temp.frequency
            java.lang.String tempFrequency = null;
            // temp
            com.crysart.student.models.Activities temp = mTemp;
            // temp != null
            boolean tempJavaLangObjectNull = false;



            tempJavaLangObjectNull = (temp) != (null);
            if (tempJavaLangObjectNull) {




                temp.setFrequency(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener startInputFieldandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of temp.time
            //         is temp.setTime((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(startInputField);
            // localize variables for thread safety
            // temp.time
            java.lang.String tempTime = null;
            // temp
            com.crysart.student.models.Activities temp = mTemp;
            // temp != null
            boolean tempJavaLangObjectNull = false;



            tempJavaLangObjectNull = (temp) != (null);
            if (tempJavaLangObjectNull) {




                temp.setTime(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public AddActivityCardBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private AddActivityCardBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.appbar.AppBarLayout) bindings[6]
            , (android.widget.Switch) bindings[2]
            , (com.google.android.material.textfield.TextInputLayout) bindings[10]
            , (com.google.android.material.textfield.TextInputEditText) bindings[1]
            , (com.google.android.material.textfield.TextInputLayout) bindings[11]
            , (com.google.android.material.textfield.TextInputEditText) bindings[3]
            , (com.google.android.material.textfield.TextInputLayout) bindings[14]
            , (com.google.android.material.textfield.TextInputEditText) bindings[5]
            , (com.google.android.material.textfield.TextInputLayout) bindings[15]
            , (com.google.android.material.textfield.TextInputEditText) bindings[16]
            , (com.google.android.material.textfield.TextInputLayout) bindings[17]
            , (com.google.android.material.textfield.TextInputEditText) bindings[18]
            , (android.widget.TextView) bindings[13]
            , (android.widget.CheckBox) bindings[19]
            , (android.widget.Button) bindings[8]
            , (android.widget.LinearLayout) bindings[0]
            , (com.google.android.material.textfield.TextInputLayout) bindings[12]
            , (com.google.android.material.textfield.TextInputEditText) bindings[4]
            , (com.google.android.material.appbar.MaterialToolbar) bindings[7]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[9]
            , (android.widget.TextView) bindings[20]
            );
        this.classSwitch.setTag(null);
        this.codeInputField.setTag(null);
        this.dayInputField.setTag(null);
        this.freqInputField.setTag(null);
        this.sheet.setTag(null);
        this.startInputField.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.temp == variableId) {
            setTemp((com.crysart.student.models.Activities) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setTemp(@Nullable com.crysart.student.models.Activities Temp) {
        this.mTemp = Temp;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.temp);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String tempFrequency = null;
        com.crysart.student.models.Activities temp = mTemp;
        java.lang.String tempDate = null;
        java.lang.String tempTitle = null;
        boolean tempDuringClass = false;
        java.lang.String tempTime = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (temp != null) {
                    // read temp.frequency
                    tempFrequency = temp.getFrequency();
                    // read temp.date
                    tempDate = temp.getDate();
                    // read temp.title
                    tempTitle = temp.getTitle();
                    // read temp.duringClass
                    tempDuringClass = temp.getDuringClass();
                    // read temp.time
                    tempTime = temp.getTime();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.classSwitch, tempDuringClass);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.codeInputField, tempTitle);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.dayInputField, tempDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.freqInputField, tempFrequency);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.startInputField, tempTime);
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(this.classSwitch, (android.widget.CompoundButton.OnCheckedChangeListener)null, classSwitchandroidCheckedAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.codeInputField, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, codeInputFieldandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.freqInputField, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, freqInputFieldandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.startInputField, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, startInputFieldandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): temp
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}