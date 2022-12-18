package pl.edu.uwr.pum.lista3


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import pl.edu.uwr.pum.lista3.ListDetail
import kotlin.jvm.internal.Intrinsics.Kotlin

class DBHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PUMLists"
        private const val TABLE_NAME = "Lists"

        private const val COLUMN_ID = "_id"
        private const val COLUMN_POINTS = "points"
        private const val COLUMN_INFO = "info"
        private const val COLUMN_LISTORPIC = "lOrP"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_LIST_TABLE =
            "CREATE TABLE $TABLE_NAME(" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "$COLUMN_POINTS INTEGER," +
                    "$COLUMN_INFO TEXT," +
                    "$COLUMN_LISTORPIC INTEGER)"
        db?.execSQL(CREATE_LIST_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

//Dodawanie zadania
    fun addRec(points: Int, info: String, lOrP: Int) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_POINTS, points)
        contentValues.put(COLUMN_INFO, info)
        contentValues.put(COLUMN_LISTORPIC, lOrP)

        db.insert(TABLE_NAME, null, contentValues)
        db.close()
    }
//Pobranie zadan z danej listy
    fun getAllItems(n: Int): List<ListDetail> {
        val itemList: MutableList<ListDetail> = mutableListOf()

        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_LISTORPIC=$n"
        val db = this.readableDatabase

        try {
            val cursor: Cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val place = ListDetail(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                    )
                    itemList.add(place)
                } while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e: SQLiteException) {
            e.printStackTrace()
            return emptyList()
        }

        return itemList
    }

//Pobranie zadania do edycji
    fun getEdit(n: Int): List<String>{

        val itemList: MutableList<String> = mutableListOf()

        println(n)
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=$n"
        val db = this.readableDatabase

        try {
            val cursor: Cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                        val points = cursor.getInt(1)
                        val info = cursor.getString(2)
                        val pos = cursor.getString(3)
                        itemList.add(points.toString())
                        itemList.add(info)
                        itemList.add(pos)


                } while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e: SQLiteException) {
            e.printStackTrace()
            return emptyList()
        }
        return itemList
    }
//Edycja zadania

    fun updateInfo (id: Int,  points: Int, info: String){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_POINTS, points)
        contentValues.put(COLUMN_INFO, info)

        db.update(TABLE_NAME,
            contentValues,
            "$COLUMN_ID=$id",
            null)

        db.close()
    }
//Usuwanie wpisu
    fun deleteRec(id: Int){
        val db = this.writableDatabase

        db.delete(
            TABLE_NAME,
            "$COLUMN_ID=${id}",
            null)
        db.close()
    }
//Dodanie zjecia

    fun addToGallery(singleItem: ListDetail): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_INFO, singleItem.info)
        contentValues.put(COLUMN_LISTORPIC, singleItem.lOrP)

        val result = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return result
    }
//Pobranie listy zdjec
    fun getAllPhotos(n: Int): List<ListDetail> {
        val itemList: MutableList<ListDetail> = mutableListOf()

        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_LISTORPIC=$n"
        val db = this.readableDatabase

        try {
            val cursor: Cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val place = ListDetail(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                    )
                    itemList.add(place)
                } while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e: SQLiteException) {
            e.printStackTrace()
            return emptyList()
        }
        if(itemList.isEmpty()){
            var emptyL:List<ListDetail> = listOf(ListDetail(0,0,"none", 0))
            return emptyL
        }else {
            return itemList
        }
    }
}