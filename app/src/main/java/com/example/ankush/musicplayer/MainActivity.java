package com.example.ankush.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.ankush.musicplayer.activities.YoutubeSearchActivity;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    ActionBarDrawerToggle mDrawerToggle;
    android.support.v7.widget.Toolbar toolbar;
    boolean stateYoutube;
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText searchText;
    YouTubeTabFragment youTubeTabFragment;
    String TAG="MainActivityTag";
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view) ;

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        MusicTabFragment musicTabFragment =new MusicTabFragment();
        mFragmentTransaction.replace(R.id.containerView, musicTabFragment).commit();
        mNavigationView.getMenu().findItem(R.id.nav_mysongs).setChecked(true);
        musicTabFragment.setupview(mNavigationView);
        stateYoutube=false;
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(MenuItem menuItem) {
             mDrawerLayout.closeDrawers();

             if (menuItem.getItemId() == R.id.nav_youtube) {
                 mFragmentManager = getSupportFragmentManager();
                 mFragmentTransaction = mFragmentManager.beginTransaction();
                 youTubeTabFragment =new YouTubeTabFragment();
                 mFragmentTransaction.replace(R.id.containerView, youTubeTabFragment).commit();
                 youTubeTabFragment.setupview(mNavigationView);
                 toolbar.setTitle("YouTube");
                 stateYoutube=true;
                 mNavigationView.getMenu().findItem(R.id.nav_mysongs).setChecked(false);
                 mNavigationView.getMenu().findItem(R.id.nav_youtube).setChecked(true);
             }
             else if (menuItem.getItemId() == R.id.nav_mysongs) {
                 mFragmentManager = getSupportFragmentManager();
                 mFragmentTransaction = mFragmentManager.beginTransaction();
                 MusicTabFragment musicTabFragment =new MusicTabFragment();
                 mFragmentTransaction.replace(R.id.containerView, musicTabFragment).commit();
                 musicTabFragment.setupview(mNavigationView);
                 toolbar.setTitle("My Songs");
                 stateYoutube=false;
                 mNavigationView.getMenu().findItem(R.id.nav_mysongs).setChecked(true);
                 mNavigationView.getMenu().findItem(R.id.nav_youtube).setChecked(false);
             }
             else if(menuItem.getItemId()==R.id.contact_us) {

             }
             else if(menuItem.getItemId()==R.id.feedback) {

             }
             return false;
        }

        });
        /**
         * Setup Drawer Toggle of the Toolbar
         */
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,
                toolbar,R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setSupportActionBar(toolbar);
        mDrawerToggle.syncState();

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction=menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings){

        }
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        if(id==R.id.action_search){
            //handleMenuSearch();
            if(stateYoutube) {
                Intent intent = new Intent();
                intent.setClass(this, YoutubeSearchActivity.class);
                startActivityForResult(intent, IntentConstants.INTENT_YT_SEARCH_ACTIVITY_REQUEST_CODE);
                return true;
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==IntentConstants.INTENT_YT_SEARCH_ACTIVITY_REQUEST_CODE){
            if(resultCode==IntentConstants.INTENT_YT_SEARCH_PLAY_VIDEO_RESULT_CODE){
                String s=data.getStringExtra(IntentConstants.INTENT_KEY_YOUTUBE_VID_ID);
                youTubeTabFragment.mFragment.setNowPlayingTab(s);
                Log.i(TAG, "onActivityResult: "+s);
            }
        }
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }
    //    private void handleMenuSearch() {
//        Log.i(TAG,"searchHandler");
//        ActionBar action = getSupportActionBar(); //get the actionbar
//
//        if(isSearchOpened){ //test if the search is open
//
//            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
//            action.setDisplayShowTitleEnabled(true); //show the title in the action bar
//
//            //hides the keyboard
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
//
//            //add the search icon in the action bar
//            mSearchAction.setIcon(R.drawable.ic_search_white_24dp);
//
//            isSearchOpened = false;
//        } else { //open the search entry
//
//            action.setDisplayShowCustomEnabled(true); //enable it to display a custom view in the action bar.
//            action.setCustomView(R.layout.search_bar);//add the custom view
//            action.setDisplayShowTitleEnabled(false); //hide the title
//
//            searchText = (EditText)action.getCustomView().findViewById(R.id.edit_search); //the text editor
//
//            //this is a listener to do a search when the user clicks on search button
//            searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                @Override
//                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                        Log.i(TAG,"action search button pressed");
//                        doSearch();
//                        return true;
//                    }
//                    return false;
//                }
//            });
//
//
//            searchText.requestFocus();
//
//            //open the keyboard focused in the edtSearch
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.showSoftInput(searchText, InputMethodManager.SHOW_IMPLICIT);
//            //add the close icon
//            mSearchAction.setIcon(android.R.drawable.ic_delete);
//
//            isSearchOpened = true;
//        }
//
//    }
//    @Override
//    public void onBackPressed() {
//        if(isSearchOpened) {
//            //handleMenuSearch();
//            return;
//        }
//        super.onBackPressed();
//    }
//    private void doSearch() {
//        Log.i(TAG,"doSearch");
//        if(stateYoutube){
//            String s=searchText.getText().toString();
//            Log.i(TAG,s);
////            mFragmentManager = getSupportFragmentManager();
////            mFragmentTransaction = mFragmentManager.beginTransaction();
////            YoutubeListFragment youtubeListFragment=YoutubeListFragment.newInstance(s);
////            mFragmentTransaction.replace(R.id.containerView, youtubeListFragment).commit();
//            Intent intent = new Intent();
//            intent.setClass(MainActivity.this, YoutubeSearchActivity.class);
//            intent.putExtra(IntentConstants.INTENT_KEY_YOUTUBE_SEARCH_KEYWORD,s);
//            startActivity(intent);
//        }
//    }
}