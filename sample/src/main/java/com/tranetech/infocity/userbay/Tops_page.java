package com.tranetech.infocity.userbay;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Tops_page extends AppCompatActivity {
    ProgressDialog p;
    RecyclerView lv;
    String json;
    JSONParser jsonParser = new JSONParser();
    public static ArrayList<HashMap<String, String>> Movielist = null;
    public static final String URL_movie = "http://api.androidhive.info/json/movies.json";

    private static final String TAG_Movie = "title";
    private static final String TAG_image = "image";
    private static final String TAG_rating = "rating";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tops_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle("Tops");
        ab.setDisplayHomeAsUpEnabled(true);

        Spannable text = new SpannableString(ab.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        ab.setTitle(text);

        lv = (RecyclerView) findViewById(R.id.recyclerView);

        new LoadMovies().execute();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class LoadMovies extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            Log.e("helloo", "2");
            json = jsonParser.makeServiceCall(URL_movie);
            Log.e("b_name JSON: ", "> " + json);
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            Movielist = new ArrayList<HashMap<String, String>>();
            JSONArray b_name;
            if (json != null) {
                try {
                    b_name = new JSONArray(json);
                    if (b_name != null) {
                        // looping through All b_name
                        for (int i = 0; i < b_name.length(); i++) {
                            JSONObject c = b_name.getJSONObject(i);

                            // Storing each json item values in variable
                            String br_image = c.getString(TAG_image);
                            String br_name = c.getString(TAG_Movie);
                            String br_rating = c.getString(TAG_rating);

                            // creating new HashMap
                            HashMap<String, String> map = new HashMap<String, String>();

                            // adding each child node to HashMap key => value
                            //map.put(TAG_Image, br_image);
                            map.put(TAG_Movie, br_name);
                            map.put(TAG_image, br_image);
                            map.put(TAG_rating, br_rating);

                            // adding HashList to ArrayList
                            Movielist.add(map);
                        }

                    } else {
                        Log.d("b_name: ", "null");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            lv.setHasFixedSize(true);
            // use a linear layout manager
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            lv.setLayoutManager(mLayoutManager);
            // seting custom adapter
            lv.setAdapter(new MyAdapter(Movielist));


        }

    }

    // adapter class for Fragment home
    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<HashMap<String, String>> mDataset;

        public MyAdapter(ArrayList<HashMap<String, String>> BranchList) {
            mDataset = BranchList;
        }

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            private TextView rating, branch_name;
            private ImageView imageView;


            public ViewHolder(View itemView) {
                super(itemView);
                rating = (TextView) itemView.findViewById(R.id.count);
                branch_name = (TextView) itemView.findViewById(R.id.name);
                imageView = (ImageView) itemView.findViewById(R.id.profile_image);


            }
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tops, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {


            final String Branch_ID = mDataset.get(position).get(TAG_Movie);
            final String Rating = mDataset.get(position).get(TAG_rating);
            final String images = mDataset.get(position).get(TAG_image);

            holder.branch_name.setText(Branch_ID);

            holder.rating.setText(Rating);


            Picasso.with(Tops_page.this)
                    .load(images)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

}

