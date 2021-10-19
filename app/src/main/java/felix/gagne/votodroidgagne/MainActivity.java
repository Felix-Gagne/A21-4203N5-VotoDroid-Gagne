package felix.gagne.votodroidgagne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import felix.gagne.votodroidgagne.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("VotoDroid");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Initialisation du recyclerView de la page d'acceuil.
//        this.initRecycler();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.bouttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intention = new Intent(MainActivity.this, AjouterActivity.class);
                startActivity(intention);
            }
        });
    }

//    private void initRecycler()
//    {
//        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
//        recyclerView.setHasFixedSize(true);
//
//    }
}