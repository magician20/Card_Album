package android.magician.com.songsapp;

import android.content.res.Resources;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Album> mAlbumList;
    AlbumsAdapter mAlbumsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        mRecyclerView = findViewById(R.id.recycler_view);

        mAlbumList = new ArrayList<>();
        mAlbumsAdapter = new AlbumsAdapter(this, mAlbumList);

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(mAlbumsAdapter);//zero items

        //pass new items and notify adapter for change
        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.imageCover));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r =getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    // Shows or hides the toolbar title when the toolbar is collapsed or expanded.
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);//u can now expand it

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }

                if (scrollRange + verticalOffset == 0) {
                    //collapsing
                    collapsingToolbarLayout.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    //expanding
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        Album a = new Album("True Romance", 13, covers[0]);
        mAlbumList.add(a);

        a = new Album("Xscpae", 8, covers[1]);
        mAlbumList.add(a);

        a = new Album("Maroon 5", 11, covers[2]);
        mAlbumList.add(a);

        a = new Album("Born to Die", 12, covers[3]);
        mAlbumList.add(a);

        a = new Album("Honeymoon", 14, covers[4]);
        mAlbumList.add(a);

        a = new Album("I Need a Doctor", 1, covers[5]);
        mAlbumList.add(a);

        a = new Album("Loud", 11, covers[6]);
        mAlbumList.add(a);

        a = new Album("Legend", 14, covers[7]);
        mAlbumList.add(a);

        a = new Album("Hello", 11, covers[8]);
        mAlbumList.add(a);

        a = new Album("Greatest Hits", 17, covers[9]);
        mAlbumList.add(a);

        mAlbumsAdapter.notifyDataSetChanged();
    }
}
