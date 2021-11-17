package felix.gagne.votodroidgagne.modele;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(indices = {@Index("question")})
public class Question {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public String question;
}
