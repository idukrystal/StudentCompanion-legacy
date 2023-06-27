package com.crysart.student.ui;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
public abstract class OptionsSelectWrapper {
    public static void showOptions(String[] options,Context ct,OnClickListener o,int p){
         new MaterialAlertDialogBuilder(ct).setTitle("Select Frequency").setItems(options,o).show();
    }
}




