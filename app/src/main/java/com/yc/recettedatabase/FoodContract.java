package com.yc.recettedatabase;

import android.provider.BaseColumns;

public class FoodContract {
    private FoodContract(){}

    public static class FoodEntry implements BaseColumns {
        public static final String TABLE_NAME = "Ingredients";
        public static final String COLUMN_NAME_ID= "id";
        public static final String COLUMN_NAME_NOM= "nom";
    }
}
