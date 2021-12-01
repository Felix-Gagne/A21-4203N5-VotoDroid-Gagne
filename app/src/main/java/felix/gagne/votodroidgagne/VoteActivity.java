package felix.gagne.votodroidgagne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import felix.gagne.votodroidgagne.dao.MaBD;
import felix.gagne.votodroidgagne.databinding.ActivityVoteBinding;
import felix.gagne.votodroidgagne.exceptions.MauvaisVote;
import felix.gagne.votodroidgagne.modele.Vote;
import felix.gagne.votodroidgagne.service.Service;

public class VoteActivity extends AppCompatActivity {

    ActivityVoteBinding binding;
    private Service service;
    private MaBD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setTitle("VotoDroid");

        MaBD bd = Room.databaseBuilder(getApplicationContext(), MaBD.class, "Questions")
                .allowMainThreadQueries()
                .build();

        service = Service.getInstance(bd);

        binding.bouttonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreerVote();
            }
        });
    }

    private void CreerVote()
    {
        EditText nomDuVotant = (EditText)findViewById(R.id.nomDuVotant);
        RatingBar valeurVote = (RatingBar)findViewById(R.id.valeurDuVote);

        try
        {
            Long idQuestion = getIntent().getLongExtra("idQuestion", 0);
            Vote vote = new Vote();
            vote.nomDuVotant = nomDuVotant.getText().toString();
            vote.value = valeurVote.getRating();
            vote.idQuestion = idQuestion;
            service.creerVote(vote, idQuestion);
            Intent i1 = new Intent(VoteActivity.this, MainActivity.class);
            startActivity(i1);
        }
        catch (MauvaisVote m)
        {
            Toast.makeText(getApplicationContext(), m.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}