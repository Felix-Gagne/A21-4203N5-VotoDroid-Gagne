package felix.gagne.votodroidgagne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import felix.gagne.votodroidgagne.dao.MaBD;
import felix.gagne.votodroidgagne.databinding.ActivityMainBinding;
import felix.gagne.votodroidgagne.modele.Question;
import felix.gagne.votodroidgagne.service.Service;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    QuestionAdapter adapter;
    private MaBD bd;
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // initialiser l'ecran
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setTitle("VotoDroid");

        MaBD bd = Room.databaseBuilder(getApplicationContext(), MaBD.class, "Questions")
                .allowMainThreadQueries()
                .build();

        service = Service.getInstance(bd);

        //Initialisation du recyclerView de la page d'acceuil.
        this.initRecycler();
        this.remplacer();


        binding.bouttonAjouter.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                Intent intention = new Intent(MainActivity.this, AjouterActivity.class);
                startActivity(intention);
            }
        });


    }

    private void initRecycler()
    {
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);

        //linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Spécifier un adaptateur
        adapter = new QuestionAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void remplacer(){
        adapter.list.clear();
        for(Question q : service.toutesLesQuestions())
        {
           adapter.list.add(q);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.delete_action)
        {
            Toast.makeText(getApplicationContext(), "Questions supprimé", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.deletevt_action)
        {
            Toast.makeText(getApplicationContext(), "Votes suprimé", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void supprimerQuestions()
    {

    }

    private void supprimerVote()
    {

    }

}