package me.zongren.pullablelayoutplayground;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.zongren.pullablelayout.Main.PullableLayout;
import me.zongren.pullablelayout.View.PullableRecyclerView;

public class MainActivity extends AppCompatActivity {

    private PullableLayout pullableLayout;
    private PullableRecyclerView pullableRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullableRecyclerView = (PullableRecyclerView) findViewById(R.id.recyclerView);

        data = new ArrayList<>();
        for (int i = 0, len = 2; i < len; i++) {
            data.add(i + "");
        }

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(pullableRecyclerView);
        adapter.setData(data);
        pullableRecyclerView.setLayoutManager(linearLayoutManager = new LinearLayoutManager(this));
        pullableRecyclerView.setAdapter(adapter);
        pullableRecyclerView.addOnScrollListener(RecyclerViewScrollHelper.getScrollListener(onScrollBottomFun));
    }


    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.RecyclerHolder> {
        private Context mContext;
        private List<String> dataList = new ArrayList<>();

        public MyRecyclerViewAdapter(RecyclerView recyclerView) {
            this.mContext = recyclerView.getContext();
        }

        public void setData(List<String> dataList) {
            if (null != dataList) {
                this.dataList.clear();
                this.dataList.addAll(dataList);
                notifyDataSetChanged();
            }
        }

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.id_rv_item_layout, parent, false);
            return new RecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, int position) {
            holder.textView.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class RecyclerHolder extends RecyclerView.ViewHolder {
            TextView textView;

            private RecyclerHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv_id_item_layout);
            }
        }
    }

    private RecyclerViewScrollHelper.SimpleScrollDirectionStateListener onScrollBottomFun = new RecyclerViewScrollHelper.SimpleScrollDirectionStateListener() {
        @Override
        public void onScrollToBottom() {
            super.onScrollToBottom();
            Log.e("TTT","滑动到底部");
        }
    };

}
