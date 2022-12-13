package com.anirayoubil.kidsLearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;

import com.anirayoubil.kidsLearning.adapter.MainAdapter;
import com.anirayoubil.kidsLearning.helpers.LessonHelper;
import com.anirayoubil.kidsLearning.helpers.SQLiteDbHelper;

public class MainActivity extends AppCompatActivity implements RecyclerViewAction  {
    RecyclerView recyclerViewShape;
    RecyclerView.Adapter adapter;
    String[] elementsNames ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        recyclerViewShape = findViewById(R.id.recycler_main);
//        elementsNames = new String[] {"shapes", "daysOfWeek", "numbers", "alphabets"};
        elementsNames = new SQLiteDbHelper(MainActivity.this).readDataLessonsByAge(4);

        featuredShapes(elementsNames);
    }




    private void featuredShapes(String[] elementsNames) {
        ArrayList<LessonHelper> questionLocations = new ArrayList<>();
        int drawable;
        for (String element: elementsNames) {
            questionLocations.add(new LessonHelper(element));
        }
        adapter = new MainAdapter(questionLocations, this, this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerViewShape.setLayoutManager(gridLayoutManager);
        recyclerViewShape.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewClicked(int clickedViewId, int clickedItemPosition) {
        Intent intent = new Intent(MainActivity.this, GenericActivity.class);
        intent.putExtra("lessonName", elementsNames[clickedItemPosition]);
        startActivity(intent);
    }

    @Override
    public void onViewLongClicked(int clickedViewId, int clickedItemPosition) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}