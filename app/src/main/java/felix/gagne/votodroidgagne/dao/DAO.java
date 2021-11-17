package felix.gagne.votodroidgagne.dao;

import felix.gagne.votodroidgagne.modele.Question;

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

    @Query("SELECT * FROM Question")
    public abstract List<Question> tousLesQuestions();

}
