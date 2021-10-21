package felix.gagne.votodroidgagne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import felix.gagne.votodroidgagne.databinding.ActivityAjouterBinding;
import felix.gagne.votodroidgagne.databinding.ActivityMainBinding;

public class AjouterActivity extends AppCompatActivity {

    ActivityAjouterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAjouterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setTitle("VotoDroid");

        binding.poseLaQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(AjouterActivity.this, MainActivity.class);
                startActivity(i1);
            }
        });
    }
}