package felix.gagne.votodroidgagne.dao;

import felix.gagne.votodroidgagne.modele.Question;
import felix.gagne.votodroidgagne.modele.Vote;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public abstract class DAO {

    @Insert
    public abstract Long saveQuestion(Question question);

    @Insert
    public abstract List<Long> saveQuestions(List<Question> questions);

    @Insert
    public abstract Long saveVote(Vote vote);

    @Query("SELECT * FROM Question")
    public abstract List<Question> tousLesQuestions();

    @Query("SELECT * FROM Vote")
    public abstract List<Vote> tousLesVote();

}
