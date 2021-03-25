package fr.sophieham.listeplanetes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class PlanetAdapter extends BaseAdapter {
    private final MainActivity mainActivityContext;
    private ArrayList<String> planetes;
    private Data data;
    public static int nb=0;

    public PlanetAdapter(MainActivity mainActivityContext, Data data) {
        this.planetes = data.getPlanetes();
        this.data = data;
        this.mainActivityContext = mainActivityContext;
    }
    @Override
    public int getCount() {

        return planetes.size();
    }

    @Override
    public Object getItem(int i) {
        return planetes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    mainActivityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.listeitem, null);
        }
        final TextView nomPlanete = itemView.findViewById(R.id.textView);
        final CheckBox checkBox = itemView.findViewById(R.id.checkbox);
        final Spinner spinner = itemView.findViewById(R.id.spinner);
        nomPlanete.setText(planetes.get(position));


        final ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(mainActivityContext, android.R.layout.simple_spinner_item, data.getTaillePlanetes());
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinadapter);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CheckBox checkBox = compoundButton.findViewById(R.id.checkbox);
                spinner.setEnabled(!checkBox.isChecked());
                spinadapter.notifyDataSetChanged();

                if(checkBox.isChecked()){
                    nb++;
                }
                else {
                    nb--;
                }
                if(nb==planetes.size()){
                    Button btn = mainActivityContext.findViewById(R.id.button);
                    btn.setEnabled(true);
                    }
            }
        });
        return itemView;
    }

}
