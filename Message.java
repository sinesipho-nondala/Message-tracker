/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;

/**Message.java - new file Part 2
 *
 * @author Student
 */
public class Message {
    //Part 3: NEW STATIC ARRAYS
    // These are the PARALLEL ARRAYS
    
    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashList = new ArrayList<>();
    public static ArrayList<String> messageIDList = new ArrayList<>();
    public static ArrayList<String> recipientList = new ArrayList<>();
     
    // Fields - the data the messager will hold
     private String messageID;// 10-digit auto-generated
     private int messageNumber;// from loop counter
     private String recipient;//validated phone number
     private String messageText;//maximaum 250 characters
     private String messageHash;// auto - generated
     private String sendStatus;
     private static ArrayList <Message> messageHistory = new ArrayList<>();
    //Constructor
public Message( int messageNumber, String recipient, String messageText){
    //Initialise fields
    this.messageNumber = messageNumber;
    this.messageText = messageText;
    this.recipient = recipient;
    //Step 2: Generate 10-digit random MessageID
    generateRandomID();
    // Step 3: Validate ID length
    checkMessageID();
    // Step 5: Generate Message Hash from existing data
    this.messageHash = createMessageHash();
    //Step 6: Default status until user chooses
    this.sendStatus = "Disregared";
    //Step 7: Add to the history list
    messageHistory.add(this);
    
    
}
//Method 1: checkMessageID()
//Returns True if message ID is not more than 10 characters
public boolean checkMessageID(){
return(this.messageID.length()==10);
}
//Method 2: checkRecipientCell()
public String checkRecipientCell(){
//rule starts with "+" 
if(recipient.startsWith("+") && recipient.length()>=8 && recipient.length()<=15){
return "SUCCESS: Valid international number";
}else{
return "FAILURE: Invalid format - use + [code] [number]";
}
}
//Method 3: CreateMessageHash()
//Biulds and returns hash string using manipulation
public String createMessageHash(){
//Example logic: First 4 digits of number + message number + text length
String part1 = recipient.substring(1,5).toUpperCase();
String part2 = String.format("%03d",messageNumber);
String part3 = String.valueOf(messageText.length());
return part1 + "-" + part2 + "-"+ part3 + "HASH";


}
//Method 4: sentMessage()
public String sentMessage(){
    
    String status = this.sendStatus;
    
    if(status.equalsIgnoreCase("Sent"))
    {
        
        
            messageHashList.add(this.messageHash);
            recipientList.add(this.recipient);
            sentMessages.add(this.messageText);
            
            
            System.out.println("Message saved to array. Total messages:"+ sentMessages.size());
        
    }
    else
        {
            System.out.println("Storage is full");
        }
   
    return status;
}
//Helper to set choice from main menu
public void setSentStatus(String choice){
    if (choice.equalsIgnoreCase("Sent")||
            choice.equalsIgnoreCase("Stored")||
            choice.equalsIgnoreCase("Disregarded")){
        this.sendStatus = choice;
    }
}
//Method 5: printMessage()
//Returns all message sent this session as a formatted string
public static String printMessages(){
    StringBuilder output = new StringBuilder("\n=====ALL MESSAGES SENT=====\n");
    for(Message msg: messageHistory){
        output.append("Msg#").append(msg.messageNumber)
                .append("|ID:").append(msg.messageID)
                .append("|To:").append(msg.recipient)
                .append("|Status:").append(msg.sendStatus)
                .append("\n");
                
    }
    return output.toString();
}
//Method 6: returnTotalMessage()
//Return totalcount of message sent
public static int returnTotalMessage(){
    return messageHistory.size();
}
//Method 7: storeMessage()
//Save this message to a JSON file
public void storeMessage(){
    
    try( FileWriter writer = new FileWriter("message_"+ messageNumber + ".json")){
        
        String jsonContent = "{\n" + "\"messageID\":\""+ messageID + "\",\n"
                +"\"messageNumber\":\"" + messageNumber + ","
                + "\n"
                + "\"recipient\":\" "+ recipient + "\",\n"+"messageText\":\"" 
        + messageText.replace("\"","\\\"")+ "\",\n"+
           "\"messageHash\":\""+messageHash +"\",\n"+
                "\"sendStatus\":\"" + sendStatus + "\"\n"+
                "}";
        writer.write(jsonContent);
        
        this.sendStatus = "Stored"; //Update status after save
        
    }catch (IOException e){
        System.out.println("Save Error:" + e.getMessage());
    }
}
//SECTION 5
//Auto-generates exactly 10-digit random number using string manipulation
private void generateRandomID(){
    Random random = new Random();
    //Generate number between1000000000 and 9999999999
    long randomNumber = 10000000000L +
            random.nextInt(900000000);
    this.messageID = String.valueOf(randomNumber);
    
}
public void displayFullDetails(){
    System.out.println("================================");
    System.out.println("Message Number:" + messageNumber);
    System.out.println("MessageID:" + messageID + "|Valid:" + checkMessageID());
    System.out.println("Recipient:" + recipient + "|" + checkRecipientCell());
    System.out.println("Message Text:"+ messageText + "("+ messageText.length()+ "2/50)");
    System.out.println("Message Hash:" + messageHash);
    System.out.println("Status:" + sendStatus);
    System.out.println("=================================");
    
    
}
public String getMessageID(){return messageID;}
public int getMessageNumber(){return messageNumber;}
public String getRecipient() {return recipient;}
public String getMessageText(){return messageText;}
public String getMessageHash (){return messageHash;}
public String getSendStatus (){return sendStatus;}

public static void searchByRecipient(String searchRecipient){
    boolean found = false;
    System.out.println("\n MESSAGE FOR:" + searchRecipient);
    System.out.println("-----------------------");
    for( int i = 0; i<recipientList.size(); i++){
        //Compares what the user typed to the array data
        if(recipientList.get(i).equalsIgnoreCase(searchRecipient)){
            System.out.println("Message:" + sentMessages.get(i));
            System.out.println("Hash ID:" + messageHashList.get(i));
            found = true;
           
        }
            
    }
    if(!found)System.out.println("No messages found for this recipient.");
        
}

public static void loadStoredMessages(){
    try{
       sentMessages.clear();
       recipientList.clear();
       messageHashList.clear();
       messageIDList.clear();
       
        System.out.println("Message loaded successfully into arrays.");
    }
    catch(Exception e){
        System.out.println("No previous messages found or error loading:" + e.getMessage());
    }
}
public static void displayLongestMessage(){
    if(sentMessages.isEmpty()){
        System.out.println("No messages stored");
        return;
    }
    int longestIndex = 0; //Assume first message is longest
    int maxLength = sentMessages.get(0).length();
    
    //Loop through all messages
    for (int i= 1; i< sentMessages.size(); i++){
        if (sentMessages.get(i).length()> maxLength){
            maxLength = sentMessages.get(i).length();
            longestIndex = i;
        }
    }
    //Display results
    System.out.println("--- LONGEST MESSAGE ---");
    System.out.println("Recipinet:" + recipientList.get(longestIndex));
    System.out.println("Message:" +sentMessages.get(longestIndex));
    System.out.println("Length:" + maxLength + "character");
    System.out.println("Hash:" + messageHashList.get(longestIndex));
   
}
//PART 3: Search by Hash
 public static void searchByMessageID(String searchHash){
     boolean found = false;
     for (int i= 0; i< messageHashList.size();i++){
         if (messageHashList.get(i).equalsIgnoreCase(searchHash)){
             System.out.println("---MESSAGE FOUND---");
             System.out.println("Recipient:" + recipientList.get(i));
             System.out.println("Message:" + sentMessages.get(i));
             System.out.println("Hash:" + messageHashList.get(i));
             found = true;
             break; //Exit loop once found
         }
     }
     if(!found){
         System.out.println("Message with that Hash ID NOT FOUND.");
     }
 }
 public static void deleteByHash(String deleteHash){
     int foundIndex = -1; //-1 means not found
     
     //Step 1: Find the index position
     for(int i = 0; i<messageHashList.size(); i++){
         if(messageHashList.get(i).equalsIgnoreCase(deleteHash)){
             foundIndex = i;
             break;
         }
     }
     if(foundIndex == -1){
         System.out.println("Could not find message to deleate.");
         return;
     }
     //Step 2: SHIFT ALL THE ELEMENTS LEFT TO FIT GAP
     messageHashList.remove(foundIndex);
     recipientList.remove(foundIndex);
     sentMessages.remove(foundIndex);
     messageIDList.remove(foundIndex);
     
     
     
     System.out.println("Message DELETED successfully.");
 }
 public static void printMessage(){
     if(sentMessages.isEmpty()){
         System.out.println("No message to display.");
         return;
     }
     System.out.println("======= ALL STORED MESSAGES======");
     for(int i = 0; i<sentMessages.size(); i++){
         System.out.println("Message:"+ (i+1));
     System.out.println("To:" + recipientList.get(i));
     System.out.println("Text:" + sentMessages.get(i));
     System.out.println("Hash:" + messageHashList.get(i));
     System.out.println("-------------------");
     }
     
 }
     public static String printMessagesReport(){
     StringBuilder report = new StringBuilder();
     
     report.append("\n==========MESSAGE REPORT==========\n");
      if(sentMessages.isEmpty()){
          report.append("NO MESSAGES STORED\n");
          
      }else{
          for(int i = 0; i <sentMessages.size(); i++){
              report.append("MESSAGE").append(i+1)
                      .append("|RECIPIENT:")
                      .append(recipientList.get(i)).append("|TEXT:")
                      .append(sentMessages.get(i))
                      .append("|HASH:")
                      .append("\n");
              
                      
                      
          }
      }
     report.append("===================\n");
     return report.toString();
     }
}


     
