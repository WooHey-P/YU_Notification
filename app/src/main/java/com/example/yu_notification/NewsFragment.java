package com.example.yu_notification;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NewsFragment extends Fragment {
    private static String TAG = "TAG 뉴스";

    ImageView iv_clear;
    EditText et_search;
    RecyclerView recycle_news;
    NewsAdapter adapter;

    ProgressDialog progdlg;

    public NewsFragment() {    }

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_news, container, false);
        Log.d(TAG, "onCreateView 호출됨");

        init(rootView);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycle_news.setLayoutManager(manager);

        progDlgsetting();
        new MyTask().execute();
        SearchCodes();

        return rootView;
    }

    private class MyTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            Document doc = null;
            try {
                for (int i = 1; i <= 20; i++) {
                    String news_url = "http://www.yu.ac.kr/_korean/about/index.php?c=about_08_a_list&page=" + i;
                    doc = Jsoup.connect(news_url).get();
                    Elements elements = doc.select("table.boardList > tbody > tr");

                    for (Element row : elements) {
                        Elements td = row.getElementsByTag("td");

                        String _no = td.get(0).text();
                        String _title = td.get(1).text();
                        String _author = td.get(2).text();
                        String _date = td.get(3).text();
                        String _view = td.get(4).text();
                        String _url = "http://www.yu.ac.kr" + td.get(1).getElementsByTag("a").attr("href");

                        adapter.addItem(new Contents(_no, _title, _author, _date, _view, _url));
                    }//end for_row
                }//end for_i
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            recycle_news.setAdapter(adapter);

            progdlg.dismiss();
        }

    }//end MyTask

    private void init(ViewGroup rootView) {
        recycle_news = rootView.findViewById(R.id.recycle_news);
        et_search = rootView.findViewById(R.id.et_search);
        iv_clear = rootView.findViewById(R.id.iv_clear);

        adapter = new NewsAdapter();

    }

    public void SearchCodes() {
        //검색창에 X버튼 클릭시 클리어
        iv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText("");
            }
        });

        //edit_text에 문자 입력 시
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                ((NewsAdapter)recycle_news.getAdapter()).getFilter().filter(filterText);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });
    }

    public void progDlgsetting() {
        progdlg = new ProgressDialog(getContext());
        progdlg.setMessage("영대소식 로딩 중...");
        progdlg.setCancelable(false);
        progdlg.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal);
        progdlg.show();
    }
}