package me.rikinmarfatia.mintask.util;

/**
 * Schema for local SQLite db.
 *
 * @author Rikin (rikinm10@gmail.com)
 */
public class MinTaskDBSchema {
    public static final class TaskTable {
        public static final String NAME = "tasks";

        public static final class Columns {
            public static final String ID = "id";
            public static final String TITLE = "title";
            public static final String COMPLETE = "complete";
            public static final String COLOR = "color";
        }
    }
}
