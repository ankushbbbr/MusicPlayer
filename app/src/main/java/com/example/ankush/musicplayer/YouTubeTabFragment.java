package com.example.ankush.musicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ankush.musicplayer.MyMusicFragments.SocialFragment;
import com.example.ankush.musicplayer.MyMusicFragments.UpdatesFragment;
import com.example.ankush.musicplayer.YtFragments.YtNowPlayingFragment;

import java.util.HashMap;

/**
 * Created by Ratan on 7/27/2015.
 */
public class YouTubeTabFragment extends Fragment {

    public static TabLayout mTabLayout;
    public static ViewPager mViewPager;
    public static int int_items = 3 ;
    NavigationView navigationView;
    public static YouTubeTabFragment mFragment;
    YtNowPlayingFragment ytnplaying;
    private String TAG="YouTubeTabFragmentTag";

    private static String MAP_KEY_NOW_PLAYING="youtubefragment";
    public void setupview(NavigationView navigationView)
    {
        this.navigationView=navigationView;
    }
    public void setNowPlayingTab(String vidId){
        Log.i(TAG, "setNowPlayingTab: ");
        TabLayout.Tab tab= mTabLayout.getTabAt(0);
        tab.select();
        ytnplaying.NowPlayingId=vidId;
        ytnplaying.playSong(vidId);
       // mTabLayout.addTab(mTabLayout.newTab().setText(""));
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View x =  inflater.inflate(R.layout.youtube_tab_layout,null);
        mTabLayout = (TabLayout) x.findViewById(R.id.youtube_tabs);
        mViewPager = (ViewPager) x.findViewById(R.id.youtube_viewpager);

        mViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                mTabLayout.setupWithViewPager(mViewPager);
            }
        });
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
//        setUpTabs();
        mFragment=this;
        mViewPager.setOffscreenPageLimit(3);
        return x;
    }
    private void setUpTabs(){
        mTabLayout.addTab(mTabLayout.newTab().setText("Now playing"));
        mTabLayout.addTab(mTabLayout.newTab().setText("2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("3"));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(),true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        HashMap<String,Fragment> fragmentHashMap=new HashMap<>();

        @Override
        public Fragment getItem(int position)
        {
            FragmentManager fm=getFragmentManager();
            Log.i(TAG, "Fragment pager adapter "+position);
            switch (position){
                case 0 : // PrimaryFragment primaryFragment=(PrimaryFragment)fm.findFragmentByTag("primary");
//                        if(primaryFragment==null)
//                        {
//                            primaryFragment=new PrimaryFragment();
//                            fm.beginTransaction().add(primaryFragment,"primary").commit();
//                        }
                        if(ytnplaying==null) {
                            ytnplaying = new YtNowPlayingFragment();
                            Log.i(TAG, "new ytnplaying fragment created");
                            fragmentHashMap.put(MAP_KEY_NOW_PLAYING,ytnplaying);
                        }
                        return fragmentHashMap.get(MAP_KEY_NOW_PLAYING);
                        //return ytnplaying;

                case 1 :  //SocialFragment socialFragment=(SocialFragment)fm.findFragmentByTag("social");
//                        if(socialFragment==null)
//                        {
//                            primaryFragment=new PrimaryFragment();
//                            fm.beginTransaction().add(primaryFragment,"social").commit();
//                        }
                        Log.i(TAG, "new social fragment");
                        return new SocialFragment();

                case 2 : // UpdatesFragment updatesFragment=(UpdatesFragment)fm.findFragmentByTag("updates");
//                        if(updatesFragment==null)
//                        {
//                            updatesFragment=new UpdatesFragment();
//                            fm.beginTransaction().add(updatesFragment,"updates").commit();
//                        }
                        Log.i(TAG, "new Updates fragment");
                        return new UpdatesFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Now playing";
                case 1 :
                    return "Recently Played";//trending
                case 2 :
                    return "Genres";
            }
            return null;
        }
    }

}
