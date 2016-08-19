package database;

/**
 * Created by Mars on 15.07.2016.
 */
public class CrimeDBSchema {
    public static final class CrimeTable{
        public static final String NAME = "Crimes";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
        }
    }
}
