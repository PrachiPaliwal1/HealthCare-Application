package com.threehero.myhealth.main;// ArticlesActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.threehero.myhealth.R;
import com.threehero.myhealth.others.Article;
import com.threehero.myhealth.others.ArticleAdapter;

import java.util.Arrays;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class ArticlesActivity extends AppCompatActivity {

    private List<Article> articleList;
    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        // Hardcoded data
        articleList = Arrays.asList(
                new Article("Cardiovascular Health Becoming a Major Risk Factor for Dementia", "Ralph Ellis", "https://www.webmd.com/alzheimers/news/20240628/cardiovascular-health-becoming-major-risk-factor-for-dementia"),
                new Article("From Greens to Meat: Why Women Are Choosing the Carnivore Diet", " Kelly Wairimu Davis, MS", "https://www.webmd.com/diet/news/20240628/from-greens-to-meat-why-women-are-choosing-carnivore-diet"),
                new Article("Many People on Statins May Not Need Them", "Ken Terry", "https://www.webmd.com/heart-disease/news/20240627/many-people-on-statins-may-not-need-them"),
                new Article("Loneliness After 50 Tied to Higher Stroke Risk", "Lisa O’Mary", "https://www.webmd.com/stroke/news/20240625/loneliness-after-50-tied-to-higher-stroke-risk"),
                new Article("In Conversation: What makes a diet truly heart-healthy?", "Maria Cohut", "https://www.medicalnewstoday.com/articles/in-conversation-what-makes-a-diet-truly-heart-healthy#1"),
                new Article("Wegovy May Cause Greater Weight Loss in Women Than Men", " Nancy Schimelpfening, MS ", "https://www.healthline.com/health-news/wegovy-more-weight-loss-women"),
                new Article("As Emergency Abortions Are Preserved in Idaho, Strict Bans Remain In Texas", "Julia Ries", "https://www.healthline.com/health-news/texas-abortion-ban-infant-deaths-increase"),
                new Article("Why Most Plant-Based Meat Alternatives Are Healthier Than Real Meat", "Nancy Schimelpfening, MS ", "https://www.healthline.com/health-news/plant-based-meat-healthier"),
                new Article("Taking a Daily Multivitamin May Not Help You Live Longer", " Gigen Mammoser ", "https://www.healthline.com/health-news/daily-multivitamin-may-not-help-live-longer"),
                new Article("Exercising to keep the brain young: Latest research and expert opinions", " Paul Ian Cross, PhD  ", "https://www.medicalnewstoday.com/articles/exercising-to-keep-the-brain-young-latest-research-and-expert-opinions"),
                new Article("New research helps explain why people move slower as they get older", "Corrie Pelc ", "https://www.medicalnewstoday.com/articles/why-do-people-move-slower-as-they-get-older-study"),
                new Article("Managing chronic inflammation with psoriasist", " Nancy Carteron, M.D., FACR ", "https://www.medicalnewstoday.com/articles/psoriasis-and-chronic-inflammationr")
        );

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArticleAdapter(this, articleList);
        recyclerView.setAdapter(adapter);

        EditText searchBar = findViewById(R.id.search_bar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        ImageView backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticlesActivity.this, Home.class);
                startActivity(intent);
            }
        });
    }
}