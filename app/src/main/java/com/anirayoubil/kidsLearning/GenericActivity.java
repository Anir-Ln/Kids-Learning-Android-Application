package com.anirayoubil.kidsLearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.anirayoubil.kidsLearning.adapter.LessonAdapter;
import com.anirayoubil.kidsLearning.helpers.LessonElementHelper;
import com.anirayoubil.kidsLearning.helpers.SQLiteDbHelper;

public class GenericActivity extends AppCompatActivity implements RecyclerViewAction {

    RecyclerView recyclerViewShape;
    RecyclerView.Adapter adapter;

    static List<MediaPlayer> mediaElements;
    String[] elementsNames;
//    static MediaPlayer mpone;
//    static MediaPlayer mptwo;
//    static MediaPlayer mpthree;
//    static MediaPlayer mpfour;
//    static MediaPlayer mpfive;
//    static MediaPlayer mpsix;
//    static MediaPlayer mpseven;
//    static MediaPlayer mpeight;

    ImageView backMenu;
    TextView colorName;
    Resources resources;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_generic);

        recyclerViewShape = findViewById(R.id.recycler_element);
        backMenu = findViewById(R.id.menu_nav);
        colorName = findViewById(R.id.element_number);
        resources = getResources();

        // get the lessonName from the intent extra
        String lessonName = getIntent().getStringExtra("lessonName");
        System.out.println(lessonName);
        // get the elements names from the database
        elementsNames = new SQLiteDbHelper(GenericActivity.this).getElementsByLessonName(lessonName);
        mediaElements = new ArrayList<>();
        int sound;
        for (String element : elementsNames) {
            sound = resources.getIdentifier(element, "raw", getPackageName());
            mediaElements.add(MediaPlayer.create(GenericActivity.this, sound));
        }

        backMenu.setOnClickListener(v -> GenericActivity.super.onBackPressed());
        featuredShapes(elementsNames);
    }

    private void featuredShapes(String[] elementsNames) {
        ArrayList<LessonElementHelper> questionLocations = new ArrayList<>();
        int drawable;
        for (String element: elementsNames) {
            drawable = resources.getIdentifier(element, "drawable", getPackageName());
            questionLocations.add(new LessonElementHelper(drawable));
        }
        adapter = new LessonAdapter(questionLocations, this, this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerViewShape.setLayoutManager(gridLayoutManager);
        recyclerViewShape.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewClicked(int clickedViewId, int clickedItemPosition) {
        try {
            mediaElements.get(clickedItemPosition).start();
            colorName.setText(elementsNames[clickedItemPosition]);
        } catch (Exception e) {
            Toast.makeText(this, "Wrong index", Toast.LENGTH_SHORT).show();
            colorName.setText("Elements");
        }
    }

    @Override
    public void onViewLongClicked(int clickedViewId, int clickedItemPosition) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (MediaPlayer mediaPlayer: mediaElements) mediaPlayer.release();
    }
}
