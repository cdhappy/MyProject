package com.example.myprojectapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchInfo extends AppCompatActivity {
    private Connection m_con= null;
    private Context context;
    private Spinner mCollegeSpinner = null;
    private Spinner mMajorSpinner = null;
    private Spinner mYearSpinner = null;
    private Spinner mProvinceSpinner  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_info);

        context = this;
        mProvinceSpinner = (Spinner)findViewById(R.id.province);
        mCollegeSpinner = (Spinner)findViewById(R.id.college);
        mMajorSpinner = (Spinner)findViewById(R.id.major);
        mYearSpinner = (Spinner)findViewById(R.id.year);

        String[] arr0 = {""};
        final ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arr0);
        String[] arr1 = {"Sichuan University","Chengdu University of Electronics","Southwest University of Finance and Economics","Southwest Jiaotong University","Sichuan Agricultural University"};
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arr1);
        String[] arr2 = {"CQU","Sichuan Fine Arts Institute","XiNan University"};
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arr2);

        mProvinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0)
                    mCollegeSpinner.setAdapter(adapter0);
                if (position == 27)
                    mCollegeSpinner.setAdapter(adapter1);
                if (position == 2)
                    mCollegeSpinner.setAdapter(adapter2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        mCollegeSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                //String selectText = mSpinner_type.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
        mMajorSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {

            }
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
        mYearSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {

            }
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });


        Button btn_Search_Information = (Button)findViewById(R.id.search_info);

        btn_Search_Information.setOnClickListener(new View.OnClickListener() {
            String searched_point = "";

            @Override
            public void onClick(View v){

                final Spinner txt0 = (Spinner)findViewById(R.id.province);
                final Spinner txt1 = (Spinner)findViewById(R.id.college);
                final Spinner txt2 = (Spinner)findViewById(R.id.major);
                final Spinner txt3 = (Spinner)findViewById(R.id.year);
                final TextView txt5 = (TextView)findViewById(R.id.search_info_txt5);

                if(mProvinceSpinner.getSelectedItem().toString().equals(""))
                {
                    Toast.makeText(SearchInfo.this, "Please choose province! ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(mCollegeSpinner.getSelectedItem().toString().equals(""))
                    {
                        Toast.makeText(SearchInfo.this, "Please choose University name! ",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(mMajorSpinner.getSelectedItem().toString().equals(""))
                        {
                            Toast.makeText(SearchInfo.this, "Please choose the major!",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(mYearSpinner.getSelectedItem().toString().equals(""))
                            {
                                Toast.makeText(SearchInfo.this, "Please choose the year!",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                new Thread()
                                {
                                    public void run()
                                    {
                                        try
                                        {
                                            searched_point = Point(mProvinceSpinner.getSelectedItem().toString(),mCollegeSpinner.getSelectedItem().toString(),mMajorSpinner.getSelectedItem().toString(),mYearSpinner.getSelectedItem().toString());
                                            if(searched_point.equals("Not found"))
                                            {
                                                txt5.setText("Not found");
                                            }
                                            else
                                            {
                                                txt5.setText("The point is "+Point(mProvinceSpinner.getSelectedItem().toString(),mCollegeSpinner.getSelectedItem().toString(),mMajorSpinner.getSelectedItem().toString(),mYearSpinner.getSelectedItem().toString()));
                                            }
                                        }
                                        catch(Exception e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            }
                        }
                    }
                }
            }
        });
    }


    public String Point(String a, String b,String c,String d) throws Exception
    {
        String point = "Not found";
        String sql = "SELECT point FROM Point WHERE  province=? and college=? and major=? and year=?" ;
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        Log.d("加载驱动", "成功");
        //10.253.219.33192.168.145.1
        m_con = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.145.1:1433/MyProjectApp" ,"sa", "998528");

        if(m_con!=null)
        {
            Log.d("sqlserver", "数据库连接成功");
        }
        try
        {
            PreparedStatement pstmt = m_con.prepareStatement(sql) ;  ;
            if(pstmt == null)
                return point;
            else{
                pstmt.setString(1,a);
                pstmt.setString(2,b);
                pstmt.setString(3,c);
                pstmt.setString(4,d);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next())
                {
                    point = rs.getString("point");
                }
                Log.d("加载驱动", "更新成功");
                pstmt.close() ;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            m_con.close() ;
        }
        return point;
    }
}
