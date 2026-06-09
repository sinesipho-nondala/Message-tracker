/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;
/**The class will:
 * Ask the user for input
 * Display messages to the user
 * Call the methods inside Login
 * @author Student
 */

    
    public class MainApp{
        public static void main(String[] args)  {
            
            // Scanner allows the user to enter information
            Scanner input = new Scanner(System.in);
            
            // Create an object of the login class so we can call its methods
            Login login = new Login ();
            // --- REGISTRATION SECTION ---
       System.out.println("===USER REGISTRATION===");
       
       System.out.print("Enter a username: ");
       String username = input.nextLine();
       
       System.out.print("Enter a password: ");
       String password = input.nextLine();
       
       System.out.print("Enter your South African phone number (+27...): ");
       String phone = input.nextLine();
       
       // Call the registration method and store the message it returns
       String response = login.registerUser(username, password, phone);
       
       // Show the registration message 
       System.out.println(response);
       
       //---LOGIN SECTION---
       System.out.println("\n===USER LOGIN===");
       
       System.out.print("enter your username: ");
       String loginUsername = input.nextLine();
       
       System.out.print("Enter your password: ");
       String loginPassword = input.nextLine();
       
       // Call loginUser to check if details match the stored ones
       boolean loggedIn = login.loginUser(loginUsername, loginPassword);
      
       // Print out the correct login message
       String loginMessage = login.returnLoginStatus(loggedIn);
       System.out.println(loginMessage);
       
       //PART 3: LOAD MESSAGES FROM JSON
       Message.loadStoredMessages();
           System.out.println("Loading stored messages..");
           try{
               Message.sentMessages.clear();
               Message.recipientList.clear();
               Message.messageHashList.clear();
               Message.messageIDList.clear();
               Message.storedMessages.clear();
               
               System.out.println("Messages loaded:" + Message.storedMessages.size());
           }catch(Exception e){
               System.out.println("No existing message file found.");
           
           }
       }
        
        //PART 2
        int messageCounter = 1;//Message number from loop counter
        boolean appRunning = true;
        while (appRunning){
        System.out.println("\n=====MAIN MENU=====");
        System.out.println("1.Send New Message");
        System.out.println("2. View All Sent Messages");
        System.out.println("3. Total Message Sent");
        System.out.println("4. Logout");
        System.out.println("Enter choice");
        
        Scanner input = new Scanner(System.in);
        int menuChoice = input.nextInt();
        input.nextLine();
        switch (menuChoice){
            case 1:
        //FOR LOOP: Send multiple messages
        System.out.println("How many messages do you want to send?");
        
        int numOfMessages = input.nextInt();
        input.nextLine();
        for (int i = 0; i< numOfMessages;i++){//FOR LOOP
        System.out.println("\n--- MESSAGE" + messageCounter + "---");
        //get inputs
        System.out.print("Recipient number (+_[code][number]):");
        String recipient = input.nextLine();
        System.out.println("Message text (max 250 chars):");
        String text = input.nextLine();
        if (text.length()>250){
        text = text.substring(0,250);//Enter maximum length
        System.out.println("Text trimmed to 250 characters.");
        
    }
        //CREATE MESSAGE OBJECT
        Message newMsg = new Message(messageCounter, recipient,text);
        //Validate (call required methods)
        System.out.println("ID Valid:" + newMsg.checkMessageID());
        System.out.println("Recipient:" + newMsg .checkRecipientCell());
        System.out.println("Hash Generated:" + newMsg.createMessageHash());
        //Choose status: send / store/desregarded
        System.out.println("Action:[1=Send,2=Store(JSON),3=Disregarded:");
        
      
        int action = input.nextInt();
        input.nextLine();
        if (action==1){
        newMsg.setSentStatus("Sent");
        System.out.println("Message Sent!");
    } else if (action == 2){
        newMsg.storeMessage();//Call Method 7- JSON
        System.out.println("Message Stored as JSON");
    }else{
        newMsg.setSentStatus("Disregarded");
        System.out.println("Message Disregarded");
        
    }
        //DISPLAY FULL DETAILS
        newMsg.displayFullDetails();
        messageCounter++; //Increment counter
    
    }break;
            case 2:
        //Call printMessage()
        System.out.println(Message.printMessages());
        break;
            case 3:
        //Calll returnTotalMessage()
        int total = Message.returnTotalMessage();
        System.out.println("Total messages sent this session:" + total);
        break;
            case 4:
        appRunning = false;
        System.out.println("Invalid selection. Try again");
        }
    
        
        
      
        
    }
       
    }
//PART 3: NEW SUB MENU STORED FEATURES
 public static void storedMessageMenu(){
     int choice = 0;
     Scanner input = new Scanner(System.in);
     do{
         System.out.println("\n===== STORED MESSAGES MENU ====");
         System.out.println("1.Dispaly Longest Message");
         System.out.println("2.Search by Message Hash ID");
         System.out.println("3.Search by Recipient");
         System.out.println("4.Delete Message by Hash");
         System.out.println("5.View All Messages");
         System.out.println("6.Return To Main Menu");
         System.out.print("Enter choice:");
         
         choice = input.nextInt();
         input.nextLine();//Buffer
         
         switch(choice){
             case 1:
                 Message.displayLongestMessage();
                 break;
             case 2:
                 System.out.print("Enter Hash ID to search:");
                 input.nextLine();//Fix buffer issue
                 String hashSearch = input.nextLine();
                 Message.searchByMessageID(hashSearch);
                 break;
             case 3:
                 System.out.print("Enter Recipient Name to search:");
                 String recSearch = input.nextLine();
                 Message.searchByRecipient(recSearch);
                 break;
             case 4:
                 System.out.print("Enter Hash ID to delete:");
                 String hashDel = input.nextLine();
                 Message.deleteByHash(hashDel);
                 break;
             case 5:
                 Message.printMessage();
             case 6:
                 System.out.println("Returning to Main Menu...");
                 break;
             default:
                 System.out.println("Invalid choice!");
                 
         }
     
        
     }while  (choice!= 6);
        
       
    }
    
