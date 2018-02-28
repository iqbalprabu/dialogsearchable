package com.iqbalprabu.dialogsearchable;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iqbalprabu on 26/12/17.
 * Email: iqbalprabu14@gmail.com
 * Facebook: https://web.facebook.com/ipj14
 * Github: https://github.com/iqbalprabu
 */

public class DialogListSearch implements SearchView.OnQueryTextListener, SearchView.OnCloseListener, OnRecyclerItemClickLister {

    private final LayoutInflater inflater;
    private Context context;
    private String title;
    private View dialogLayout;
    private TextView dialogTitle;
    private SearchView dialogSearchView;
    private RecyclerView dialogRecyclerView;
    private AlertDialog.Builder alertDialog;
    private AlertDialog dialogList;
    private List<DialogData> listItems;
    private DialogSearchOnItemSelected dialogSearchOnItemSelected;
    private DialogAdapter dialogAdapter;


    public DialogListSearch(Context context) {
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        dialogLayout = inflater.inflate(R.layout.dialog_search, null);
        alertDialog = new AlertDialog.Builder(this.context);
    }


    public void setListItems(List<DialogData> listItems) {
        this.listItems = listItems;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void show() {

        if(dialogList == null && dialogSearchView == null && dialogRecyclerView == null){
            createDialog();
        }

        dialogList.show();
    }

    public void close() {
        dialogList.cancel();
    }

    private void createDialog() {

        initAlertDialog();
        initAdapter();

        dialogSearchView.setIconifiedByDefault(false);
        dialogSearchView.setOnQueryTextListener(this);
        dialogSearchView.setOnCloseListener(this);
        dialogSearchView.clearFocus();

    }

    private void initAlertDialog() {
        alertDialog
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setView(dialogLayout);

        dialogList = alertDialog.create();

        dialogTitle = (TextView) dialogLayout.findViewById(R.id.txtTitle);
        dialogSearchView = (SearchView) dialogLayout.findViewById(R.id.searchView);
        dialogRecyclerView = (RecyclerView) dialogLayout.findViewById(R.id.recyclerView);

        dialogTitle.setText(title);
        dialogRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        dialogRecyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayout.VERTICAL));
    }

    private void initAdapter() {
        dialogAdapter = new DialogAdapter(context, listItems);
        dialogAdapter.setOnRecyclerItemClickLister(this);
        dialogRecyclerView.setAdapter(dialogAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<DialogData> filteredModelList = filter(listItems, query);
        dialogAdapter.setFilter(filteredModelList);
        return true;
    }

    @Override
    public boolean onClose() {
        return false;
    }

    public void setDialogSearchOnItemSelected(DialogSearchOnItemSelected dialogSearchOnItemSelected) {
        this.dialogSearchOnItemSelected = dialogSearchOnItemSelected;
    }

    private List<DialogData> filter(List<DialogData> models, String query) {
        query = query.toLowerCase();final List<DialogData> filteredModelList = new ArrayList<>();
        for (DialogData model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, int position) {
        DialogData dialogData = dialogAdapter.getDataDialog(position);
        String index = dialogData.getId() == null ? String.valueOf(position) : dialogData.getId();
        dialogSearchOnItemSelected.onItemsSelected(index, dialogData.getName());
    }
}

