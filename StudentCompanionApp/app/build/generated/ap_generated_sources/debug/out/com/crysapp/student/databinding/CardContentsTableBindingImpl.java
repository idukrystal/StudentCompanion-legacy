package com.crysapp.student.databinding;
import com.crysapp.student.R;
import com.crysapp.student.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CardContentsTableBindingImpl extends CardContentsTableBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.image, 5);
        sViewsWithIds.put(R.id.images, 6);
    }
    // views
    @NonNull
    private final android.widget.ImageView mboundView1;
    @NonNull
    private final android.widget.CheckBox mboundView4;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CardContentsTableBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private CardContentsTableBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[2]
            , (android.widget.FrameLayout) bindings[5]
            , (android.widget.ImageView) bindings[6]
            , (androidx.cardview.widget.CardView) bindings[0]
            , (android.widget.TextView) bindings[3]
            );
        this.corse.setTag(null);
        this.mboundView1 = (android.widget.ImageView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView4 = (android.widget.CheckBox) bindings[4];
        this.mboundView4.setTag(null);
        this.midCard.setTag(null);
        this.times.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.item == variableId) {
            setItem((com.crysapp.student.models.Class) variable);
        }
        else if (BR.presenter == variableId) {
            setPresenter((com.crysapp.student.presenter.ItemPresenter) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.crysapp.student.models.Class Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }
    public void setPresenter(@Nullable com.crysapp.student.presenter.ItemPresenter Presenter) {
        this.mPresenter = Presenter;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.presenter);
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
        com.crysapp.student.models.Class item = mItem;
        java.lang.String itemGetCourse = null;
        boolean itemIsSelected = false;
        com.crysapp.student.presenter.ItemPresenter presenter = mPresenter;
        java.lang.String presenterGetSecondaryTextItem = null;
        int itemIsSelectedViewVISIBLEViewINVISIBLE = 0;
        boolean itemIsRemind = false;

        if ((dirtyFlags & 0x7L) != 0) {


            if ((dirtyFlags & 0x5L) != 0) {

                    if (item != null) {
                        // read item.getCourse()
                        itemGetCourse = item.getCourse();
                        // read item.isSelected
                        itemIsSelected = item.isSelected;
                        // read item.isRemind()
                        itemIsRemind = item.isRemind();
                    }
                if((dirtyFlags & 0x5L) != 0) {
                    if(itemIsSelected) {
                            dirtyFlags |= 0x10L;
                    }
                    else {
                            dirtyFlags |= 0x8L;
                    }
                }


                    // read item.isSelected ? View.VISIBLE : View.INVISIBLE
                    itemIsSelectedViewVISIBLEViewINVISIBLE = ((itemIsSelected) ? (android.view.View.VISIBLE) : (android.view.View.INVISIBLE));
            }

                if (presenter != null) {
                    // read presenter.getSecondaryText(item)
                    presenterGetSecondaryTextItem = presenter.getSecondaryText(item);
                }
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.corse, itemGetCourse);
            this.mboundView1.setVisibility(itemIsSelectedViewVISIBLEViewINVISIBLE);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.mboundView4, itemIsRemind);
        }
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.times, presenterGetSecondaryTextItem);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): presenter
        flag 2 (0x3L): null
        flag 3 (0x4L): item.isSelected ? View.VISIBLE : View.INVISIBLE
        flag 4 (0x5L): item.isSelected ? View.VISIBLE : View.INVISIBLE
    flag mapping end*/
    //end
}