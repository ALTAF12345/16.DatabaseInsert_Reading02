package org.sawhers.altafhussain.databasereading.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.sawhers.altafhussain.databasereading.DATABASE.DBHelper;
import org.sawhers.altafhussain.databasereading.MODEL.Student;
import org.sawhers.altafhussain.databasereading.R;

public class MainActivity extends AppCompatActivity {
    EditText et_NAME,et_TotalFee,et_FeePaid;
    AutoCompleteTextView autoCourse;
    Button btn_Save,btn_ShowAll;

    ArrayAdapter<String> myarrayAdapter;//for auto complete course


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_NAME=(EditText)findViewById(R.id.etName);
        et_TotalFee=(EditText)findViewById(R.id.etTotalFee);
        et_FeePaid=(EditText)findViewById(R.id.etFeePaid);
        btn_Save=(Button)findViewById(R.id.btnSave);
        btn_ShowAll=(Button)findViewById(R.id.btnShowAll);
        autoCourse = (AutoCompleteTextView) findViewById(R.id.etCourse);
        //**********************************StART OF AUTOCOMPLETE EDITTEXTVIEW********************************
        //first we declare array for autocomplete and then pass to adopter and then we pass this adopter to
        // autocomplete edittextview
        String Courses[]={"C++","ANDROID","JAVA","SWIFT","CCNA","MCSE","PHYTON","VITUALIZATION","CCNP"};
        myarrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,Courses);
        autoCourse.setAdapter(myarrayAdapter);
        autoCourse.setThreshold(1);//this is used when we put one word this will show the related word

        //**********************************END OF AUTOCOMPLETE EDITTEXTVIEW****************************

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*String name=et_NAME.getText().toString().trim();
                String course=autoCourse.getText().toString().trim();
                String totalfee=et_TotalFee.getText().toString().trim();
                String feepaid=et_FeePaid.getText().toString().trim();
                if(TextUtils.isEmpty(name))
                {
                    et_NAME.setError("please provide name");
                    return ;
                }
               Student student=new Student(name,course,Integer.parseInt(totalfee),Integer.parseInt(feepaid));
               */

                Student student = getStudentFromFileds();
                if (student!=null) {

                    DBHelper dbHelper = new DBHelper(MainActivity.this);
                    long result = dbHelper.saveStudent(student);
                    if (result != -1) {
                        Toast.makeText(MainActivity.this, "Record added with ID " + result, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Record not added", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        btn_ShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,StudentListActivity.class));

            }
        });

    }
   private Student getStudentFromFileds(){
       String name=et_NAME.getText().toString().trim();
       String course=autoCourse.getText().toString().trim();
       String totalfee=et_TotalFee.getText().toString().trim();
       String feepaid=et_FeePaid.getText().toString().trim();
       if(TextUtils.isEmpty(name))
       {
           et_NAME.setError("please provide name");
           return null;
       }
       if(TextUtils.isEmpty(course))
       {
           autoCourse.setError("please provide course");
           return null;
       }
       if(TextUtils.isEmpty(totalfee))
       {
           et_TotalFee.setError("please provide totalfee");
           return null;
       }
       if(TextUtils.isEmpty(feepaid))
       {
           et_FeePaid.setError("please provide feepaid");
           return null;
       }

       Student student = new Student(name, course, Integer.parseInt(totalfee), Integer.parseInt(feepaid));
       return student;
   }

    }
