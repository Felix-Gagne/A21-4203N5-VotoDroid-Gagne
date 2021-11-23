package felix.gagne.votodroidgagne.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import felix.gagne.votodroidgagne.modele.Question;
import felix.gagne.votodroidgagne.modele.Vote;

@Database(entities = {Question.class, Vote.class},
        version = 2)
@TypeConverters({Converters.class})
public abstract class MaBD extends RoomDatabase {
    public abstract DAO dao();
}
