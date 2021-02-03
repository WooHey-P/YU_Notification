package com.example.yu_notification;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements Filterable {
    ArrayList<Contents> newslist = new ArrayList<Contents>();
    ArrayList<Contents> filternewslist = newslist;
    Filter listfilter;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.news_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Contents news = filternewslist.get(position);
        holder.setItem(news);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _url = filternewslist.get(position).url;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(_url));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {return filternewslist.size();}

    public void addItem(Contents item) {newslist.add(item);}

//    public void addItemIndex(int idx, Contents item) {newslist.add(idx, item);}
//
//    public Contents getItem(int pos) {return newslist.get(pos);}
//
//    public void setItem(ArrayList<Contents> contentslist) {this.newslist = contentslist;}

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_no, tv_title, tv_author, tv_date, tv_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_no = itemView.findViewById(R.id.tv_no);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_view = itemView.findViewById(R.id.tv_view);

        }

        public void setItem(Contents item) {
            tv_no.setText(item.getNo());
            tv_title.setText(item.getTitle());
            tv_author.setText(item.getAuthor());
            tv_date.setText(item.getDate());
            tv_view.setText(item.getView());
        }
    }//end view holder

    //filter
    @Override
    public Filter getFilter() {
        if (listfilter == null) {
            listfilter = new ListFilter();
        }
        return listfilter;
    }

    private class ListFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;

            if (constraint == null || constraint.length() == 0) {
                results.values = newslist;
                results.count = newslist.size() ;
            } else {
                ArrayList<Contents> itemList = new ArrayList<Contents>();

                for (Contents item : newslist) {
                    // || item.getCode().toUpperCase().contains(constraint.toString().toUpperCase())

                    if (item.getTitle().toUpperCase().contains(constraint.toString().toUpperCase())
                            || item.getNo().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        itemList.add(item) ;
                    }
                }
                results.values = itemList ;
                results.count = itemList.size() ;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // update listview by filtered data list.
            filternewslist = (ArrayList<Contents>) results.values ;
            notifyDataSetChanged();
        }
    } //end ListFilter class
}
