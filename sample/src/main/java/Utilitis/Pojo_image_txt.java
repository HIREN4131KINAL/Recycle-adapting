package Utilitis;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


/**
 * Adapter holding a list of animal names of type String. Note that each item must be unique.
 */


public abstract class Pojo_image_txt<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    public static ArrayList<String> items = new ArrayList<String>();
    public static ArrayList<String> image_item = new ArrayList<String>();

    public Pojo_image_txt() {
        setHasStableIds(true);
    }

    public void add(String object) {
        items.add(object);
        notifyDataSetChanged();
    }

    public void add_image(String object) {
        image_item.add(object);
        notifyDataSetChanged();
    }

/*    public void add(int index, String object) {
        items.add(index, object);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends String> collection) {
        if (collection != null) {
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void addAll(String... items) {
        addAll(Arrays.asList(items));
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void remove(String object) {
        items.remove(object);
        notifyDataSetChanged();
    }*/

    public String getItem(int position) {
        return items.get(position);
    }

    public String get_image_Item(int position) {
        return image_item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        // return items.size();
        return image_item.size();
    }

}
