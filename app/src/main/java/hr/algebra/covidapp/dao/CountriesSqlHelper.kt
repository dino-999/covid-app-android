package hr.algebra.covidapp.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import hr.algebra.covidapp.model.Country
import hr.algebra.covidapp.model.Item


private const val DB_NAME = "covid.db"
private const val DB_VERSION = 1
private const val TABLE_NAME = "countries"
private val CREATE_TABLE = "create table $TABLE_NAME( " +
        "${Country::_id.name} integer primary key autoincrement, " +
        "${Country::iD.name} text not null, " +
        "${Country::country.name} text not null, " +
        "${Country::countryCode.name} text not null, " +
        "${Country::slug.name} text not null, " +
        "${Country::newConfirmed.name} integer not null, " +
        "${Country::totalConfirmed.name} integer not null, " +
        "${Country::newDeaths.name} integer not null, " +
        "${Country::totalDeaths.name} integer not null, " +
        "${Country::newRecovered.name} integer not null, " +
        "${Country::totalRecovered.name} integer not null, " +
        "${Country::date.name} text not null " +
        ")"
private const val DROP_TABLE = "drop table $TABLE_NAME"



private const val TABLE_NAME_TOTAL = "total"
private val CREATE_TABLE_TOTAL = "create table $TABLE_NAME_TOTAL( " +
        "${Item::_id.name} integer primary key autoincrement, " +
        "${Item::newConfirmed.name} integer not null, " +
        "${Item::totalConfirmed.name} integer not null, " +
        "${Item::newDeaths.name} integer not null, " +
        "${Item::totalDeaths.name} integer not null, " +
        "${Item::newRecovered.name} integer not null, " +
        "${Item::totalRecovered.name} integer not null, " +
        "${Item::date.name} text not null " +
        ")"
private const val DROP_TABLE_TOTAL = "drop table $TABLE_NAME_TOTAL"



class CountriesSqlHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),CovidRepository{

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
        db.execSQL(CREATE_TABLE_TOTAL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE)
        db.execSQL(DROP_TABLE_TOTAL)

        onCreate(db)
    }

    override fun delete(tableName: String,selection: String?, selectionArgs: Array<String>?): Int =
        writableDatabase.delete(tableName,selection,selectionArgs)


    override fun insert(tableName: String,values: ContentValues?): Long =writableDatabase.insert(tableName,null,values)

    override fun query(tableName: String,projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? = readableDatabase.query(tableName,projection,selection,selectionArgs,null,null,sortOrder)

    override fun update(tableName: String,values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int =
            writableDatabase.update(tableName,values,selection,selectionArgs)
}