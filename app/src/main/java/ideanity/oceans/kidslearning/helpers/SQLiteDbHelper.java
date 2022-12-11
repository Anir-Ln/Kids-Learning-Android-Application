package ideanity.oceans.kidslearning.helpers;

public class SQLiteDbHelper {

    private static final SQLiteDbHelper instance = new SQLiteDbHelper();
    private SQLiteDbHelper() {}

    public static SQLiteDbHelper getInstance() {
        return SQLiteDbHelper.instance;
    }

    public  String[] getElementsByClass(String lessonName) {
        return null;
    }
}
