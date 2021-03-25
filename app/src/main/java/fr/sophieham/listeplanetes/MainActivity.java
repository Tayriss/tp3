package fr.sophieham.listeplanetes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ListView listview;
    PlanetAdapter adapter;
    Button btn;
    Spinner spinner;
    Data data = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listview = findViewById(R.id.listView);

        adapter = new PlanetAdapter(this, data);
        listview.setAdapter(adapter);

        btn = findViewById(R.id.button);
        btn.setEnabled(false);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int j = 0; // compteur score
                String[] taillesPlanetes = data.getTaillePlanetes();
                for (int i=0; i<taillesPlanetes.length; i++){
                    View vw = listview.getChildAt(i);
                    spinner = vw.findViewById(R.id.spinner);
                    if(spinner.getSelectedItem().toString().equals(taillesPlanetes[i])){
                        j++; // on incrémente le score
                    }
                }
                Toast.makeText(MainActivity.this, "Bonnes réponses: "+j, Toast.LENGTH_LONG).show();
            }
        });
    }
}
