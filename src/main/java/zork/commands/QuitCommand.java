package zork.commands;

import zork.Game;

import java.util.List;
import java.util.Scanner;

public class QuitCommand implements Command{
    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "quit";
    }

    @Override
    public void execute(Game game, List<String> argument) {
        Scanner in = new Scanner(System.in);
        if (game.getGameStatus() == game.PLAY_STATUS) {
            System.out.println("---------------------------");
            System.out.println("Do you want to quit to the main menu ?");
            System.out.println("[ QUIT ] : [ YES ] / [ NO ]");
            String result = in.nextLine();
            if (result.toUpperCase().equals("YES")) {
                System.out.println("---------------------------");
                game.setStartMenu();
            }
            else {
                System.out.println("Continue Playing");
                System.out.println("---------------------------");
            }
        }
        else {
            System.out.println("!!Cannot use this command in game!!");
        }
    }
}
