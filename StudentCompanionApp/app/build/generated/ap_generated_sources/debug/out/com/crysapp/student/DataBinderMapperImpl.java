package com.crysapp.student;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.crysapp.student.databinding.AddActivityCardBindingImpl;
import com.crysapp.student.databinding.CardAttachmentsBindingImpl;
import com.crysapp.student.databinding.CardContentsTableBindingImpl;
import com.crysapp.student.databinding.CardCorseBindingImpl;
import com.crysapp.student.databinding.CardUpcommingBindingImpl;
import com.crysapp.student.databinding.FragmentCurentInfoBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ADDACTIVITYCARD = 1;

  private static final int LAYOUT_CARDATTACHMENTS = 2;

  private static final int LAYOUT_CARDCONTENTSTABLE = 3;

  private static final int LAYOUT_CARDCORSE = 4;

  private static final int LAYOUT_CARDUPCOMMING = 5;

  private static final int LAYOUT_FRAGMENTCURENTINFO = 6;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(6);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.crysapp.student.R.layout.add_activity_card, LAYOUT_ADDACTIVITYCARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.crysapp.student.R.layout.card_attachments, LAYOUT_CARDATTACHMENTS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.crysapp.student.R.layout.card_contents_table, LAYOUT_CARDCONTENTSTABLE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.crysapp.student.R.layout.card_corse, LAYOUT_CARDCORSE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.crysapp.student.R.layout.card_upcomming, LAYOUT_CARDUPCOMMING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.crysapp.student.R.layout.fragment_curent_info, LAYOUT_FRAGMENTCURENTINFO);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ADDACTIVITYCARD: {
          if ("layout/add_activity_card_0".equals(tag)) {
            return new AddActivityCardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for add_activity_card is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDATTACHMENTS: {
          if ("layout/card_attachments_0".equals(tag)) {
            return new CardAttachmentsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_attachments is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDCONTENTSTABLE: {
          if ("layout/card_contents_table_0".equals(tag)) {
            return new CardContentsTableBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_contents_table is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDCORSE: {
          if ("layout/card_corse_0".equals(tag)) {
            return new CardCorseBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_corse is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDUPCOMMING: {
          if ("layout/card_upcomming_0".equals(tag)) {
            return new CardUpcommingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_upcomming is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCURENTINFO: {
          if ("layout/fragment_curent_info_0".equals(tag)) {
            return new FragmentCurentInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_curent_info is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(4);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "item");
      sKeys.put(2, "presenter");
      sKeys.put(3, "temp");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(6);

    static {
      sKeys.put("layout/add_activity_card_0", com.crysapp.student.R.layout.add_activity_card);
      sKeys.put("layout/card_attachments_0", com.crysapp.student.R.layout.card_attachments);
      sKeys.put("layout/card_contents_table_0", com.crysapp.student.R.layout.card_contents_table);
      sKeys.put("layout/card_corse_0", com.crysapp.student.R.layout.card_corse);
      sKeys.put("layout/card_upcomming_0", com.crysapp.student.R.layout.card_upcomming);
      sKeys.put("layout/fragment_curent_info_0", com.crysapp.student.R.layout.fragment_curent_info);
    }
  }
}
