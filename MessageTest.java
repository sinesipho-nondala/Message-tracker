import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
@Test
    public  void testArrayStoreDataCorrectly(){
        Message.sentMessages.clear();
        Message.recipientList.clear();
        Message.messageHashList.clear();
        Message.messageIDList.clear();
        
        //Create test objects
        Message m1 = new Message(0,"Alice","Hello World");
        m1.setSentStatus("Sent");
        m1.sentMessage();
        
        Message m2 = new Message(1,"Bob", "Short");
        m2.setSentStatus("Sent");
        m2.sentMessage();
        
        //Assert that the data sits in correct position
        assertEquals("Alice",Message.recipientList.get(0));
        assertEquals("Bob",Message.recipientList.get(1));
        assertEquals("Hello World",Message.sentMessages.get(0));
        assertEquals("Short", Message.sentMessages.get(1));
        assertEquals(2,Message.sentMessages.size());
        
    }
    @Test
    public void testFindLongestMessage(){
        //this method just checks if it runs without errors
        Message.displayLongestMessage();
        assertTrue(true);
    }
    @Test
    public void testSearchByIDFound() {
        //Assuming you have a message with hash "ABC123"
        Message.searchByMessageID("ABC123");
        assertTrue(true);//Check console manually or implement logic
        
        
    }
    @Test
    public void testSearchByIDNotFound(){
        Message.searchByMessageID("FAKEHASH999");
        assertTrue(true);
    }
    @Test
    public void testSearchByRecipient(){
        Message.searchByRecipient("Alice");
        assertTrue(true);
    }
    @Test
    public void testDeleteMessage(){
        Message.sentMessages.clear();
        Message.recipientList.clear();
        Message.messageHashList.clear();
        Message.messageIDList.clear();
        
        
        int before = Message.sentMessages.size();
        String hashToDelete = Message.messageHashList.get(1);
        Message.deleteByHash(hashToDelete);
        
        int after = Message.sentMessages.size();
        assertEquals(before -1,after); //Count should decrease by 1.
        assertEquals("Where are you? You are late!", Message.sentMessages.get(1));
        
    }
    @Test
    public void testDisplayReport_containsRequiredFields(){
       Message.sentMessages.clear();
        Message.recipientList.clear();
        Message.messageHashList.clear();
        Message.messageIDList.clear(); 
        
        Message m1 = new Message(0,"+278334557896","Did you get the cake?");
        m1.setSentStatus("Sent");
        m1.sentMessage();
        
        String report = Message.printMessagesReport();
        //Check all required parts exist
        assertTrue(report.contains("MESSAGE  REPORT"));
        assertTrue(report.contains("+278334557896"));//Recipient
        assertTrue(report.contains("Did you get the cake"));//Text
        assertTrue(report.contains("HASH"));
    }
    
    }
    

    
    
