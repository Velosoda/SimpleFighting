import java.util.*;
public class User 
{
	Scanner scans = new Scanner(System.in);
	public int protein = 400;
	public int potion = 0; 
	public double avgAttack = 0;
	public double avgHealth = 0; 
	ArrayList<Hero> team = new ArrayList<Hero>();
	ArrayList<Hero> party = new ArrayList<Hero>(3);
	ArrayList<Hero> gym = new ArrayList<Hero>();
	ArrayList<Enemy> foes = new ArrayList<Enemy>();
	
	
	public void getAvgs()
	{
		avgAttack = 0;
		avgAttack = 0;
		for(int i = 0; i < party.size(); i++)
		{
			avgAttack += party.get(i).attack;
			avgHealth += party.get(i).maxHealth;
		}
		avgAttack = avgAttack/party.size();
		avgHealth = avgHealth/party.size();
	}
	public void usePotion(Fighter potionDrinker)
	{
		if(potion>0 && potionDrinker.health!=10)
		{
			potionDrinker.health=10;
			potion--;
		}
	}
	
	public void chooseMissionTeam()
	{
		boolean finish = true;
		while(finish == true)
		{
			System.out.println("Please Choose the names of up to three Heros to join you on your missions:");
			Constants.arrayStats(team);
			String chosen = scans.nextLine();
		
			for(int i=0;i<team.size();i++)
			{
				Hero hero = team.get(i); 
				if(hero.name.equals(chosen)) 
				{
					//<-----------while loop, so that if you enter an invalid response it doesnt skip back to loop, it asks user again
					System.out.println("Is this the correct hero? \n1: Yes \n2: No");
					Constants.statsOne(hero);
					String answer = scans.nextLine();
					if(answer.toUpperCase().equals("1") || answer.toUpperCase().equals("YES"))
					{
						if(party.size()<3)
						{	
							party.add(hero);
							System.out.println(hero.name + "has been added to the mission squad!");
						}
						else
						{
							System.out.println("Sorry, mission team is full, please fight in dungeon with your team first...");
							finish = false;
							//break; //same as above comment, needs to break because it will keep running loop until for condition is met.
						}
					}
					else if(answer.toUpperCase().equals("2") || answer.toUpperCase().equals("NO"))
					{
						System.out.println("Okay moving on...");
						return;//why do we need a return here? this would return out of the method right?
					}
					else
					{
						System.out.println("Invalid Response!");
					}
					return;//what is the purpose of this, we want to run this method until it finds a name ?
				}
			}
			System.out.println("Sorry, nobody on your team is named " + chosen);//Should go here, so that if novody in the for loops name==chosen, it will exit for loop, run this message and start the While loop over again
		}
	}
	public void viewGym()
	{
		Constants.arrayStats(gym);
		test();
	}
	public void test()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("type 1");
		if(scan.nextInt()==1)
		{
			System.out.println("testing");
			viewGym();
		}
		scan.close();
	}
	public void addHeroToGym(Hero hero)
	{
		gym.add(hero);
		hero.repsTillRevive = (int) hero.maxHealth * 10;
		hero.health=0;
		hero.startWorkOut();
	}
	public void gymHeroCheck()
	
	{
		if(gym.isEmpty())
		{
			System.out.println("Sorry, Looks like you have nobody in the gym.");
			return;			
		}
		System.out.println("Please Choose the name of the Hero you wish to remove from the gym");
		Constants.gymStats(gym);
		String chosen = scans.nextLine();
	
		for(int i=0;i<gym.size();i++)
		{
			Hero hero = gym.get(i); 
			if(hero.name.equals(chosen)) 
			{
				
				Constants.statsOne(hero);
				
				if(hero.health == hero.maxHealth)
				{
					System.out.println("Congratulations! Your hero has finished his workout and has rejoined your team.");
					gym.remove(i);
					return;
				}
				else
				{
					System.out.println("Sorry, " + hero.name + " has not finished training. He still has " + (hero.repsTillRevive-hero.currentReps) + " reps remaining");
				}
				
			
			}
			else
			{
				System.out.println("Sorry, nobody on your team is named " + chosen);
			}
			
		}
	}
}
