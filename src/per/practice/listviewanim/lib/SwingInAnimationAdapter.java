package per.practice.listviewanim.lib;

import per.practice.listviewanim.R;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

public class SwingInAnimationAdapter extends AnimationAdapter {
    private Animation mSwingInBottomAnim;
    private Animation mSwingInTopAnim;

    public SwingInAnimationAdapter(BaseAdapter adapter,
            AbsListView absListView) {
        super(adapter, absListView);

        mSwingInBottomAnim = AnimationUtils.loadAnimation(
                absListView.getContext(), R.anim.anim_swing_in_bottom);
        mSwingInTopAnim = AnimationUtils.loadAnimation(
                absListView.getContext(), R.anim.anim_swing_in_bottom);
    }

    @Override
    protected Animation getSwingInBottomAnimation() {
        return mSwingInBottomAnim;
    }

    @Override
    protected Animation getSwingInTopAnimation() {
        return mSwingInTopAnim;
    }
}
