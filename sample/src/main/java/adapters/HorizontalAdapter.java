package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tranetech.infocity.userbay.MainActivity;
import com.tranetech.infocity.userbay.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HIREN AMALIYAR on 26/07/2016.
 */
public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    private Context mcontext;
    private MainActivity.Pojoimagetxt adapter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtView;
        private CircleImageView profile_image_cmnts;

        public MyViewHolder(View view) {
            super(view);
            txtView = (TextView) view.findViewById(R.id.tv_cmnts);
            profile_image_cmnts = (CircleImageView) view.findViewById(R.id.profile_image_cmnts);

        }
    }

    public HorizontalAdapter(Context mcontext, MainActivity.Pojoimagetxt adapter2) {
        this.mcontext = mcontext;
        this.adapter = adapter2;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_comments, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.txtView.setText(adapter.getItem(position));
        Picasso.with(mcontext)
                .load(adapter.get_image_Item(position))
                .placeholder(R.drawable.placeholder)   // optional
                .resize(400, 400)                        // optional
                .into(holder.profile_image_cmnts);
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount();
    }
}