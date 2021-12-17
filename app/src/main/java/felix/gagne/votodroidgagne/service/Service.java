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


    public void creerVote(Vote vdVote, Long questionID) throws MauvaisVote {
        if(vdVote.value == 0) throw new MauvaisVote("Le vote n'a pas de valeur.");

        if (vdVote.nomDuVotant == null || vdVote.nomDuVotant.trim().length() == 0) throw new MauvaisVote("Veuillez inserer le nom du votant");
        if (vdVote.nomDuVotant.trim().length() < 4) throw new MauvaisVote("Nom du votant trop court.");
        if (vdVote.nomDuVotant.trim().length() > 15) throw new MauvaisVote("Nom du votant trop long.");

        //vote de la meme personne
        for (Vote v : toutesLesVotes()){
            if(v.nomDuVotant.toUpperCase().equals(vdVote.nomDuVotant.toUpperCase()) && questionID == v.idQuestion){
                throw new MauvaisVote("Le votant à déjà voté pour cette question.");
            }
        }

        //nb de vote par question
//        for(Question q : toutesLesQuestions()){
//            if(questionID == q.id){
//                maBD.dao().ajouterVote();
//            }
//        }

        //Ajout
        vdVote.id = maBD.dao().saveVote(vdVote);
    }


    public List<Question> toutesLesQuestions() {
        //TODO Trier la liste reçue en BD par nombre de votes et la retourner
        List list = new ArrayList();
        for(Question q : maBD.dao().tousLesQuestions())
        {
            list.add(q);
        }
        return list;
    }

    public List<Vote> toutesLesVotes()
    {
        List list = new ArrayList();
        for(Vote v : maBD.dao().tousLesVote())
        {
            list.add(v);
        }
        return list;
    }


    public float moyenneVotes(Long idQuestion)
    {
        float moyenne1;

        int nbValeur = maBD.dao().votesPourQuestionParNote(idQuestion,1).size() + maBD.dao().votesPourQuestionParNote(idQuestion,2).size() + maBD.dao().votesPourQuestionParNote(idQuestion,3).size() +
                maBD.dao().votesPourQuestionParNote(idQuestion,4).size() + maBD.dao().votesPourQuestionParNote(idQuestion,5).size();

        moyenne1 = (maBD.dao().votesPourQuestionParNote(idQuestion,1).size() + (maBD.dao().votesPourQuestionParNote(idQuestion,2).size() * 2) + (maBD.dao().votesPourQuestionParNote(idQuestion,3).size() * 3) +
                (maBD.dao().votesPourQuestionParNote(idQuestion,4).size() * 4) + (maBD.dao().votesPourQuestionParNote(idQuestion,5).size() * 5)) / nbValeur ;

        return moyenne1;
    }


    public double ecartTypeVotes(Long idQuestion) {
        //l'ecart type ne fonctionne pas, mais voici le code.
//        double ecartType;
//        int moyenne;
//        int nbVotes;
//        int somme;
//        int division;
//
//        //Calcule le nombre de vote
//        nbVotes = maBD.dao().votesPourQuestionParNote(idQuestion,1).size() + maBD.dao().votesPourQuestionParNote(idQuestion,2).size() + maBD.dao().votesPourQuestionParNote(idQuestion,3).size() +
//                maBD.dao().votesPourQuestionParNote(idQuestion,4).size() + maBD.dao().votesPourQuestionParNote(idQuestion,5).size();
//
//        //Calculer la moyenne.
//        moyenne = (maBD.dao().votesPourQuestionParNote(idQuestion,1).size() + (maBD.dao().votesPourQuestionParNote(idQuestion,2).size() * 2) + (maBD.dao().votesPourQuestionParNote(idQuestion,3).size() * 3) +
//                (maBD.dao().votesPourQuestionParNote(idQuestion,4).size() * 4) + (maBD.dao().votesPourQuestionParNote(idQuestion,5).size() * 5)) / nbVotes ;
//
//        //Somme de x * ((moyenne - x) * (moyenne - x))
//        //X = valeur du vote
//        somme = (maBD.dao().votesPourQuestionParNote(idQuestion, 1).size() * ((moyenne - 1) * (moyenne - 1)) + (maBD.dao().votesPourQuestionParNote(idQuestion, 2).size() * ((moyenne - 2) * (moyenne - 2)) +
//                (maBD.dao().votesPourQuestionParNote(idQuestion, 3).size() * ((moyenne - 3) * (moyenne - 3)) + (maBD.dao().votesPourQuestionParNote(idQuestion, 4).size() * ((moyenne - 4) * (moyenne - 4)) +
//                        (maBD.dao().votesPourQuestionParNote(idQuestion, 5).size() * ((moyenne - 5) * (moyenne - 5)))))));
//
//        //Division de la somme par le nombre de vote
//        division = somme / nbVotes;
//
//        //Racine carrer de la divison
//        ecartType = Math.sqrt(division);
//
//        return ecartType;

        return 0;
    }

    public Map<Integer, Integer> distributionVotes(Question question) {
        return null;
    }

    public void supprimerQuestion()
    {
        maBD.dao().supprimerQuestionBd();
        maBD.dao().supprimerVoteBd();
        toutesLesVotes().clear();
        toutesLesQuestions().clear();
    }

    public void supprimerVotes()
    {
        maBD.dao().supprimerVoteBd();
        toutesLesVotes().clear();
    }
}
