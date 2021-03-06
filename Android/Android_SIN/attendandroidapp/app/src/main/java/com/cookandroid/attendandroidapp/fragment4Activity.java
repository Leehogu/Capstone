package com.cookandroid.attendandroidapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class fragment4Activity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment4, container, false);
        final String[] data = {"man1","man2","man3","man4","man5","man6"};
        Button search_btn = linearLayout.findViewById(R.id.check_btn);
        final EditText editbox = linearLayout.findViewById(R.id.editbox);
        int nDatCnt=0;

        ArrayList<itemActivity> oData = new ArrayList<>();


        for (int i=0; i<data.length; ++i)
        {
            itemActivity oItem = new itemActivity();
            oItem.data = data[nDatCnt++];
            oData.add(oItem);
            if (nDatCnt >= data.length) nDatCnt = 0;
        }

        final ListView list4 = linearLayout.findViewById(R.id.list4);
        adapterActivity Adapter = new adapterActivity(oData);
        list4.setAdapter(Adapter);
        list4.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (editbox.getText().toString()) {

                    case "man1":
                        list4.setItemChecked(0, true);
                        break;
                    case "man2":
                        list4.setItemChecked(1, true);
                        break;
                    case "man3":
                        list4.setItemChecked(2, true);
                        break;
                    case "man4":
                        list4.setItemChecked(3, true);
                        break;
                    case "man5":
                        list4.setItemChecked(4, true);
                        break;
                    case "man6":
                        list4.setItemChecked(5, true);
                        break;
                }
            }
        });

        return linearLayout;
    }
    }

