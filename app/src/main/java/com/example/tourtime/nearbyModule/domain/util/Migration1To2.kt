package com.example.tourtime.nearbyModule.domain.util

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE IF EXISTS NearByPlace");
        database.execSQL("ALTER TABLE NearByPlace CHANGE business_status business_status TEXT NULL;")
    }
}