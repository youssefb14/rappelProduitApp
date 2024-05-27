package com.example.tprojet.ui.configuration;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tprojet.R;

import java.util.List;

public class SelectorAdapter extends RecyclerView.Adapter<SelectorAdapter.ViewHolder> {
    private List<SelectorItem> selectorItems;

    public SelectorAdapter(List<SelectorItem> selectorItems) {
        this.selectorItems = selectorItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selector_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SelectorItem selectorItem = selectorItems.get(position);
        holder.bind(selectorItem);
    }

    @Override
    public int getItemCount() {
        return selectorItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private Spinner optionsSpinner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
            optionsSpinner = itemView.findViewById(R.id.spinner);
        }

        public void bind(SelectorItem selectorItem) {
            titleTextView.setText(selectorItem.getTitle());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_item, selectorItem.getOptions());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            optionsSpinner.setAdapter(adapter);
            optionsSpinner.setSelection(adapter.getPosition(selectorItem.getSelectedOption()));

            optionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedOption = (String) parent.getItemAtPosition(position);
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(selectorItem.getTitle(), selectedOption);
                    editor.apply();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
}