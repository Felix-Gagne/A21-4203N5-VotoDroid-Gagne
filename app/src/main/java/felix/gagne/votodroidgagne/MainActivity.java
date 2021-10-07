package felix.gagne.votodroidgagne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import felix.gagne.votodroidgagne.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("VoteDroid");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        binding =ActivityMainBinding.inflate(getLayoutInflater());
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
}