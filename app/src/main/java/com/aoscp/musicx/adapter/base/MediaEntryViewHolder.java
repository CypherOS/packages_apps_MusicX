package com.aoscp.musicx.adapter.base;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.omnirom.gramophone.R;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */
public class MediaEntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    @Nullable
    public ImageView image;

    @Nullable
    public TextView imageText;

    @Nullable
    public TextView title;

    @Nullable
    public TextView text;

    @Nullable
    public View menu;

    @Nullable
    public View separator;

    @Nullable
    public View shortSeparator;

    @Nullable
    public View dragView;

    @Nullable
    public View paletteColorContainer;

    public MediaEntryViewHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
        imageText = (TextView) itemView.findViewById(R.id.image_text);
        title = (TextView) itemView.findViewById(R.id.title);
        text = (TextView) itemView.findViewById(R.id.text);
        menu = itemView.findViewById(R.id.menu);
        separator = itemView.findViewById(R.id.separator);
        shortSeparator = itemView.findViewById(R.id.short_separator);
        dragView = itemView.findViewById(R.id.drag_view);
        paletteColorContainer = itemView.findViewById(R.id.palette_color_container);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    protected void setImageTransitionName(@NonNull String transitionName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && image != null) {
            image.setTransitionName(transitionName);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
