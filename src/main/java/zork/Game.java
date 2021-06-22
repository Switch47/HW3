package zork;

import zork.commands.Command;
import zork.object.Player;
import zork.object.Room;
import zork.object.item.Item;
import zork.object.levels.Level;
import zork.object.levels.Level1;
import zork.object.monster.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {

    public static Player player;

    private GameOutput output = new GameOutput();

    private CommandParser commandParser = new CommandParser();

    public static Room currentRoom;

    public static Level currentLevel;

    public static List<Level> listOfLevels = new ArrayList<Level>();

    public void run() {
        player = new Player();
        Level1 level1 = new Level1();
        listOfLevels.add(level1);

        for (Level level : listOfLevels) {
            currentLevel = level;
            System.out.println("Welcome to " + level.name);
            System.out.println("Your objective is to " + level.objective);
            currentRoom = level.startRoom;
            while (true) {
                Scanner in = new Scanner(System.in);
                String s = in.nextLine();
                List<String> words = commandParser.parse(s);
                Command command = CommandFactory.get(words.get(0));
                if (command != null) {
                    command.execute(this, words.subList(1, words.size()));
                }
                else {
                    System.out.println("Unknown command [" + s + "].");
                }
                if (level.objectiveCompleted()){
                    System.out.println("!!!!Objective completed!!!!");
                    this.exit();
                }
            }

        }
    }

    public GameOutput getOutput() {
        return output;
    }

    public void exit() {
        System.exit(0);
    }

    public boolean fight(Monster monster) {
        Scanner in = new Scanner(System.in);
        boolean fightState = true;
        while(true) {
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
                        System.out.println(player.HP);
                        String command = in.nextLine();
                        if (command.equals("attack")) {
                            int totalAttackPower = (player.strength + player.weapon.strength);
                            monster.decreaseHP(totalAttackPower);
                            System.out.println(monster.HP);
                            System.out.println(monster.alive);
                            fightState = !fightState;
                        }
                        else if (command.equals("inventory")) {
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
                                        player.increaseHP(item.addHP);
                                        player.items.remove(item);
                                        fightState = !fightState;
                                        break;
                                    } else if (!useItemCommand.equals("use ")) {
                                        System.out.println("There is no [ " + useItemCommand + " ] command.");
                                    } else {
                                        System.out.println("There is no [ " + useItemCommand.split("use ")[0] + " ] in your inventory.");
                                    }
                                }
                            }
                            else {
                                System.out.println("[ INVENTORY ] -> Empty");
                            }
                        }
                        else {
                            System.out.println("!!Unknown Command!!");
                        }
                    } else {
                        return false;
                    }
                    if (monster.alive == true) {
                        System.out.println("------------" + monster.name + " TURN-----------");
                        player.decreaseHP(monster.strength);
                        System.out.println("ACTION : Monster is attacking you.");
                        fightState = !fightState;
                    } else {
                        System.out.println(monster.name + " was defeated");
                        return true;
                    }
                }
                else {
                    System.out.println(monster.name + " is faster than you.");
                    if (monster.alive == true) {
                        System.out.println("------------" + monster.name + " TURN-----------");
                        player.decreaseHP(monster.strength);
                        System.out.println("ACTION : Monster is attacking you.");
                        fightState = !fightState;
                    }
                    else {
                        System.out.println(monster.name + " was defeated");
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
                                        System.out.println("There is no [ " + useItemCommand + " ] command.");
                                    } else {
                                        System.out.println("There is no [ " + useItemCommand.split("use ")[0] + " ] in your inventory.");
                                    }
                                }
                            }
                            else {
                                System.out.println("[ INVENTORY ] -> Empty");
                            }
                        }
                        else {
                            System.out.println("!!Unknown Command!!");
                        }
                    } else {
                        return false;
                    }
                }
            }
            else {
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
                                        System.out.println("There is no [ " + useItemCommand + " ] command.");
                                    } else {
                                        System.out.println("There is no [ " + useItemCommand.split("use ")[0] + " ] in your inventory.");
                                    }
                                }
                            }
                            else {
                                System.out.println("[ INVENTORY ] -> Empty");
                            }
                        }
                        else {
                            System.out.println("!!Unknown Command!!");
                        }
                    } else {
                        return false;
                    }
                    if (monster.alive == true) {
                        System.out.println("------------" + monster.name + " TURN-----------");
                        player.decreaseHP(monster.strength);
                        System.out.println("ACTION : Monster is attacking you.");
                        fightState = !fightState;
                    } else {
                        System.out.println(monster.name + " was defeated");
                        return true;
                    }
                } else {
                    System.out.println(monster.name + " is faster than you.");
                    if (monster.alive == true) {
                        System.out.println("------------" + monster.name + " TURN-----------");
                        player.decreaseHP(monster.strength);
                        System.out.println("ACTION : Monster is attacking you.");
                        fightState = !fightState;
                    } else {
                        System.out.println(monster.name + " was defeated");
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
                                        System.out.println("There is no [ " + useItemCommand + " ] command.");
                                    } else {
                                        System.out.println("There is no [ " + useItemCommand.split("use ")[0] + " ] in your inventory.");
                                    }
                                }
                            }
                            else {
                                System.out.println("[ INVENTORY ] -> Empty");
                            }
                        }
                        else {
                            System.out.println("!!Unknown Command!!");
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {

    }
}
