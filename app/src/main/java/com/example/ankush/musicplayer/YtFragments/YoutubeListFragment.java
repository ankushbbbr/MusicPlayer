package com.example.ankush.musicplayer.YtFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ankush.musicplayer.BuildConfig;
import com.example.ankush.musicplayer.Networking.APIClient;
import com.example.ankush.musicplayer.Networking.ApiService;
import com.example.ankush.musicplayer.Networking.URLConstants;
import com.example.ankush.musicplayer.Networking.YoutubeResponse;
import com.example.ankush.musicplayer.R;
import com.example.ankush.musicplayer.YoutubeVideo;
import com.example.ankush.musicplayer.adapters.YtAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ankushbabbar on 13-Oct-16.
 */
public class YoutubeListFragment extends Fragment {
    ListView listView;
    ArrayList<YoutubeVideo> videos;
    YtAdapter ytAdapter;
    private static String ARG_KEYWORD;
    String TAG="YoutubeListFragmentTag";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_youtube_list,container,false);
        listView=(ListView)v.findViewById(R.id.fragment_youtube_listview);
        videos=new ArrayList<>();
        ytAdapter= new YtAdapter(getActivity(),videos);
        listView.setAdapter(ytAdapter);
        //addFake();
        Bundle b=getArguments();
        if(b!=null){
            String s=b.getString(ARG_KEYWORD);
            Log.i(TAG,s);
            char[] c=s.toCharArray();
            for(int i=0;i<s.length();i++){
                if(c[i]==' '){
                    c[i]='+';
                }
            }
            s=String.valueOf(c);
            Log.i(TAG,s);
            fetchVideos(s);
        }
        return v;
    }

    private void fetchVideos(String s) {
        Log.i(TAG,"fetchVideos "+s);
        ApiService service = APIClient.getService();
        Call<YoutubeResponse> call=service.searchVideos("snippet",50,s,"video", BuildConfig.Youtube_API_KEY);
        call.enqueue(new Callback<YoutubeResponse>() {
            @Override
            public void onResponse(Call<YoutubeResponse> call, Response<YoutubeResponse> response) {
                //Toast.makeText(StudentActivity.this, "onResponse", Toast.LENGTH_SHORT).show();
//                response.body().getData().
                ArrayList<YoutubeResponse.Item> itemList=response.body().getItems();
                if(itemList==null){
                    return;
                }
                videos.clear();
                for(YoutubeResponse.Item item:itemList){
                    String id=item.getId().getVideoId();
                    String title=item.getSnippet().getTitle();
                    Log.i(TAG,title);
                    String desc=item.getSnippet().getDescription();
                    String imgURL=item.getSnippet().getThumbnails().getMedium().getUrl();
                    videos.add(new YoutubeVideo(title,desc,imgURL,id));
                }
                ytAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<YoutubeResponse> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static YoutubeListFragment newInstance(String keyword) {
        Bundle args = new Bundle();
        YoutubeListFragment fragment = new YoutubeListFragment();
        args.putString(ARG_KEYWORD,keyword);
        fragment.setArguments(args);
        return fragment;
    }
    public void addFake(){
        Log.i(TAG,"add fake");
        YoutubeVideo hello= new YoutubeVideo("Adele - Skyfall (Lyric Video)",
                "Skyfall is the official theme song to the James Bond film of the same name.",
                "https://i.ytimg.com/vi/DeumyOzKqgI/hqdefault.jpg","DeumyOzKqgI");
        videos.add(hello);
        videos.add(hello);
        videos.add(hello);
        videos.add(hello);
        ytAdapter.notifyDataSetChanged();
    }
}
