package com.iqbalprabu.dialogsearchable;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by iqbalprabu on 28/02/18.
 * Email: iqbalprabu14@gmail.com
 * Facebook: https://web.facebook.com/ipj14
 * Github: https://github.com/iqbalprabu
 */

public class DialogSearchable extends LinearLayout implements View.OnClickListener, DialogSearchOnItemSelected {

    private Context context;
    private String placeholder = "Select Item";
    private boolean radius;
    private TextView txtSelected;

    private String title;
    private List<DialogData> listItems;
    private DialogListSearch dialogListSearch;
    private DialogSearchOnItemSelected dialogSearchOnItemSelected;


    public DialogSearchable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.dialog_searchable);
        radius = a.getBoolean(R.styleable.dialog_searchable_set_radius, false);

        if(radius) {
            radiusLayout();
        } else {
            setNotRadiusLayout();
        }

        txtSelected = (TextView) findViewById(R.id.txtSelected);
        txtSelected.setOnClickListener(this);
        initPlaceholder();
    }

    private void radiusLayout() {
        LayoutInflater inflater = (LayoutInflater)  getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.spinner_searchable_radius, this, true);
    }

    private void setNotRadiusLayout() {
        LayoutInflater inflater = (LayoutInflater)  getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.spinner_searchable, this, true);
    }

    private void initPlaceholder() {
        txtSelected.setText(placeholder);
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public void setRadius(boolean radius) {
        this.radius = radius;
    }


    @Override
    public void onClick(View v) {
        dialogListSearch = new DialogListSearch(getContext());
        dialogListSearch.setTitle(title);
        dialogListSearch.setListItems(listItems);
        dialogListSearch.setDialogSearchOnItemSelected(this);
        dialogListSearch.show();
    }

    public void setListItems(List<DialogData> listItems) {
        this.listItems = listItems;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void onItemsSelected(String key, String text) {
        this.dialogSearchOnItemSelected.onItemsSelected(key, text);
        dialogListSearch.close();
    }

    public void setDialogSearchOnItemSelected(DialogSearchOnItemSelected dialogSearchOnItemSelected) {
        this.dialogSearchOnItemSelected = dialogSearchOnItemSelected;
    }

}
