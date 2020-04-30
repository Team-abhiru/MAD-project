package com.example.scienceguider;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter {

//    RecyclerView recyclerView;
//    Context context;
//    ArrayList<String> items= new ArrayList<>();
//    ArrayList<String> urls = new ArrayList<>();
//
//    public void update(String name,String url){
//
//        items.add(name);
//        urls.add(url);
//        notifyDataSetChanged();
//    }
//
//
//
//    public MyAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items,ArrayList<String> urls) {
//        this.recyclerView = recyclerView;
//        this.context = context;
//        this.items = items;
//        this.urls = urls;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        holder.nameOfFile.setText(items.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//
//        return items.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//
//        TextView nameOfFile;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            nameOfFile = itemView.findViewById(R.id.nameOfFile);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    int position = recyclerView.getChildLayoutPosition(view);
//                    Intent intent = new Intent();
//                    intent.setType(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(urls.get(position)));
//                    context.startActivity(intent);
//
//                }
//            });
//        }
//    }

    private Activity contex;
    private List<Upload_file> list;

    public MyAdapter(Activity contex, List<Upload_file> list){
        super(contex,R.layout.items,list);

        this.contex = contex;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = contex.getLayoutInflater();

        View listView = inflater.inflate(R.layout.items,null,true);

        TextView fileName= (TextView) listView.findViewById(R.id.id_file_name);

        Upload_file file = list.get(position);

        fileName.setText(file.getFileName());

        return listView;
    }
}
