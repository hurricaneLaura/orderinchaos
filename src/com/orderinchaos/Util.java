package com.orderinchaos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.stream.Stream;

public class Util {
  private static Scanner scanner;
  private static HttpURLConnection connection;
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";

  public static void red(String text) {
    System.out.println(ANSI_RED  + text + ANSI_RESET);
  }

  public static void yellow(String text) {
    System.out.println(ANSI_YELLOW  + text + ANSI_RESET);
  }

  public static void green(String text) {
    System.out.println(ANSI_GREEN + text + ANSI_RESET);
  }

  public static void blue(String text) {
    System.out.println(ANSI_BLUE  + text + ANSI_RESET);
  }

  public static void purple(String text) {
    System.out.println(ANSI_PURPLE + text + ANSI_RESET);
  }

  public static void cyan(String text) {
    System.out.println(ANSI_CYAN  + text + ANSI_RESET);
  }

  public static int INPUT_HANDLER(String[] items) {
    int userInput = 1;
    boolean validInput = false;
    while (!validInput) {
      try{
        for (int i = 0; i < items.length; i++) {
          System.out.print(i+1 + ": " + items[i] + "\t\t");
        }
        System.out.println();
        scanner = new Scanner(System.in);
        userInput = scanner.nextInt();
        if (userInput >= 1 && userInput <= items.length) {
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
    // TODO: send to call API upon start
    boolean isValidInput = false;
    while (!isValidInput) {
      try{
        String input = "";
        if (file == null) {
          System.out.print("\t>>> ");
          scanner = new Scanner(System.in);
          input = scanner.nextLine();
        } else {
          // TODO: Read file and get commands for test
          try {
            scanner = new Scanner(new File("docs/tests/" + file));
            while (scanner.hasNext()) {
              input = scanner.nextLine();
            }
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        }
        String[] inputArr = input.toUpperCase().trim().split(" ",2);
        String verb = inputArr[0].trim();
        String noun = inputArr[1].trim();

        // DONE: check verb inputArr[0] & noun inputArr[1]
        String synonym = checkSynonyms(verb);
        if (synonym != null) {
          isValidInput = true;
          userInput[0] = synonym;
          userInput[1] = noun;
          CLEAR_SCREEN();
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
    // DONE: Send GET request to Dictionary API (word?key=your-api-key)
    String dictionaryUrl = "https://www.dictionaryapi.com/api/v3/references/thesaurus/json/";
    String key = GET_KEY("config.txt");

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest req = HttpRequest.newBuilder().uri(URI.create(dictionaryUrl.concat(verb).concat("?key=").concat(key))).build();
    List<String> syns = client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(Util::parse)
            .join();

    // TODO: Save HashMap<String, List<String>> synonyms
  }

  private static List<String> parse(String res) {
    List<String> result = new ArrayList<>();
    try {
      JSONObject meta = new JSONArray(res).getJSONObject(0).getJSONObject("meta");
      JSONArray synonyms = meta.getJSONArray("syns").getJSONArray(0);

      for (int i = 0; i < synonyms.length(); i++) {
        result.add(synonyms.getString(i));
      }
    } catch (CompletionException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return result;
  }

  private static String checkSynonyms(String verb) {
    // TODO: store in a HashMap and return key
    Stream<String> words = TEXT_READER("synonyms.txt");
    List<String> result = new ArrayList<>();
    words.forEach(line -> {
      String[] synonyms = line.toUpperCase().split("[,]");
      if (Arrays.asList(synonyms).contains(verb)) {
        result.add(synonyms[0]);
      }
    });

    if (result.size() > 0) {
      return result.get(0);
    } else {
      return null;
    }

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

  public static void LOAD_SCREEN() {
      try {
        System.out.print("\r  |  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  |  0  %");
        Thread.sleep(75);
        System.out.print("\r  |██████  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  |  10 %");
        Thread.sleep(150);
        System.out.print("\r  |██████████████████  -  -  -  -  -  -  -  -  -  -  -  -  -  -  |  30 %");
        Thread.sleep(100);
        System.out.print("\r  |███████████████████████████████████-  -  -  -  -  -  -  -  -  |  50 %");
        Thread.sleep(200);
        System.out.print("\r  |█████████████████████████████████████████████████ -  -  -  -  |  75 %");
        Thread.sleep(300);
        System.out.print("\r  |██████████████████████████████████████████████████████  -  -  |  85 %");
        Thread.sleep(150);
        System.out.print("\r  |██████████████████████████████████████████████████████████████|  100%");
        Thread.sleep(75);
      }
      catch(Exception e) {
        System.err.println(e);
      }
    System.out.println("\r              ");
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

  public static void PRINT_TEXT_FILE(String txtFile, int duration) {
    STREAM_DISPLAY(Util.TEXT_READER(txtFile), duration);
  }
}