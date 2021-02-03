package com.example.yu_notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CodesAdapter extends RecyclerView.Adapter<CodesAdapter.CodeViewHolder> {
    ArrayList<Codes> codelist = new ArrayList<Codes>();

    @NonNull
    @Override
    public CodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_text, parent, false);

        return new CodeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CodeViewHolder holder, int position) {
        Codes code = codelist.get(position);
        holder.setItem(code);
    }

    public void addCode(Codes code){ codelist.add(code); }
    @Override
    public int getItemCount() { return codelist.size(); }


    static class CodeViewHolder extends RecyclerView.ViewHolder{
        TextView tv_key, tv_layout_title, tv_layout_code;

        public CodeViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_key = itemView.findViewById(R.id.tv_key);
            tv_layout_title = itemView.findViewById(R.id.tv_layout_title);
            tv_layout_code = itemView.findViewById(R.id.tv_layout_code);
        }

        public void setItem(Codes code){
            tv_key.setText(code.getKey());
            tv_layout_title.setText(code.getTitle());
            tv_layout_code.setText(code.getCode());
        }
    }
}
