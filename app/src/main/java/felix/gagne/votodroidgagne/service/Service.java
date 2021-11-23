package felix.gagne.votodroidgagne.service;

import androidx.room.Room;

import felix.gagne.votodroidgagne.dao.MaBD;
import felix.gagne.votodroidgagne.exceptions.MauvaisVote;
import felix.gagne.votodroidgagne.exceptions.MauvaiseQuestion;
import felix.gagne.votodroidgagne.modele.Question;
import felix.gagne.votodroidgagne.modele.Vote;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Service {
    private static Service single_instance = null;
    private MaBD maBD;

    private Service (MaBD maBD){
        this.maBD = maBD;
    }

    public static Service getInstance(MaBD maBD)
    {
        if (single_instance == null)
            single_instance = new Service(maBD);

        return single_instance;
    }


    public void creerQuestion(Question vdQuestion) throws MauvaiseQuestion
    {
        // Validation
        if (vdQuestion.question == null || vdQuestion.question.trim().length() == 0) throw new MauvaiseQuestion("Question vide");
        if (vdQuestion.question.trim().length() < 5) throw new MauvaiseQuestion("Question trop courte");
        if (vdQuestion.question.trim().length() > 255) throw new MauvaiseQuestion("Question trop longue");
        if (vdQuestion.id != null) throw new MauvaiseQuestion("Id non nul. La BD doit le gérer");

         //Doublon du texte de la question
        for (Question q : toutesLesQuestions()){
            if (q.question.toUpperCase().equals(vdQuestion.question.toUpperCase())){
                throw new MauvaiseQuestion("Question existante");
            }
        }

        // Ajout
        vdQuestion.id = maBD.dao().saveQuestion(vdQuestion);
    }


    public void creerVote(Vote vdVote) throws MauvaisVote {
        if(vdVote.value == 0) throw new MauvaisVote("Le vote n'a pas de valeur.");

        if (vdVote.nomDuVotant == null || vdVote.nomDuVotant.trim().length() == 0) throw new MauvaisVote("Veuillez inserer le nom du votant");
        if (vdVote.nomDuVotant.trim().length() < 4) throw new MauvaisVote("Nom du votant trop court.");
        if (vdVote.nomDuVotant.trim().length() > 15) throw new MauvaisVote("Nom du votant trop long.");

        //Ajout
        vdVote.id = maBD.dao().saveVote(vdVote);
    }


    public List<Question> toutesLesQuestions() {
        //TODO Trier la liste reçue en BD par nombre de votes et la retourner
        return new ArrayList<>();
    }


    public float moyenneVotes(Question question) {
        return 0;
    }


    public float ecartTypeVotes(Question question) {
        return 0;
    }


    public Map<Integer, Integer> distributionVotes(Question question) {
        return null;
    }
}
