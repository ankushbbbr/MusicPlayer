package com.example.ankush.musicplayer.YtFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.ankush.musicplayer.MainActivity;
import com.example.ankush.musicplayer.R;

/**
 * Created by ankushbabbar on 18-Oct-16.
 */
public class YtNowPlayingFragment extends Fragment {
    public static String NowPlayingId;
    WebView wv;
    private String TAG="YtNowPlayingFragmentTag";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: YTnowplayingFragment");
        View v=inflater.inflate(R.layout.fragment_yt_now_playing,container,false);
        wv=(WebView)v.findViewById(R.id.youtube_webview);
        return v;
    }
    public void playSong(String s){
        //Log.i(TAG, "playSong: "+NowPlayingId+s);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://www.youtube.com/embed/"+s);


    }
    //wv.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putBundle();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }
    @Override
    public void onPause() {
        Log.i(TAG, "onPause: YtNowplaying");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    public void onResume(){
        Log.i(TAG, "onResume: ");
        //wv.onResume();
        super.onResume();
    }
}
