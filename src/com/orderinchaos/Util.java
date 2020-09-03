package com.orderinchaos;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Util {
  private static Scanner scanner;
  private static HttpURLConnection connection;

  public static int INPUT_HANDLER() {
    int userInput = 1;
    boolean validInput = false;
    String prompt = "1: New Game\n2: Load Game\n3: How to Play";
    while (!validInput) {
      try{
        System.out.println(prompt);
        scanner = new Scanner(System.in);
        userInput = scanner.nextInt();
        if (userInput >= 1 && userInput <= 3) {
          validInput = true;
        } else {
          System.out.println("Please select an item from the menu.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please select an item from the menu.");
      }
    }

    return userInput;
  }

  public static String[] INPUT_HANDLER(List<String> validCommands) {
    return INPUT_HANDLER(validCommands, null);
  }

  public static String[] INPUT_HANDLER(List<String> validCommands, String file) {
    String[] userInput = new String[2];
    sendGetRequest(validCommands.get(0));
    boolean isValidInput = false;
    while (!isValidInput) {
      try{
        if (file == null) {
          System.out.print("\t>>> ");
          scanner = new Scanner(System.in);
        } else {
          // TODO: Read file and get commands for test
          try {
            scanner = new Scanner(new File(file));
            while (scanner.hasNext()) {
              String line = scanner.nextLine();
              System.out.println(line);
            }
            scanner.close();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        }
        String input = scanner.nextLine();
        String[] inputArr = input.toUpperCase().trim().split(" ",2);
        String verb = inputArr[0].trim();
        String noun = inputArr[1].trim();

        // DONE: check verb inputArr[0] & noun inputArr[1]
        String synonym = checkSynonyms(verb);
        if (synonym != null) {
          isValidInput = true;
          userInput[0] = synonym;
          userInput[1] = noun;
        } else {
          System.out.println("I'm not sure what " + input + " means...");
        }
      } catch (IndexOutOfBoundsException e) {
        // TODO: display suggestion
        System.out.println("Invalid input.");
      } catch (NullPointerException e) {
        e.printStackTrace();
      }
    }

    return userInput;
  }

  private static void sendGetRequest(String verb) {
    // TODO: Send GET request to Dictionary API (umpire?key=your-api-key)
    BufferedReader reader;
    String line;
    StringBuffer resContent = new StringBuffer();
    try {
      String key = GET_KEY("config.txt");
      String dictionaryUrl = "https://www.dictionaryapi.com/api/v3/references/thesaurus/json/";
      // DONE: hide api-key
      URL url = new URL(dictionaryUrl.concat(verb).concat("?key=").concat(key));
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setConnectTimeout(3000);
      connection.setReadTimeout(3000);

      int status = connection.getResponseCode();

      if (status == 200) {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      } else {
        reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
      }
      while ((line = reader.readLine()) != null) {
        resContent.append(line);
      }
      reader.close();
      System.out.println(resContent.toString());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      connection.disconnect();
    }

  }

  private static String checkSynonyms(String verb) {
    // TODO: store in a HashMap and return key
    Stream<String> words = TEXT_READER("synonyms.txt");
    String result = "";
    words.forEach(line -> {
      String[] synonyms = line.toUpperCase().split("[,]");
      if (Arrays.asList(synonyms).contains(verb)) {
        result.concat(synonyms[0]);
      }
    });

    return result;
  }

  public static Stream<String> TEXT_READER(String fileName) {
    Stream<String> result = null;
    try {
      result = Files.lines(Paths.get("docs",fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return result;
  }

  private static String GET_KEY(String fileName) {
    List<String> keys = new ArrayList<>();
    TEXT_READER(fileName).forEach(line -> keys.add(line));

    return keys.get(0);
  }

  public static void STREAM_DISPLAY(Stream<String> stream, int duration) {
    stream.forEach(line -> {
      System.out.println(line);
      try {
        Thread.sleep(duration);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    System.out.print("\n");
  }

  public static void CLEAR_SCREEN() {
    try {
      final String os = System.getProperty("os.name");
      if (os.contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        new ProcessBuilder("clear").inheritIO().start().waitFor();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
