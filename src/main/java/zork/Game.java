package zork;

import zork.commands.Command;
import zork.object.Player;
import zork.object.Room;
import zork.object.levels.Map;
import zork.object.levels.Map1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {

    public static Player player;

    private GameOutput output = new GameOutput();

    private CommandParser commandParser = new CommandParser();

    public Room currentRoom;

    public Map currentLevel;

    public static List<Map> listOfMaps = new ArrayList<Map>();

    public void run() {
        player = new Player();
        Map1 level1 = new Map1();
        listOfMaps.add(level1);

        for (Map level : listOfMaps) {
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
}
