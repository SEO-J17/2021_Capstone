package jeongbuk.galaxys3.fishfinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<ItemData> datalist;

    public MyRecyclerAdapter(Context context, ArrayList<ItemData> items) {
        this.context = context;
        this.datalist = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item,parent,false);
        VH holder = new VH(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        ItemData item = datalist.get(position);
        vh.title.setText(item.getTitle());
        vh.content.setText(item.getContent());
        vh.date.setText(item.getDate());
        vh.writer.setText(item.getName());

        Glide.with(context).load(item.getImgpath()).into(vh.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), BoardView_Activity.class);
                intent.putExtra("writer", datalist.get(position).getName());
                intent.putExtra("post_time", datalist.get(position).getDate());
                intent.putExtra("title", datalist.get(position).getTitle());
                intent.putExtra("content", datalist.get(position).getContent());
                intent.putExtra("img_path", datalist.get(position).getImgpath());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView title, content, writer, date;
        ImageView imageView;
        public VH(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.board_title);
            content = itemView.findViewById(R.id.board_content);
            writer = itemView.findViewById(R.id.board_writer);
            date = itemView.findViewById(R.id.board_date);
            imageView = itemView.findViewById(R.id.board_image);
        }
    }


}
