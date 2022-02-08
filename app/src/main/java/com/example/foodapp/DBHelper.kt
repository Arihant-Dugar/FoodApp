package com.example.foodapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.foodapp.Models.OrdersModel

class DBHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "FoodApp.db"
        val TABLE_NAME = "orders"
        val ID_COL = "id"
        val NAME_COL = "name"
        val PHONE_COL = "phone"
        val PRICE_COL = "price"
        val IMAGE_COL = "image"
        val QUANTITY_COL = "quantity"
        val DESCRIPTION_COL = "description"
        val FOODNAME_COL = "foodname"
    }

    override fun onCreate(p0: SQLiteDatabase?) {

        val query = "CREATE TABLE " + TABLE_NAME + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME_COL + " TEXT," +
                PHONE_COL + " TEXT," +
                PRICE_COL + " INT," +
                IMAGE_COL + " INT," +
                QUANTITY_COL + " INT," +
                DESCRIPTION_COL + " TEXT," +
                FOODNAME_COL + " TEXT" + ")"

        Log.d("arihant", "query is : $query")

        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p0 != null) {
            p0.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(p0)
        }
    }
    fun insertorder(name: String, phone: String, quantity: Int, price : String, image : Int, description : String, foodname: String) : Boolean{
        val database = this.writableDatabase
        val values = ContentValues()
        values.put(NAME_COL,name)
        values.put(PHONE_COL,phone)
        values.put(PRICE_COL,price)
        values.put(IMAGE_COL,image)
        values.put(QUANTITY_COL,quantity)
        values.put(DESCRIPTION_COL,description)
        values.put(FOODNAME_COL,foodname)
        val id: Long = database.insert(TABLE_NAME,null,values)
        return id > 0
    }
    @SuppressLint("Range")
    fun getorders() : ArrayList<OrdersModel>{
        val list = ArrayList<OrdersModel>()
        val database = this.readableDatabase
        val cursor = database.rawQuery("select foodname, image, price, description, id from $TABLE_NAME", null)

        var id : Int
        var foodname : String
        var image : Int
        var price : String
        var description : String

        if (cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(ID_COL))
                foodname = cursor.getString(cursor.getColumnIndex(FOODNAME_COL))
                image = cursor.getInt(cursor.getColumnIndex(IMAGE_COL))
                price = cursor.getString(cursor.getColumnIndex(PRICE_COL))
                description = cursor.getString(cursor.getColumnIndex(DESCRIPTION_COL))

                val model = OrdersModel(image,foodname,price,id,description)
                list.add(model)
            }while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return list
    }
//    fun getorderbyid(id: Int): Cursor{
//
//        val database = this.readableDatabase
//        val cursor = database.rawQuery("select * from $TABLE_NAME where id = $ID_COL", null)
//
//        cursor?.moveToNext()
//
//        return cursor
//    }

    fun updateorder(name: String, phone: String, quantity: Int, price : String, image : Int, description : String, foodname: String, id: Int): Long {
        val database = this.writableDatabase
        val values = ContentValues()
        values.put(NAME_COL, name)
        values.put(PHONE_COL, phone)
        values.put(PRICE_COL, price)
        values.put(IMAGE_COL, image)
        values.put(QUANTITY_COL, quantity)
        values.put(DESCRIPTION_COL, description)
        values.put(FOODNAME_COL, foodname)
        val update_row = database.update(TABLE_NAME, values,"$ID_COL=?" , arrayOf(id.toString())).toLong()
        database.close()
        return update_row
    }

    fun deleteorder(id: Int): Long {
        val database = this.writableDatabase
        val delete_row = database.delete(TABLE_NAME, "$ID_COL=?",arrayOf(id.toString())).toLong()
        database.close()
        return delete_row
    }
}
