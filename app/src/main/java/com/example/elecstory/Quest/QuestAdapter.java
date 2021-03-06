package com.example.elecstory.Quest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elecstory.Object.Item;
import com.example.elecstory.R;

import java.util.ArrayList;

@SuppressLint("SetTextI18n")
public class QuestAdapter extends BaseAdapter {

    private Context Context;
    private ArrayList<Item> listItems;
    private ArrayList<Long> listNbObjects;
    private LayoutInflater layoutInflater;
    View view;

    protected static final String TAG = "Elecstory.QuestAdapter";


    public QuestAdapter(Context o, ArrayList<Item> listItem, ArrayList<Long> listNbObject){
        this.Context = o;
        this.listItems = listItem;
        this.listNbObjects = listNbObject;
        this.layoutInflater = LayoutInflater.from(Context);
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        layoutInflater = (LayoutInflater) Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = new View(Context);
        view = layoutInflater.inflate(R.layout.quest_adapter, null);

        ImageView imageView = view.findViewById(R.id.ImageObject);
        TextView nameTitle = view.findViewById(R.id.NameObject);
        TextView nbObject = view.findViewById(R.id.NbObject);

        imageView.setImageResource(Integer.parseInt(listItems.get(position).getSkin()));
        nameTitle.setText(listItems.get(position).getName());
        nbObject.setText("x"+listNbObjects.get(position));

        return view;
    }
}
