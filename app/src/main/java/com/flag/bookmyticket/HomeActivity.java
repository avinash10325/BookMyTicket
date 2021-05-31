package com.flag.bookmyticket;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Movie> movieList;
    public static String imageIntent = "imageIntent";
    public static  String titleIntent ="titleIntent";
    public static  String ratingIntent ="ratingIntent";
    public static  String runtimeIntent ="runtimeIntent";
    public static  String genreIntent ="genreIntent";

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Email = "emailKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d32f2f")));



        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_dashboard:
                        Intent a = new Intent(HomeActivity.this,BookingsActivity.class);
                        startActivity(a);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.navigation_notifications:
                        Intent b = new Intent(HomeActivity.this,ProfileActivity.class);
                        startActivity(b);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                }
                return false;
            }
        });

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        Toast.makeText(getApplicationContext(),sharedpreferences.getString(Email, ""),Toast.LENGTH_LONG).show();

        movieList = new ArrayList<>();
        prepareMovie();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(movieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        recyclerViewAdapter.setOnItemClickListener(new ClickListener<Movie>(){
            @Override
            public void onItemClick(Movie data) {
                Intent intent = new Intent(HomeActivity.this, ScreenSelection.class);
                Bundle extras = new Bundle();
                extras.putInt(imageIntent, data.getImage());
                extras.putString(titleIntent,data.getTitle());
                extras.putString(ratingIntent,data.getRating());
                extras.putString(runtimeIntent,data.getRuntime());
                extras.putString(genreIntent,data.getGenre());
                intent.putExtras(extras);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        recyclerView.setAdapter(recyclerViewAdapter);
    }
    private void prepareMovie(){
        Movie movie = new Movie("Godzilla vs. Kong (2021)","PG-13","Action, Science Fiction","1h 53m","",R.drawable.one);
        movieList.add(movie);
        movie = new Movie("Zack Snyder Justice League","UA","Action, Adventure, Fantasy, Science Fiction","4h 2m","",R.drawable.two);
        movieList.add(movie);
        movie =new Movie("Chaos Walking (2021)","PG-13","Science Fiction, Action, Adventure, Thriller","1h 49m","",R.drawable.three);
        movieList.add(movie);
        movie =new Movie("Raya and the Last Dragon","PG","Animation, Adventure, Fantasy, Family","1h 47m","",R.drawable.four);
        movieList.add(movie);
        movie =new Movie("Mortal Kombat","A","Fantasy, Action, Adventure","1h 50m","",R.drawable.five);
        movieList.add(movie);
        movie =new Movie("Cherry ","A","Crime, Drama","2h 20m","",R.drawable.six);
        movieList.add(movie);
        movie =new Movie("Tom & Jerry","PG","Comedy, Family, Animation","1h 41m","",R.drawable.seven);
        movieList.add(movie);
        movie =new Movie("Scorpion Revenge","R","Animation, Action, Fantasy","1h 20m","",R.drawable.eight);
        movieList.add(movie);
        movie =new Movie("Twist","12","Crime, Drama, Action","1h 30m","",R.drawable.nine);
        movieList.add(movie);
        movie =new Movie("Monster Hunter ","PG-13","Fantasy, Action, Adventure","1h 44m","",R.drawable.ten);
        movieList.add(movie);
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Quit Application")
                .setMessage("Are you sure you want to Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}