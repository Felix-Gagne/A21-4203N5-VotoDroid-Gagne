package felix.gagne.votodroidgagne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import felix.gagne.votodroidgagne.databinding.ActivityVoteBinding;

public class VoteActivity extends AppCompatActivity {

    ActivityVoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setTitle("VotoDroid");

        binding.bouttonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}