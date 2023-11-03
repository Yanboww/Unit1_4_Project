import java.util.Scanner;
public class DungeonRunner {
    public static void main(String[] args) {
        //color ASNI codes for changing string color
        final String blue = "\u001B[34m";
        final String red ="\u001B[31m";
        final String green = "\u001B[32m";
        final String reset = "\u001B[0m";
        final String blackBG= "\u001B[40m";
        final String magenta = "\u001B[35m";

        Scanner s = new Scanner(System.in);

        //Introduction to the game
        System.out.println(green + "Welcome nameless hero, will it be you who clear this dungeon?" + reset);
        System.out.print("Choose your class(type mage, warrior ,or paladin): ");
        String classChar = s.nextLine();
        DungeonConquestSim newGame = new DungeonConquestSim(classChar);
        System.out.println(green + "Welcome, oh great " + classChar + "\nOh? You have amnesia? tsk..." + reset);
        newGame.wait(1500);
        System.out.println(green +"Oh apologies what I meant is but of course! I shall present you your skills" + reset);
        newGame.wait(1000);
        System.out.println(blue + newGame + reset);
        newGame.wait(1500);
        System.out.println(green + "What do you think? Pretty cool right? You better be grateful of me! You rarely find someone as kind as me after all"+ reset);
        newGame.wait(2000);
        System.out.println(green + "Now off you go!" + reset);
        System.out.printf("|%s|%n", "You were pushed into the dungeon by a mysterious force... ");
        System.out.println("\nSystem pop up!");
        newGame.wait(2000);
        System.out.println(blue + "Move: " + newGame.describeMove(1) +reset);
        System.out.println(blue + "Ultimate: " + newGame.describeMove(2) + reset);
        while (!newGame.finale())
        {
            int count=0;
            System.out.println("\nAn enemy appears before you...");
            newGame.wait(2000);
            String enemy = newGame.generateEnemies();
            String describeEnemy = newGame.describeEnemy();
            System.out.println("A wild " + enemy + " appeared!");
            System.out.println(blue + describeEnemy + reset);
            newGame.wait(2000);
            System.out.println(green + "Now go kill it!" + reset +" says a distant voice");
            newGame.wait(1500);
            boolean inBattle = newGame.victory();
            while (!inBattle && count!=10)
            {
                int stamina = newGame.returnStamina();
                int ehp = newGame.returnEHP();
                int hp = newGame.returnHP();
                System.out.println(green + "Player Health: " + hp + reset);
                System.out.println(blue + "Stamina: " + stamina + reset);
                System.out.println(red + "Enemy Health: " + ehp + reset);
                System.out.print("What is your move?\n1) use skill(2 stamina)\n2) use ultimate(10 stamina)\n3) heal(2 stamina)\n4) do nothing\n:");
                String moveNumString = s.nextLine();
                int moveNum = Integer.parseInt(moveNumString);
                int dmg = newGame.useMove(moveNum);
                newGame.wait(1000);
                if (moveNum == 1)
                {
                    if(stamina>=2)
                    {
                        System.out.println("You do " + dmg + " damage against " + enemy +"!");
                    }
                    else{
                        System.out.println(green + "foolish hero you can't use a skill you don't have the stamina for." + reset);
                    }
                }
                else if(moveNum == 2)
                {
                    if(stamina>=10)
                    {
                        System.out.println("You use your ultimate! You did a massive " + dmg +" damage against " + enemy + "!");
                    }
                    else{
                        System.out.println(green + "foolish hero you can't use a skill you don't have the stamina for." + reset);
                    }

                }
                else if(moveNum==3)
                {
                    if(stamina>=2)
                    {
                        System.out.println(green + "Oh Hero are you scared?" + red + "STOP HEALING AND GET KILLING!" + reset);
                        System.out.println("You healed 5 hp!");
                    }
                    else{
                        System.out.println(green + "foolish hero you can't use a skill you don't have the stamina for." + reset);
                    }
                }
                else {
                    System.out.println("You did nothing... it was not very effective!");
                }
                newGame.wait(1000);
                newGame.changeStamina();
                if(!inBattle)
                {
                    int enemyDMG = newGame.enemyMove();
                    System.out.println("\n"+enemy + " does " + red + enemyDMG +" damage!\n" + reset);
                }
            }
            count++;
            newGame.resetHP();

        }



    }
}