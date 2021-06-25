package zork.commands;

import zork.Game;

import java.util.List;

public class HelpCommand implements Command{
    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public void execute(Game game, List<String> argument) {
        if (game.getGameStatus() == game.PLAY_STATUS) {
            System.out.println("###ALL COMMAND###");
            System.out.println("[ go | north , south , west , east | ] : go to the next area/door");
            System.out.println("[ take ] : pick up weapon/items");
            System.out.println("[ drop ] : drop weapon/items to the floor");
            System.out.println("[ attack ] : attack monsters in the area");
            System.out.println("[ inventory ] : open your inventory to use items");
            System.out.println("[ info ] : indicate player status");
            System.out.println("[ save ] : save the game");
            System.out.println("[ load ] : load save");
            System.out.println("[ quit ] : back to the game menu");
            System.out.println("[ exit ] : exit game");
        }
        else {
            System.out.println("###ALL COMMAND###");
            System.out.println("[ play ] : start game");
            System.out.println("[ load ] : load save");
            System.out.println("[ exit ] : exit game");
        }
    }
}
