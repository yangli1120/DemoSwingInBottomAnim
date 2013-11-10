package per.practice.listviewanim;

import per.practice.listviewanim.lib.SwingInAnimationAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewAnimActivity extends Activity {
    private ListView mLv;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_anim);

        mLv = (ListView)findViewById(R.id.listView);
        mAdapter = new MyAdapter(this, 30);
        SwingInAnimationAdapter mAnimAdapter = new SwingInAnimationAdapter(mAdapter, mLv);

        mLv.setAdapter(mAnimAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_view_anim, menu);
        return true;
    }

    public class MyAdapter extends BaseAdapter {
        private int mItemCount = 30;
        private Context mContext;

        public MyAdapter(Context context, int count) {
            mContext = context;
            mItemCount = count;
        }
        @Override
        public int getCount() {
            return mItemCount;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if(convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.layout_item, null);
                holder.mTv = (TextView)convertView.findViewById(R.id.item_tv);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.mTv.setText("Item " + position);

            return convertView;
        }
    }

    private static class ViewHolder {
        public TextView mTv;
    }
}
