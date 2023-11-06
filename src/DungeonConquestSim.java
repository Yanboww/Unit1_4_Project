/**
*This class represents a simulator
* It is computations for the program
 */
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
    final String red ="\u001B[31m";
    final String green = "\u001B[32m";
    final String reset = "\u001B[0m";
    final String blackBG= "\u001B[40m";
    final String magenta = "\u001B[35m";
    /**
     * Constructor for the DungeonConquestSim class. This creates a new instance of a game with
     * the below parameter
     *
     * @param classChar represents the class the player chosen out of warrior, mage and paladin
     */

    public DungeonConquestSim(String classChar)
    {
        classChar = classChar.toLowerCase();
        decideConstruct(classChar);

    }

    /** Decides what stats the player is going to have depending on the class they chose
     *
     * @param classChar a string representing the class the player chose out  of warrior, mage and paladin
     */
    public void decideConstruct(String classChar)
    {
        count=0;
        if(classChar.equals("mage"))
        {
            this.classChar = classChar;
            int statBase = (int)(Math.random()*10)+1;
            health = statBase+100;
            stamina = statBase+2;
            atk = statBase+4;
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

    /** A method that rolls a die which has 6 different equal outcomes
     *
     * @return the value of the dice in integer format after it is rolled
     */
    public int rollDice()
    {
        dice = (int)(Math.random()*6)+1;
        return dice;
    }

    /**A method that adds 50 to the private variable health.
     *
     */
    public void resetHP()
    {
        health+=50;
    }

    /** A method that processes what the player chose to do during the gameplay out of 4
     * possible inputs and change the EHP variable according to the calculations whilst
     * also testing for stamina requirements
     *
     * @param moveNum an integer value of what the player chose from 1 to 4
     * @return returns the dmg variable as an integer after the calculations
     */
    public int useMove(int moveNum)
    {
        int dmg = 0;
        if(moveNum==1 && stamina>=2)
        {
            if(move.equals("Icicle Lance"))
            {
                stamina-=2;
                rollDice();
                if (dice ==6)
                {
                    dmg = dice*atk/2+2;
                }
                else if(dice >=3)
                {
                    dmg = atk+3;
                }
                else{
                    dmg = 7;
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
                    dmg = atk+2;
                }
                else{
                    dmg = 5;
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
                    dmg = 6;
                }
            }
        }
        else if(moveNum == 2 && stamina>=10)
        {
            if(ultimate.equals("Cocytus"))
            {
                stamina-=10;
                rollDice();
                if (dice ==6)
                {
                    dmg = dice*atk+20;
                }
                else if(dice >=3)
                {
                    dmg = atk+dice+25;
                }
                else{
                    dmg = 25;
                }
            }
            else if(ultimate.equals("Strong Cleave")  )
            {
                stamina-=10;
                rollDice();
                if (dice ==6)
                {
                    dmg = dice+atk+atk+10;
                }
                else if(dice >=3)
                {
                    dmg = atk+15;
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
                    dmg = dice*atk+10;
                }
                else if(dice >=3)
                {
                    dmg = atk+20;
                }
                else{
                    dmg = 20;
                }
            }
        }
        else if (stamina>=2 && moveNum ==3)
        {
           changeHealth();
        }
        changeEHP(dmg);
        return dmg;
    }

    /** A method that rolls the dice using the rollDice() method and adds 6 + the dice value
     * to health and removes 2 stamina
     *
     */

    public void changeHealth()
    {
        int diceNum = rollDice();
        health+=6+diceNum;
        stamina-=2;
    }

    /** A method that changes the stamina variable based on the rollDice() method + 1
     *
     */

    public void changeStamina()
    {
        int diceNum =rollDice();
        stamina+=diceNum+1;
    }

    /** Describes the move of the player by testing the string value in the move or ultimate
     * variable .
     *
     * @param value an integer between 1 or 2 that determines whether the ultimate move or
     * the normal move gets defined.
     * @return returns the string value of the move description.
     */

    public String describeMove(int value)
    {
        String words ="";
        if (move.equals("Icicle Lance") && value ==1)
        {
            words+= "Icicle Lance is  basic ice type magic that is long ranged";

        }
        else if (move.equals("Cleave") && value ==1)
        {
            words+= "Cleave sends a fast cleave towards your enemy";

        }
        else if (move.equals("Stab") && value ==1)
        {
            words+= "Stab is as its name suggests...";

        }
        else if (ultimate.equals("Cocytus") && value ==2)
        {
            words+= "Cocytus! Freeze your enemies with the ice covering deepest layer of hell.";

        }
        else if (ultimate.equals("Strong Cleave") && value ==2)
        {
            words+= "Strong Cleave - You use a cleave... but BETTER!";

        }
        else
        {
            words+= "Holy Smite: The faith in our lord shall grant us his providence and purge the world of sin.";

        }
        return words;

    }

    /** A method that randomly rolls a die to determine between 3 enemy characters and adds 1
     * to the variable count. When count is 3 or more, it guarantees the boss to be generated
     *
     * @return returns the enemy name based on the dice roll and calculations
     */
    public String generateEnemies()
    {
        count++;
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
        else if(diceNum == 5 || diceNum ==6)
        {
            enemy = "Cute Slime that you must murder(you have no choice)";
            name = "Cute Slime that you must murder(you have no choice)";
        }
        if (count>=3)
        {
            enemy = "The Ancient One";
            name = "The Ancient One";
        }
        setEnemy();
        return name;
    }

    /** A method that returns the private variable ehp.
     *
     * @return returns the private variable ehp
     */

    public int returnEHP()
    {
        return ehp;
    }

    /** A method that returns the private variable health
     *
     * @return returns the private variable health
     */
    public int returnHP()
    {
        return health;
    }

    /** Set the enemy stats such as ehp and eatk based on the name assigned to the variable
     * enemy. This process is after the generateEnemies() method which assigned the enemy variable
     * values.
     *
     */
    public void setEnemy()
    {
        if (enemy.equals("The Ancient One"))
        {
            ehp = 300;
            eatk = 20;

        }
        else if (enemy.equals("??????"))
        {
            ehp = 120;
            eatk = 7;

        }
        else if (enemy.equals("Cute Slime that you must murder(you have no choice)"))
        {
            ehp = 400;
            eatk = 0;

        }
        else {
            ehp = 100;
            eatk = 5;

        }
    }

    /** Describes the
     *
     * @return
     */

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
        else if (enemy.equals("Cute Slime that you must murder(you have no choice)"))
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
        int dmg=0;
        if (enemy.equals("The Ancient One"))
        {
            dmg = eatk+ (int)(Math.random()*10)+5;
            int diceNum = (int)(Math.random()*1000);
            if (diceNum == 10)
            {
                dmg+=health;
            }
        }
        else if (enemy.equals("??????"))
        {
            dmg = eatk+ (int)(Math.random()*6);
        }
        else if (enemy.equals("Cute Slime that you must murder(you have no choice)"))
        {
            dmg = 0;
        }
        else if(enemy.equals("Very Extremely Scary Clown")){
            dmg = eatk;
        }
        health-=dmg;
        return dmg;
    }

    public boolean finale()
    {
        if (count==3 & ehp == 0 || health <= 0)
        {
            return true;
        }
        return false;
    }

    public String endMessage(int count)
    {
        if (health <= 0 && count<3)
        {
            return green + "Congrats you just died\nBetter luck next time HAHAHAHA." + reset;
        }
        else if (health <=0 && count==3)
        {
            return blackBG + red + "The combination of maximum output blue and p*** yellow! LIME GREEN!" + reset +"\nYou died whilst fighting The Ancient One";
        }
        else{
            String words = "";
            words+= "What a silly and fun journey this was.\nRenowned for clearing the dungeon, you become";
            words+= " renowned as the Dungeon Master.\nYou are a true hero who braved the darkness brought";
            words+=" peace with your blinding light.";
            return magenta + words + reset;
        }
    }

    public int returnStamina()
    {
        return stamina;
    }

    public boolean victory()
    {
        if (ehp <= 0)
        {
            return true;
        }
        return false;
    }

    public void wait(int ms)
    {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String moveMessage(int moveNum,int dmg,int stamina)
    {
        String phrase ="";
        if (moveNum == 1)
        {
            if(stamina>=2)
            {
                phrase = "You do " +red+ dmg + reset + " damage against " + enemy +"!";
            }
            else{
                phrase = green + "foolish hero you can't use a skill you don't have the stamina for." + reset;
            }
        }
        else if(moveNum == 2)
        {
            if(stamina>=10)
            {
                phrase = "You use your ultimate! You did a massive " + red + dmg + reset +" damage against " + enemy + "!";
            }
            else{
                phrase = green + "foolish hero you can't use a skill you don't have the stamina for." + reset;
            }

        }
        else if(moveNum==3)
        {
            if(stamina>=2)
            {
                phrase = green + "Oh Hero are you scared?" + red + "STOP HEALING AND GET KILLING!" + reset;
                phrase += "\nYou healed hp!";
            }
            else{
                phrase = green + "foolish hero you can't use a skill you don't have the stamina for." + reset;
            }
        }
        else {
            phrase = "You did nothing... it was not very effective!";
        }
        return phrase;
    }

    public String moveMessageBoss(int moveNum, int dmg, int stamina)
    {
        String phrase ="";
        if (moveNum == 1)
        {
            if(stamina>=2)
            {
                phrase = "You do " + red + dmg + reset + " damage against " + enemy +"!";
            }
            else{
                phrase = blackBG + red + "..." + reset;
            }
        }
        else if(moveNum == 2)
        {
            if(stamina>=10)
            {
                phrase = "You use your ultimate! You did a massive " + red +  dmg + reset +" damage against " + enemy + "!";
            }
            else{
                phrase = blackBG+red + "Stupid hero" + reset;
            }

        }
        else if(moveNum==3)
        {
            if(stamina>=2)
            {
                phrase = blackBG + red + "Go ahead and delay the inevitable" + reset;
                phrase += "\nYou healed hp!";
            }
            else{
                phrase = blackBG + red + "Do you want to lose?." + reset;
            }
        }
        else {
            phrase = "You did nothing... it was not very effective!";
        }
        return phrase;
    }

    /**
     * toString method for the DungeonConquestSim class. This method will return a String
     * showing all the information about the player character in a list.
     *
     * @return returns a String in a properly formatted list containing all
     * the information about the player character's stats
     */

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
