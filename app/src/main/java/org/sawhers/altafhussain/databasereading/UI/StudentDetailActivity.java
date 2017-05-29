package org.sawhers.altafhussain.databasereading.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.sawhers.altafhussain.databasereading.MODEL.Student;
import org.sawhers.altafhussain.databasereading.R;

public class StudentDetailActivity extends AppCompatActivity {
    EditText et_NameD,et_TotalFeeD,et_FeePaidD,et_CourseD;
    Button btn_Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        et_NameD= (EditText) findViewById(R.id.etNameD);
        et_CourseD= (EditText) findViewById(R.id.etCourseD);
        et_TotalFeeD= (EditText) findViewById(R.id.etTotalFeeD);
        et_FeePaidD= (EditText) findViewById(R.id.etFeePaidD);
        btn_Update= (Button) findViewById(R.id.btnUpdate);


        Bundle bundle=getIntent().getExtras();
        Student s = (Student) bundle.getSerializable("STUDENT");
        et_NameD.setText(s.getName());
       et_CourseD.setText(s.getCourse());
       et_TotalFeeD.setText(String.valueOf(s.getTotalFee()));
       et_FeePaidD.setText(String.valueOf(s.getFeePaid()));

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudentDetailActivity.this, "next class", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
