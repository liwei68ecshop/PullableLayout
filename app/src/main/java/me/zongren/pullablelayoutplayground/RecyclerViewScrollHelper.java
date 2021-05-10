package me.zongren.pullablelayoutplayground;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.zongren.pullablelayout.Constant.Direction;
import me.zongren.pullablelayout.Constant.Side;
import me.zongren.pullablelayout.Main.PullableLayout;
import me.zongren.pullablelayout.View.PullableRecyclerView;

/**
 * Created by Smart on 2018/4/26.
 */
public class RecyclerViewScrollHelper {


    //返回滚动监听
    public static RecyclerView.OnScrollListener getScrollListener(final OnScrollDirectionStateListener listener) {

        return new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (listener != null && recyclerView instanceof PullableRecyclerView && recyclerView.getParent() instanceof PullableLayout && newState == 0) {
                    PullableLayout pullableLayout = (PullableLayout) recyclerView.getParent();

                    Direction direction = Direction.NONE;
                    try {
                        Field field = recyclerView.getParent().getClass().getDeclaredField("mCurrentDirection");
                        field.setAccessible(true);
                        direction = ((Direction)field.get(recyclerView.getParent()));
                    }catch (Exception e){
                    }

                    if (direction.equals(Direction.FROM_BOTTOM_TO_TOP) && ((PullableRecyclerView) recyclerView).reachEdgeOfSide(Side.BOTTOM)) {
                        listener.onScrollToBottom();
                    } else if(direction.equals(Direction.FROM_TOP_TO_BOTTOM) && ((PullableRecyclerView) recyclerView).reachEdgeOfSide(Side.TOP)) {
                        listener.onScrollToTop();
                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (listener != null) listener.onScrolled(recyclerView, dx, dy);
            }
        };
    }



    /**
     * 滑动位置改变监听事件,滑动到顶部/底部或者非以上两个位置时
     */
    public interface OnScrollDirectionStateListener {
        /**
         * 滑动到顶部的回调事件
         */
        public void onScrollToTop();

        /**
         * 滑动到底部的回调事件
         */
        public void onScrollToBottom();

        public void onScrolled(RecyclerView recyclerView, int dx, int dy);
    }

    public static class SimpleScrollDirectionStateListener implements OnScrollDirectionStateListener {

        @Override
        public void onScrollToTop() {

        }

        @Override
        public void onScrollToBottom() {

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        }

    }

}
