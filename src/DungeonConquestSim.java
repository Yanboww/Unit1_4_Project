import java.util.Locale;

public class DungeonConquestSim {
    private int health;
    private int stamina;
    private int atk;
    private String classChar;
    private String move;
    private String ultimate;
    private int ehp;
    private int eatk;
    private int count;
    private String enemy;
    private int dice;

    public DungeonConquestSim(String classChar)
    {
        classChar = classChar.toLowerCase();
        decideConstruct(classChar);

    }

    public void decideConstruct(String classChar)
    {
        if(classChar.equals("mage"))
        {
            this.classChar = classChar;
            int statBase = (int)(Math.random()*10)+1;
            health = statBase+100;
            stamina = statBase+2;
            atk = statBase+1;
            move = "Icicle Lance";
            ultimate = "Cocytus";
        }
        else if (classChar.equals("warrior"))
        {
            this.classChar = classChar;
            int statBase = (int)(Math.random()*20)+1;
            health = statBase+100;
            stamina = statBase+2;
            atk = statBase+1;
            move = "Cleave";
            ultimate = "Strong Cleave";
        }
        else {
            this.classChar = "paladin";
            int statBase = (int)(Math.random()*15)+1;
            health = statBase+100;
            stamina = statBase+2;
            atk = statBase+1;
            move = "Stab";
            ultimate = "Holy Smite";
        }
    }

    public int rollDice()
    {
        dice = (int)(Math.random()*6)+1;
        return dice;
    }

    public int useMove(int moveNum)
    {
        int dmg = 0;
        if(moveNum==1)
        {
            if(move.equals("Icicle Lance"))
            {
                stamina-=2;
                rollDice();
                if (dice ==6)
                {
                    dmg = dice*atk/2;
                }
                else if(dice >=3)
                {
                    dmg = atk+1;
                }
                else{
                    dmg = 0;
                }
            }
            else if(move.equals("Cleave"))
            {
                stamina-=2;
                rollDice();
                if (dice ==6)
                {
                    dmg = atk+dice;
                }
                else if(dice >=3)
                {
                    dmg = atk+1;
                }
                else{
                    dmg = 0;
                }
            }
            else
            {
                stamina-=2;
                rollDice();
                if (dice ==6)
                {
                    dmg = atk+2;
                }
                else if(dice >=3)
                {
                    dmg = atk+1;
                }
                else{
                    dmg = 0;
                }
            }
        }
        else if(moveNum == 2)
        {
            if(move.equals("Cocytus"))
            {
                stamina-=10;
                rollDice();
                if (dice ==6)
                {
                    dmg = dice*atk;
                }
                else if(dice >=3)
                {
                    dmg = atk+dice+15;
                }
                else{
                    dmg = 15;
                }
            }
            else if(move.equals("Strong Cleave"))
            {
                stamina-=10;
                rollDice();
                if (dice ==6)
                {
                    dmg = dice+atk+atk;
                }
                else if(dice >=3)
                {
                    dmg = atk+10;
                }
                else{
                    dmg = 10;
                }
            }
            else
            {
                stamina-=10;
                rollDice();
                if (dice ==6)
                {
                    dmg = dice*atk-5;
                }
                else if(dice >=3)
                {
                    dmg = atk+15;
                }
                else{
                    dmg = 12;
                }
            }
        }
        else
        {
           changeHealth();
        }
        changeEHP(dmg);
        return dmg;
    }

    public void changeHealth()
    {
        health+=5;
        stamina-=2;
    }

    public void changeStamina()
    {
        stamina+=2;
    }

    public String describeMove(String moveName)
    {
        String words ="";
        if (moveName.equals("Icicle Lance"))
        {
            words+= "A basic ice type magic that is long ranged";

        }
        else if (moveName.equals("Cleave"))
        {
            words+= "Send a fast cleave towards your enemy";

        }
        if (moveName.equals("Stab"))
        {
            words+= "As its name suggests...";

        }
        if (moveName.equals("Cocytus"))
        {
            words+= "Freeze your enemies with the ice covering deepest layer of hell.";

        }
        if (moveName.equals("Strong Cleave"))
        {
            words+= "You use a cleave... but BETTER!";

        }
        if (moveName.equals("Holy Smite"))
        {
            words+= "The faith in our lord shall grant us his providence and purge the world of sin.";

        }
        return words;

    }

    public String generateEnemies()
    {
        count = 10;
        String name ="";
        int diceNum = rollDice();
        if (diceNum<4)
        {
            enemy = "Very Extremely Scary Clown";
            name = "Very Extremely Scary Clown";
        }
        else if (diceNum == 4)
        {
            enemy = "??????";
            name = "??????";
        }
        else
        {
            enemy = "Cute Slime that you must murder(you have no choice)";
            name = "Cute Slime that you must murder(you have no choice)";
        }
        if (count==10)
        {
            enemy = "The Ancient One";
            name = "The Ancient One";
        }
        setEnemy(name);
        return name;
    }

    public void setEnemy(String name)
    {
        if (enemy.equals("The Ancient One"))
        {
            ehp = 200;
            eatk = 10;

        }
        else if (enemy.equals("??????"))
        {
            ehp = 120;
            eatk = 7;

        }
        else if (enemy.equals("Cute Slime that you must murder(you have no choice"))
        {
            ehp = 400;
            atk = 0;

        }
        else {
            ehp = 100;
            atk = 5;

        }
    }

    public String describeEnemy()
    {
        String words;
        if (enemy.equals("The Ancient One"))
        {
            words = "A being of the past, forgotten through time, a nameless god";
        }
        else if (enemy.equals("??????"))
        {
            words = "... I don't quite know what it is perhaps its identity will be revealed later";
        }
        else if (enemy.equals("Cute Slime that you must murder(you have no choice"))
        {
            words = "The cutest thing you have and will ever see";
        }
        else {
            words = "Just a regular old clown";
        }
        return words;
    }

    public void changeEHP (int dmg)
    {
        ehp-=dmg;
    }

    public int enemyMove()
    {
        int dmg;
        if (enemy.equals("The Ancient One"))
        {
            dmg = atk+ (int)(Math.random()*10);
        }
        else if (enemy.equals("??????"))
        {
            dmg = atk+ (int)(Math.random()*6);
        }
        else if (enemy.equals("Cute Slime that you must murder(you have no choice"))
        {
            dmg = 0;
        }
        else {
            dmg = atk+3;
        }
        health-=dmg;
        return dmg;
    }

    public boolean finale()
    {
        if (count==10 & ehp == 0 || health == 0)
        {
            return true;
        }
        return false;
    }

    public String endMessage()
    {
        if (health == 0)
        {
            return "Congrats you just died\nBetter luck next time LLLLLLL";
        }
        else{
            String words = "";
            words+= "What a silly and fun journey this was. Renowned for clearing the dungeon, you become";
            words+= " renowned as the Dungeon Master. You are a true hero who braved the darkness brought";
            words+=" peace with your blinding light.";
            return words;
        }
    }

    public int returnStamina()
    {
        return stamina;
    }

    public boolean victory()
    {
        if (ehp == 0)
        {
            return true;
        }
        return false;
    }
    public void nextEnemy()
    {
        if (victory())
        {
            String enemy = generateEnemies();
            setEnemy(enemy);
        }
    }

    public String toString()
    {
        String words = "";
        words+= "Class: " +classChar;
        words+="\nHP: " + health;
        words+="\nStamina: " + stamina;
        words+="\nAttack " + atk;
        words+="\nBasic Skill: " + move;
        words+="\nUltimate: " + ultimate;
        return words;

    }


}