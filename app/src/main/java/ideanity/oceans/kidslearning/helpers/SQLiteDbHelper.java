package ideanity.oceans.kidslearning.helpers;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
    private static final String DB_PATH = "data/data/ideanity.oceans.kidslearning/databases/";
    private static final int DB_VERSION = 1;

    private Context context;
    private SQLiteDatabase db;

    public SQLiteDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        if (db != null && db.isOpen()) close();

        openDatabase();

        lessons.put("shapes", shapes);
        lessons.put("daysOfWeek", daysOfWeek);
        lessons.put("numbers", numbers);
        lessons.put("alphabets", alphabets);
    }

    public void openDatabase() {
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

    public  String[] getElementsByLessonName(String lessonName) {
        if (!lessons.containsKey(lessonName))
            return null;
        return readData(lessonName);
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

    public String[] readData(String lessonName) {
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
}
