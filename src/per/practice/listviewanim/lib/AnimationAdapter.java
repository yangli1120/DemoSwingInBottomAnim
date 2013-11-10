package per.practice.listviewanim.lib;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;

public abstract class AnimationAdapter extends BaseAdapter implements OnScrollListener{
    private BaseAdapter mBaseAdapter;
    private AbsListView mAbsLv;

    private int mFirstVisibleItemPosition = 0;
    private int mLastVisibleItemPosition = -1;
    private int mCurScrollState = SCROLL_STATE_IDLE;
    private boolean isForwardDown = true;

    private SparseArray<View> mListItemView = new SparseArray<View>();
    private View mFirstVisibleItemView, mLastVisibleItemView;

    public AnimationAdapter(BaseAdapter adapter, AbsListView absListView) {
        mBaseAdapter = adapter;
        mAbsLv = absListView;

        absListView.setOnScrollListener(this);
    }

    @Override
    public int getCount() {
        return mBaseAdapter == null ? 0 : mBaseAdapter.getCount();
    }

    @Override
    public Object getItem(int position) {
        return mBaseAdapter == null ? null : mBaseAdapter.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return mBaseAdapter == null ? 0l : mBaseAdapter.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mBaseAdapter.getView(position, convertView, parent);

        mListItemView.put(position, convertView);
        convertView.clearAnimation();

        return convertView;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
            int visibleItemCount, int totalItemCount) {
        if(mCurScrollState == SCROLL_STATE_TOUCH_SCROLL &&
                mLastVisibleItemPosition < view.getLastVisiblePosition() &&
                view.getLastVisiblePosition() == firstVisibleItem + visibleItemCount - 1) {
            //cancel the old mLastVisibleItemView's animation
            mLastVisibleItemView = mListItemView.get(view.getLastVisiblePosition() - 1);
            if(mLastVisibleItemView != null)
                mLastVisibleItemView.clearAnimation();

            mLastVisibleItemView = mListItemView.get(view.getLastVisiblePosition());
            if(mLastVisibleItemView != null &&
                    mLastVisibleItemView.getBottom() <= view.getBottom()) {
                mLastVisibleItemView.clearAnimation();
            } else {
                mLastVisibleItemView.startAnimation(getSwingInBottomAnimation());
            }
        }
        mFirstVisibleItemPosition = firstVisibleItem;
        mLastVisibleItemPosition = firstVisibleItem + visibleItemCount - 1;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        mCurScrollState = scrollState;
    }

    protected abstract Animation getSwingInBottomAnimation();
    protected abstract Animation getSwingInTopAnimation();

    public static void Log(String msg) {
        android.util.Log.i("Young Lee", msg);
    }
}
