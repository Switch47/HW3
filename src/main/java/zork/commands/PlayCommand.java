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
        if (game.getGameStatus() == game.HOME_STATUS) {
            game.player = new Player();
            Scanner sc  = new Scanner(System.in);
            boolean check = false;
            while (check == false) {
                String x = "[ CHAPTER ] -> [ " + game.listOfLevels.get(0).name + " ] ";
                for (int i = 1; i < game.listOfLevels.size(); i++) {
                    x += ", [ " + game.listOfLevels.get(i).name + " ] ";
                }
                System.out.println(x);
                System.out.println("[ back ]");
                System.out.println("TYPE : {map name} or {back}");
                String levelName = sc.next();
                if (levelName.toLowerCase().equals("back")) {
                    System.out.println("  Back to the main menu!  ");
                    System.out.println("--------------------------");
                    game.setStartMenu();
                    break;
                }
                else {
                    int count = 0;
                    for (Level level : game.listOfLevels) {
                        if (level.name.toLowerCase().equals(levelName.toLowerCase())) {
                            count = count + 1;
                        }
                    }
                    if (count == 0) {
                        System.out.println("There is no [ " + levelName + " ] map.");
                    } else {
                        for (Level level : game.listOfLevels) {
                            if (level.name.toLowerCase().equals(levelName.toLowerCase())) {
                                game.setStartPlay();
                                game.inGame(level);
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
