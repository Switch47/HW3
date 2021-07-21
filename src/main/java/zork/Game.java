package zork;

import zork.commands.Command;
import zork.object.Player;
import zork.object.Room;
import zork.object.item.Item;
import zork.object.item.weapons.Weapon;
import zork.object.levels.Level;
import zork.object.levels.Map;
import zork.object.monster.Monster;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {

    private static boolean gameStatus;

    public static final boolean PLAY_STATUS = false;

    public static final boolean HOME_STATUS = true;

    public static Player player;

    private GameOutput output = new GameOutput();

    private CommandParser commandParser = new CommandParser();

    public static Room currentRoom;

    public static Level currentLevel;

    public static List<Level> listOfLevels = new ArrayList<Level>();

    public Game() {
    }

    public void run() throws IOException {
        setStartMenu();
        useCommandMenu();
    }


    public void setMap() {
        Map level1 = new Map();
        Map level2 = new Map();
        Map level3 = new Map();
        level1.loadMap("C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Map//ATutorial.txt");
        level2.loadMap("C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Map//EvilHouse.txt");
        level3.loadMap("C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Map//Bloodborne.txt");
        listOfLevels.add(level1);
        listOfLevels.add(level2);
        listOfLevels.add(level3);
    }

    public void useCommandMenu() throws IOException {
        while (true) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            List<String> words = commandParser.parse(s);
            Command command = CommandFactory.get(words.get(0));
            if (command != null) {
                command.execute(this, words.subList(1, words.size()));
            } else {
                System.out.println("Unknown command [" + s + "].");
            }
        }
    }


    public void inGame(Level level) throws IOException {
        currentLevel = level;
        System.out.println("Welcome to " + level.name);
        System.out.println("***PLEASE READ***");
        System.out.println("Your objective is to " + level.objective);
        System.out.println("TYPE [ help ] : to print all command!!");
        currentRoom = level.startRoom;
        System.out.println("Now you are in " + currentRoom.name);
//        useCommandInGame(level);
        while (true) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            List<String> words = commandParser.parse(s);
            Command command = CommandFactory.get(words.get(0));
            if (command != null) {
                command.execute(this, words.subList(1, words.size()));
            } else {
                System.out.println("Unknown command [" + s + "].");
            }
            if (level.objectiveCompleted()) {
                System.out.println("---------------------------");
                System.out.println("!!!!Objective completed!!!!");
                System.out.println("  !!Go back to the menu!!  ");
                System.out.println("---------------------------");
                setStartMenu();
                listOfLevels = new ArrayList<>();
            }
        }
    }

        public void takeItem (Item it){
            if (it instanceof Weapon) {
                if (player.weapon == null) {
                    player.pickWeapon(it);
                    System.out.println("you receive [ " + it.name + " ].");
                    currentRoom.items.remove(it);
                } else {
                    System.out.println("You must drop current weapon first.");
                }
            } else {
                player.takeItems(it);
                System.out.println("you receive [ " + it.name + " ].");
                currentRoom.items.remove(it);
            }
        }

        public static boolean getGameStatus () {
            return gameStatus;
        }

        public void setStartPlay () {
            gameStatus = PLAY_STATUS;
        }

        public void setStartMenu () {
            System.out.println("!!!Welcome to Zork Game!!!");
            System.out.println("          [play]          ");
            System.out.println("          [load]          ");
            System.out.println("          [help]          ");
            System.out.println("          [exit]          ");
            gameStatus = HOME_STATUS;
        }

        public GameOutput getOutput () {
            return output;
        }

        public void exit () {
            System.exit(0);
        }

    public boolean fightAuto (Monster monster,Scanner in){
        boolean fightState = true;
        while (true) {
            if (player.weapon != null) {
                if (player.agility + player.weapon.agility >= monster.agility) {
                    System.out.println("You are faster than " + monster.name);
                    if (player.alive == true) {
                        System.out.println("------------YOUR TURN-----------");
                        System.out.println("Player HP : " + player.HP + " / " + player.MAX_HP);
                        System.out.println("--------------------------------");
                        System.out.println("------###Monster Detail###------");
                        System.out.println("Name : " + monster.name);
                        System.out.println("Monster HP : " + monster.HP + " / " + monster.MAX_HP);
                        System.out.println("--------------------------------");
                        System.out.println("ACTION : [ attack ] [ inventory ]");
                        String command = in.nextLine();
                        if (command.equals("attack")) {
                            int totalAttackPower = (player.strength + player.weapon.strength);
                            monster.decreaseHP(totalAttackPower);
                            fightState = !fightState;
                        } else if (command.equals("inventory")) {
                            if (player.items.size() > 0) {
                                String itemName = "[ INVENTORY ] -> [ " + player.items.get(0).name;
                                for (int i = 1; i < player.items.size(); i++) {
                                    itemName += " , " + player.items.get(i).name;
                                }
                                System.out.println(itemName + " ]");
                                String useItemCommand = in.nextLine();
                                for (Item item : player.items) {
                                    if (useItemCommand.equals("use " + item.name)) {
                                        System.out.println(item.skill);
                                        this.player.increaseHP(item.addHP);
                                        player.items.remove(item);
                                        fightState = !fightState;
                                        break;
                                    } else if (!useItemCommand.equals("use")) {
                                        System.out.println("TYPE -> [ use 'item name' ]");
                                    } else {
                                        System.out.println("There is no [ " + useItemCommand.split("use ")[0] + " ] in your inventory.");
                                    }
                                }
                            } else {
                                System.out.println("[ INVENTORY ] -> Empty");
                            }
                        } else {
                            System.out.println("!!Unknown Command!!");
                            System.out.println("Your Attack Misses!!!");
                        }
                    } else {
                        return false;
                    }
                    if (monster.alive == true) {
                        System.out.println("------------" + monster.name.toUpperCase() + " TURN-----------");
                        monster.attack(player);
                        fightState = !fightState;
                    } else {
                        System.out.println("*****" + monster.name.toUpperCase() + " was defeated*****");
                        return true;
                    }
                } else {
                    System.out.println(monster.name + " is faster than you.");
                    if (monster.alive == true) {
                        System.out.println("------------" + monster.name.toUpperCase() + " TURN-----------");
                        monster.attack(player);
                        fightState = !fightState;
                    } else {
                        System.out.println("*****" + monster.name.toUpperCase() + " was defeated*****");
                        return true;
                    }
                    if (player.alive == true) {
                        System.out.println("------------YOUR TURN-----------");
                        System.out.println("Player HP : " + player.HP + " / " + player.MAX_HP);
                        System.out.println("--------------------------------");
                        System.out.println("------###Monster Detail###------");
                        System.out.println("Name : " + monster.name);
                        System.out.println("Monster HP : " + monster.HP + " / " + monster.MAX_HP);
                        System.out.println("--------------------------------");
                        System.out.println("ACTION : [ attack ] [ inventory ]");
                        String command = in.nextLine();
                        if (command.equals("attack")) {
                            int totalAttackPower = (player.strength + player.weapon.strength);
                            monster.decreaseHP(totalAttackPower);
                            fightState = !fightState;
                        } else if (command.equals("inventory")) {
                            if (player.items.size() > 0) {
                                String itemName = " [ INVENTORY ] -> " + player.items.get(0).name;
                                for (int i = 1; i < player.items.size(); i++) {
                                    itemName += " , " + player.items.get(i).name;
                                }
                                System.out.println(itemName + " ]");
                                String useItemCommand = in.nextLine();
                                for (Item item : player.items) {
                                    if (useItemCommand.equals("use " + item.name)) {
                                        System.out.println(item.skill);
                                        player.increaseHP(item.addHP);
                                        player.items.remove(item);
                                        fightState = !fightState;
                                    } else if (!useItemCommand.equals("use ")) {
                                        System.out.println("TYPE -> [ use 'item name' ]");
                                    } else {
                                        System.out.println("There is no [ " + useItemCommand.split("use ")[0] + " ] in your inventory.");
                                    }
                                }
                            } else {
                                System.out.println("[ INVENTORY ] -> Empty");
                            }
                        } else {
                            System.out.println("!!Unknown Command!!");
                            System.out.println("Your Attack Misses!!!");
                        }
                    } else {
                        return false;
                    }
                }
            } else {
                if (player.agility >= monster.agility) {
                    System.out.println("You are faster than " + monster.name);
                    if (player.alive == true) {
                        System.out.println("------------YOUR TURN-----------");
                        System.out.println("Player HP : " + player.HP + " / " + player.MAX_HP);
                        System.out.println("--------------------------------");
                        System.out.println("------###Monster Detail###------");
                        System.out.println("Name : " + monster.name);
                        System.out.println("Monster HP : " + monster.HP + " / " + monster.MAX_HP);
                        System.out.println("--------------------------------");
                        System.out.println("ACTION : [ attack ] [ inventory ]");
                        String command = in.nextLine();
                        if (command.equals("attack")) {
                            int totalAttackPower = (player.strength);
                            monster.decreaseHP(totalAttackPower);
                            fightState = !fightState;
                        } else if (command.equals("inventory")) {
                            if (player.items.size() > 0) {
                                String itemName = " [ INVENTORY ] -> " + player.items.get(0).name;
                                for (int i = 1; i < player.items.size(); i++) {
                                    itemName += " , " + player.items.get(i).name;
                                }
                                System.out.println(itemName + " ]");
                                String useItemCommand = in.nextLine();
                                for (Item item : player.items) {
                                    if (useItemCommand.equals("use " + item.name)) {
                                        System.out.println(item.skill);
                                        player.increaseHP(item.addHP);
                                        player.items.remove(item);
                                        fightState = !fightState;
                                    } else if (!useItemCommand.equals("use ")) {
                                        System.out.println("TYPE -> [ use 'item name' ]");
                                        ;
                                    } else {
                                        System.out.println("There is no [ " + useItemCommand.split("use ")[0] + " ] in your inventory.");
                                    }
                                }
                            } else {
                                System.out.println("[ INVENTORY ] -> Empty");
                            }
                        } else {
                            System.out.println("!!Unknown Command!!");
                            System.out.println("Your Attack Misses!!!");
                        }
                    } else {
                        return false;
                    }
                    if (monster.alive == true) {
                        System.out.println("------------" + monster.name.toUpperCase() + " TURN-----------");
                        monster.attack(player);
                        fightState = !fightState;
                    } else {
                        System.out.println("*****" + monster.name.toUpperCase() + " was defeated*****");
                        return true;
                    }
                } else {
                    System.out.println(monster.name + " is faster than you.");
                    if (monster.alive == true) {
                        System.out.println("------------" + monster.name.toUpperCase() + " TURN-----------");
                        monster.attack(player);
                        fightState = !fightState;
                    } else {
                        System.out.println("*****" + monster.name.toUpperCase() + " was defeated*****");
                        return true;
                    }
                    if (player.alive == true) {
                        System.out.println("------------YOUR TURN-----------");
                        System.out.println("Player HP : " + player.HP + " / " + player.MAX_HP);
                        System.out.println("--------------------------------");
                        System.out.println("------###Monster Detail###------");
                        System.out.println("Name : " + monster.name);
                        System.out.println("Monster HP : " + monster.HP + " / " + monster.MAX_HP);
                        System.out.println("--------------------------------");
                        System.out.println("ACTION : [ attack ] [ inventory ]");
                        String command = in.nextLine();
                        if (command.equals("attack")) {
                            int totalAttackPower = (player.strength + player.weapon.strength);
                            monster.decreaseHP(totalAttackPower);
                            fightState = !fightState;
                        } else if (command.equals("inventory")) {
                            if (player.items.size() > 0) {
                                String itemName = " [ INVENTORY ] -> " + player.items.get(0).name;
                                for (int i = 1; i < player.items.size(); i++) {
                                    itemName += " , " + player.items.get(i).name;
                                }
                                System.out.println(itemName + " ]");
                                String useItemCommand = in.nextLine();
                                for (Item item : player.items) {
                                    if (useItemCommand.equals("use " + item.name)) {
                                        System.out.println(item.skill);
                                        player.increaseHP(item.addHP);
                                        player.items.remove(item);
                                        fightState = !fightState;
                                    } else if (!useItemCommand.equals("use ")) {
                                        System.out.println("TYPE -> [ use 'item name' ]");
                                    } else {
                                        System.out.println("There is no [ " + useItemCommand.split("use ")[0] + " ] in your inventory.");
                                    }
                                }
                            } else {
                                System.out.println("[ INVENTORY ] -> Empty");
                            }
                        } else {
                            System.out.println("!!Unknown Command!!");
                            System.out.println("Your Attack Misses!!!");
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
    }


        public boolean fight (Monster monster){
            Scanner in = new Scanner(System.in);
            boolean fightState = true;
            while (true) {
                if (player.weapon != null) {
                    if (player.agility + player.weapon.agility >= monster.agility) {
                        System.out.println("You are faster than " + monster.name);
                        if (player.alive == true) {
                            System.out.println("------------YOUR TURN-----------");
                            System.out.println("Player HP : " + player.HP + " / " + player.MAX_HP);
                            System.out.println("--------------------------------");
                            System.out.println("------###Monster Detail###------");
                            System.out.println("Name : " + monster.name);
                            System.out.println("Monster HP : " + monster.HP + " / " + monster.MAX_HP);
                            System.out.println("--------------------------------");
                            System.out.println("ACTION : [ attack ] [ inventory ]");
                            String command = in.nextLine();
                            if (command.equals("attack")) {
                                int totalAttackPower = (player.strength + player.weapon.strength);
                                monster.decreaseHP(totalAttackPower);
                                fightState = !fightState;
                            } else if (command.equals("inventory")) {
                                if (player.items.size() > 0) {
                                    String itemName = "[ INVENTORY ] -> [ " + player.items.get(0).name;
                                    for (int i = 1; i < player.items.size(); i++) {
                                        itemName += " , " + player.items.get(i).name;
                                    }
                                    System.out.println(itemName + " ]");
                                    String useItemCommand = in.nextLine();
                                    for (Item item : player.items) {
                                        if (useItemCommand.equals("use " + item.name)) {
                                            System.out.println(item.skill);
                                            this.player.increaseHP(item.addHP);
                                            player.items.remove(item);
                                            fightState = !fightState;
                                            break;
                                        } else if (!useItemCommand.equals("use")) {
                                            System.out.println("TYPE -> [ use 'item name' ]");
                                        } else {
                                            System.out.println("There is no [ " + useItemCommand.split("use ")[0] + " ] in your inventory.");
                                        }
                                    }
                                } else {
                                    System.out.println("[ INVENTORY ] -> Empty");
                                }
                            } else {
                                System.out.println("!!Unknown Command!!");
                                System.out.println("Your Attack Misses!!!");
                            }
                        } else {
                            return false;
                        }
                        if (monster.alive == true) {
                            System.out.println("------------" + monster.name.toUpperCase() + " TURN-----------");
                            monster.attack(player);
                            fightState = !fightState;
                        } else {
                            System.out.println("*****" + monster.name.toUpperCase() + " was defeated*****");
                            return true;
                        }
                    } else {
                        System.out.println(monster.name + " is faster than you.");
                        if (monster.alive == true) {
                            System.out.println("------------" + monster.name.toUpperCase() + " TURN-----------");
                            monster.attack(player);
                            fightState = !fightState;
                        } else {
                            System.out.println("*****" + monster.name.toUpperCase() + " was defeated*****");
                            return true;
                        }
                        if (player.alive == true) {
                            System.out.println("------------YOUR TURN-----------");
                            System.out.println("Player HP : " + player.HP + " / " + player.MAX_HP);
                            System.out.println("--------------------------------");
                            System.out.println("------###Monster Detail###------");
                            System.out.println("Name : " + monster.name);
                            System.out.println("Monster HP : " + monster.HP + " / " + monster.MAX_HP);
                            System.out.println("--------------------------------");
                            System.out.println("ACTION : [ attack ] [ inventory ]");
                            String command = in.nextLine();
                            if (command.equals("attack")) {
                                int totalAttackPower = (player.strength + player.weapon.strength);
                                monster.decreaseHP(totalAttackPower);
                                fightState = !fightState;
                            } else if (command.equals("inventory")) {
                                if (player.items.size() > 0) {
                                    String itemName = " [ INVENTORY ] -> " + player.items.get(0).name;
                                    for (int i = 1; i < player.items.size(); i++) {
                                        itemName += " , " + player.items.get(i).name;
                                    }
                                    System.out.println(itemName + " ]");
                                    String useItemCommand = in.nextLine();
                                    for (Item item : player.items) {
                                        if (useItemCommand.equals("use " + item.name)) {
                                            System.out.println(item.skill);
                                            player.increaseHP(item.addHP);
                                            player.items.remove(item);
                                            fightState = !fightState;
                                        } else if (!useItemCommand.equals("use ")) {
                                            System.out.println("TYPE -> [ use 'item name' ]");
                                        } else {
                                            System.out.println("There is no [ " + useItemCommand.split("use ")[0] + " ] in your inventory.");
                                        }
                                    }
                                } else {
                                    System.out.println("[ INVENTORY ] -> Empty");
                                }
                            } else {
                                System.out.println("!!Unknown Command!!");
                                System.out.println("Your Attack Misses!!!");
                            }
                        } else {
                            return false;
                        }
                    }
                } else {
                    if (player.agility >= monster.agility) {
                        System.out.println("You are faster than " + monster.name);
                        if (player.alive == true) {
                            System.out.println("------------YOUR TURN-----------");
                            System.out.println("Player HP : " + player.HP + " / " + player.MAX_HP);
                            System.out.println("--------------------------------");
                            System.out.println("------###Monster Detail###------");
                            System.out.println("Name : " + monster.name);
                            System.out.println("Monster HP : " + monster.HP + " / " + monster.MAX_HP);
                            System.out.println("--------------------------------");
                            System.out.println("ACTION : [ attack ] [ inventory ]");
                            String command = in.nextLine();
                            if (command.equals("attack")) {
                                int totalAttackPower = (player.strength);
                                monster.decreaseHP(totalAttackPower);
                                fightState = !fightState;
                            } else if (command.equals("inventory")) {
                                if (player.items.size() > 0) {
                                    String itemName = " [ INVENTORY ] -> " + player.items.get(0).name;
                                    for (int i = 1; i < player.items.size(); i++) {
                                        itemName += " , " + player.items.get(i).name;
                                    }
                                    System.out.println(itemName + " ]");
                                    String useItemCommand = in.nextLine();
                                    for (Item item : player.items) {
                                        if (useItemCommand.equals("use " + item.name)) {
                                            System.out.println(item.skill);
                                            player.increaseHP(item.addHP);
                                            player.items.remove(item);
                                            fightState = !fightState;
                                        } else if (!useItemCommand.equals("use ")) {
                                            System.out.println("TYPE -> [ use 'item name' ]");
                                            ;
                                        } else {
                                            System.out.println("There is no [ " + useItemCommand.split("use ")[0] + " ] in your inventory.");
                                        }
                                    }
                                } else {
                                    System.out.println("[ INVENTORY ] -> Empty");
                                }
                            } else {
                                System.out.println("!!Unknown Command!!");
                                System.out.println("Your Attack Misses!!!");
                            }
                        } else {
                            return false;
                        }
                        if (monster.alive == true) {
                            System.out.println("------------" + monster.name.toUpperCase() + " TURN-----------");
                            monster.attack(player);
                            fightState = !fightState;
                        } else {
                            System.out.println("*****" + monster.name.toUpperCase() + " was defeated*****");
                            return true;
                        }
                    } else {
                        System.out.println(monster.name + " is faster than you.");
                        if (monster.alive == true) {
                            System.out.println("------------" + monster.name.toUpperCase() + " TURN-----------");
                            monster.attack(player);
                            fightState = !fightState;
                        } else {
                            System.out.println("*****" + monster.name.toUpperCase() + " was defeated*****");
                            return true;
                        }
                        if (player.alive == true) {
                            System.out.println("------------YOUR TURN-----------");
                            System.out.println("Player HP : " + player.HP + " / " + player.MAX_HP);
                            System.out.println("--------------------------------");
                            System.out.println("------###Monster Detail###------");
                            System.out.println("Name : " + monster.name);
                            System.out.println("Monster HP : " + monster.HP + " / " + monster.MAX_HP);
                            System.out.println("--------------------------------");
                            System.out.println("ACTION : [ attack ] [ inventory ]");
                            String command = in.nextLine();
                            if (command.equals("attack")) {
                                int totalAttackPower = (player.strength + player.weapon.strength);
                                monster.decreaseHP(totalAttackPower);
                                fightState = !fightState;
                            } else if (command.equals("inventory")) {
                                if (player.items.size() > 0) {
                                    String itemName = " [ INVENTORY ] -> " + player.items.get(0).name;
                                    for (int i = 1; i < player.items.size(); i++) {
                                        itemName += " , " + player.items.get(i).name;
                                    }
                                    System.out.println(itemName + " ]");
                                    String useItemCommand = in.nextLine();
                                    for (Item item : player.items) {
                                        if (useItemCommand.equals("use " + item.name)) {
                                            System.out.println(item.skill);
                                            player.increaseHP(item.addHP);
                                            player.items.remove(item);
                                            fightState = !fightState;
                                        } else if (!useItemCommand.equals("use ")) {
                                            System.out.println("TYPE -> [ use 'item name' ]");
                                        } else {
                                            System.out.println("There is no [ " + useItemCommand.split("use ")[0] + " ] in your inventory.");
                                        }
                                    }
                                } else {
                                    System.out.println("[ INVENTORY ] -> Empty");
                                }
                            } else {
                                System.out.println("!!Unknown Command!!");
                                System.out.println("Your Attack Misses!!!");
                            }
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
    }
