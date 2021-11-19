package felix.gagne.votodroidgagne.modele;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vote {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public Long value;

    @ColumnInfo
    public String nomDuVotant;
}
