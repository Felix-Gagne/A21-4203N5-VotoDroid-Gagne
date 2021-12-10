package felix.gagne.votodroidgagne;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import felix.gagne.votodroidgagne.dao.MaBD;
import felix.gagne.votodroidgagne.modele.Question;


public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder>{
    public List<Question> list;

    //Donne une référance à la vue pour chaque data item
    //Les data items complexe peuvent avoir besoin de plus de 1 vue par item
    //donc je donne accès a tout les vue pour un data item dans un view holder
    public static class MyViewHolder extends  RecyclerView.ViewHolder
    {
        public TextView tvQuestion;
        public ImageButton btButton;
        public MyViewHolder(LinearLayout v){
            super(v);
            tvQuestion = v.findViewById(R.id.tvQuestion);
            btButton = v.findViewById(R.id.btButton);
        }
    }

    //Meilleur Constructeur
    public QuestionAdapter()
    {
        list = new ArrayList<>();
    }

    //Crée de nouvelle vues
    @Override
    public QuestionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
          //Crée une nouvelle vue
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        Log.i("DEBOGAGE", "appel a onCreateViewHolder");
        return vh;
    }

    //Remplace le contenu d'une vue
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        //Prend l'élément du dataset a cette position
        //Remplace le contenu de la vue avec cet élément
        Question questionCourante = list.get(position);
        holder.tvQuestion.setText(questionCourante.question);
        Log.i("DEBOGAGE", "appel a onBindViewHolder" + position);
        holder.tvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VoteActivity.class);
                intent.putExtra("idQuestion", questionCourante.id);
                v.getContext().startActivity(intent);
            }
        });

        holder.btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ResultatActivity.class);
                intent.putExtra("idQuestion", questionCourante.id);
                v.getContext().startActivity(intent);
            }
        });

    }

    //renvoie la taille de la liste
    @Override
    public int getItemCount()
    {
        return list.size();
    }
}


