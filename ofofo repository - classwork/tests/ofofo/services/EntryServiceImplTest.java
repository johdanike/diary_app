package ofofo.services;

import ofofo.data.models.Entry;
import ofofo.data.repositories.EntryRepositories;
import ofofo.data.repositories.EntryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntryServiceImplTest {
    private EntryServiceImpl service;
    private EntryRepositories repositories;


    @BeforeEach
    public void setUp() {
        service = new EntryServiceImpl();
        repositories = new EntryRepositoryImpl();
        repositories.deleteAll();
    }

    @Test
    public void test_userCanAddEntry() {
        assertFalse(service.isEntryExist());
        service.createEntry("Dani", "Daniel's first day at Semicolon","Another freaking day at SemiColon Africa");
        service.createEntry("Fems", "Today", "Another busy database day at Semicolon Africa");
        assertEquals(2, service.countNumberOfEntries());
        assertEquals(1, service.countNumberOfEntries("Dani"));
        assertTrue(service.isEntryExist());
    }
    @Test
    public void test_userCanDeleteAllEntries_byUsername() {
        assertFalse(service.isEntryExist());
        service.createEntry("Dani", "Daniel's first day at Semicolon","Another freaking day at SemiColon Africa");
        service.createEntry("Dani", "Today", "Another busy database day at Semicolon Africa");
        service.createEntry("Frau_Esther", "Today", "Was with C21, taught them Database-mySQL, They were famished");
        assertEquals(2, service.countNumberOfEntries("Dani"));
        service.deleteAllEntries("Dani");
        assertEquals(0, service.countNumberOfEntries("Dani"));
        assertEquals(1, service.countNumberOfEntries());
    }
    @Test
    public void test_userCanDeleteEntryById() {
        assertFalse(service.isEntryExist());
        Entry daniEntry = service.createEntry("Dani", "Daniel's first day at Semicolon","Another freaking day at SemiColon Africa");
        Entry femsEntry = service.createEntry("Fems", "Today", "Another busy database day at Semicolon Africa");
        service.deleteEntry(daniEntry);
        assertEquals(0, service.countNumberOfEntries("Dani"));
        assertEquals("Fems",service.getEntryById(2).getDiaryId());
        assertEquals(1, service.countNumberOfEntries());
        System.out.println(femsEntry);
    }
    @Test
    public void test_userCanUpdateEntry() {
        assertFalse(service.isEntryExist());
        service.createEntry("Dani", "Daniel's first day at Semicolon","Another freaking day at SemiColon Africa");
        service.createEntry("Fems", "Today", "Another busy database day at Semicolon Africa");
        assertEquals("Fems",service.getEntryById(2).getDiaryId());

        service.updateEntry("Dani", 1, "Tomorrow","Another busy database day at Semicolon Africa");
        service.updateEntry("Fems", 2, "Yesterday","Another busy database day at Semicolon Africa");
        assertEquals("Tomorrow", service.getEntryById(1).getTitle());
        assertEquals("Yesterday", service.getEntryById(2).getTitle());
    }
    @Test
    public void test_entriesCanBeFoundByDiaryId(){
        assertFalse(service.isEntryExist());
        service.createEntry("Dani", "Daniel's first day at Semicolon","Another freaking day at SemiColon Africa");
        service.createEntry("Fems", "Today", "Another busy database day at Semicolon Africa");
        assertEquals(2, service.countNumberOfEntries());
        service.findAllEntryById("Dani");
        assertEquals(1, service.countNumberOfEntries("Dani"));
    }
    @Test
    public void test_entriesTitleCannotBeNull_throwsException_throwsException(){
        assertFalse(service.isEntryExist());
        service.createEntry("Dani", "Daniel's first day at Semicolon","Another freaking day at SemiColon Africa");
        service.createEntry("Fems", "Today", "Another busy database day at Semicolon Africa");
        assertThrows(IllegalArgumentException.class, () -> service.createEntry("Gbemi", "", "got a promotion at work today"));
    }

    @Test
    public void test_entryCanBeCreated_diaryIdCannotBeNull_throwsException(){
        assertFalse(service.isEntryExist());
        String diaryId = null;
        String title = "Daniel's first day at Semicolon";
        String body = "Another busy database day at Semicolon Africa";
        assertThrows(IllegalArgumentException.class, () -> service.createEntry(diaryId, title, body));
    }

    @Test
    public void test_entryCanBeCreated_titleCannotBeNull_throwsException(){
        assertFalse(service.isEntryExist());
        String diaryId = "Dani";
        String title = "";
        String body = "Another busy database day at Semicolon Africa";
        assertThrows(IllegalArgumentException.class, () -> service.createEntry(diaryId, title, body));
    }

    @Test
    public void test_entryCanBeCreated_bodyCannotBeNull_throwsException(){
        assertFalse(service.isEntryExist());
        String diaryId = "Dani";
        String title = "Daniel's first day at Semicolon";
        String body = "";
        assertThrows(IllegalArgumentException.class, () -> service.createEntry(diaryId, title, body));
    }

    @Test
    public void test_entryCanBeCreated_noFieldCanBeNull_throwsException(){
        assertFalse(service.isEntryExist());
        String diaryId = null;
        String title = null;
        String body = null;
        assertThrows(IllegalArgumentException.class, () -> service.createEntry(diaryId, title, body));
    }

    @Test public void test_entryFieldsCannotBeNull_throwsException(){
        assertFalse(service.isEntryExist());
        assertThrows(IllegalArgumentException.class, () -> service.createEntry(null, null, ""));
    }


}