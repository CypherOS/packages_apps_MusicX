package co.aoscp.musicx.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.GlideModule;
import co.aoscp.musicx.glide.artistimage.ArtistImage;
import co.aoscp.musicx.glide.artistimage.ArtistImageLoader;
import co.aoscp.musicx.glide.audiocover.AudioFileCover;
import co.aoscp.musicx.glide.audiocover.AudioFileCoverLoader;

import java.io.InputStream;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */
public class PhonographGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(AudioFileCover.class, InputStream.class, new AudioFileCoverLoader.Factory());
        glide.register(ArtistImage.class, InputStream.class, new ArtistImageLoader.Factory(context));
    }
}
