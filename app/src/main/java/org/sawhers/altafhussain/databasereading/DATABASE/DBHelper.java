package org.sawhers.altafhussain.databasereading.DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.sawhers.altafhussain.databasereading.MODEL.Student;

import java.util.ArrayList;

/**
 * Created by ALTAFHUSSAIN on 12/29/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mydb.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_STUDENT = "tblstudent";
    //here the feild of database
    //when we final variable we need to write it in capital
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_COURSE = "course";
    private static final String KEY_TOTAL_FEE = "totalFee";
    private static final String KEY_FEE_PAID = "feepaid";

    //here we create DB and table with SQL Query and we assign this Query into a String
    //for the table creation in SQL QUERY we do this like
    /*
     CREATE TABLE tablename ( id Integer primary key Autoincrement,
                             name TEXT,
                             course TEXT,
                             totalfee Integer,
                             feepaid Integer
                             );

     */
    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + " ( "

            + KEY_ID + " Integer Primary Key Autoincrement, "//apace integer ->the space is must
            + KEY_NAME + " TEXT NOT NULL,"
            + KEY_COURSE + " TEXT NOT NULL,"
            + KEY_TOTAL_FEE + " Integer,"
            + KEY_FEE_PAID + " Integer"
            + ");";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);//this is factory method this is automatically execute don't need to call and execut

        //if you have two or more table you need create the Qeury and then here you need to
        // write sqLiteDatabase.execSQL(yourTableName);
        //and also onUpgrade method
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //table change or if you change the stracture of table then you need to write code here

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(sqLiteDatabase);//recreate the database if you have change some thing in table etc

    }

    /*
    For insertion in this database we need to make a method here
    here we need to pass name ,course , total fee,fee paid*/

    public long saveStudent(Student student) {
        long result = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, student.getName());
        values.put(KEY_COURSE, student.getCourse());
        values.put(KEY_TOTAL_FEE, student.getTotalFee());
        values.put(KEY_FEE_PAID, student.getFeePaid());

        result = db.insert(TABLE_STUDENT, null, values);

        return result;
    }
//this method is used to Read record from database tables
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> studentsList=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        //db.rawQuery(String sql,String[]  selectionArgs )
        //the above statement is used to Query from database ,the first argument "String sql" is used for query
        //and the other "String[] selectionArgs" used for where condition.and this return Cursar
        //cursar mean that the carsar is used to read the database record this is record give us in the form of cursar
        Cursor c =db.rawQuery("select * from "+TABLE_STUDENT,null);
    if (c.moveToFirst()){//c.moveToFirst() mean move the cursar into the first record .they are boolean true or false
        do {

            //--->int id=c.getInt(0);this mean go to index zero in the table but this cause problem if we add
            //other field in the table due to new record the index change what we want first.
            int id=c.getInt(c.getColumnIndex(KEY_ID));//this search the name of field and then get the index
            String name=c.getString(c.getColumnIndex(KEY_NAME));
            String course=c.getString(c.getColumnIndex(KEY_COURSE));
            int totalFee=c.getInt(c.getColumnIndex(KEY_TOTAL_FEE));
            int feePaid=c.getInt(c.getColumnIndex(KEY_FEE_PAID));

           //this is last you need to read in last
            //here we need to call the constrator which with out id in student class. b/c id is autoincremented
            Student s = new Student(id,name,course,totalFee,feePaid);
            //now we need to add this s into arraylist b/c we return from this method arraylist
            studentsList.add(s);


        }while (c.moveToNext());//c.moveToNext() mean when the cursar find next record move this cursar and store in carsar
        //when the c did not find any record this give us null and the loop finish


    }
        return studentsList;
    }
}




