package felix.gagne.votodroidgagne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import felix.gagne.votodroidgagne.dao.MaBD;
import felix.gagne.votodroidgagne.modele.Question;
import felix.gagne.votodroidgagne.modele.Vote;
import felix.gagne.votodroidgagne.service.Service;

public class ResultatActivity extends AppCompatActivity {

    BarChart chart;
    private MaBD bd;
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        setTitle("VotoDroid");

        chart = findViewById(R.id.graph);

        /* Settings for the graph - Change me if you want*/
        chart.setMaxVisibleValueCount(6);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1); // only intervals of 1 day
        xAxis.setLabelCount(6);
        xAxis.setValueFormatter(new DefaultAxisValueFormatter(0));

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(5, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setGranularity(1);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setValueFormatter(new DefaultAxisValueFormatter(0));
        chart.getDescription().setEnabled(false);
        chart.getAxisRight().setEnabled(false);

        //Initier la BD
        bd = Room.databaseBuilder(getApplicationContext(), MaBD.class, "Questions")
                .allowMainThreadQueries()
                .build();

        //Initier service
        service = Service.getInstance(bd);

        //idQuestion
        Long idQuestion = getIntent().getLongExtra("idQuestion", 0);

        //Set la question.
        TextView question = (TextView)findViewById(R.id.question123);
        question.setText(bd.dao().laQuestion(idQuestion));

        //Moyenne
        float moyennes = service.moyenneVotes(idQuestion);
        TextView moy = (TextView)findViewById(R.id.moyenne);
        moy.setText("" + moyennes);

        //Écart type ne marche pas mais voici ce que j'ai fait
//        double ecart = service.ecartTypeVotes(idQuestion);
//        TextView ecart1 = (TextView)findViewById(R.id.moyenne);
//        moy.setText("" + ecart);

        /* Data and function call to bind the data to the graph */
        Map<Integer, Integer> dataGraph = new HashMap<Integer, Integer>() {{
            put(1, bd.dao().votesPourQuestionParNote(idQuestion, 1).size());
            put(2, bd.dao().votesPourQuestionParNote(idQuestion, 2).size());
            put(3, bd.dao().votesPourQuestionParNote(idQuestion, 3).size());
            put(4, bd.dao().votesPourQuestionParNote(idQuestion, 4).size());
            put(5, bd.dao().votesPourQuestionParNote(idQuestion, 5).size());
        }};
        setData(dataGraph);
    }

    private void setData(Map<Integer, Integer> datas) {

        ArrayList<BarEntry> values = new ArrayList<>();

        /* Every bar entry is a bar in the graphic */
        for (Map.Entry<Integer, Integer> key : datas.entrySet()){
            values.add(new BarEntry(key.getKey() , key.getValue()));
        }

        BarDataSet set1;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Points");

            set1.setDrawIcons(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(.9f);
            chart.setData(data);
        }
    }
}