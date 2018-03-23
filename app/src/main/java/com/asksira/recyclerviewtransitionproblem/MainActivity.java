package com.asksira.recyclerviewtransitionproblem;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rootView;
    Button recyclerViewButton, scrollViewButton;
    RecyclerView recyclerView;
    ScrollView scrollView;
    LinearLayout linearLayout;

    List<String> items = new ArrayList<>();
    JustAnAdapter adapter;

    public static final int COLLAPSED = 1;
    public static final int EXPANDED = 2;

    int recyclerViewState = COLLAPSED;
    int scrollViewState = COLLAPSED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = findViewById(R.id.rootview);
        recyclerViewButton = findViewById(R.id.btn_recyclerview);
        scrollViewButton = findViewById(R.id.btn_scrollview);
        recyclerView = findViewById(R.id.recyclerview);
        scrollView = findViewById(R.id.sv_ll_container);
        linearLayout = findViewById(R.id.ll_list_container);

        recyclerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerViewState == COLLAPSED) {
                    TransitionManager.beginDelayedTransition(rootView, new ChangeBounds());
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
                    lp.height = MATCH_PARENT;
                    recyclerView.setLayoutParams(lp);
                    recyclerViewState = EXPANDED;
                } else {
                    TransitionManager.beginDelayedTransition(rootView, new ChangeBounds());
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
                    lp.height = 0;
                    recyclerView.setLayoutParams(lp);
                    recyclerViewState = COLLAPSED;
                }
            }
        });

        scrollViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scrollViewState == COLLAPSED) {
                    TransitionManager.beginDelayedTransition(rootView, new ChangeBounds());
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) scrollView.getLayoutParams();
                    lp.height = MATCH_PARENT;
                    scrollView.setLayoutParams(lp);
                    scrollViewState = EXPANDED;
                } else {
                    TransitionManager.beginDelayedTransition(rootView, new ChangeBounds());
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) scrollView.getLayoutParams();
                    lp.height = 0;
                    scrollView.setLayoutParams(lp);
                    scrollViewState = COLLAPSED;
                }
            }
        });

        generateListItem();
        adapter = new JustAnAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        populateScrollViewItems();
    }

    private void generateListItem () {
        for (int i = 1; i < 21; i++) {
            items.add(String.valueOf(i));
        }
    }

    private void populateScrollViewItems(){
        linearLayout.removeAllViews();
        for (String each : items) {
            TextView textview = (TextView) LayoutInflater.from(this).inflate(R.layout.item_text, linearLayout, false);
            textview.setText(each);
            linearLayout.addView(textview);
        }
    }

    public class JustAnAdapter extends RecyclerView.Adapter<JustAnAdapter.JustAViewHolder> {

        Context context;

        public JustAnAdapter(Context context) {
            this.context = context;
        }

        @Override
        public JustAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new JustAViewHolder(LayoutInflater.from(context).inflate(R.layout.item_text, parent, false));
        }

        @Override
        public void onBindViewHolder(JustAViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return items == null ? 0 : items.size();
        }

        public class JustAViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            public JustAViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.item_textview);
            }

            public void bind(int position) {
                textView.setText(items.get(position));
            }


        }

    }


}
