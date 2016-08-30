package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tranetech.infocity.userbay.MainActivity;
import com.tranetech.infocity.userbay.R;

import Utilitis.Pojo_image_txt;

/**
 * Created by HIREN AMALIYAR on 26/07/2016.
 */
public class PriviousPost extends RecyclerView.Adapter<PriviousPost.MyViewHolder> {
    private Context mcontext;
    private MainActivity.Pojoimagetxt adapter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_profile_privious;

        public MyViewHolder(View view) {
            super(view);
            iv_profile_privious = (ImageView) view.findViewById(R.id.iv_profile_privious);
        }
    }

    public PriviousPost(Context mcontext, MainActivity.Pojoimagetxt adapter) {
        this.mcontext = mcontext;
        this.adapter = adapter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_privious_post, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Picasso.with(mcontext)
                .load(adapter.get_image_Item(position))
                .placeholder(R.drawable.placeholder)   // optional
                .resize(400, 400)                        // optional
                .into(holder.iv_profile_privious);
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount();
    }
}