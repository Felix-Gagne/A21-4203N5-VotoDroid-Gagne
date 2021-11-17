package felix.gagne.votodroidgagne.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import felix.gagne.votodroidgagne.modele.Question;

@Database(entities = {Question.class},
        version = 1)
@TypeConverters({Converters.class})
public abstract class MaBD extends RoomDatabase {
    public abstract DAO dao();
}
