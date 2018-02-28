package com.iqbalprabu.dialogsearchableexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.iqbalprabu.dialogsearchable.DialogData;
import com.iqbalprabu.dialogsearchable.DialogSearchOnItemSelected;
import com.iqbalprabu.dialogsearchable.DialogSearchable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogSearchOnItemSelected {

    private DialogSearchable dialogSearchable;
    private List<DialogData> dialogDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialogDataList = new ArrayList<>();

        dialogSearchable = (DialogSearchable) findViewById(R.id.dialogSearchable);
        for (int i = 0; i < 10; i++) {
            DialogData dialogData = new DialogData();
            dialogData.setName("Item: " + (i + 1));

            dialogDataList.add(dialogData);
        }

        dialogSearchable.setTitle("Search Item");
        dialogSearchable.setListItems(dialogDataList);
        dialogSearchable.setDialogSearchOnItemSelected(this);
    }

    @Override
    public void onItemsSelected(String key, String text) {
        Toast.makeText(this, "Key: " + key + " & Text: " + text + " selected", Toast.LENGTH_SHORT).show();
    }
}
