package zork.commands;

import zork.Game;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ExitCommand implements Command{

    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "exit";
    }

    @Override
    public void execute(Game game, List<String> args) {
        Scanner in = new Scanner(System.in);
        boolean x = false;
        if (game.getGameStatus() == game.HOME_STATUS) {
            while (x == false) {
                System.out.println("---------------------------");
                System.out.println("Do you want to exit game ?");
                System.out.println("[ EXIT ] : [ YES ] / [ NO ]");
                String result = in.nextLine();
                if (result.toUpperCase().equals("YES")) {
                    game.getOutput().println("Game exit");
                    System.out.println("---------------------------");
                    game.exit();
                    break;
                }
                else if (result.toUpperCase().equals("NO")){
                    System.out.println("---------------------------");
                    game.setStartMenu();
                    break;
                }
                else {
                    System.out.println("!!Unknown Command!!");
                    System.out.println("TYPE : only yes or no");
                }
            }
        }
        else if (game.getGameStatus() == game.PLAY_STATUS){
            while (x == false) {
                System.out.println("---------------------------");
                System.out.println("Do you want to exit game ?");
                System.out.println("[ EXIT ] : [ YES ] / [ NO ]");
                String result = in.nextLine();
                if (result.toUpperCase().equals("YES")) {
                    game.getOutput().println("Game exit");
                    System.out.println("---------------------------");
                    game.exit();
                    break;
                }
                else if (result.toUpperCase().equals("NO")){
                    System.out.println("Continue Playing");
                    System.out.println("---------------------------");
                    System.out.println("Now you are in " + game.currentRoom.name);
                    break;
                }
                else {
                    System.out.println("!!Unknown Command!!");
                    System.out.println("TYPE : only yes or no");
                }
            }
        }

    }
}
