package id.qferizstudio.mvvm;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import id.qferizstudio.mvvm.addItems.AddActivity;
import id.qferizstudio.mvvm.db.AppDatabase;
import id.qferizstudio.mvvm.db.BorrowModel;
import id.qferizstudio.mvvm.listItems.BorrowedListViewModel;
import id.qferizstudio.mvvm.listItems.RecyclerTouchListener;
import id.qferizstudio.mvvm.listItems.RecyclerViewAdapter;

public class MainActivity extends LifecycleActivity {

    private BorrowedListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<BorrowModel>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(BorrowedListViewModel.class);

        viewModel.getItemAndPersonList().observe(MainActivity.this, new Observer<List<BorrowModel>>() {
            @Override
            public void onChanged(@Nullable List<BorrowModel> itemAndPeople) {
                recyclerViewAdapter.addItems(itemAndPeople);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        viewModel.deleteItem(position);
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }
}
