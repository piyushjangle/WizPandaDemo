package com.pitej.wizpandademo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class VideoDemoActivity extends YouTubeBaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, YouTubePlayer.OnInitializedListener {

    //UI elements
    Context context;

    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView navigation_view;

    /*
    *youtube player uis
    * */
    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        context = this;

        /*
         * init all ui elements
         * */
        initView();

        //init Youtube player
        initYoutubePlayer();

    }

    /*
    * init youtube player
    * */
    private void initYoutubePlayer() {
        youTubeView = findViewById(R.id.youtube_view);
        youTubeView.initialize(getString(R.string.YOUTUBE_API_KEY), this);
    }

    /*
     * Init all the views
     * */
    private void initView() {

        drawer_layout = findViewById(R.id.drawer_layout);
        navigation_view = findViewById(R.id.navigation_view);
        navigation_view.setNavigationItemSelectedListener(this);

        View headerView = navigation_view.inflateHeaderView(R.layout.nav_header);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        //init drawer menus
        toolbar = findViewById(R.id.toolbar);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawer_layout,
                toolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.text_heading));
        drawer_layout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
        }
        drawer_layout.closeDrawers();
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
        drawer_layout.closeDrawers();
    }

    /*youtube player */
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer.cueVideo("CJN1n3fId_A"); //add video id here
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), youTubeInitializationResult.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }
    /*end of youtube player*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(getString(R.string.YOUTUBE_API_KEY), this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //do the animation after backpressed
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

    public void finishVideoDemoActivity(View view) {
        onBackPressed();
    }
}
