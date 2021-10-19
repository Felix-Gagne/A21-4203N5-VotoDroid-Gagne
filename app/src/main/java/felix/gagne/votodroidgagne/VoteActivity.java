package felix.gagne.votodroidgagne;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import felix.gagne.votodroidgagne.databinding.ActivityVoteBinding;

public class VoteActivity extends AppCompatActivity {

    ActivityVoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("VotoDroid");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        binding = ActivityVoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}