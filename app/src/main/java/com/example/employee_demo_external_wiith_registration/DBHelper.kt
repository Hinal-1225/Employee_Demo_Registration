package com.example.employee_demo_external_wiith_registration

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(var context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION)
{
    companion object {
        private var DB_NAME="PracticeDB"
        private var TB_USER="UserDetail"
        private var TB_EMPLOYEE="EmployeeDetail"
        private var DB_VERSION=1

        private val USER_ID = "ur_id"
        private val USER_EMAIL = "ur_email"
        private val USER_CITY = "ur_city"
        private val USER_PASS = "ur_password"


        private var EM_ID="em_id"
        private var EM_POST="em_post"
        private var EM_DEPT="em_dept"
        private var EM_SALARY="em_salary"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        var sql1 = "CREATE TABLE $TB_USER ($USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, $USER_EMAIL TEXT,  $USER_CITY TEXT, $USER_PASS TEXT)"
        var sql2="CREATE TABLE $TB_EMPLOYEE($EM_ID INTEGER PRIMARY KEY AUTOINCREMENT, $EM_POST TEXT, $EM_DEPT TEXT, $EM_SALARY INTEGER)";
        p0?.execSQL(sql1);
        p0?.execSQL(sql2);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertUser(user : User): Long
    {
        var db = writableDatabase
        var cn = ContentValues()
        cn.put(USER_EMAIL,user.ur_email)
        cn.put(USER_CITY,user.ur_city)
        cn.put(USER_PASS,user.ur_password)

        var res = db.insert(TB_USER,null,cn)
        return res
        db.close()
    }

    fun getUser(uname:String): ArrayList<User>
    {
        var db = readableDatabase
        var sql = "Select * from $TB_USER where $USER_EMAIL = '$uname'"
        var arr = ArrayList<User>()
        var cursor = db.rawQuery(sql,null)
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(0)
            var email = cursor.getString(1)
            var city = cursor.getString(2)
            var password = cursor.getString(3)
            var user = User(id,email,city,password)

            arr.add(user)
        }
        return arr
        db.close()
    }

    fun insert(Employee:Employee):Boolean
    {
        var db=writableDatabase
        var cv= ContentValues()
        cv.put(EM_POST,Employee.em_post)
        cv.put(EM_DEPT,Employee.em_dept)
        cv.put(EM_SALARY,Employee.em_salary)

        var flag=db.insert(TB_EMPLOYEE,null,cv)
        if (flag>0)
        {
            return true
        }
        else {
            return false
        }
    }

    fun retriveAll():ArrayList<Employee>
    {
        var db = readableDatabase
        var cursor = db.query(TB_EMPLOYEE,null,null,null,null,null,null)
        var arr:ArrayList<Employee> = ArrayList()
        while (cursor.moveToNext())
        {
            var id = cursor.getInt(0)
            var post = cursor.getString(1)
            var dept = cursor.getString(2)
            var salary = cursor.getInt(3)
            var employee = Employee(id,post,dept,salary)
            arr.add(employee)
        }
        return arr
    }

    fun delete(id:Int)
    {
        var db=writableDatabase
        db.delete(TB_EMPLOYEE,"$EM_ID=$id",null)
        db.close()
    }

    fun update(Employee:Employee)
    {
        var db=writableDatabase
        var cv= ContentValues()
        cv.put(EM_POST,Employee.em_post)
        cv.put(EM_DEPT,Employee.em_dept)
        cv.put(EM_SALARY,Employee.em_salary)
        db.update(TB_EMPLOYEE,cv,"$EM_ID=${Employee.em_id}", null)
        db.close()
    }
}