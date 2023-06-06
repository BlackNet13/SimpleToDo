package sg.rp.edu.rp.c346.id22038845.simpletodo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddDel;
    EditText etInpt;
    Button btnAdd;
    Button btnDel;
    Button btnClear;
    ListView lv;

    ArrayList<String> toDolist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddDel =findViewById(R.id.spinner);
        btnAdd =findViewById(R.id.btnAdd);
        btnDel =findViewById(R.id.btnDelete);
        btnClear=findViewById(R.id.btnClear);
        etInpt = findViewById(R.id.editTextInpt);
        lv=findViewById(R.id.lvShown);

        toDolist = new ArrayList<String>();
        AlertDialog.Builder popup = new AlertDialog.Builder(this);


        ArrayAdapter listUtil = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,toDolist);
        lv.setAdapter(listUtil);

        spnAddDel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etInpt.setHint("Enter to do list item to add");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        etInpt.setHint("Enter the index position to delete");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = etInpt.getText().toString();
                if(TextUtils.isEmpty(val)){
                    Toast.makeText(MainActivity.this, "Please enter input needed before proceeding", Toast.LENGTH_SHORT).show();
                }else if(val!=(String)val){
                    Toast.makeText(MainActivity.this, "Please enter only string values", Toast.LENGTH_SHORT).show();
                }else{
                    toDolist.add(val);
                    listUtil.notifyDataSetChanged();
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = Integer.parseInt(etInpt.getText().toString());
                if(toDolist.isEmpty()){
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
            }else{
                if (TextUtils.isEmpty(etInpt.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter input needed before proceeding", Toast.LENGTH_SHORT).show();
                } else if (val != (int) val) {
                    Toast.makeText(MainActivity.this, "Please enter only integer values", Toast.LENGTH_SHORT).show();
                } else if(val>=toDolist.size()){
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }else{
                    toDolist.remove(val);
                    listUtil.notifyDataSetChanged();
                }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDolist.clear();
                listUtil.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                etInpt.setText("");
                String text = toDolist.get(position);
                popup.setTitle("Edit text:");
                final EditText input = new EditText(MainActivity.this);
                input.setText(text);
                popup.setView(input);
                popup.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toDolist.set(position,input.getText().toString());
                    }
                });
                        popup.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                popup.show();




                //Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });





    }
}