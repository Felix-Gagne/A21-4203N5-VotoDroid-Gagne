package felix.gagne.votodroidgagne;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import felix.gagne.votodroidgagne.databinding.ActivityMainBinding;
import felix.gagne.votodroidgagne.databinding.ActivityResultatBinding;

public class ResultatActivity extends AppCompatActivity {

    ActivityResultatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // initialiser l'ecran
        binding = ActivityResultatBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setTitle("VotoDroid");
    }
}