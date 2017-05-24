package id.qferizstudio.mvvm.listItems;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.qferizstudio.mvvm.R;
import id.qferizstudio.mvvm.db.BorrowModel;

/**
 * Created by Qferiz on 23/05/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<BorrowModel> borrowModelList;

    public RecyclerViewAdapter(List<BorrowModel> borrowModelList) {
        this.borrowModelList = borrowModelList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        BorrowModel borrowModel = borrowModelList.get(position);
        holder.itemTextView.setText(borrowModel.getItemName());
        holder.nameTextView.setText(borrowModel.getPersonName());
        holder.dateTextView.setText(borrowModel.getBorrowDate().toLocaleString().substring(0, 11));
    }

    @Override
    public int getItemCount() {
        return borrowModelList.size();
    }

    public void addItems(List<BorrowModel> borrowModelList) {
        this.borrowModelList = borrowModelList;
        notifyDataSetChanged();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        // TODO: List item UI widget variables here

        private TextView itemTextView, nameTextView, dateTextView;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            //TODO: init list UI here

            itemTextView = (TextView) itemView.findViewById(R.id.itemTextView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
        }
    }
}
