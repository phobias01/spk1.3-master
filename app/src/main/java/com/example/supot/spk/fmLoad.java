package com.example.supot.spk;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmLoad extends Fragment {


    public fmLoad() {
        // Required empty public constructor
    }

    private Context context;
    private ListView loadList;
    private ArrayList<String> arrayList;
    private ArrayAdapter adapter;
    private Button butOpen,butDel;
    private TextView TextView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_load, container, false);
        initloadList(view);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void initloadList(View view){
        File f = new File("/การ์ด SD ของ SanDisk/project");
        File[] list = f.listFiles();
        loadList = (ListView) view.findViewById(R.id.loadList);
        butOpen = (Button) view.findViewById(R.id.butOpen);
        butDel = (Button) view.findViewById(R.id.butDel);
        TextView2 = (TextView) view.findViewById(R.id.textView2);
        //int numlist = list.length;
        arrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            arrayList.add("ListView Items " + i);

        }
        //TextView2.setText(numlist);
        /*for (int i = 0; i < list.length; i++) {
            arrayList.add(list[i].getName());
        }*/
        adapter = new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayList);
        loadList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        loadList.setAdapter(adapter);

        loadList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                butDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SparseBooleanArray checkedItemPositions = loadList.getCheckedItemPositions();
                        int itemCount = adapter.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                adapter.remove(arrayList.get(i));
                                Toast.makeText(getContext(), "DELETE "+arrayList.get(i) , Toast.LENGTH_LONG).show();
                            }
                        }
                        checkedItemPositions.clear();
                        adapter.notifyDataSetChanged();
                        //Toast.makeText(getContext(), "DELETE"+ , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
