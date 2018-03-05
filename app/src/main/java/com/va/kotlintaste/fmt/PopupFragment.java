package com.va.kotlintaste.fmt;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.va.kotlintaste.R;

/**
 * @author Junmeng.Chen
 * @date 2018/3/5
 */
public class PopupFragment extends AppCompatDialogFragment {

    public static final String TAG = PopupFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popup, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(getActivity(), getTheme());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            Fragment fragment = Fragment.instantiate(getActivity(), BlankFragment.class
                    .getName(), null);
            getChildFragmentManager().beginTransaction().add(R.id.content, fragment).commit();
        }
    }

    static class BottomSheetDialog extends android.support.design.widget.BottomSheetDialog {

        public BottomSheetDialog(@NonNull Context context, int theme) {
            super(context, theme);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            int width = getContext().getResources().getDimensionPixelSize(R.dimen
                    .bottom_sheet_width);
            getWindow().setLayout(width > 0 ? width : ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }
}
