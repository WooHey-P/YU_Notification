package com.example.yu_notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CodeFragment extends Fragment {
    private static String TAG = "TAG 학사안내1";


    RecyclerView recycle_codes;
    CodesAdapter adapter;

    DatabaseReference DBRef;

    public CodeFragment() {    }



    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach 호출됨");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy 호출됨");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView 호출됨");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause 호출됨");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume 호출됨");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop 호출됨");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart 호출됨");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated 호출됨");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate 호출됨");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach 호출됨");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_code, container, false);
        Log.d(TAG, "onCreateView 호출됨");

        init(rootView);
        CodeDataListener();

        return rootView;
    }

    private void CodeDataListener() {
        final Intent intent = new Intent(getContext(), MyService.class);
        getContext().startService(intent);


    }

    private void init(ViewGroup rootView) {
        recycle_codes = rootView.findViewById(R.id.recycle_codes);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycle_codes.setLayoutManager(manager);

        adapter = new CodesAdapter();
        DBRef = FirebaseDatabase.getInstance().getReference();
    }
}