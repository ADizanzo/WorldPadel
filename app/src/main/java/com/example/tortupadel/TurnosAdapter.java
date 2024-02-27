package com.example.tortupadel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TurnosAdapter extends ArrayAdapter<String> {
    private List<String> turnos;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onModificarClick(int position);

        void onEliminarClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public TurnosAdapter(@NonNull Context context, @NonNull List<String> turnos) {
        super(context, 0, turnos);
        this.turnos = turnos;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item_1, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(android.R.id.text1);
            holder.modificarButton = convertView.findViewById(R.id.modificarButton);
            holder.eliminarButton = convertView.findViewById(R.id.darDeBajaButton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String turno = turnos.get(position);

        holder.textView.setText(turno);

        holder.modificarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onModificarClick(position);
                }
            }
        });

        holder.eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onEliminarClick(position);
                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView textView;
        ImageView modificarButton;
        ImageView eliminarButton;
    }
}