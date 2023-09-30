package com.example.crud_usersandbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class UserList extends AppCompatActivity {

    ListView lvUsers;
    Button btnBack;
    ArrayList<String> arrUsers = new ArrayList<String>();
    DbConnection dbase = new DbConnection(this, "dbusers", null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        btnBack = findViewById(R.id.btnBack);
        lvUsers = findViewById(R.id.lvUsers);

        LoadUsers();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void LoadUsers() {
        arrUsers = myArrUsers();
        lvUsers.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, arrUsers));
    }

    private ArrayList<String> myArrUsers() {
        ArrayList<String> dataUsers = new ArrayList<String>();

        SQLiteDatabase db = dbase.getReadableDatabase();
        String query = "SELECT nameUser, emailUser, status FROM Users";
        Cursor cUsers = db.rawQuery(query,null);

        if (cUsers.moveToFirst()){
            do{
                String mrec = "User Name: "+cUsers.getString(0) + "\n"
                            + "Email: "+cUsers.getString(1);

                dataUsers.add(mrec);
            }while(cUsers.moveToNext());
        }
        db.close();
        return dataUsers;
    }
}