package com.carvalho.education.hbc.estudolist.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carvalho.education.hbc.R;
import com.carvalho.education.hbc.entities.Estudo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 06094547659 on 12/07/2016.
 */
public class EstudoAdapter extends RecyclerView.Adapter<EstudoAdapter.ViewHolder> {


    private List<Estudo> estudoList;
    private OnItemClickListerner onItemClickListerner;

    public EstudoAdapter(List<Estudo> estudoList, OnItemClickListerner onItemClickListerner) {
        this.estudoList = estudoList;
        this.onItemClickListerner = onItemClickListerner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_stored_estudo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Estudo currentEstudo = estudoList.get(position);
        holder.txtEstudo.setText(currentEstudo.getComment().toString()); // alterar para foreing key
        holder.txtDate.setText(currentEstudo.getDate().toString());
        holder.txtEstudoId.setText(currentEstudo.getEstudoID().toString());

        holder.setOnItemClickListerner(currentEstudo, onItemClickListerner);
    }

    public void setEstudos(List<Estudo> estudos) {
        this.estudoList = estudos;
        notifyDataSetChanged();
    }

    public void removeEstudo(Estudo estudo) {
        estudoList.remove(estudo);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return estudoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtEstudoId)
        TextView txtEstudoId;
        @Bind(R.id.txtEstudo)
        TextView txtEstudo;
        @Bind(R.id.txtDate)
        TextView txtDate;
        @Bind(R.id.trash)
        ImageView trash;
        @Bind(R.id.edit)
        Button edit;
        @Bind(R.id.delete)
        Button delete;
        @Bind(R.id.layoutButtons)
        LinearLayout layoutButtons;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListerner(final Estudo currentEstudo, final OnItemClickListerner onItemClickListerner) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListerner.onItemClick(currentEstudo);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListerner.onDeleteClick(currentEstudo);
                }
            });
        }
    }


}
