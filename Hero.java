package pack;

import java.util.Timer;
import java.util.TimerTask;

public class Hero extends Fighter
{
    public double exp = 0; 
    public double expTillNextLevel = 10; 
    public int lvl = 1;
    public int currentReps = 0; 
    public int repsTillRevive= 0; 
    public void levelUp()
    {
        if(exp >= expTillNextLevel)
        {
           lvl = lvl+1;
           //alter stats 
           System.out.println(this.name + " has grown to level "+ this.lvl);
           if(lvl<25) 
           {
               expTillNextLevel = expTillNextLevel * 2; 
           }
           else if (lvl>25 && lvl<50 )
           {
              	expTillNextLevel = expTillNextLevel * 2.25;
           }
        }       
    }
    public void startWorkOut()
    {
    	Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {
            public void run()
            {
            	currentReps++;
            	if(currentReps % 10 == 0)
            	{
            		health+=1;
            	}
                if(currentReps==repsTillRevive)
                {
                	timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task,1000,1000);
    }
}
