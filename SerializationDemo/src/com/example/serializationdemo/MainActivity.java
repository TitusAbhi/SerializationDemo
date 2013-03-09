
package com.example.serializationdemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

   Model model;
   
   EditText name,number,gender;
   
   Button save,retrieve;
   
   TextView retrievedText;
   
   DemoHelper helper;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        model=new Model();
        
        helper=new DemoHelper(this);
        
        name=(EditText)findViewById(R.id.name);
        number=(EditText)findViewById(R.id.number);
        gender=(EditText)findViewById(R.id.gender);
        
        save=(Button)findViewById(R.id.save);
        retrieve=(Button)findViewById(R.id.retrieve);
        
        retrievedText=(TextView)findViewById(R.id.retrievedtext);
        
        save.setOnClickListener(this);
        retrieve.setOnClickListener(this);
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public void onClick(View v) 
	{
		if(v.equals(save))
		{
			
			 model.setName(name.getText().toString());
		     model.setNumber(number.getText().toString());
		     model.setGender(gender.getText().toString());
		     
		     //Serailization will be done here
		     Serialization(model);
		}
		if(v.equals(retrieve))
		{
			Cursor c=helper.getAll();
			c.moveToPosition(c.getCount()-1);
			
			byte[] buf=c.getBlob(c.getColumnIndex("model"));
			
			//DeSerailization will be done here
			Model retrievedModel=DeSerialization(buf);
			
			Log.e("object",retrievedModel.getName());
			
			retrievedText.setText(retrievedModel.getName()+"--"+retrievedModel.getNumber()+"---"+retrievedModel.getGender());
		}
	}   
	
	private void Serialization(Model model)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		 
	    try 
	    { 
	      ObjectOutput out = new ObjectOutputStream(bos); 
	      out.writeObject(model); 
	      out.close(); 
	 
	      byte[] buf = bos.toByteArray(); 
	      
	      DeSerialization(buf);
	      
	      helper.insert(buf);
	     
	    } catch(IOException ioe) 
	    { 
		      Log.e("serializeObject", "error", ioe);
	 
	    } 
	}
	
	private Model DeSerialization(byte[] b)
	{
		 try 
		 { 
		      ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(b)); 
		      Model object = (Model)in.readObject(); 
		      in.close(); 
		      Log.e("object",object.getName());
		      return object;
		 
		   } 
		   catch(ClassNotFoundException cnfe) 
		    { 
		      Log.e("deserializeObject", "class not found error", cnfe); 
		      return null;
		 
		    } 
		    catch(IOException ioe) 
		    { 
		      Log.e("deserializeObject", "io error", ioe); 
		       return null;
		    }
		
	}
}
