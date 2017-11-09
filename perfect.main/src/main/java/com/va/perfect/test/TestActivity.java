package com.va.perfect.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.base.adapter.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/11/6
 */
public class TestActivity extends AppCompatActivity {

    public static final String TAG = TestActivity.class.getSimpleName();

    private ScrollView scrollView;

    private TextView tvSticky;

    private ListView lv;

    private int tvStickyTop;
    private int sh;
    private float lastY;
    private float mLastMoveY;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setTitle("test 1");

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

                tvSticky.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


        lv.setOnTouchListener((v, event) -> {

            int action = event.getAction();

            if (action == MotionEvent.ACTION_DOWN) {
                lastY = event.getY();
                scrollView.requestDisallowInterceptTouchEvent(true);
                Log.i(TAG, "onCreate: ACTION_DOWN lv " + "sh = " + sh);
                return true;
            }

            if (action == MotionEvent.ACTION_MOVE) {
                float y = event.getY();
                if (mLastMoveY == 0) {
                    mLastMoveY = lastY;
                }
                float moveDirection = y - lastY;
                mLastMoveY = y;
                Log.i(TAG, "onCreate: motionEventY is " + y);
                lastY = y;
                int[] tvStickypoint = new int[2];
                tvSticky.getLocationOnScreen(tvStickypoint);
                int i = tvStickypoint[1];
                Log.i(TAG, "onCreate: lv " + "sh = " + sh + " i = " + i);
                // 判断需要悬浮的窗体的位置 是否 到达需要悬浮的位置，如果没有到达指定位置则 请求 ScrollView 拦截事件，否则进行进一步判断
                if (i <= sh) {
                    //悬浮窗体到达指定位置，进行下一步判断
                    int top = lv.getChildAt(0).getTop();
                    // 判断 listView 是否滑动到第一条，如果没有滑动到第一条，则 事件交由ListView处理，否则进行进一步判断
                    if (top == 0) {
                        if (moveDirection < 0) {
                            //如果是 向上滑动的情况，则 事件交由listView处理
                            // listView 滑动 请求父布局 不拦截
                            scrollView.requestDisallowInterceptTouchEvent(true);
                            Log.i(TAG, "悬浮窗体到达指定位置 listView 滑动到了第一条 如果是 向上滑动的情况，则 事件交由listView处理");
                        } else {
                            // 如果是 向下滑动的情况，则 事件交由ScrollView 处理
                            // scrollView 滑动 请求父布局 拦截
                            scrollView.requestDisallowInterceptTouchEvent(false);
                            Log.i(TAG, "悬浮窗体到达指定位置 listView 滑动到了第一条 如果是 向下滑动的情况，则 事件交由ScrollView 处理");
                        }
                    } else {
                        // listView 没有滑动到 第一条，事件交由 listView 处理
                        // listView 滑动 请求父布局 不拦截
                        Log.i(TAG, "悬浮窗体到达指定位置 listView 没有滑动到 第一条，事件交由 listView 处理");
                        scrollView.requestDisallowInterceptTouchEvent(true);
                    }
                } else {
                    //悬浮窗体没有到达指定位置，事件交由ScrollView 处理
                    // scrollView 滑动 请求父布局 拦截
                    scrollView.requestDisallowInterceptTouchEvent(false);
                    Log.i(TAG, "悬浮窗体没有到达指定位置，事件交由ScrollView 处理");
                }
            }

            return false;
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
