package koshon.com.autosudoku;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class GridAdapter extends android.widget.BaseAdapter {
    GridView[] blocks;
    Context context;
    int id;

    public GridAdapter(Context context, int id, GridView[] blocks) {
        this.blocks = blocks;
        this.context = context;
        this.id = id;

    }

    @Override
    public int getCount() {
        return blocks.length;
    }

    @Override
    public Object getItem(int i) {
        return blocks[i];
    }

    @Override
    public long getItemId(int i) {
        return blocks[i].getId();
    }

    class ViewHolder {
        GridView block;

        ViewHolder(View v) {
            block = v.findViewById(id);
        }

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.square, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        holder.block = blocks[i];
        return null;
    }
}
