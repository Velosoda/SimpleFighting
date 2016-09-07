package pack;

public class Enemy extends Fighter
{
	Enemy(double avgAttack, double avgHealth)
    {
        String[] typeList = {"Chest", "Arms", "Legs", "Neutral"};
        this.type = typeList[this.rand.nextInt(4)];
        this.attack = avgAttack;
        this.health = avgHealth;
        this.maxHealth = avgHealth;
        this.name = "rip bongs";
    }
}