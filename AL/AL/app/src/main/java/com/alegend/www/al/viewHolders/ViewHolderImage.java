package com.alegend.www.al.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alegend.www.al.R;
import com.alegend.www.al.base.App;
import com.android.volley.toolbox.NetworkImageView;


/**
 * View holder for a recycler view item holding an image
 */
public class ViewHolderImage extends RecyclerView.ViewHolder
{
    private final NetworkImageView mNetworkImageView;

    public ViewHolderImage(@NonNull final View itemView)
    {
        super(itemView);

        mNetworkImageView = (NetworkImageView) itemView.findViewById(R.id.networkImageView);
        mNetworkImageView.setDefaultImageResId(R.drawable.ic_sun_smile);
        mNetworkImageView.setErrorImageResId(R.drawable.ic_cloud_sad);
    }

    /**
     * Sets the data in the view holder
     *
     * @param imageUrl is the image Url to get the image
     */
    public void setData(@NonNull final String imageUrl)
    {
        mNetworkImageView.setImageUrl(imageUrl, App.getInstance().getVolleyImageLoader());
    }
}
