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
    private int dice;
    private int count;
    final private Enemy enemy;
    final String red ="\u001B[31m";
    final String green = "\u001B[32m";
    final String reset = "\u001B[0m";
    final String blackBG= "\u001B[40m";
    final String magenta = "\u001B[35m";
    final private String blue = "\u001B[34m";
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
        this.enemy = new Enemy();

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
            atk = statBase+6;
            move = "Icicle Lance";
            ultimate = "Cocytus";
        }
        else if (classChar.equals("warrior"))
        {
            this.classChar = classChar;
            int statBase = (int)(Math.random()*20)+1;
            health = statBase+100;
            stamina = statBase+6;
            atk = statBase+1;
            move = "Cleave";
            ultimate = "Strong Cleave";
        }
        else {
            this.classChar = "paladin";
            int statBase = (int)(Math.random()*15)+1;
            health = statBase+100;
            stamina = statBase+3;
            atk = statBase+3;
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
                rollDice();
                if (dice ==6)
                {
                    dmg = dice*atk/2+17;
                }
                else if(dice >=3)
                {
                    dmg = atk+20;
                }
                else{
                    dmg = 20;
                }
            }
            else if(move.equals("Cleave"))
            {
                rollDice();
                if (dice ==6)
                {
                    dmg = atk+dice;
                }
                else if(dice >=3)
                {
                    dmg = atk+15;
                }
                else{
                    dmg = 15;
                }
            }
            else
            {
                rollDice();
                if (dice ==6)
                {
                    dmg = atk+17;
                }
                else if(dice >=3)
                {
                    dmg = atk+17;
                }
                else{
                    dmg = 17;
                }
            }
        }
        else if(moveNum == 2 && stamina>=30)
        {
            int dice = rollDice();
            if(ultimate.equals("Cocytus"))
            {
                if (dice ==6)
                {
                    dmg = dice*atk+75;
                }
                else if(dice >=3)
                {
                    dmg = atk+dice+75;
                }
                else{
                    dmg = 80;
                }
            }
            else if(ultimate.equals("Strong Cleave")  )
            {
                if (dice ==6)
                {
                    dmg = dice+atk*3+65;
                }
                else if(dice >=3)
                {
                    dmg = atk+65;
                }
                else{
                    dmg = 69;
                }
            }
            else
            {
                if (dice ==6)
                {
                    dmg = dice*atk+70;
                }
                else if(dice >=3)
                {
                    dmg = atk+70;
                }
                else{
                    dmg = 70;
                }
            }
        }
        else if (stamina>=2 && moveNum ==3)
        {
            changeHealth();
        }
        enemy.changeEHP(dmg);
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

    public int changeStamina()
    {
        int diceNum =rollDice()+1;
        stamina+=diceNum;
        return diceNum;
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

    /** A method that returns the private variable health
     *
     * @return returns the private variable health
     */
    public int returnHP()
    {
        return health;
    }


    /** The method that tests whether the game is finished or not based on the player
     * hp, enemy ehp and the count.
     *
     * @return returns a boolean value representing whether the game ended or not
     */
    public boolean finale()
    {
        int ehp = enemy.returnEHP();
        if (!(count==3 && ehp <= 0 || health <= 0) && count<2)
        {
            return true;
        }
        return false;

    }

    /** The method that returns a finishing message after the conditions for ending the game is met
     * This is calculated through the health and count variables.
     *
     * @param count the count parameter represents the rounds that the player has played the game,
     *              each count representing one enemy spawned since the game started.
     *
     * @return returns a formatted string as a sentence to print.
     */
    public String endMessage(int count)
    {
        if (health <= 0 && count<3)
        {
            return green + "Congrats you just died\nBetter luck next time HAHAHAHA." + reset;
        }
        else if (health <=0 && count==3)
        {
            return blackBG + red + "The combination of maximum output blueberry blue and banana yellow! LIME GREEN!" + reset +"\nYou died whilst fighting The Ancient One";
        }
        else{
            String words = "";
            words+= "What a silly and fun journey this was.\nRenowned for clearing the dungeon, you become";
            words+= " renowned as the Dungeon Master.\nYou are a true hero who braved the darkness brought";
            words+=" peace with your blinding light.";
            return magenta + words + reset;
        }
    }

    /** A method that returns the stamina of the player.
     *
     * @return returns the value from the private variable stamina.
     */
    public int returnStamina()
    {
        return stamina;
    }

    /** The method that determines whether the player has defeated the enemy or not. This is calculated
     * based on ehp and health.
     *
     * @return returns a boolean value representing the status of the fight. True if battling, false if otherwise.
     */
    public boolean battling()
    {
        int ehp = enemy.returnEHP();
        if (ehp > 0 && health> 0)
        {
            return true;
        }
        return false;
    }

    /** The method that allows for the program to pause to allow for more readability.
     *
     * @param ms represents the amount of time in milliseconds that the program should stop.
     */
    public void wait(int ms)
    {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** Formats strings into sentences that can be printed based on the player's decision out of
     * move 1, 2, 3, and 4.
     *
     * @param moveNum represents the move that the player decided to do during their turn.
     * @param dmg = the damage that the player done in that move against the enemy.
     * @param stamina = the stamina of the player currently possesses.
     * @return returns the formatted string as a sentence that be printed based on calculations based on prior
     * variables.
     */
    public String moveMessage(int moveNum,int dmg,int stamina, int hp)
    {
        String enemyName = enemy.returnName();
        String phrase ="";
        if (moveNum == 1)
        {
            if(stamina>=2)
            {
                phrase = "You do " +red+ dmg + reset + " damage against " + enemyName +"!";
                this.stamina-=2;
            }
            else{
                phrase = green + "foolish hero you can't use a skill you don't have the stamina for." + reset;
            }
        }
        else if(moveNum == 2)
        {
            if(stamina>=30)
            {
                phrase = "You use your ultimate! You did a massive " + red + dmg + reset +" damage against " + enemyName + "!";
                this.stamina-=30;
            }
            else{
                phrase = green + "foolish hero you can't use a skill you don't have the stamina for." + reset;
            }

        }
        else if(moveNum==3)
        {
            if(stamina>=2)
            {
                int healed = health - hp;
                phrase = green + "Oh Hero are you scared?" + red + "STOP HEALING AND GET KILLING!" + reset;
                phrase += "\nYou healed " + green + healed + reset + " hp!";
                this.stamina-=2;
            }
            else{
                phrase = green + "foolish hero you can't use a skill you don't have the stamina for." + reset;
            }
        }
        else {
            phrase = "You did nothing... it was not very effective!";
        }
        int staminaChange = changeStamina();
        phrase+="\nYou regained " + blue + staminaChange + reset + " stamina!";
        return phrase;
    }

    /** The method used to return a formatted string based on the player's move during their round
     * exclusively during the final boss fight.
     *
     * @param moveNum represents the move that the player decided to take during their round.
     * @param dmg = the amount of damage the player did during their round.
     * @param stamina = the amount of stamina that the player currently have.
     * @param hp = the amount of health the player currently possesses.
     * @return returns a formatted string that can be printed depending on the variable values in the parameter.
     */
    public String moveMessageBoss(int moveNum, int dmg,int stamina, int hp)
    {
        String enemyName = enemy.returnName();
        String phrase ="";
        if (moveNum == 1)
        {
            if(stamina>=2)
            {
                phrase = "You do " + red + dmg + reset + " damage against " + enemyName +"!";
                this.stamina-=2;
            }
            else{
                phrase = blackBG + red + "..." + reset;
            }
        }
        else if(moveNum == 2)
        {
            if(stamina>=30)
            {
                phrase = "You use your ultimate! You did a massive " + red +  dmg + reset +" damage against " + enemyName + "!";
                this.stamina -=30;
            }
            else{
                phrase = blackBG+red + "Stupid hero" + reset;
            }

        }
        else if(moveNum==3)
        {
            if(stamina>=2)
            {
                int healed = health - hp;
                phrase = blackBG + red + "Go ahead and delay the inevitable" + reset;
                phrase += "\nYou healed " + green +  healed + reset +" hp!";
                this.stamina -=2;
            }
            else{
                phrase = blackBG + red + "Do you want to lose?." + reset;
            }
        }
        else {
            phrase = "You did nothing... it was not very effective!";
        }
        int staminaChange = changeStamina();
        phrase+="\nYou regained " + blue + staminaChange + reset + " stamina!";
        return phrase;
    }

    /** The method that allows the enemies to do damage to the players.
     *
     * @param dmg the damage that the enemies do to the players.
     */
    public void takeDamage(int dmg)
    {
        health-=dmg;
    }

    /** The method that spawns the enemies from the enemy class and returns a formatted string
     * to display and or notify the user
     *
     * @return the formatted string that the method returns to display to the user.
     */
    public String enemySpawn()
    {
        String enemyName = enemy.generateEnemies();
        String describeEnemy = enemy.describeEnemy();
        String enemyPrompt = "\nAn enemy appears before you...";
        enemyPrompt += "\nA wild " + enemyName + " appeared!";
        enemyPrompt +="\n" + blue + describeEnemy + reset;
        enemyPrompt += "\n" +  green + "Now go kill it!" + reset +" says a distant voice\n";
        wait(1000);
        return enemyPrompt;
    }

    /** the method that returns player health, stamina and enemy health. This allows the player
     * to understand the situation and make decisions in their fight.
     *
     * @return returns a formatted string with color with information about the battle.
     */
    public String battleInformation()
    {
        int ehp = enemy.returnEHP();
        String gameStats = green + "Player Health: " + health + reset;
        gameStats += "\n" + blue + "Stamina: " + stamina + reset;
        gameStats += "\n" + red + "Enemy Health: " + ehp + reset;
        return gameStats;
    }

    /** The variable that allows the player to retrieve the private count variable.
     *
     * @return the value of the private variable count.
     */
    public int returnCount()
    {
        return count;
    }

    /** The method that returns a formatted string about the boss dialogue.
     *
     * @return the formatted string with color of the boss speaking.
     */
    public String bossMessage()
    {
        enemy.generateEnemies();
        String bossPrompt = green + "Good job hero, you have made it far. Far exceeding my expectations" + reset;
        bossPrompt += "\n" + green+"You have performed well so far but you have yet to face your greatest challenge...";
        bossPrompt += "\n" + blackBG + red + "Throughout heaven and earth, I alone am the honored one." + reset;
        bossPrompt += "\n" + blackBG + red + "It is I! DIO! No wait I meant IT IS I! The Ancient One!" + reset;
        return bossPrompt;
    }

    /** Allows the enemies to do something in the game such as doing damage by calling
     * the enemyMove() method from the enemy class.
     *
     * @return returns a formatted string that informs the user how much damage they had
     * taken.
     */
    public String enemyMove()
    {
        String enemyName = enemy.returnName();
        int enemyDmg = enemy.enemyMove();
        takeDamage(enemyDmg);
        return "\n"+ enemyName + " does " + red + enemyDmg +" damage!\n" + reset;

    }

    /** The method that displays the 4 choices a player can make. This is in the form of
     * a string.
     *
     * @return returns the formatted string for the user to decide their next move.
     */
    public String playerChoice()
    {
        String choice = "What is your move?\n1) use skill(2 stamina)";
        choice += "\n2) use ultimate(30 stamina)";
        choice+="\n3) heal(2 stamina)";
        choice+="\n4) do nothing\n:";
        return choice;
    }

    /** The method that tests whether the player is dead or not.
     *
     * @return returns a boolean variable depending on the player is alive or not.
     */
    public boolean checkHP()
    {
        int hp = returnHP();
        if (hp>0)
        {
            return true;
        }
        return false;
    }

    /** The method that allows the boss to do damage to the player in the game through
     * the enemyMove() method of the enemy class.
     *
     * @return returns a formatted string depending on the boss damage.
     */
    public String bossMove()
    {
        String bossName = enemy.returnName();
        String gameReply ="";
        int enemyDMG = enemy.enemyMove();
        if(enemyDMG>=health)
        {
            gameReply +="\nYou just got off screened!\n";
        }
        else {
            gameReply += "\n"+bossName + " does " + red + enemyDMG +" damage!\n" + reset;
        }
        takeDamage(enemyDMG);
        return gameReply;
    }

    /** The method that allows the user to interact with the private variable count. It can only
     * increment by 1.
     *
     */

    public void changeCount()
    {
        count++;
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