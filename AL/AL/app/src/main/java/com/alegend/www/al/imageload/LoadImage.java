package com.alegend.www.al.imageload;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.alegend.www.al.R;
import com.alegend.www.al.network.netModule.NetStatus;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2015-12-22.
 */
public class LoadImage {
    private Context mContext;
    public LoadImage(Context context){
        mContext=context;
    }
    public static void loadImg(Context context,String url,ImageView image){
        if(NetStatus.isWifiMode(context)){
            Picasso.with(context).load(url).placeholder(R.drawable.ic_luncher).into(image);
        }else{
            Toast.makeText(context, "非WIFI状态下不能加载图片", Toast.LENGTH_SHORT).show();
        }

    }
}
