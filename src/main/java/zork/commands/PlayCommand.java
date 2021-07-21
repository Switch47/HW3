package zork.commands;

import zork.Game;
import zork.object.Player;
import zork.object.levels.Level;
import java.io.IOException;
import java.util.*;


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
    public void execute(Game game, List<String> argument) throws IOException {
        if (Game.getGameStatus() == Game.HOME_STATUS) {
            Game.player = new Player();
            game.setMap();
            Scanner sc  = new Scanner(System.in);
//            boolean check = false;
            while (true) {
                StringBuilder x = new StringBuilder("[ CHAPTER ] -> [ " + Game.listOfLevels.get(0).name + " ] ");
                for (int i = 1; i < Game.listOfLevels.size(); i++) {
                    x.append(", [ ").append(Game.listOfLevels.get(i).name).append(" ] ");
                }
                System.out.println(x);
                System.out.println("[ BACK ]");
                System.out.println("TYPE : {map name} or {back}");
                String levelName = sc.next();
                if (levelName.equalsIgnoreCase("back")) {
                    System.out.println("  Back to the main menu!  ");
                    System.out.println("--------------------------");
                    game.setStartMenu();
                    game.listOfLevels = new ArrayList<>();
                    break;
                }
                else {
                    int count = 0;
                    for (Level level : Game.listOfLevels) {
                        if (level.name.equalsIgnoreCase(levelName)) {
                            count = count + 1;
                        }
                    }
                    if (count == 0) {
                        System.out.println("There is no [ " + levelName + " ] map.");
                    } else {
                        for (Level level : Game.listOfLevels) {
                            Level new_level = level;
                            if (new_level.name.equalsIgnoreCase(levelName)) {
                                game.setStartPlay();
                                game.inGame(new_level);
                            }
                        }
                        break;
                    }
                }
            }

        }
        else {
            System.out.println("!!Cannot use this command in game!!");
        }
    }




}
