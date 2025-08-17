package com.threehero.myhealth.others;// ArticleAdapter.java
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.threehero.myhealth.R;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> implements Filterable {

    private Context context;
    private List<Article> articleList;
    private List<Article> filteredArticleList;

    public ArticleAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = new ArrayList<>(articleList);
        this.filteredArticleList = new ArrayList<>(articleList);
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = filteredArticleList.get(position);
        holder.headlineTextView.setText(article.getHeadline());
        holder.authorTextView.setText(article.getAuthor());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(article.getUrl()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filteredArticleList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterPattern = constraint.toString().toLowerCase().trim();
                List<Article> filteredList = new ArrayList<>();
                if (filterPattern.isEmpty()) {
                    filteredList.addAll(articleList);
                } else {
                    for (Article article : articleList) {
                        if (article.getHeadline().toLowerCase().contains(filterPattern)
                                || article.getAuthor().toLowerCase().contains(filterPattern)) {
                            filteredList.add(article);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredArticleList.clear();
                filteredArticleList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView headlineTextView;
        TextView authorTextView;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            headlineTextView = itemView.findViewById(R.id.article_headline);
            authorTextView = itemView.findViewById(R.id.article_author);
        }
    }
}
