package zork.commands;

import zork.Game;

import java.util.List;

public class DownCommand implements Command{
    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "go south";
    }

    @Override
    public void execute(Game game, List<String> argument) {
        if (game.getGameStatus() == game.PLAY_STATUS) {
            if (Game.currentRoom.downRoom != null) {
                Game.currentRoom = Game.currentRoom.downRoom;
                Game.player.increaseHP(2);
                System.out.println("Now you are in : [ " + Game.currentRoom.name + " ]");
                String x = "Available Exit : |";
                if (game.currentRoom.rightRoom != null) {
                    x = x + " EAST : " + game.currentRoom.rightRoom.name + " |";
                }
                if (game.currentRoom.leftRoom != null) {
                    x = x + " WEST : " + game.currentRoom.leftRoom.name + " |";
                }
                if (game.currentRoom.upRoom != null) {
                    x = x + " NORTH : " + game.currentRoom.upRoom.name + " |";
                }
                if (game.currentRoom.downRoom != null) {
                    x = x + " SOUTH : " + game.currentRoom.downRoom.name + " |";
                }
                if (game.currentRoom.downRoom == null
                        && game.currentRoom.upRoom == null
                        && game.currentRoom.leftRoom == null
                        && game.currentRoom.rightRoom == null) {
                    System.out.println("You are trapped!!!");
                    System.out.println("This is a locked room!!!");
                }
                System.out.println(x);
                if (game.currentRoom.isMonsterExist()) {
                    System.out.println("There is a monster in this room!!!");
                }
            } else {
                System.out.println("There is no door.");
            }
        }
        else {
            System.out.println("!!Cannot use this command in start menu!!");
        }
    }
}
