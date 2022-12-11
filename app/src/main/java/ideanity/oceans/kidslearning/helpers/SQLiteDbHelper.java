package ideanity.oceans.kidslearning.helpers;

public class SQLiteDbHelper {
    private static final String[] shapes = new String[] {"circle", "square", "triangle", "star", "rectangle", "oval", "diamond", "hexagon"};
    // days of the week in lowercase
    private static final String[] daysOfWeek = new String[] {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
    private static final String[] numbers = new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty"};
    // alphabet from a to z
    private static final String[] alphabets = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};


    private static final SQLiteDbHelper instance = new SQLiteDbHelper();
    private SQLiteDbHelper() {}

    public static SQLiteDbHelper getInstance() {
        return SQLiteDbHelper.instance;
    }

    public  String[] getElementsByLessonName(String lessonName) {
        switch (lessonName) {
            case "shapes":
                return shapes;
            case "week":
                return daysOfWeek;
            case "numbers":
                return numbers;
            case "alphabets":
                return alphabets;
        }
        return null;
    }


}
