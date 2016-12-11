package com.example.ankush.musicplayer.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ankush.musicplayer.BuildConfig;
import com.example.ankush.musicplayer.IntentConstants;
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

public class YoutubeSearchActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<YoutubeVideo> videos;
    YtAdapter ytAdapter;
    private static String ARG_KEYWORD;
    String TAG="YoutubeSearchTag";
    EditText editText;
    String vidId=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_search);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_youtube_search_activity);
        setSupportActionBar(toolbar);
        ActionBar action = getSupportActionBar();
        action.setDisplayShowTitleEnabled(false);//hide title
//        action.setDisplayShowCustomEnabled(true); //enable it to display a custom view in the action bar.
//        action.setCustomView(R.layout.search_bar);

        listView=(ListView)findViewById(R.id.youtube_search_listview);
        videos=new ArrayList<>();
        ytAdapter= new YtAdapter(this,videos);
        listView.setAdapter(ytAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                YoutubeSearchActivity.this.vidId=videos.get(position).videoId;
                finish();
            }
        });
        //String s=getIntent().getStringExtra(IntentConstants.INTENT_KEY_YOUTUBE_SEARCH_KEYWORD);
        editText=(EditText)findViewById(R.id.youtube_search_editext);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    doSearch();
                    return true;
                }
                return false;
            }
        });
        ImageButton backButton=(ImageButton) findViewById(R.id.back_youtube_search);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void doSearch() {
        String s=editText.getText().toString();
        char[] chArray=s.toCharArray();
        for(int i=0;i<s.length();i++){
            if(chArray[i]==' '){
                chArray[i]='+';
            }
        }
        s=String.valueOf(chArray);
        fetchVideos(s);
    }

    private void fetchVideos(String s) {
        Log.i(TAG,"fetchVideos "+s);
        ApiService service = APIClient.getService();
        Call<YoutubeResponse> call=service.searchVideos("snippet",30,s,"video", BuildConfig.Youtube_API_KEY);
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
                Toast.makeText(YoutubeSearchActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void finish() {
        Intent i=new Intent();
        if(this.vidId!=null){
            i.putExtra(IntentConstants.INTENT_KEY_YOUTUBE_VID_ID,this.vidId);
            setResult(IntentConstants.INTENT_YT_SEARCH_PLAY_VIDEO_RESULT_CODE,i);
            Log.i(TAG, "finish:if "+this.vidId);
        }
        else{
            setResult(0,i);
        }
        Log.i(TAG, "finish: ");
        super.finish();
    }
}
