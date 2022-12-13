package com.anirayoubil.kidsLearning.helpers;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLiteDbHelper extends SQLiteOpenHelper {
    private static final String[] lessonsNames = {"shapes", "daysOfWeek", "numbers", "alphabets"};
    private static final String[] shapes = new String[] {"circle", "square", "triangle", "star", "rectangle", "oval", "diamond", "hexagon"};
    private static final String[] daysOfWeek = new String[] {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
    private static final String[] numbers = new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty"};
    private static final String[] alphabets = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private static final HashMap<String, String[]> lessons = new HashMap<>();

    private static final String DB_NAME = "KidsLearning.db";
    private static final String DB_PATH = "data/data/com.anirayoubil.kidsLearning/databases/";
    private static final int DB_VERSION = 1;

    private Context context;
    private SQLiteDatabase db;

    public SQLiteDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        if (db != null && db.isOpen()) close();

        try {
            createDataBase();
            openDatabase();
        } catch (IOException e) {
            // System.out.println("Exception in creation of database : "+
            // e.getMessage());
            e.printStackTrace();
        }


        lessons.put("shapes", shapes);
        lessons.put("daysOfWeek", daysOfWeek);
        lessons.put("numbers", numbers);
        lessons.put("alphabets", alphabets);
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDatabase();
        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (checkDB != null) {
            checkDB.close();
            System.out.println("My db is:- " + checkDB.isOpen());
        }

        return checkDB != null;
    }

    private void copyDatabase() throws IOException {
        InputStream input = context.getAssets().open(DB_NAME);
        String outputFileName = DB_PATH + DB_NAME;
        OutputStream output = new FileOutputStream(outputFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }

        // Close the streams
        output.flush();
        output.close();
        input.close();
        System.out.println(DB_NAME + "Database Copied !");
    }

    private void openDatabase() {
        String path = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public boolean isOpen() {
    if (db != null)
        return db.isOpen();
    return false;
}

    @Override
    public synchronized void close() {
        if (db != null)
            db.close();
        super.close();
    }

    @SuppressLint("Range")
    public  String[] getElementsByLessonName(String lessonName) {
        if (!lessons.containsKey(lessonName))
            return null;
        db = getReadableDatabase();
        List<String> data = new ArrayList<>();
        try (
            Cursor cursor = db.rawQuery(selectTableQuery(lessonName), null)
        ) {
            cursor.moveToFirst();
            do {
                System.out.println(cursor);
                data.add(cursor.getString(cursor.getColumnIndex("name")));
            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return data.toArray(new String[data.size()]);
    }
    public String getLessonsNamesByAge(int age){
        return "SELECT * FROM lessons_names where min_age <= "+age+" and max_age > "+age ;
    }

    @SuppressLint("Range")
    public String[] readDataLessonsByage(int age) {
        db = getReadableDatabase();
        List<String> data = new ArrayList<>();
        try (
                Cursor cursor = db.rawQuery(getLessonsNamesByAge(age), null)
        ) {
            cursor.moveToFirst();
            int i=0;
            do {
                System.out.println(cursor);
                data.add(cursor.getString(cursor.getColumnIndex("name")));
            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return data.toArray(new String[data.size()]);
    }


    private String createTableQuery(String tableName) {
        return "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL);";
    }

    private String insertTableQuery(String element, String tableName) {
        return "INSERT INTO " + tableName + "(name) VALUES (\"" + element + "\")";
    }

    private String selectTableQuery(String tableName) {
        return "SELECT name FROM " + tableName;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        for (String lessonName: lessonsNames) {
//            try {
//                sqLiteDatabase.execSQL(createTableQuery(lessonName));
//                for (String element: Objects.requireNonNull(lessons.get(lessonName))) {
//                    sqLiteDatabase.execSQL(insertTableQuery(element, lessonName));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        for (String lessonName: lessonsNames) {
//            try {
//                db.execSQL("DROP TABLE IF EXISTS " + lessonName);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        onCreate(db);
    }


}
