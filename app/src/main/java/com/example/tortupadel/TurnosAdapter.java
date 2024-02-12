package com.example.tortupadel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TurnosAdapter extends ArrayAdapter<String> {
    private List<String> turnos;

    public TurnosAdapter(@NonNull Context context, @NonNull List<String> turnos) {
        super(context, 0, turnos);
        this.turnos = turnos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String turno = turnos.get(position);

        holder.textView.setText(turno);

        return convertView;
    }

    static class ViewHolder {
        TextView textView;
    }

}