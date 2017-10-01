package hw2;

public class ClockRadio 
		{
	
	private int hour, min, ahour, aminutes;
	
	public static final int SNOOZE_MINUTES = 9; 
	
	public static final int MINUTES_PER_DAY = 1440;
	
	private boolean alarm, time24, radio, on, snooze,am;
	
	private int SnoozeMinutes;
	
	//Constructs a clock radio with initial clock time at 00:00 and alarm time at 01:00
	
	public ClockRadio()
	
	{
		
		snooze=false; SnoozeMinutes=0;
		on=false; radio=false;
		ahour=01; aminutes=00;
		alarm=false;
		time24=true;
		am=true;
	}
	
	//Constructs a clock radio with the given initial clock time and with alarm time at 01:00
	
	public ClockRadio(int givenMinutesPastMidnight)
	
	{	
		snooze=false; SnoozeMinutes=0;
		on=false;
		hour=givenMinutesPastMidnight/60; min=givenMinutesPastMidnight%60;
		ahour=01; aminutes=00;
		alarm=false;
		time24=true;
		radio=false;
		am=true;
	}
	
	//Advances the clock time by the specified number of minutes
	
	public void advanceTime(int givenMinutes) 
	
	{
		int one=1;
		for(int add= 1; add<=givenMinutes; add++)	
		{
			
			hour=hour+((min+one)/60);
			min=(min+one)%60;
				
			if(getAlarmTimeRaw()==getClockTimeRaw())
				alarm=true;
			if(snooze==true && SnoozeMinutes==getClockTimeRaw())
				alarm=true;
		
		}
	}
	
	//Advances the clock time by the given hours and minutes
	
	public void advanceTime(int givenHours, int givenMinutes) 
	{
		int one=1;
		hour=hour+givenHours;
		for(int add= 1; add<=givenMinutes; add++)
		{
			hour=hour+((min+one)/60);
			min=(min+one)%60;
			if(getAlarmTimeRaw()==getClockTimeRaw())
					alarm=true;
			if(snooze==true && SnoozeMinutes==getClockTimeRaw())
				alarm=true;
		}
	}
	
	//Turns off the alarm, stops it from sounding (if it is sounding) and cancels snooze if it is in effect
	
	public void alarmDisabled() 
	{
		on=false;
		alarm=false;
		snooze=false;	
	}
	
	//Turns the alarm on
	
	public void alarmEnabled()
	{
		on=true;	
	}
	
	//Returns the current alarm time as a string in one of the following forms, depending on whether the clock is currently in 24-hour mode: "hh:mm", "hh:mm AM", or "hh:mm PM"
	
	public String getAlarmTimeAsString()
	{	
		String display=null;
		if(time24==true) {
		display= String.format("%02d:%02d", ahour, aminutes);
		}
		
		else if (time24==false)
		{
			if (am==true)		
			{
				String h=String.format("%02d", ahour);
				String m=String.format("%02d", aminutes);
				 display= h + ":" + m +" "+"AM";	 
			}
			else if (am==false)	
			{
				String h=String.format("%02d", ahour);
				String m=String.format("%02d", aminutes);
				 display= h + ":" + m +"PM";	 
			}
		}
		return display;	
	}
	
	//Returns the current alarm time as the number of minutes past midnight
	
	public int getAlarmTimeRaw()
	{
		int numberMin;
		numberMin= aminutes + (ahour*60);
		return numberMin;	
	}
	
	//Returns the current clock time as a string in one of the following forms, depending on whether the clock is currently in 24-hour mode: "hh:mm", "hh:mm AM", or "hh:mm PM"
	
	public String getClockTimeAsString()
	{
		String display=null;
		if(time24==true) {
		display= String.format("%02d:%02d", hour	, min);	
		}
		
		else if (time24==false)
		{
			if (am==true)		
			{
				String h=String.format("%02d", hour);
				String m=String.format("%02d A", min);
				 display= h + ":" + m + "M";
			}
			else if (am==false)	
			{
				String h=String.format("%02d", hour);
				String m=String.format("%02d P", min);
				 display= h + ":" + m +"M";	 
			}
		}
		
		return display;
		
	}
	
	//Returns the current clock time as the number of minutes past midnight
	
	public int getClockTimeRaw()
	{
		int numberMin=0;
		if(time24==false)	
		{
			if(am==false)
				numberMin=((hour+12)*60)+min;
			else
				numberMin=((hour%12)*60)+min;
		}
		
		else 
			numberMin= min + (hour*60);		
		
		while(numberMin>=MINUTES_PER_DAY)
			numberMin= numberMin- MINUTES_PER_DAY;
		
		return numberMin;	
		
	}
	
	//Returns the current effective alarm time as a string in one of the following forms, depending on whether the clock is currently in 24-hour mode: "hh:mm", "hh:mm AM", or "hh:mm PM"
	
	public String getEffectiveAlarmTimeAsString()
	{	String s = ahour + ":" + aminutes;
		return s;
	
	}
	
	//Returns the effective alarm time as the number of minutes past midnight
	
	public int getEffectiveAlarmTimeRaw()
	
	{
		int numberMin=0;
		if(time24==false && am==false)
			numberMin= aminutes + ((ahour+12)*60);
		
		else 
			
			numberMin= aminutes + ((ahour)*60);
		if
		(snooze==true)
			numberMin=SnoozeMinutes;
			
		while
			(numberMin>=MINUTES_PER_DAY)
			numberMin=numberMin-MINUTES_PER_DAY;
		
		
		return numberMin;

		

	}
	
	
	//Determines whether the alarm is currently buzzing
	
	public boolean isBuzzing()
	
	{
		
		if ( radio==false)
			return true;
		else 
			return false;
	
	}
	
	//Determines whether the alarm is currently playing the radio
	
	public boolean isPlayingRadio()
	
	{
		if 
		(radio==true)
			return true;
		else 
			return false;
	
	}
	
	//Determines whether the alarm is currently sounding (buzzer or radio)
	
	public boolean isSounding()
	
	{
		if 
		(on==true && alarm==true)
			return true;
		else 
			return false;
			

	


	}
	
	//Sets whether the clock should display time strings using 24-hour mode 
	
	public void set24HourDisplay(boolean use24HourDisplay)
	
	{
		
		time24=use24HourDisplay;
		
		
	}
	
	//Sets the alarm time to the given number of minutes past midnight
	
	public void setAlarmTime(int givenMinutesPastMidnight)
	
	{
		
		snooze=false;
		aminutes=givenMinutesPastMidnight%60;
		ahour=givenMinutesPastMidnight/60;
		while(ahour>=24)
		
		{
			
			ahour=ahour-24;
		
		}
			
		
	}
	
	
	//Sets the alarm time to the given hours and minutes
	
	public void setAlarmTime(int givenHours, int givenMinutes, boolean isPm)
	
	{
		
		snooze=false;
		time24=false;
		ahour=givenHours+(givenMinutes/60);
		aminutes=givenMinutes%60;
		if(isPm==true)
		
		{
			
			ahour=ahour%12;
			ahour=ahour+12;


		}
		
		else 
			if(isPm==false)
		
		{
				
			ahour=ahour%12;

		}
		
	}
	
	//Sets whether the clock should play the radio or the buzzer when sounding the alarm
	
	public void setRadioMode(boolean useRadio)
	{
		radio = useRadio;
		
	}
	
	//Sets the current clock time to the given number of minutes past midnight
	
	public void setTime(int givenMinutesPastMidnight)
	
	{
		
		snooze=false;
		min=givenMinutesPastMidnight%60;
		hour=givenMinutesPastMidnight/60;
		if(time24==false)
			
		{
			if(hour%24>=13)
				
			{
				
				hour=(hour%24)-12;
				am=false;
				
			}
			
			else
				
			{
				hour= hour%24;
				if(hour==0)
					hour=12;
				am=true;
			}
		}
		
		else
			
			hour= hour%24;	
		
	}
	
	
	//Sets the current clock time to the given hours and minutes.
	
	public void setTime(int givenHours, int givenMinutes, boolean isPm)
	
	{
		
		time24=false;
		hour=givenHours+(givenMinutes/60);
		min=givenMinutes%60;
		if(isPm==true)
			
		{
			
			hour=hour%12;
			if (hour==0)
				hour=24;
			am=false;
			
		}
		
		else 
			if(isPm==false)
			
		{
				
			hour=hour%12;
			if (hour==0)
				hour=0;
			am=true;
			
		}
		
	
	
	}
	
	//Stops the clock from sounding and sets the effective alarm time for SNOOZE_MINUTES minutes after the current clock time.
	
	public void snooze()
	
	{
		if
		(isSounding()==true )
		
		{
			
			alarm=false;
			SnoozeMinutes=min+(hour*60)+SNOOZE_MINUTES;
			
			snooze=true;

		}
	
	}
		
}
