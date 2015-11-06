package com.intelliwaves.vaultmonitor;

//import java.text.ParseException;
//import java.text.ParsePosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("vaultmsg")
public class SupervsiorNotification {
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{timeStamp}")
    public String getIt(@PathParam("timeStamp") String timestamp) {
    	int id = supervisorOnDuty(timestamp);
    	Supervisor supervisor = new Supervisor(id);
    	String msg = supervisor.showmsg();
        return msg;
    }
    
    private int supervisorOnDuty(String timestamp) {
    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    	fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
    	//Date date = fmt.parse("2015/11/05T10:59:48Z", new ParsePosition(0));
    	Date date = fmt.parse(timestamp, new ParsePosition(0));
    	//Date date = new Date();	 
    	//fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
    	cal.setTime(date);
    	int hrod = cal.get(Calendar.HOUR_OF_DAY);
    	int mins = cal.get(Calendar.MINUTE);
    	int cm = hrod * 60 + mins;
    	
    	//the below logic will store on the server, for now it is hardcoded
    	int onduty = 1;
    	if (cm >= 0 && cm < 360) {
    		onduty = 1;
    	} else if (cm >= 360 && cm < 720) {
    		onduty = 2;
    	} else if (cm >= 720 && cm < 1079) {
    		onduty = 3;
    	} else if (cm >= 1080 && cm < 1440) {
    		onduty = 4;
    	}
    	return onduty;
	 	
    }
    
}
