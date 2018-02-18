package android.magician.com.songsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Magician on 2/17/2018.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsAdapterViewHolder> {
    private Context mContext;
    private List<Album> mAlbumList;
    //private AlbumsAdapterListener mListener;

    public AlbumsAdapter(Context context, List<Album> albumList) {//add AlbumsAdapterListener argument
        mContext = context;
        mAlbumList = albumList;
       // this.mListener = listener;
    }

    @Override
    public AlbumsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.album_card, parent, false);
        return new AlbumsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AlbumsAdapterViewHolder holder, final int position) {
        //get data
        Album album = mAlbumList.get(position);
        //pass data to the views
        holder.mTitle.setText(album.getName());

        String sNumbOfSongs = new StringBuilder().append(String.valueOf(album.getNumbOfSongs()))
                .append(" ")
                .append(mContext.getString(R.string.extra_title)).toString();
        holder.mNumbOfSongs.setText(sNumbOfSongs);

        //cover image
        Glide.with(mContext).load(album.getThumbnail()).into(holder.mThumbnail);
        //click lisener for menu album
        holder.mMenuAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopupMenu(holder.mMenuAlbum);
            }
        });

//       //here if u want to add click lisener when u click image or card view
//        holder.mCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mListener.onCardSelected(position, holder.mThumbnail);
//            }
//        });
//
//        holder.mThumbnail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mListener.onCardSelected(position, older.mThumbnail);
//            }
//        });

    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void ShowPopupMenu(ImageView menuAlbum) {
        //inflate menu xml
        PopupMenu popupMenu = new PopupMenu(mContext, menuAlbum);
        popupMenu.getMenuInflater().inflate(R.menu.menu_album, popupMenu.getMenu());
        //add lisener for each item
        popupMenu.setOnMenuItemClickListener(new AlbumMenuItemClickListener());
        //show the menu
        popupMenu.show();
    }

    /**
     * Click listener for popup menu items
     */
    class AlbumMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    //  //here add action to the item when click it
                    // listener.onAddToFavoriteSelected(position);
                    return true;
                case R.id.play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    //  //here add action to the item when click it
                   // listener.onPlayNextSelected(position);
                    return true;
            }

            return false;
        }
    }

    @Override
    public int getItemCount() {
        if (mAlbumList != null) return mAlbumList.size();
        return 0;
    }

    public class AlbumsAdapterViewHolder extends RecyclerView.ViewHolder {
        private ImageView mThumbnail;
        private ImageView mMenuAlbum;
        private TextView mTitle;
        private TextView mNumbOfSongs;
        //private CardView mCardView;

        public AlbumsAdapterViewHolder(View itemView) {
            super(itemView);
            mThumbnail = itemView.findViewById(R.id.thumbnail);
            mMenuAlbum = itemView.findViewById(R.id.overflow);
            mTitle = itemView.findViewById(R.id.title);
            mNumbOfSongs = itemView.findViewById(R.id.count);
            // mCardView = itemView.findViewById(R.id.card_view);
        }
    }
//    public interface AlbumsAdapterListener {
//        void onAddToFavoriteSelected(int position);//position to define which object u click on by calculate it
//        void onPlayNextSelected(int position);
//        void onCardSelected(int position, ImageView thumbnail);
//    }
}
