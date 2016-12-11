package com.example.ankush.musicplayer.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ankush.musicplayer.R;
import com.example.ankush.musicplayer.YoutubeVideo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.TooManyListenersException;

/**
 * Created by ankushbabbar on 14-Oct-16.
 */
public class YtAdapter extends ArrayAdapter<YoutubeVideo>{
    Context mContext;
    ArrayList<YoutubeVideo> mVideos;
    String TAG="YtAdapterTag";
    public YtAdapter(Context context, ArrayList<YoutubeVideo> videos) {
        super(context, 0, videos);
        mContext=context;
        mVideos=videos;
    }
    public static class ViewHolder{
        private TextView mVideoTitleTxv = null;
        private TextView mVideoDescTxv = null;
        private ImageView mVideoThumbnail = null;
        ViewHolder(TextView Title,TextView Desc,ImageView Thumbnail){
            this.mVideoTitleTxv= Title;
            this.mVideoDescTxv=Desc;
            this.mVideoThumbnail=Thumbnail;
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(mContext, R.layout.youtube_list_item,null);
            TextView Title=(TextView) convertView.findViewById(R.id.video_title_txv);
            TextView Desc=(TextView) convertView.findViewById(R.id.video_desc_txv);
            ImageView Thumbnail=(ImageView) convertView.findViewById(R.id.video_thumbnail_imv);
            ViewHolder vh = new ViewHolder(Title,Desc,Thumbnail);
            convertView.setTag(vh);
        }
        YoutubeVideo curr= mVideos.get(position);
        ViewHolder vh=(ViewHolder)convertView.getTag();
        vh.mVideoTitleTxv.setText(curr.title);
        vh.mVideoDescTxv.setText(curr.description);
        Picasso.with(mContext).load(curr.imgURL).into(vh.mVideoThumbnail);
        Log.i(TAG,mVideos.size()+"");
        Log.i(TAG,curr.title);
        return convertView;
    }
}
