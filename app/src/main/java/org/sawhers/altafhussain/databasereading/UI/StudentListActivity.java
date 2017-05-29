package org.sawhers.altafhussain.databasereading.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.sawhers.altafhussain.databasereading.DATABASE.DBHelper;
import org.sawhers.altafhussain.databasereading.MODEL.Student;
import org.sawhers.altafhussain.databasereading.R;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
    ListView lvStudent;
    ArrayAdapter<Student> studentArrayAdapter;
    ArrayList<Student> studentArrayList;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        lvStudent=(ListView)findViewById(R.id.listView);
        dbHelper = new DBHelper(this);
        studentArrayList = new ArrayList<>();
        studentArrayList=dbHelper.getAllStudents();
        studentArrayAdapter=new ArrayAdapter<>(StudentListActivity.this,android.R.layout.simple_list_item_1,studentArrayList);
        lvStudent.setAdapter(studentArrayAdapter);//lvStudent is xml
        //
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student s=studentArrayList.get(position);//studentArrayList is arraylist
                Bundle bundle=new Bundle();
                bundle.putSerializable("STUDENT",s);
                Intent i=new Intent(StudentListActivity.this,StudentDetailActivity.class);
                i.putExtras(bundle);
                startActivity(i);

            }
        });

    }
}
