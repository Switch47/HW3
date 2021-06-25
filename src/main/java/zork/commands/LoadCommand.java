package zork.commands;

import zork.Game;
import zork.object.levels.Level1;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LoadCommand implements Command{

    Level1 level = new Level1();

    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "load";
    }

    @Override
    public void execute(Game game, List<String> argument) throws IOException {
        if (game.getGameStatus() == game.HOME_STATUS) {
            Boolean check = false;
            while (check == false) {
                Scanner in = new Scanner(System.in);
                System.out.println("*****************************************");
                System.out.println("                LOAD GAME                ");
                System.out.println("          [ Save01 ] [ Save02 ]          ");
                System.out.println("          [ Save03 ] [ Save04 ]          ");
                System.out.println("          [ Save05 ] [ Save06 ]          ");
                System.out.println("*****************************************");
                System.out.println("PLEASE READ : type only the name of save.");
                System.out.println("TYPE : {back} to back to the menu");
                String parameter = in.nextLine();
                if (parameter.toLowerCase().equals("save01")) {
                    System.out.println("LOAD SAVE : Save01");
                    String save01 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save01.txt";
                    loadFile(save01);
                    game.setStartPlay();
                    System.out.println("Continue Playing from the last save.");
                    game.inGame(level);
                    break;

                } else if (parameter.toLowerCase().equals("save02")) {
                    System.out.println("LOAD SAVE : Save02");
                    String save02 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save02.txt";
                    loadFile(save02);
                    game.setStartPlay();
                    System.out.println("Continue Playing from the last save.");
                    game.inGame(level);
                    break;

                } else if (parameter.toLowerCase().equals("save03")) {
                    System.out.println("LOAD SAVE : Save03");
                    String save03 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save03.txt";
                    loadFile(save03);
                    game.setStartPlay();
                    System.out.println("Continue Playing from the last save.");
                    game.inGame(level);
                    break;

                } else if (parameter.toLowerCase().equals("save04")) {
                    System.out.println("LOAD SAVE : Save04");
                    String save04 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save04.txt";
                    loadFile(save04);
                    game.setStartPlay();
                    System.out.println("Continue Playing from the last save.");
                    game.inGame(level);
                    break;

                } else if (parameter.toLowerCase().equals("save05")) {
                    System.out.println("LOAD SAVE : Save05");
                    String save05 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save05.txt";
                    loadFile(save05);
                    game.setStartPlay();
                    System.out.println("Continue Playing from the last save.");
                    game.inGame(level);
                    break;

                } else if (parameter.toLowerCase().equals("save06")) {
                    System.out.println("LOAD SAVE : Save06");
                    String save06 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save06.txt";
                    loadFile(save06);
                    game.setStartPlay();
                    System.out.println("Continue Playing from the last save.");
                    game.inGame(level);
                    break;

                }
                else if (parameter.toLowerCase().equals("back")) {
                    System.out.println("...Back to the main menu...");
                    System.out.println("---------------------------");
                    game.setStartMenu();
                    break;
                }
                else {
                    System.out.println("!!!Nothing Load!!!");
                }
            }
        }
        else {
            Boolean check = false;
            while (check == false) {
                Scanner in = new Scanner(System.in);
                System.out.println("*****************************************");
                System.out.println("                LOAD GAME                ");
                System.out.println("          [ Save01 ] [ Save02 ]          ");
                System.out.println("          [ Save03 ] [ Save04 ]          ");
                System.out.println("          [ Save05 ] [ Save06 ]          ");
                System.out.println("*****************************************");
                System.out.println("PLEASE READ : type only the name of save.");
                System.out.println("TYPE : {back} to continue playing");
                String parameter = in.nextLine();
                if (parameter.toLowerCase().equals("save01")) {
                    System.out.println("LOAD SAVE : Save01");
                    String save01 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save01.txt";
                    loadFile(save01);
                    game.setStartPlay();
                    System.out.println("Continue Playing from the last save.");
                    game.inGame(level);
                    break;

                } else if (parameter.toLowerCase().equals("save02")) {
                    System.out.println("LOAD SAVE : Save02");
                    String save02 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save02.txt";
                    loadFile(save02);
                    game.setStartPlay();
                    System.out.println("Continue Playing from the last save.");
                    game.inGame(level);
                    break;

                } else if (parameter.toLowerCase().equals("save03")) {
                    System.out.println("LOAD SAVE : Save03");
                    String save03 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save03.txt";
                    loadFile(save03);
                    game.setStartPlay();
                    System.out.println("Continue Playing from the last save.");
                    game.inGame(level);
                    break;

                } else if (parameter.toLowerCase().equals("save04")) {
                    System.out.println("LOAD SAVE : Save04");
                    String save04 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save04.txt";
                    loadFile(save04);
                    game.setStartPlay();
                    System.out.println("Continue Playing from the last save.");
                    game.inGame(level);
                    break;

                } else if (parameter.toLowerCase().equals("save05")) {
                    System.out.println("LOAD SAVE : Save05");
                    String save05 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save05.txt";
                    loadFile(save05);
                    game.setStartPlay();
                    System.out.println("Continue Playing from the last save.");
                    game.inGame(level);
                    break;

                } else if (parameter.toLowerCase().equals("save06")) {
                    System.out.println("LOAD SAVE : Save06");
                    String save06 = "C://Users//USER//IdeaProjects//Software System//hw3//src//main//java//zork//object//levels//Save//Save06.txt";
                    loadFile(save06);
                    game.setStartPlay();
                    System.out.println("Continue Playing from the last save.");
                    game.inGame(level);
                    break;

                }
                else if (parameter.toLowerCase().equals("back")) {
                    System.out.println(".....Continue Playing!.....");
                    System.out.println("---------------------------");
                    System.out.println("Now you are in " + game.currentRoom.name);
                    break;
                }
                else {
                    System.out.println("!!!Nothing Load!!!");
                }
            }
        }
    }

    public void loadFile(String filename) {
        level.loadMap(filename);
    }
}
