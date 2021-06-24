package zork.commands;

import zork.Game;
import zork.object.Player;
import zork.object.levels.Level;
import zork.object.levels.Level1;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayCommand implements Command{


    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "play";
    }

    @Override
    public void execute(Game game, List<String> argument) {

        if (game.getGameStatus() == game.HOME_STATUS) {
//            game.run();
            Scanner sc  = new Scanner(System.in);
            String x = "[ CHAPTER ] -> [ " + game.listOfLevels.get(0).name + " ] ";
            for (int i = 1; i < game.listOfLevels.size(); i++) {
                x += ", [ " + game.listOfLevels.get(i).name + " ] ";
            }
            System.out.println(x);
            System.out.println("//TYPE Map Name//");
            String levelName = sc.next();
            int count = 0;
            for (Level level : game.listOfLevels) {
                if (level.name.toLowerCase().equals(levelName.toLowerCase())) {
                    count = count + 1;
                }
            }
            if (count == 0) {
                System.out.println("There is no [ " + levelName + " ] map." );
            }
            else {
                for (Level level : game.listOfLevels) {
                    if (level.name.toLowerCase().equals(levelName.toLowerCase())) {
                        game.setStartPlay();
                        game.currentLevel = level;
                        System.out.println("Welcome to " + level.name);
                        System.out.println("***PLEASE READ***");
                        System.out.println("Your objective is to " + level.objective);
                        System.out.println("TYPE [ help ] : to print all command!!");
                        game.currentRoom = level.startRoom;
                        game.inGame(level);
                    }
                }

            }

        }
        else {
            System.out.println("!!Cannot use this command in game!!");
        }
    }




}
