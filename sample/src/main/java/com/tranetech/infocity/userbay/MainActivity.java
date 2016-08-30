package com.tranetech.infocity.userbay;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tranetech.infocity.StickyRecyclerHeadersAdapter;
import com.tranetech.infocity.StickyRecyclerHeadersDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;

import Utilitis.DividerDecoration;
import Utilitis.JSONParser;
import Utilitis.Pojo_image_txt;
import Utilitis.RecyclerItemClickListener;
import adapters.HorizontalAdapter;
import adapters.PriviousPost;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Boolean isFabOpen = false;
    private Button fab1, fab2, fab3, fab4, fab5, fab6, fab7, fab8, fab9, fab10, fab11, left, right;
    private Button fab;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    public Dialog dialog;
    Button btn;
    private RecyclerView recyclerView;
    private String DEPARTMENT, jsonstr;
    public HorizontalAdapter horizontalAdapter;
    public PriviousPost privious_post;
    Pojoimagetxt adapter;
    int mpriviousScroll;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadUIElements();

        //Start download
        new AsyncHttpTask().execute();

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void LoadUIElements() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        fab = (Button) findViewById(R.id.fab);
        fab1 = (Button) findViewById(R.id.fab1);
        fab2 = (Button) findViewById(R.id.fab2);
        fab3 = (Button) findViewById(R.id.fab3);
        fab4 = (Button) findViewById(R.id.fab4);
        fab5 = (Button) findViewById(R.id.fab5);
        fab6 = (Button) findViewById(R.id.fab6);
        fab7 = (Button) findViewById(R.id.fab7);
        fab8 = (Button) findViewById(R.id.fab8);
        fab9 = (Button) findViewById(R.id.fab9);
        fab10 = (Button) findViewById(R.id.fab10);
        fab11 = (Button) findViewById(R.id.fab11);
        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);

        btn = (Button) findViewById(R.id.btn);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        fab.setOnClickListener(clickListener);
        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);
        fab4.setOnClickListener(clickListener);
        fab5.setOnClickListener(clickListener);
        fab10.setOnClickListener(clickListener);
        btn.setOnClickListener(clickListener);
        fab11.setOnClickListener(clickListener);
        fab9.setOnClickListener(clickListener);

        left.setOnClickListener(this);
        right.setOnClickListener(this);

      /*  recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (oldScrollX > mpriviousScroll) {
                    fab.setVisibility(View.INVISIBLE);
                } else if (oldScrollX < mpriviousScroll) {
                    fab.setVisibility(View.VISIBLE);
                }
                mpriviousScroll = oldScrollX;
            }
        });*/


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    fab.setVisibility(View.INVISIBLE);
                    left.setVisibility(View.INVISIBLE);
                    right.setVisibility(View.INVISIBLE);

                } else if (dy < 0) {
                    fab.setVisibility(View.VISIBLE);
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab:
                    animateFAB();
                    break;
                case R.id.fab3:
                    dialog = new Dialog(MainActivity.this, R.style.PauseDialog);
                    dialog.setContentView(R.layout.custom_keep_up);
                    dialog.setTitle("Keep up");
                    dialog.show();
                    break;

                case R.id.fab10:
                    dialog = new Dialog(MainActivity.this, R.style.PauseDialog);
                    dialog.setContentView(R.layout.custom_dialog);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.setTitle("Info");
                    dialog.show();
                    break;

                case R.id.btn:

                    Intent tops = new Intent(MainActivity.this, Tops_page.class);
                    startActivity(tops);
                    break;

                case R.id.fab11:

                    Intent wall = new Intent(MainActivity.this, Brand_Wall.class);
                    startActivity(wall);
                    break;

                case R.id.fab9:

                    Intent best_verdies = new Intent(MainActivity.this, Best_verdies.class);
                    startActivity(best_verdies);

                    break;

                case R.id.fab4:

                    Intent profile = new Intent(MainActivity.this, Profile_page.class);
                    startActivity(profile);

                    break;


            }
        }
    };

    public void animateFAB() {

        if (isFabOpen) {

            fab1.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.startAnimation(fab_close);
            fab2.setClickable(false);
            fab3.startAnimation(fab_close);
            fab3.setClickable(false);
            fab4.startAnimation(fab_close);
            fab4.setClickable(false);
            fab5.startAnimation(fab_close);
            fab5.setClickable(false);
            fab6.startAnimation(fab_close);
            fab6.setClickable(false);
            fab7.startAnimation(fab_close);
            fab7.setClickable(false);
            fab8.startAnimation(fab_close);
            fab8.setClickable(false);
            fab9.startAnimation(fab_close);
            fab9.setClickable(false);
            fab10.startAnimation(fab_close);
            fab10.setClickable(false);
            fab11.startAnimation(fab_close);
            fab11.setClickable(false);
            btn.startAnimation(fab_close);
            btn.setClickable(false);
            isFabOpen = false;
            Log.d("hp", "close");

        } else {

            fab1.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.startAnimation(fab_open);
            fab2.setClickable(true);
            fab3.startAnimation(fab_open);
            fab3.setClickable(true);
            fab4.startAnimation(fab_open);
            fab4.setClickable(true);
            fab5.startAnimation(fab_open);
            fab5.setClickable(true);
            fab6.startAnimation(fab_open);
            fab6.setClickable(true);
            fab7.startAnimation(fab_open);
            fab7.setClickable(true);
            fab8.startAnimation(fab_open);
            fab8.setClickable(true);
            fab9.startAnimation(fab_open);
            fab9.setClickable(true);
            fab10.startAnimation(fab_open);
            fab10.setClickable(true);
            fab11.startAnimation(fab_open);
            fab11.setClickable(true);
            btn.startAnimation(fab_open);
            btn.setClickable(true);

            isFabOpen = true;
            Log.d("hp", "open");

        }
    }

    @Override
    public void onClick(View v) {
        if (left == v) {
            /*Toast.makeText(MainActivity.this,"Parties",Toast.LENGTH_LONG).show();*/
            Intent intent = new Intent(MainActivity.this, Parties_Activity.class);
            startActivity(intent);

            /*Fragment frag=new Parties();
            android.app.FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_container,frag);
            ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();*/
        }


    }

    //Downloading data asynchronously
    public class AsyncHttpTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                JSONParser call = new JSONParser();

                //DEPARTMENT = "http://api.androidhive.info/json/movies.json";
                DEPARTMENT = "http://api.androidhive.info/json/movies.json";
                jsonstr = call.makeServiceCall(DEPARTMENT);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("Json url view", jsonstr);
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Download complete. Lets update UI
            //Hide progressbar
            //mProgressBar.setVisibility(View.GONE);
            adapter = new Pojoimagetxt();

            if (jsonstr != null) {
                try {
                    JSONArray response = new JSONArray(jsonstr);
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject post = response.getJSONObject(i);
                        String item_name = post.getString("title").toString();
                        String imagename = post.getString("image").toString();
                        // set custom adapter with array list of pojo class
                        adapter.add(item_name);
                        adapter.add_image(imagename);


                    }
                    FillAdapter();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else
                Toast.makeText(MainActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();

        }
    }

    private void FillAdapter() {


        // Set layout manager
        int orientation = getLayoutManagerOrientation(getResources().getConfiguration().orientation);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, orientation, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // Add the sticky headers decoration
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);

        // Add decoration for dividers between list items
        recyclerView.addItemDecoration(new DividerDecoration(this));

      /*  // Add touch listeners
        StickyRecyclerHeadersTouchListener touchListener =
                new StickyRecyclerHeadersTouchListener(recyclerView, headersDecor);


      *//*  touchListener.setOnHeaderClickListener(
                new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
                    @Override
                    public void onHeaderClick(View header, int position, long headerId) {
                        Toast.makeText(MainActivity.this, "Header position: " + position + ", id: " + headerId,
                                Toast.LENGTH_SHORT).show();
                    }
                });*//*

        recyclerView.addOnItemTouchListener(touchListener);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapter.remove(adapter.getItem(position));
            }
        }));
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });*/
    }


    private int getLayoutManagerOrientation(int activityOrientation) {
        if (activityOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            return LinearLayoutManager.VERTICAL;
        } else {
            return LinearLayoutManager.HORIZONTAL;
        }
    }


    public class Pojoimagetxt extends Pojo_image_txt<RecyclerView.ViewHolder>
            implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

        ImageView iv_post_image;
        RecyclerView rv_comments;
        RecyclerView rv_privious;

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_header, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

            TextView tv_profile = (TextView) holder.itemView.findViewById(R.id.tv_profile);
            tv_profile.setText(getItem(position));

            CircleImageView profile_image_one = (CircleImageView) holder.itemView.findViewById(R.id.profile_image_one);

            Picasso.with(getApplicationContext())
                    .load(adapter.get_image_Item(position))
                    .placeholder(R.drawable.placeholder)   // optional
                    .resize(400, 400)                        // optional
                    .into(profile_image_one);


            CircleImageView profile_image_two = (CircleImageView) holder.itemView.findViewById(R.id.profile_image_two);

            Picasso.with(getApplicationContext())
                    .load(adapter.get_image_Item(position))
                    .placeholder(R.drawable.placeholder)   // optional
                    .resize(400, 400)                        // optional
                    .into(profile_image_two);

            // holder.itemView.setBackgroundColor(getRandomColor());
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_item, parent, false);

            return new RecyclerView.ViewHolder(view) {
            };
        }


        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            iv_post_image = (ImageView) holder.itemView.findViewById(R.id.iv_post_image);
            rv_comments = (RecyclerView) holder.itemView.findViewById(R.id.rv_comments);
            rv_privious = (RecyclerView) holder.itemView.findViewById(R.id.rv_privious);

            Picasso.with(getApplicationContext())
                    .load(adapter.get_image_Item(position))
                    .placeholder(R.drawable.placeholder)     // optional
                    .resize(400, 400)                        // optional
                    .into(iv_post_image);


            horizontalAdapter = new HorizontalAdapter(mcontext, adapter);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
            rv_comments.setLayoutManager(horizontalLayoutManagaer);
            rv_comments.setAdapter(horizontalAdapter);

            privious_post = new PriviousPost(mcontext, adapter);

            LinearLayoutManager horizontalLayoutManagaer_Two
                    = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
            rv_privious.setLayoutManager(horizontalLayoutManagaer_Two);
            rv_privious.setAdapter(privious_post);
            // Add decoration for dividers between list items

            rv_privious.addItemDecoration(new DividerDecoration(getApplicationContext()));

            rv_privious.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int hPosition) {
                    // Toast.makeText(getApplicationContext(), "V:" + position + "h:" + hPosition, Toast.LENGTH_SHORT).show();
                    ImageView iv_pr_post = (ImageView) holder.itemView.findViewById(R.id.iv_post_image);
                    Picasso.with(MainActivity.this)
                            .load(adapter.get_image_Item(hPosition))
                            .placeholder(R.drawable.placeholder)     // optional
                            .resize(400, 400)                        // optional
                            .into(iv_pr_post);
                }
            }));
        }

        @Override
        public long getHeaderId(int position) {
            if (position == 0) {
                return 0;
            } else {
                return get_image_Item(position).hashCode();
            }
        }

        private int getRandomColor() {
            SecureRandom rgen = new SecureRandom();
            return Color.HSVToColor(150, new float[]{
                    rgen.nextInt(150), 1, 1
            });
        }


    }


}
