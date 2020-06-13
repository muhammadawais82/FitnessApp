package logic2magic.com.health_fitness;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import logic2magic.com.health_fitness.MODEL.Employee;
import logic2magic.com.health_fitness.db.SQ_EMP;
import logic2magic.com.health_fitness.rv.SUPER_STATUS_ADOPTER;

public class ADMIN extends AppCompatActivity {


    RecyclerView ADMIN_RV;
    ArrayList<Employee> list;
    SQ_EMP sq_emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ADMIN_RV = findViewById(R.id.ADMIN_RV);
        ADMIN_RV.setLayoutManager(new LinearLayoutManager(this));
        sq_emp = new SQ_EMP(this);
        list = new ArrayList<>();
        SUPER_STATUS_ADOPTER super_status_adopter = new SUPER_STATUS_ADOPTER(list,this);

        Cursor res = sq_emp.GET_USER_DETAIL();

        if (res.getCount()==0)
        {
            Toast.makeText(this, "NO DATA FOUND", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (res.moveToNext())
            {
                String str_id = res.getString(res.getColumnIndex(SQ_EMP.U_ID));
                String str_name = res.getString(res.getColumnIndex(SQ_EMP.U_user_name));
                String str_address = res.getString(res.getColumnIndex(SQ_EMP.U_address));
                String str_cnic = res.getString(res.getColumnIndex(SQ_EMP.U_CNIC));
                String str_degree = res.getString(res.getColumnIndex(SQ_EMP.U_DEGREE));
                String str_subject = res.getString(res.getColumnIndex(SQ_EMP.U_subject));
                String str_number = res.getString(res.getColumnIndex(SQ_EMP.U_number));
                String str_email = res.getString(res.getColumnIndex(SQ_EMP.U_email));
                String str_status = res.getString(res.getColumnIndex(SQ_EMP.U_JOB_ACTIVATION));
                String str_password = res.getString(res.getColumnIndex(SQ_EMP.U_password));
                //String str_lat = res.getString(res.getColumnIndex(SQ_EMP.U_LAT));
                //String str_lang = res.getString(res.getColumnIndex(SQ_EMP.U_LONG));
               // String str_type = res.getString(res.getColumnIndex(SQ_EMP.U_TYPE));

                Employee emp = new Employee(str_id,str_name,str_subject,str_degree,str_cnic,str_number,str_status,str_address,str_email,str_password);

                list.add(emp);


            }
            ADMIN_RV.setAdapter(super_status_adopter);
        }



    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//
////            }else if (id == R.id.nav_attendence)
////            {
////                startActivity(new Intent(ADMIN.this,Attendence_DEATILS.class));
////
////            }
//
//        return super.onOptionsItemSelected(item);
//    }

}
