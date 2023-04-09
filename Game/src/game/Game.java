package game;
/*
Name: Phong Tran
Project : Game
Description : This game is just a more convoluted version of rock/paper/scissor against the computer

                                    Game Details
- There are 5 different classes each with their own set of moves available
- The 5 classes are Mercenary, Paladin, Runeslayer, Shade, Spellcaster
- Player will pick a class and the computer will randomly pick another class different from player
- The 5 moves available are Physical, Range, Magic, Defense, and Counter
- Both player start with 100 Health Points (HP)
- Physical  beat    Defense and Magic      (loser lose 10 HP)
- Range     beat    Physical and Counter   (loser lose 10 HP)
- Magic     beat    Range and Counter      (loser lose 10 HP)
- Counter   beat    Physical and Defense   (winner gain 5 HP  and loser lose 5 HP)
- Defense   beat    Range and Magic        (loser cannot act next turn)
- Physical  vs      Physical               (tie and both player lose 5 HP)
- Magic     vs      Magic                  (tie and both player lose 10 HP)
- Range     vs      Range                  (computer randomly choose who win and loser lose 10 HP)
- Defense   vs      Defense                (nothing happen)
- Counter   vs      Counter                (nothing happen)
*/
import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        boolean newGame = true;
        while(newGame == true){
        //prompt user for name
        System.out.print("Enter your name: ");
        Scanner input = new Scanner(System.in);
        String Name = input.nextLine();
        //display the rules and available classes
        displayRules();
        displayClasses();
        int cpuClass;
        int playerClass = 0;
        //prompt user to pick a class
        System.out.print("Enter a number between 1-5 to pick your class >> ");
        try{
            playerClass = input.nextInt();
            //check if user input is valid
            while(playerClass < 1 || playerClass > 5){
                System.out.print("Invalid input, try again >> ");
                playerClass = input.nextInt();
            }
        }
        catch(Exception e){
            System.out.println("Error has occur, invalid input detected");
        }
        //create player class
        Class player = new Class(Name,playerClass);
        //generate random cpu class
        cpuClass = (int)(Math.random() * 5) + 1;
        //randomize if player class is the same as cpu class
        while(cpuClass == playerClass){
            cpuClass = (int)(Math.random() * 5) + 1;
        }
        //create CPU class
        Class cpu = new Class("CPU",cpuClass);
        System.out.println();
        //keep track of turn and who was blocked
        int turnCounter = 1;
        int blockCounter = 0;
        int intPlayerMove = 0;
        int intCpuMove;
        //run game until player or cpu run out of HP
        while(player.HP > 0 && cpu.HP > 0){
            printLine();
            System.out.println("\t\t\t\t turn : " + turnCounter);
            //print cpu's HP bar
            System.out.println(cpu.Name + "'s HP" + "(" + cpu.className + ")");
            for (int i = cpu.HP; i > 0;i--){
                System.out.print("|");
            }
            System.out.print("\n\n\n");
            //print player's HP bar
            System.out.println(player.Name + "'s HP" + "(" + player.className + ")");
            for (int i = player.HP;i > 0; i--){
                System.out.print("|");
            }
            System.out.println();
            printLine();
            //display available moves
            displayMoves(player);
            System.out.println();
            //prompt user for choice
            System.out.print("What will you do? >> ");
            //if player wasn't blocked
            if(player.blocked == false){
                //get move input from player
                try{
                intPlayerMove = input.nextInt();
                while(intPlayerMove < 1 || intPlayerMove > 4){
                    System.out.print("Invalid input,try again >> ");
                    intPlayerMove = input.nextInt();
                }
                }
                catch(Exception e){
                    System.out.print("Error has occured, invalid input detected");
                }
            }
            //if player cannot act
            else{
                intPlayerMove = 0;
                pressAnyKey();
                }
            //auto generate random move for cpu if cpu is not blocked
            if(cpu.blocked == false){
                intCpuMove = (int)(Math.random() * 4) + 1;
            }
            //if cpu cannot act
            else{
                intCpuMove = 0;
            }
            System.out.println(moveInteraction(player,cpu,intPlayerMove,intCpuMove));
            turnCounter++;
            //keeping track of blocking turns

            if(blockCounter > 0){
                blockCounter--;
                if(blockCounter == 0){
                    player.blocked = false;
                    cpu.blocked = false;
                }
            }
            if(player.blocked == true || cpu.blocked == true){
                blockCounter = 1;
            }
        }
        printLine();
        if(cpu.HP < 0 || cpu.HP == 0){
            System.out.println("Congratulation, you won!");
        }
        else{
            System.out.println("You lose! Better luck next time!");
        }
        System.out.print("Enter Z to quit or any key to start new game >> ");
        char restart = input.next().charAt(0);
        if(restart == 'Z'){
            newGame = false;
        }
    }
    }
    //this method display the rules
    public static void displayRules(){
        System.out.println("Combat rules: ");
        System.out.println(" Physical ---> Defense and Magic"
                + "\n Defense ---> Magic and Range"
                + "\n Magic --->Range and Counter"
                + "\n Range ---> Counter and Physical"
                + "\n Counter ---> Physical and Defense");
        System.out.println();
    }
    //this method display the class and their available moveset
    public static void displayClasses(){
        System.out.println("Classes: ");
        System.out.println("1. Mercenary : Physical, Range, Counter, Defense");
        System.out.println("2. Paladin : Physical, Counter, Magic, Defense");
        System.out.println("3. Runeslayer : Counter, Range, Magic, Defense");
        System.out.println("4. Shade : Physical, Range, Magic, Counter");
        System.out.println("5. Spellcaster : Physical, Range, Magic, Defense");
        System.out.println();
    }
    //this method display the moves available to the player
    public static void displayMoves(Class player){
        System.out.println("Your moves :");
        if(player.blocked == false){
        System.out.println("1: " + player.moveset[1].moveName + "\t" + "2: " + player.moveset[2].moveName);
        System.out.println();
        System.out.println("3: " + player.moveset[3].moveName + "\t" + "4: " + player.moveset[4].moveName);
        }
        else{
            System.out.println("You cannot act this turn!");
        }
    }
    //this method display the moves available to the player if they were blocked
    public static void displayNoMoves(Class player){
        System.out.println("Your moves:");
        System.out.println("1: " + player.moveset[0].moveName);
    }
    //this method display a line of dash
    public static void printLine(){
        System.out.println("----------------------------------------------------------------------------------------------------");
    }
    //this method lete user press any key to continue
    public static void pressAnyKey(){
        System.out.print("Press Enter key to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
    }
    //this method display the interaction between the move that player and cpu chose
    public static String moveInteraction(Class player,Class cpu,int intPlayerMove,int intCpuMove){
        String result = "";
        printLine();
        switch(player.moveset[intPlayerMove].moveName){
            //when player choose physical
            case "Physical" :
                //print player action
                System.out.println(player.Name + " launched a physical attack!");
                //when cpu choose physical
                if(cpu.moveset[intCpuMove].moveName == "Physical"){
                    //both player and cpu lose 10 HP
                    player.HP = player.HP - 5;
                    cpu.HP = cpu.HP - 5;
                    //print cpu action
                    System.out.println(cpu.Name + " launched a physical attack!");
                    result = player.getName() + " and " + cpu.getName() + " crossed blade"
                            + "\nIt is a tie, both player take 5 damage!";
                }
                //when cpu choose range
                else if(cpu.moveset[intCpuMove].moveName == "Range"){
                    //player lose 10 HP
                    player.HP = player.HP - 10;
                    System.out.println(cpu.Name + " launched a range attack!");
                    result = cpu.Name + " shot at " + player.Name + " and ran away"
                            + "\n" + cpu.Name + " won! " + player.Name + " take 10 damage!";
                }
                //when cpu choose defense
                else if(cpu.moveset[intCpuMove].moveName == "Defense"){
                    //cpu lose 10 HP
                    cpu.HP = cpu.HP - 10;
                    System.out.println(cpu.Name + " prepared to block an attack!");
                    result = player.Name + " smashed through " + cpu.Name + "'s defense"
                            + "\n" + player.Name + " won! " + cpu.Name + " take 10 damage!";
                }
                //when cpu choose counter
                else if(cpu.moveset[intCpuMove].moveName == "Counter"){
                    //player lose 5 HP cpu gain 5 HP
                    player.HP = player.HP - 5;
                    cpu.HP = cpu.HP + 5;
                    System.out.println(cpu.Name + " prepared to launch a counter attack!");
                    result = player.Name + " fell for a taunt, " + cpu.Name + " launched a successful counter attack!"
                            + "\n" + cpu.Name + " won! " + player.Name + " lose 5 HP! " + cpu.Name + " gain 5 HP!"; 
                }
                //when cpu choose magic
                else if(cpu.moveset[intCpuMove].moveName == "Magic"){
                    //cpu lose 10 HP
                    cpu.HP = cpu.HP - 10;
                    System.out.println(cpu.Name + " launched a magical attack!");
                    result = player.Name + " striked before " + cpu.Name + " finish chanting the spell!"
                            + "\n" + player.Name + " won! " + cpu.Name + " lose 10 HP!";
                }
                //when cpu cannot act
                else if(cpu.moveset[intCpuMove].moveName == "cannot act"){
                    //cpu lose 10 HP
                    cpu.HP = cpu.HP - 10;
                    System.out.println(cpu.Name + " is wide open");
                    result = player.Name + " savagely beat " + cpu.Name + "!\n"
                            + player.Name + " won! " + cpu.Name + " lose 10 HP!";
                }
                break;
            //when player choose range
            case "Range" :
                //print player action
                System.out.println(player.Name + " launched a range attack!");
                //when cpu choose physical
                if(cpu.moveset[intCpuMove].moveName == "Physical"){
                    //cpu lose 10 HP
                    cpu.HP = cpu.HP - 10;
                    //print cpu action
                    System.out.println(cpu.Name + " launched a physical attack!");
                    result = player.Name + " shot at " + cpu.Name + " and ran away"
                            + "\n" + player.Name + " won! " + cpu.Name + " take 10 damage!";
                }
                //when cpu choose range
                else if(cpu.moveset[intCpuMove].moveName == "Range"){
                    System.out.println(cpu.Name + " launched a range attack!");
                    //randomly decide who win
                    int luck = (int)(Math.random());
                    if (luck == 0){
                        player.HP = player.HP - 10;
                        result = cpu.Name + " won the duel"
                                + "\n" + player.Name + " lose 10 HP";
                    }
                    else{
                        cpu.HP = cpu.HP - 10;
                        result = player.Name + " won the duel"
                                + "\n" + cpu.Name + " lose 10 HP";
                    }
                }
                //when cpu choose defense
                else if(cpu.moveset[intCpuMove].moveName == "Defense"){
                    //player lose a turn
                    player.blocked = true;
                    System.out.println(cpu.Name + " prepared to block an attack!");
                    result = cpu.Name + " blocked " + player.Name + "'s range attack!"
                            + "\n" + cpu.Name + " won! " + player.Name + " run out of ammo and lost a turn!";
                }
                //when cpu choose counter
                else if(cpu.moveset[intCpuMove].moveName == "Counter"){
                    //cpu lose 10 HP
                    cpu.HP = cpu.HP - 10;
                    System.out.println(cpu.Name + " prepared to launch a counter attack!");
                    result = "Range attack cannot be counter"
                            + "\n" + player.Name + " won! " + cpu.Name + " lose 10 HP!";
                }
                //when cpu choose magic
                else if(cpu.moveset[intCpuMove].moveName == "Magic"){
                    //player lose 10 HP
                    player.HP = player.HP - 10;
                    System.out.println(cpu.Name + " launched a magical attack!");
                    result = cpu.Name + " casted a spell before " + player.Name + " could aim"
                            + "\n" + cpu.Name + " won! " + player.Name + " lose 10 HP!";
                }
                //when cpu cannot act
                else if(cpu.moveset[intCpuMove].moveName == "cannot act"){
                    //cpu lose 10 HP
                    cpu.HP = cpu.HP - 10;
                    System.out.println(cpu.Name + " is wide open");
                    result = player.Name + " savagely shot " + cpu.Name + "!\n"
                            + player.Name + " won! " + cpu.Name + " lose 10 HP!";
                }
                break;
            //when player choose magic
            case "Magic" :
                //print player action
                System.out.println(player.Name + " launched a magical attack!");
                //when cpu choose physical
                if(cpu.moveset[intCpuMove].moveName == "Physical"){
                    //player lose 10 HP
                    player.HP = player.HP - 10;
                    System.out.println(cpu.Name + " launched a physical attack!");
                    result = cpu.Name + " striked before " + player.Name + " finish chanting the spell!"
                            + "\n" + cpu.Name + " won! " + player.Name + " lose 10 HP!";
                }
                //when cpu choose range
                else if(cpu.moveset[intCpuMove].moveName == "Range"){
                    cpu.HP = cpu.HP - 10;
                    System.out.println(cpu.Name + " launched a range attack!");
                    result = player.Name + " casted a spell before " + cpu.Name + " could aim"
                            + "\n" + player.Name + " won! " + cpu.Name + " lose 10 HP!";
                }
                //when cpu choose defense
                else if(cpu.moveset[intCpuMove].moveName == "Defense"){
                    //player lose a turn
                    player.blocked = true;
                    System.out.println(cpu.Name + " prepared to block an attack!");
                    result = cpu.Name + " blocked " + player.Name + "'s magic attack!"
                            + "\n" + cpu.Name + " won! " + player.Name + " run out of mana and lost a turn!";
                }
                //when cpu choose counter
                else if(cpu.moveset[intCpuMove].moveName == "Counter"){
                    //cpu lose 10 HP
                    cpu.HP = cpu.HP - 10;
                    System.out.println(cpu.Name + " prepared to launch a counter attack!");
                    result = "Magic attack cannot be counter"
                            + "\n" + player.Name + " won! " + cpu.Name + " lose 10 HP!";
                }
                //when cpu choose magic
                else if(cpu.moveset[intCpuMove].moveName == "Magic"){
                    //player lose 10 HP
                    player.HP = player.HP - 10;
                    cpu.HP = cpu.HP - 10;
                    System.out.println(cpu.Name + " launched a magical attack!");
                    result = cpu.Name + "'s and " + player.Name + "'s magic attack resulted in an explosion!"
                            + "\nIt is a tie! both player lose 10 HP!";
                }
                //when cpu cannot act
                else if(cpu.moveset[intCpuMove].moveName == "cannot act"){
                    //cpu lose 10 HP
                    cpu.HP = cpu.HP - 10;
                    System.out.println(cpu.Name + " is wide open");
                    result = player.Name + " savagely blasted " + cpu.Name +" with a spell!" 
                            + "\n" + player.Name + " won! " + cpu.Name + " lose 10 HP!";
                }
                break;
            //player choose defense
            case "Defense" :
                //print player action
                System.out.println(player.Name + " prepared to block an attack!");
                //when cpu choose physical
                if(cpu.moveset[intCpuMove].moveName == "Physical"){
                    //player lose 10 HP
                    player.HP = player.HP - 10;
                    System.out.println(cpu.Name + " launched a physical attack!");
                    result = cpu.Name + " smashed through " + player.Name + "'s defense"
                            + "\n" + cpu.Name + " won! " + player.Name + " take 10 damage!";
                }
                //when cpu choose range
                else if(cpu.moveset[intCpuMove].moveName == "Range"){
                    //cpu lose a turn
                    cpu.blocked = true;
                    System.out.println(cpu.Name + " launched a range attack!");
                    result = player.Name + " blocked " + cpu.Name + "'s range attack!"
                            + "\n"+ player.Name + " won! " + cpu.Name + " is out of ammo and lost a turn!";
                }
                //when cpu choose defense
                else if(cpu.moveset[intCpuMove].moveName == "Defense"){
                    //nothing happen
                    System.out.println(cpu.Name + " prepared to block an attack!");
                    result = player.Name + " and " + cpu.Name + " stare awkwardly at each other"
                            + "\n It is a tie! nothing happen!";
                }
                //when cpu choose counter
                else if(cpu.moveset[intCpuMove].moveName == "Counter"){
                    //player lose 5 HP cpu gain 5 HP
                    player.HP = player.HP - 5;
                    cpu.HP = cpu.HP + 5;
                    System.out.println(cpu.Name + " prepared to launch a counter attack!");
                    result = player.Name + " fell for a feint attack, " + cpu.Name + " launched a successful counter attack!"
                             + "\n" + cpu.Name + " won! " + player.Name + " lose 5 HP! " + cpu.Name + " gain 5 HP!"; 
                }
                //when cpu choose magic
                else if(cpu.moveset[intCpuMove].moveName == "Magic"){
                    //cpu lose a turn
                    cpu.blocked = true;
                    System.out.println(cpu.Name + " launched a magical attack!");
                    result = player.Name + " blocked " + cpu.Name + "'s magic attack!"
                            + "\n" + player.Name + " won! " + cpu.Name + " run out of mana and lost a turn!";
                }
                //when cpu cannot act
                else if(cpu.moveset[intCpuMove].moveName == "cannot act"){
                    //nothing happen
                    System.out.println(cpu.Name + " is wide open");
                    result = player.Name + " merficully spared " + cpu.Name
                            + "\nIt is a tie! nothing happen!";
                }
                break;
            //when player choose counter
            case "Counter" :
                //print player action
                System.out.println(player.Name + " prepare to launch a counter attack!");
                //when cpu choose physical
                if(cpu.moveset[intCpuMove].moveName == "Physical"){
                    //player gain 5 HP cpu lose 5 HP
                    player.HP = player.HP + 5;
                    cpu.HP = cpu.HP - 5;
                    System.out.println(cpu.Name + " launched a physical attack!");
                    result = cpu.Name + " fell for a taunt, " + player.Name + " launched a successful counter attack!"
                            + "\n" + player.Name + " won! " + cpu.Name + " lose 5 HP! " + player.Name + " gain 5 HP!";
                }
                //when cpu choose range
                else if(cpu.moveset[intCpuMove].moveName == "Range"){
                    //player lose 10 HP
                    player.HP = player.HP - 10;
                    System.out.println(cpu.Name + " launched a range attack!");
                    result = "Range attack cannot be counter"
                            + "\n" + cpu.Name + " won! " + player.Name + " lose 10 HP!";
                }
                //when cpu choose defense
                else if(cpu.moveset[intCpuMove].moveName == "Defense"){
                    //player gain 5 HP cpu lose 5 HP
                    player.HP = player.HP + 5;
                    cpu.HP = cpu.HP - 5;
                    System.out.println(cpu.Name + " prepared to block an attack!");
                    result = cpu.Name + " fell for a feint attack, " + player.Name + " launched a successful counter attack!"
                             + "\n" + player.Name + " won! " + cpu.Name + " lose 5 HP! " + player.Name + " gain 5 HP!";
                }
                //when cpu choose counter
                else if(cpu.moveset[intCpuMove].moveName == "Counter"){
                    System.out.println(cpu.Name + " prepared to launch a counter attack!");
                    result = player.Name + " and " + cpu.Name + " wait for each other to make the first move!"
                            + "\n It is a tie! nothing happen!";
                }
                //when cpu choose magic
                else if(cpu.moveset[intCpuMove].moveName == "Magic"){
                    //player lose 10 HP
                    player.HP = player.HP - 10;
                    System.out.println(cpu.Name + " launched a magical attack!");
                    result = "Magic attack cannot be counter"
                            + "\n" + cpu.Name + " won! " + player.Name + " lose 10 HP!";
                }
                //when cpu cannot act
                else if(cpu.moveset[intCpuMove].moveName == "cannot act"){
                    //nothing happen
                    System.out.println(cpu.Name + " is wide open");
                    result = player.Name + " merficully spared " + cpu.Name
                            + "\nIt is a tie! nothing happen!";
                }
                break;
            //player cannot act
            case "cannot act" :
                System.out.println(player.Name + " is wide open!");
                //when cpu choose physical
                if(cpu.moveset[intCpuMove].moveName == "Physical"){
                    //player lose 10 HP
                    player.HP = player.HP - 10;
                    System.out.println(cpu.Name + " launched a physical attack!");
                    result = cpu.Name + " savagely beat " + player.Name + "!\n"
                            + cpu.Name + " won! " + player.Name + " lose 10 HP!";
                }
                //when cpu choose range
                else if(cpu.moveset[intCpuMove].moveName == "Range"){
                    //player lose 10 HP
                    player.HP = player.HP - 10;
                    System.out.println(cpu.Name + " launched a range attack!");
                    result = cpu.Name + " savagely shot " + player.Name + "!\n"
                            + cpu.Name + " won! " + player.Name + " lose 10 HP!";
                }
                //when cpu choose defense
                else if(cpu.moveset[intCpuMove].moveName == "Defense"){
                    //nothing happen
                    System.out.println(cpu.Name + " prepared to block an attack!");
                    result = cpu.Name + " merficully spared " + player.Name
                            + "\nIt is a tie! nothing happen!";
                }
                //when cpu choose counter
                else if(cpu.moveset[intCpuMove].moveName == "Counter"){
                    //nothing happen
                    System.out.println(cpu.Name + " prepared to launch a counter attack!");
                    result = cpu.Name + " merficully spared " + player.Name
                            + "\nIt is a tie! nothing happen!";
                }
                //when cpu choose magic
                else if(cpu.moveset[intCpuMove].moveName == "Magic"){
                    //player lose 10 HP
                    player.HP = player.HP - 10;
                    System.out.println(cpu.Name + " launched a magical attack!");
                    result = cpu.Name + " savagely blasted " + player.Name +" with a spell!" 
                            + "\n" + cpu.Name + " won! " + player.Name + " lose 10 HP!";
                }
                break;
        }
        return result;
    }
}