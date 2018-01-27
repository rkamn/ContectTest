package com.example.rakesh.contecttest;

import android.database.Cursor;
import android.opengl.ETC1;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText etname,etemail,etmarks,etid;
    Button btnAddNew,btnshow,btnupdate,btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb=new DatabaseHelper(this);
        etid=(EditText)findViewById(R.id.etid);
        etname=(EditText)findViewById(R.id.etname);
        etemail=(EditText) findViewById(R.id.etemail);
        etmarks=(EditText)findViewById(R.id.etmarks);
        btnAddNew=(Button)findViewById(R.id.btnaddnew);
        btnshow=(Button)findViewById(R.id.btnshow);
        btnupdate=(Button)findViewById(R.id.btnupdate);
        btndelete=(Button)findViewById(R.id.btndelete);
        AddData();
        ShowAllData();
        updateData();
        DeleteData();
    }
    // insert data
    public void AddData(){
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            boolean isInserted=myDb.insertData(etname.getText().toString(),etemail.getText().toString(),etmarks.getText().toString());
            if (isInserted==true)
                Toast.makeText(MainActivity.this,"Data inserted sucessfully",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
            }
        });
    }
      //show all data
    public void ShowAllData(){
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Cursor res=myDb.getAllData();
              if (res.getCount()==0){
            // show massage
                  showmassage("Error","No Data Found");
                  return;
              }
              StringBuffer buffer=new StringBuffer();
              while (res.moveToNext()){
                  buffer.append("id :"+res.getString(0)+"\n");
                  buffer.append("name :"+res.getString(1)+"\n");
                  buffer.append("email :"+res.getString(2)+"\n");
                  buffer.append("marks :"+res.getString(3)+"\n\n");
              }

                showmassage("Data",buffer.toString());
            }
        });
    }
    public  void showmassage(String title,String Massage){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Massage);
        builder.show();

    }
    //update data
    public  void updateData(){
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isupdate=myDb.updateData(etid.getText().toString(),etname.getText().toString(),
                        etemail.getText().toString(),etmarks.getText().toString());
                if (isupdate==true)
                    Toast.makeText(MainActivity.this,"Data updated sucessfully",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //delete data
    public  void DeleteData(){
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRow = myDb.deleteData(etid.getText().toString());
                if (deletedRow > 0)
                    Toast.makeText(MainActivity.this,"Data Deleted sucessfully",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }



}
