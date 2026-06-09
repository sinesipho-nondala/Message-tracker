import java.util.ArrayList;
import java.util.List;

public class MessageTracker{
  //Five parallel arrays
private static List<String>sentMessage = new ArrayList<>();
private static List<String>storeMessages = new ArrayList<>();
private static List<String>disregardedMessages = new ArrayList<>();
private static List<String>messageIDs = new ArrayList<>();
private static List<String>messageHashes = new ArrayList<>();
private static List<String>recipientList = new ArrayList<>();
//New: Menu method
public static void showMenu(){
  System.out.println("\n===MENU===");
  System.out.println("1)Send Messages);
  System.out.println("2)Show recently sent messages");
  System.out.println("3)Quit");
  System.out.println("4)Stored Messages");
}
    
}
  
