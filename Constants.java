
package pack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Constants 
{
	Scanner scan = new Scanner(System.in);
	public static final String menu = "Menu \n1: Guy Store \n2: Team \n3: Fight \n4: Quit";
	public static final String store = "Store \n1: Buy Guy <100 protein> \n2: Buy Potion <10 protein> \n3: Info \n4: Quit";
	public static final String invalidInput = "Invalid Input! Please Try Agian!"; 
	public static final String notEnoughProtein = "Sorry, don't have enough protein!";
	public static final String teamMenu = "Team! \n1: Full Team \n2: Choose Mission Squad \n3: Heal Team at Gym \n4: Upgrade Team \n5: View Party \n6: Quit";
	public static final String fightMenu = "1: Fight \n2: Use Potion \n3: Switch Fighter \n4: Run";
	public static void arrayStats(ArrayList<Hero> array)
	{
		for(int i=0;i<array.size();i++)
		{
			Hero hero = array.get(i);	
			System.out.println("\n Name:" + hero.name +
					"\n Health:" + hero.health +
					"\n Attack:" + hero.attack +
					"\n Speed:" + hero.speed +
					"\n Type:" + hero.type);
		}
	}
	public static void gymStats(ArrayList<Hero> gym)
	{
		for(int i=0;i<gym.size();i++)
		{
			Hero hero = gym.get(i);	
			System.out.println("\n Name:" + hero.name +
					"\n Health: " + hero.health +
					"\n Reps: " + hero.currentReps +
					"\n Reps Until Revived: " + hero.currentReps);
		}
	}
	public static void statsOne(Hero user)
	{
		System.out.println("\n Name:" + user.name +
							   "\n Health:" + user.health +
							   "\n Attack:" + user.attack +
							   "\n Speed:" + user.speed+
						       "\n Type:" + user.type +
						       "\n");
	}
	public static Hero setPlayer1(User user)//method that sets player 1 to a hero from party with health greater than 0, returns the Hero.
	{
		Hero player = null;
		for(int i=0;i<user.party.size();i++)
		{
			player = user.party.get(i);
			if(player.health>0)
			{
				return player;
			}
		}
		return player;
	}
	
	public static void determineAtk(Hero hero , Enemy enemy)// takes in a hero and an Enemy and compares their types to see whose type is better, then returns an int 
	{
		double doubleDamage = 2.0; 
		double halfDamage = 0.5;
		double regularDamage = 1; 
		if(hero.type.toUpperCase().equals("LEGS") && enemy.type.toUpperCase().equals("ARMS"))
		{
			hero.attackMultiplier = doubleDamage;
			enemy.attackMultiplier = halfDamage;
		}
		else if(hero.type.toUpperCase().equals("ARMS") && enemy.type.toUpperCase().equals("CHEST")) 
		{
			hero.attackMultiplier = doubleDamage;
			enemy.attackMultiplier = halfDamage;
		}
		else if(hero.type.toUpperCase().equals("CHEST") && enemy.type.toUpperCase().equals("LEGS"))
		{
			hero.attackMultiplier = doubleDamage;
			enemy.attackMultiplier = halfDamage;
		}
		else if(hero.type.toUpperCase().equals("LEGS") && enemy.type.toUpperCase().equals("CHEST"))
		{
			hero.attackMultiplier = halfDamage;
			enemy.attackMultiplier = doubleDamage;
		}
		else if(hero.type.toUpperCase().equals("CHEST") && enemy.type.toUpperCase().equals("ARMS"))
		{
			hero.attackMultiplier = halfDamage;
			enemy.attackMultiplier = doubleDamage;
		}
		else if(hero.type.toUpperCase().equals("LEGS") && enemy.type.toUpperCase().equals("ARMS"))
		{
			hero.attackMultiplier = halfDamage;
			enemy.attackMultiplier = doubleDamage;
		}
		else
		{
			hero.attackMultiplier = regularDamage;
			enemy.attackMultiplier = regularDamage;
		}
	}
	public static void switchParty(User user,Hero hero, Enemy enemy)
	{
		Scanner scan = new Scanner(System.in);
		if(user.party.size()>=1)
		{
			System.out.println("Who would you like to switch into:");
			Constants.arrayStats(user.party);
			//selects name 
			boolean switcher = true;
			int i = 0;
			String partyMemberToSwitch = scan.nextLine();
			while(switcher)
			{
				if(partyMemberToSwitch.equals(user.party.get(i).name))
				{
					if(user.party.indexOf(i) == 0)
					{
						System.out.println(partyMemberToSwitch + " is already out!");
						switchParty(user,hero,enemy);
					}
					System.out.println(partyMemberToSwitch + " is now out!");
					Collections.swap(user.party,0,i);
					switcher=false;
				}
				else
				{
					i++;
				}
			}
		}
		else
		{
			System.out.println("You have no one else in your party to");
		}
		scan.close();
	}
	public void switchHero(ArrayList<Hero> party)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Who would you like bring out");
		Constants.arrayStats(party);
		String newHero = scan.nextLine();
		for(int i = 0; i < party.size(); i++)
		{
			Hero heroToSwapIn = party.get(i);
			if(heroToSwapIn.name.equals(newHero))
			{
				Hero oldHero = party.get(0);
				party.add(0, heroToSwapIn);
				party.add(oldHero);
			}
			else if(i==party.size() && !heroToSwapIn.name.equals(newHero))
			{
				System.out.println("Sorry that hero doesn't exist");
			}
			else
			{
				System.out.println("You cant Swap the first player with the first player");
				scan.close();
				return;
			}
		}
		Constants.arrayStats(party);
		scan.close();
		return;
	}
	public static void main(String[] args)
	{
		Constants constants = new Constants();
		ArrayList<Hero> party = new ArrayList<Hero>();
		for(int i = 0; i < 3; i++)
		{
			Hero hero = new Hero();
			hero.name = ""+ (i + 1) ;
			party.add(hero);
			System.out.println(party.get(i).name);
		}
		constants.switchHero(party);
	}
}
}
