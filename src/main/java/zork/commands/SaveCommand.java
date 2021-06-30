package zork.commands;

import zork.Game;
import zork.object.Room;
import zork.object.item.Item;
import zork.object.levels.Level;
import zork.object.levels.Level1;
import zork.object.monster.Monster;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SaveCommand implements Command{


    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "save";
    }

    @Override
    public void execute(Game game, List<String> argument) throws IOException {
        if (game.getGameStatus() == game.PLAY_STATUS) {
            while (true) {
                Scanner in = new Scanner(System.in);
                System.out.println("*****************************************");
                System.out.println("                SAVE GAME                ");
                System.out.println("          [ Save01 ] [ Save02 ]          ");
                System.out.println("          [ Save03 ] [ Save04 ]          ");
                System.out.println("          [ Save05 ] [ Save06 ]          ");
                System.out.println("*****************************************");
                System.out.println("PLEASE READ : type only the name of save.");
                System.out.println("TYPE : {back} to continue playing");
                String word = in.nextLine();
                if (word.toLowerCase().equals("save01")) {
                    String save01 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save01.txt";
                    saveFile(game, save01);
                    System.out.println("SAVE TO : Save01");
                    break;
                } else if (word.toLowerCase().equals("save02")) {
                    String save02 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save02.txt";
                    saveFile(game, save02);
                    System.out.println("SAVE TO : Save02");
                    break;
                } else if (word.toLowerCase().equals("save03")) {
                    String save03 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save03.txt";
                    saveFile(game, save03);
                    System.out.println("SAVE TO : Save03");
                    break;
                } else if (word.toLowerCase().equals("save04")) {
                    String save04 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save04.txt";
                    saveFile(game, save04);
                    System.out.println("SAVE TO : Save04");
                    break;
                } else if (word.toLowerCase().equals("save05")) {
                    String save05 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save05.txt";
                    saveFile(game, save05);
                    System.out.println("SAVE TO : Save05");
                    break;
                } else if (word.toLowerCase().equals("save06")) {
                    String save06 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save06.txt";
                    saveFile(game, save06);
                    System.out.println("SAVE TO : Save06");
                    break;
                } else if (word.toLowerCase().equals("back")) {
                    System.out.println(".....Continue Playing!.....");
                    System.out.println("---------------------------");
                    System.out.println("Now you are in " + game.currentRoom.name);
                    break;
                }
                else {
                    System.out.println("!!!Nothing Save!!!");
                }
            }
        }
        else {
            System.out.println("!!Cannot use this command in start menu!!");
        }
    }

    public void saveFile(Game game, String filename) throws IOException {
            File output = new File(filename);
            FileWriter writer = new FileWriter(output);

            writer.write("name:" + game.currentLevel.name);
            writer.write("\n" + "object " + game.currentLevel.object);
            writer.write("\n" + "start " + game.currentRoom.name);
            Path filePath = new File("C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Map//" + game.currentLevel.name + ".txt").toPath();
            Charset charset = Charset.defaultCharset();
            List<String> stringList = Files.readAllLines(filePath, charset);
            String[] stringArray = stringList.toArray(new String[]{});
            for (int i = 0; i < stringArray.length; i++) {
                if (stringArray[i].split(" ")[0].equals("right")
                        || stringArray[i].split(" ")[0].equals("left")
                        || stringArray[i].split(" ")[0].equals("up")
                        || stringArray[i].split(" ")[0].equals("down")) {
                    writer.write("\n" + stringArray[i]);
                }
            }
            for (String i : game.currentLevel.roomLevel.keySet()) {
                if (game.currentLevel.roomLevel.get(i).isItems() == true) {
                    for (Item item : game.currentLevel.roomLevel.get(i).items) {
                        writer.write("\n" + "setItem:" + game.currentLevel.roomLevel.get(i).name + ":" + item.name);

                    }
                }
                if (game.currentLevel.roomLevel.get(i).isMonsterExist() == true) {
                    writer.write("\n" + "setMonster:" + game.currentLevel.roomLevel.get(i).name + ":" + game.currentLevel.roomLevel.get(i).monster.name);

                }
            }
            if (game.player.weapon != null) {
                writer.write("\n" + "setPlayer:" + game.player.HP + ":"
                        + game.player.MAX_HP + ":"
                        + game.player.strength + ":"
                        + game.player.weapon.name + ":"
                        + game.player.agility + ":"
                        + game.player.exp + ":"
                        + game.player.level + ":"
                        + game.player.MAX_EXP);
            }
            else {
                writer.write("\n" + "setPlayer:" + game.player.HP + ":"
                        + game.player.MAX_HP + ":"
                        + game.player.strength + ":"
                        + null + ":"
                        + game.player.agility + ":"
                        + game.player.exp + ":"
                        + game.player.level + ":"
                        + game.player.MAX_EXP);
            }

            String playerItems = "\n" + "setPlayerItems";
            if (game.player.items.size() > 0) {
                for (Item item : game.player.items) {
                    playerItems += ":" + item.name;
                }
                writer.write(playerItems);

            } else {
                writer.write(playerItems + ":null");

            }
            writer.flush();
            writer.close();
    }

}
