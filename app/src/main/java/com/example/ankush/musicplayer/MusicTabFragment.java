package com.example.ankush.musicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ankush.musicplayer.MyMusicFragments.MusicPlayerFragment;
import com.example.ankush.musicplayer.MyMusicFragments.PrimaryFragment;
import com.example.ankush.musicplayer.MyMusicFragments.SocialFragment;
import com.example.ankush.musicplayer.MyMusicFragments.UpdatesFragment;

/**
 * Created by ankushbabbar on 11-Oct-16.
 */
public class MusicTabFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 7 ;
    NavigationView navigationView;
    public void setupview(NavigationView navigationView)
    {
        this.navigationView=navigationView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate music_tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.music_tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.music_tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new PrimaryFragment();
                case 1 : return new MusicPlayerFragment();
                case 2 : return new UpdatesFragment();
                case 3 : return new PrimaryFragment();
                case 4 : return new SocialFragment();
                case 5 : return new UpdatesFragment();
                case 6 : return new UpdatesFragment();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Home";
                case 1 :
                    return "Play Queue";
                case 2 :
                    return "Artists";
                case 3 :
                    return "Albums";
                case 4 :
                    return "Songs";
                case 5 :
                    return "Folders";
                case 6 :
                    return "Playlists";
            }
            return null;
        }
    }

}
