package com.example.liyuchen.ui.epidemic;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liyuchen.MainActivity;
import com.example.liyuchen.R;

import java.util.List;

public class EntityRelationAdapter extends RecyclerView.Adapter<EntityRelationAdapter.ViewHolder> {

    private List<EntityRelationLayout> relatedlist;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView relation;
        private TextView symptom;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textView_entityrelationname);
            relation=itemView.findViewById(R.id.textView_entityrelation);
            symptom=itemView.findViewById(R.id.textView_entityrelationsymptom);
            image=itemView.findViewById(R.id.image_relationsearch);
//            image.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent=new Intent(itemView.getContext(), MainActivity.class);
//                    intent.putExtra("title",symptom.getText());
//                    intent.putExtra("return","1");
//                    v.getContext().startActivity(intent);
//                }
//            });
        }
    }

    public EntityRelationAdapter (List<EntityRelationLayout> list) {
        relatedlist = list;
    }

    @NonNull
    @Override
    public EntityRelationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entityrelationlayout,parent,false);
        EntityRelationAdapter.ViewHolder holder = new EntityRelationAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EntityRelationAdapter.ViewHolder holder, int position) {
        EntityRelationLayout re=relatedlist.get(position);
        holder.name.setText(re.name);
        holder.relation.setText(re.relation);
        holder.symptom.setText(re.symptom);
    }

    @Override
    public int getItemCount() {
        return relatedlist.size();
    }
}
