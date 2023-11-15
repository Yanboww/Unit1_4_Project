import java.util.Scanner;
public class DungeonRunner {
    public static void main(String[] args) {
        //color ASNI codes for changing string color
        final String blue = "\u001B[34m";
        final String green = "\u001B[32m";
        final String reset = "\u001B[0m";
        //create new scanner object
        Scanner s = new Scanner(System.in);

        //Introduction to the game
        System.out.println(green + "Welcome nameless hero, will it be you who clear this dungeon?" + reset);
        System.out.print("Choose your class(type mage, warrior ,or paladin): ");
        String classChar = s.nextLine();
        Enemy enemyClass = new Enemy();
        DungeonConquestSim newGame = new DungeonConquestSim(classChar, enemyClass);
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
        //start of the game
        while (newGame.finale())
        {
            //spawns enemy
            String enemyPrompt = newGame.enemySpawn();
            System.out.println(enemyPrompt);
            while (newGame.battling())
            {
                int hp = newGame.returnHP();
                //player actions
                String gameStats = newGame.battleInformation();
                System.out.println(gameStats);
                System.out.print(newGame.playerChoice());
                String moveNumString = s.nextLine();
                int moveNum = Integer.parseInt(moveNumString);
                int dmg = newGame.useMove(moveNum);
                int stamina = newGame.returnStamina();
                System.out.println(newGame.moveMessage(moveNum,dmg,stamina,hp));
                newGame.wait(1000);
                //enemy move
                if(newGame.battling())
                {
                    String enemyMove = newGame.enemyMove();
                    System.out.println(enemyMove);
                }
            }
            newGame.changeCount();
            //add hp after each round of enemy if player still alive
            if (newGame.checkHP())
            {
                newGame.resetHP();
            }

        }

        //if the while loop is finished and hp is greater than 0 then boss fight starts
        if (newGame.checkHP())
        {
            newGame.changeCount();
            //boss speech
            String bossSpeech = newGame.bossMessage();
            System.out.println(bossSpeech);
            while(newGame.battling())
            {
                int hp = newGame.returnHP();
                String gameStats = newGame.battleInformation();
                System.out.println(gameStats);
                //player move
                System.out.print(newGame.playerChoice());
                String moveNumString = s.nextLine();
                int moveNum = Integer.parseInt(moveNumString);
                int dmg = newGame.useMove(moveNum);
                int stamina = newGame.returnStamina();
                System.out.println(newGame.moveMessageBoss(moveNum,dmg,stamina,hp));
                //boss move
                if(newGame.battling())
                {
                    //changes reply based on boss damage
                    String bossMove = newGame.bossMove();
                    System.out.println(bossMove);
                }
            }
        }
        //the end and prints a message depending on how the player ended the game.
        String message = newGame.endMessage(newGame.returnCount());
        System.out.println("\n"+message);

    }

}
