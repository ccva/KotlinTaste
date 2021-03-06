package com.va.perfect.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Range;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.base.adapter.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/11/7
 */
public class Test2Activity extends AppCompatActivity {

    public static final String TAG = Test2Activity.class.getSimpleName();

    private MyScrollerView scrollView;

    private TextView tvSticky;

    private Custom2ListView lv;

    private int sh;
    private float lastY;
    private double mLastMoveY;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        setTitle("test 2");

        scrollView = findViewById(R.id.scrollView);

        tvSticky = findViewById(R.id.tv_stick);

        lv = findViewById(R.id.lv);

        lv.setFocusable(false);

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(i + "");
        }

        TestAdapter testAdapter = new TestAdapter();
        testAdapter.setmDataList(strings);
        lv.setAdapter(testAdapter);

        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int[] scrollHead = new int[2];
                scrollView.getLocationOnScreen(scrollHead);
                sh = scrollHead[1];

                int height = scrollView.getMeasuredHeight();

                int height1 = tvSticky.getMeasuredHeight();

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) lv.getLayoutParams();
                layoutParams.height = height - height1;
                lv.setLayoutParams(layoutParams);

                setEventClash();

                tvSticky.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }

    private void setEventClash() {
        scrollView.setEventClash(new MyScrollerView.EventClash() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean judgeWindowRight() {
                int[] tvStickypoint = new int[2];
                tvSticky.getLocationOnScreen(tvStickypoint);
                int i = tvStickypoint[1];

                Range<Integer> range = new Range<>(sh -30,sh +300);
                boolean contains = range.contains(i);
                return contains;
            }

            @Override
            public boolean judgeListViewOnTop() {
                int top = lv.getChildAt(0).getTop();
                return top == 0;
            }

            @Override
            public ListView getView() {
                return lv;
            }
        });
    }

    class TestAdapter extends BaseAdapter {

        private List<String> mDataList;

        public void setmDataList(List<String> mDataList) {
            this.mDataList = mDataList;
        }

        @Override
        public int getCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return mDataList == null ? null : mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
            return view;
        }
    }

    class TestRecyclerViewAdapter extends BaseRecyclerAdapter<String> {

        public TestRecyclerViewAdapter(Context mContext, List<String> dataList) {
            super(mContext, dataList);
        }

        @Override
        public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
            return new TestViewHolder(view);
        }

        @Override
        protected void onBindView(BaseRecyclerViewHolder holder, int position) {

        }
    }

    class TestViewHolder extends BaseRecyclerViewHolder {

        public TestViewHolder(View itemView) {
            super(itemView);
        }
    }
}
