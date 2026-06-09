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
 public static String displayLongestMessage(){
   if(storedMessages.isEmpty()) return "No messages stroed";
   String longest = storeMessages.get(0);
   for(String msgn: storedMessages){
     if (msg.length()>longest.length()) longest = msg;}
   return longest;
 }
  public static String searchByMessageID(String id) {
    for (int i = 0; i<messageIDs.size(); i++){
      if(messageIDs.get(i).equals(id)) return "Found:" + storedMessages.get(i);
    }
    return "Message ID not found.";
  }
  public static void loadStoredMessages(){
    try(BufferedReader reader = new BufferedReader(new FileReader("messages.json"))) {
      String line;
      while((line = reader.readLine() != null) {
        JSONObject obj = new JSONOject(line);
        storedMessages.add(obj.getString("Message"));
        messageHashes.add(obj.getString("Hash"));
        messageIDS.add(obj.getString("ID"));
        recipientList.add(obj.getString("Recipient"));
      }
    }catch(IOException e) {
      System.out.println("No stored messages found.");
    }
  }
  public static String displayReport(){
    StringBuilder report = new StringBuilder("===MESSAGE REPORT===\n);
    for(int i= 0; i< storedMessages.size();i++){
      report.append("---\nHash:").append(messageHashes.get(i))
        .append("\nID:").append(messageIDs.get(i))
        .append("\nRecipient:").append(recipientList.get(i))
        .append("\nMessage:).append(storedMessage.get(i)).append("\n");
    }
    return report.toString();
}
  
