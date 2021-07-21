package zork.commands;

import zork.Game;

import java.util.ArrayList;
import java.util.List;

public class AttackCommand implements Command{
    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "attack";
    }

    @Override
    public void execute(Game game, List<String> argument) {
        if (game.getGameStatus() == game.PLAY_STATUS) {
            if (game.currentRoom.isMonsterExist()) {
                boolean result = game.fight(game.currentRoom.getMonster());
                if (result) {
                    game.player.gainEXP(game.currentRoom.monster.expDrop);
                    game.currentLevel.monsterNum -= 1;
                    game.currentRoom.removeMonster();

                } else {
                    System.out.println("*********You were defeated*********");
                    System.out.println("*************Game Over*************");
                    System.out.println("===================================");
                    System.out.println("");
                    game.listOfLevels = new ArrayList<>();
                    game.setStartMenu();
                }
            } else {
                System.out.println("There is no any monster in this room");
            }
        }
        else {
            System.out.println("!!Cannot use this command in start menu!!");
        }
    }
}
