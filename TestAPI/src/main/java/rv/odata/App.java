package main.java.rv.odata; 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import main.java.rv.odata.ticket.IssuePriority;
import main.java.rv.odata.ticket.Note;
import main.java.rv.odata.ticket.NoteType;
import main.java.rv.odata.ticket.Ticket;

/*
 * Please use the data appropriate for your scenario in the calls below !!
 */
public class App 
{
	private static final Logger logger = Logger
			.getLogger(App.class.getName());
	
    public static void main( String[] args ) throws Exception
    {
        ServiceTicketODataConsumer consumer = new ServiceTicketODataConsumer();
        
        // Read ticket list
        consumer.readTickets();
        
        // Ticket creation
        Ticket newTicket = new Ticket();
        newTicket.setIssueCategory("CA_199");
        newTicket.setProductId("P400101");
        newTicket.setName("Testing ticket creation via OData");
        newTicket.setIssuePriority(IssuePriority.HIGH);
        
        // Add an incident description
        Note incDesc = new Note();
        incDesc.setNoteType(NoteType.INC_DESC);
        incDesc.setNoteDescription("Incident description for ticket creation via OData");
        
        List<Note> incDescList = new ArrayList<Note>();
        incDescList.add(incDesc);
        
        newTicket.setNotes(incDescList);
        
        Optional<String> ticketUUID = consumer.createTicket(newTicket);
        
        logger.info("Ticket UUID " + ticketUUID.get());
        
        // Read the ticket by Id
        Ticket updateTicket = consumer.readTicketById(ticketUUID.get());
        
       
        // Add a new note..
        Note customerNote = new Note();
        customerNote.setNoteType(NoteType.CUST_NOTE);
        customerNote.setNoteDescription("Updated comment from the customer");
        
        consumer.updateTicket(updateTicket, customerNote);
        
        // Read ticket after the update
        
        Ticket updateResult = consumer.readTicketById(ticketUUID.get());
        
        logger.info(updateResult.toString());
    }
}