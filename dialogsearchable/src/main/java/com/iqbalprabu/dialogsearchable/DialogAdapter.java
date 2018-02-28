package com.iqbalprabu.dialogsearchable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by iqbalprabu on 29/12/17.
 * Email: iqbalprabu14@gmail.com
 * Facebook: https://web.facebook.com/ipj14
 * Github: https://github.com/iqbalprabu
 */

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.ViewHolder> {

    private Context context;
    private List<DialogData> dialogDataList;
    private OnRecyclerItemClickLister onRecyclerItemClickLister;

    public DialogAdapter(Context context, List<DialogData> dialogDataList) {
        this.context = context;
        this.dialogDataList = dialogDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new DialogAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return dialogDataList.size();
    }

    public void setOnRecyclerItemClickLister(OnRecyclerItemClickLister onRecyclerItemClickLister) {
        this.onRecyclerItemClickLister = onRecyclerItemClickLister;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView txtItem;

        public ViewHolder(View itemView) {
            super(itemView);
            txtItem = (TextView) itemView.findViewById(R.id.txtItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerItemClickLister.onItemClick(DialogAdapter.this, getAdapterPosition());
                }
            });
        }

        public void bindData(int position) {
            DialogData dialogData = dialogDataList.get(position);
            txtItem.setText(dialogData.getName());
        }
    }

    public DialogData getDataDialog(int position) {
        return dialogDataList.get(position);
    }

    public void setFilter(List<DialogData> dialogData) {
        dialogDataList = new ArrayList<>();
        dialogDataList.addAll(dialogData);
        notifyDataSetChanged();
    }
}
