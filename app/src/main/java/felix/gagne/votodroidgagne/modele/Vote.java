package felix.gagne.votodroidgagne.modele;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vote {

    @PrimaryKey(autoGenerate = true)
    Long id;

    @ColumnInfo
    Long value;

    @ColumnInfo
    String nomDuVotant;
}
