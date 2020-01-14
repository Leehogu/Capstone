package com.cookandroid.attendandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class recordActivity extends AppCompatActivity {

    ArrayList courseList;
    private FragmentManager fragmentManager;
    private fragment1Activity fragment1;
    private fragment2Activity fragment2;
    private FragmentTransaction transaction;

    final String[] select_item = {""};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        setTitle("E-Attend");

        fragmentManager = getSupportFragmentManager();

        fragment1 = new fragment1Activity();
        fragment2 = new fragment2Activity();


        final ArrayList courseList = new ArrayList();
        courseList.add("과목1");
        courseList.add("과목2");
        courseList.add("과목3");
        courseList.add("과목4");

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, courseList);
        spinner2.setAdapter(adapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                transaction = fragmentManager.beginTransaction();
                if ("과목1".equals(courseList)) {
                    transaction.replace(R.id.frameLayout, fragment1).commitAllowingStateLoss();
                } else if ("과목2".equals(courseList)) {
                    transaction.replace(R.id.frameLayout, fragment2).commitAllowingStateLoss();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });


    }
}