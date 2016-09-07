package pack;
import java.util.*;
public class Main
{
    Scanner scan = new Scanner(System.in);
    User user = new User();
    public void menu()
    {
    	System.out.println(Constants.menu);
	    int input = scan.nextInt();
	    scan.nextLine();
	    switch(input)
	    {
	    	case 1: 
	    		this.guyStore();
	    		break;
	    	case 2:
	    		this.team();
	    		break;
	    	case 3:
	    		this.fight();
	    		break;
	    	case 4:
	    		this.quit();
	    		break; 
	    	default:
	    		System.out.println("Please select an item from the menu");
	    		break;
	    }	
    }
    private void quit() 
    {
        System.out.println("See you soon!");
        System.exit(1); 
    }
    public void damageDealing(Hero hero, Enemy enemy)
    {
    	double heroDamageDealt = hero.attack *  hero.attackMultiplier;
    	double enemyDamageDealt = enemy.attack * enemy.attackMultiplier;
    	hero.health = hero.health - enemyDamageDealt; //enemy does damage to player
    	enemy.health = enemy.health - heroDamageDealt;//player does damage to enemy
    	System.out.println(hero.name + "dealt " + heroDamageDealt + "to " + enemy.name);
    	System.out.println(enemy.name + "dealt " + enemyDamageDealt + "to " + hero.name);
    	System.out.println(hero.name);
    	Constants.statsOne(hero);
    	System.out.println(enemy.name + "\n HP:" + enemy.health + "\nType:" + enemy.type);
    }
    public void actualFight()
    {
    	
    }
    public void fight()
    {
    	System.out.println("Are you ready to begin fighting the aliens! \n1: Yes \n2: No  ");
    	String choice = scan.nextLine();
    	if(choice.toUpperCase().equals("1") || choice.toUpperCase().equals("YES"))
    	{
    		boolean running = true;
        	while(running)
        	{
        		if(user.party.isEmpty())
                {
    	              System.out.println("Sorry, looks like you haven't assigned any fighters to your mission team. You can assemble a team in the Team menu.");
    	              running = false;
    	              menu();
    	        }
        		
                Hero hero = user.party.get(0);
                user.getAvgs();
    	        Enemy enemy = new Enemy(user.avgAttack, user.avgHealth);//Creates new villian
    	        user.foes.add(enemy);//adds that enemy to the Arraylist
    	        enemy = user.foes.get(0);
    	        if(enemy.health == enemy.maxHealth)
    	        {
    	        	System.out.println("Watch out! " + enemy.name + " has appeared! " + "\nHP:" + enemy.health + "\nType:" + enemy.type);
    	        	System.out.println("versus " + hero.name);
    	        	Constants.statsOne(hero);
    	        }	
    	        if(hero.health > 0 && enemy.health > 0)//checks to see if theyre both still alive
    	        {
    	        	System.out.println("What would you like to do?:");
    	        	System.out.println(Constants.fightMenu);
    	        	String result = scan.nextLine();
    	        	if(result.equals("1"))
    	        	{
    	        		Constants.determineAtk(hero, enemy);
    	        		damageDealing(hero, enemy);
    	        	}
    	        	else if(result.equals("2"))//apply potion
    	        	{
    	        		if(user.potion < 1)//checks if they have any health potions
    	        		{
    	        			System.out.println("Don't have any potions!");
    	        		}
    	        		else 
    	        		{
    	        			if(hero.maxHealth - hero.health >=10)
    	        			{
    	        				hero.health+=10;
    	        				user.potion--;
    	        				System.out.println(hero.name + "'s health has increased by 10 to " + hero.health);
    	        			}
    	        			else
    	        			{
    	        				user.potion--;
    	        				System.out.println(hero.name + "'s health has increased by " + hero.health + " to " + hero.maxHealth);
    	        				hero.health = hero.maxHealth;
    	        			}
    	        		}
    	        	}
    	        	else if(result.equals("3"))
    	        	{
    	        		Constants.switchParty(user, hero, enemy);
    	        		Constants.determineAtk(hero, enemy);
    	        		double enemyDamageDealt = enemy.attack * enemy.attackMultiplier;
    	        		hero.health = hero.health - enemyDamageDealt;
    	        		System.out.println(enemy.name + "dealt " + enemyDamageDealt + "to " + hero.name);
    	        	}
    	        	else if(result.equals("4"))
    	        	{
    	        		System.out.println("Your team's retreats! Your team needs to hit the bench!");
    	        		running = false;
    	        		return;
    	        		//needs to show that they get no exp and no money
    	        	}
    	        	else
    	        	{
    	        		System.out.println(Constants.invalidInput);
    	        	}
    	        }
    	        else
    	        {
    	        	if(hero.health <= 0)
    	        	{
    	        		System.out.println("Ahhh " + hero.name + " hit the gym bitch!");
    	        		user.gym.add(hero);
    	        		user.party.remove(hero);
    	        		if(user.party.isEmpty())
    	        		{
    	        			System.out.println("You have no one left in your party! GO to the GYM! QUICK!");
    	        			return;
    	        		}
    	        	}
    	        	else if(enemy.health <= 0)
    	        	{
    	        		user.foes.remove(enemy);
    	        		System.out.println("Ahh the enemy screams in pain, his corpse lays there bloody. He look up into sky and mutters to himself, 'Was it worth it in the end...' he takes one last breath and dies.");
    	        		hero.exp+=3;
    	        		hero.levelUp();
    	        	}
    	        }
        	}
    	}
    	else if(choice.toUpperCase().equals("2") || choice.toUpperCase().equals("NO"))
    	{
    		System.out.println("Okay! Please come back when you're ready to battle!");
    		return;
    	}
    	else
    	{
    		System.out.println(Constants.invalidInput);
    	}
    }
    public void team() 
    {
    	boolean exit = true;
    	while(exit == true)
    	{
    		System.out.println(Constants.teamMenu);
    		String input = scan.nextLine();
    		
    		if(input.equals("1"))
    		{
    			Constants.arrayStats(user.team);
    		}
    		else if(input.equals("2"))
    		{
    			user.chooseMissionTeam(); 
    		}
    		else if(input.equals("3"))
    		{
    			//Heal Team 
    			//3 guys in party fight in dungeon, they all die and hp is 0, they get moved out of party array to new array called gym, 
    			//where their health is reset after a certain amount of time like a countdown to move them back from the 
    			//gym and add them back into your team to use...so does that mean thats how the healing system will work?
    			//so no option in team to heal teamates using potion outside of the battle
    		}
    		else if(input.equals("4"))
    		{
    			//Upgrade team? Where you can use exp points to level up stats
    		}
    		else if(input.equals("5"))
    		{
    			for(int i = 0; i<user.party.size(); i++)
    			{
    				Hero hero = user.party.get(i);
    				Constants.statsOne(hero);
    			}
    		}
    		else if(input.equals("6"))
    		{
    			System.out.println("See you later!");
    			exit = false;
    			menu();
    		}
    		else
    		{
    			System.out.println(Constants.invalidInput);
    		}
    	}
    }
    private void guyStore() 
    {
    	boolean exit = true;
    	while(exit == true)
    	{
    		//<--- this infinity loop will force us to use breaks when we can just avoid that by adding a condition what we currently have is messy and needs to be more simple
    		//get the input 
    		//scan the input and make sure its a number that we can use
    		//user selects item they want to buy, check to see if the have enough money
    		//make the transaction 
    		//add the item to the user's team/"inventory"
    		//open store again
    		//repeat
    		System.out.println("Protein: " + user.protein);
    		System.out.println("----------------------------------------");
    		System.out.println(Constants.store);
    		String input = scan.nextLine();
    		if(input.equals("1"))
    		{
    			if(user.protein >= 100)
    			{
    				//Adds a fighter to team
    				Hero newGuy = new Hero();
    				System.out.println("Congratulations! new fighter has been added!");
    				System.out.println("Please name your new hero!:");
    				String newName = scan.nextLine(); 
    				if(!user.team.isEmpty())
    				{
    					if(user.team.iterator().next().name.equals(newName))
    					{
    						System.out.println("hey nigga whatchyou doing??");
    						return;
    					}
    				}
    				newGuy.name = newName;
    				System.out.println("Say hello to");
    				Constants.statsOne(newGuy); 
    				user.team.add(newGuy);
    				user.protein = user.protein - 100;
    			}
    			else
    			{
    				System.out.print(Constants.notEnoughProtein);
    			}	
    		}
    		else if(input.equals("2"))
    		{
    			if(user.protein >= 10)
    			{
    				user.protein-= 10;
    				user.potion++;
    				System.out.println("Health Potion has been added!");
    				System.out.println("You currently have " + user.potion + "potions!");
    			}
    			else
    			{
    				System.out.print(Constants.notEnoughProtein);
    			}	
    		}
    		else if(input.equals("3"))
    		{
    			System.out.println("This is the store where you can buy another fighter to add to your army, or health potions to revive health!");
    		}	
    		else if(input.equals("4"))
    		{
    			System.out.println("Thank you for shopping!");
    			exit = false;
    			menu();
    		}
    		else
    		{
    			System.out.println(Constants.invalidInput);
    		}	
    	}
    }
    public static void main(String[] args)
    {
    	Main main = new Main();
        while(true)
        {       //infinite so you can return to menu after finishing a method
            try //moved try here because it didn't work in menu()
            {
            	  main.menu();
            }
            catch(InputMismatchException e) 
            {
                main.scan.next();//takes the invalid data type from input stream and stores it as a word, which it then ignores 
                System.out.println(Constants.invalidInput);
            }
        }
    }
}

