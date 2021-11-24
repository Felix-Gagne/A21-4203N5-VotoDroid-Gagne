package felix.gagne.votodroidgagne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import felix.gagne.votodroidgagne.dao.MaBD;
import felix.gagne.votodroidgagne.databinding.ActivityAjouterBinding;
import felix.gagne.votodroidgagne.databinding.ActivityMainBinding;
import felix.gagne.votodroidgagne.exceptions.MauvaiseQuestion;
import felix.gagne.votodroidgagne.modele.Question;
import felix.gagne.votodroidgagne.service.Service;

public class AjouterActivity extends AppCompatActivity {

    ActivityAjouterBinding binding;
    private Service service;
    private MaBD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAjouterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setTitle("VotoDroid");

        MaBD bd = Room.databaseBuilder(getApplicationContext(), MaBD.class, "Questions")
                .allowMainThreadQueries()
                .build();



        service = Service.getInstance(bd);

        binding.poseLaQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creerQuestion();
            }
        });

    }

    private void creerQuestion()
    {
        EditText text = (EditText)findViewById(R.id.question);
        try
        {
            Question laQuestion = new Question();
            laQuestion.question = text.getText().toString();
            laQuestion.nbDeVote = 0;
            service.creerQuestion(laQuestion);
            Intent i1 = new Intent(AjouterActivity.this, MainActivity.class);
            startActivity(i1);
        }
        catch (MauvaiseQuestion m)
        {
            Toast.makeText(getApplicationContext(), m.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}