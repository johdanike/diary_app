package ofofo.data.repositories;

import ofofo.data.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntryRepositoryImplTest {
    Entry entry;
    EntryRepositoryImpl repo;
    @BeforeEach
    public void setUp() {
        entry = new Entry();
        repo = new EntryRepositoryImpl();
        repo.deleteAll();
    }

    @Test
    public void test_thatEntryListIsEmpty() {
        assertTrue(repo.isEmpty());
    }
    @Test
    public void test_thatIAddEntryToRepo_repoIsNotEmpty() {
        assertTrue(repo.isEmpty());
        entry.setTitle("Another Day at SemiColon");
        entry.setContent("Today’s passage exposes the damage that the wicked and idolatrous lifestyle of Manasseh");
        entry.setDiaryId("1");
        repo.save(entry);
        assertFalse(repo.isEmpty());
        assertEquals(1, repo.countEntries());
    }
    @Test
    public void test_thatEntryCanBeUpdated() {
        assertTrue(repo.isEmpty());
        entry.setTitle("Another Day at SemiColon");
        entry.setContent("Today’s passage exposes the damage that the wicked and idolatrous lifestyle of Manasseh");
        entry.setDiaryId("1");
        repo.save(entry);
        System.out.println(entry.getEntryId());
        assertEquals(1, repo.countEntries());
        entry.setTitle("Any other Day at SemiColon");
        entry.setContent("All about dto's and extra classes - verbose");
        entry.setDiaryId("1");
        entry.setEntryDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        repo.save(entry);
        assertFalse(repo.isEmpty());
        assertEquals(1, repo.countEntries());
    }
    @Test
    public void test_thatEntryCanBeDeleted() {
        assertTrue(repo.isEmpty());
        entry.setTitle("Another Day at SemiColon");
        entry.setContent("Today’s passage exposes the damage that the wicked and idolatrous lifestyle of Manasseh");
        entry.setDiaryId("1");
        repo.save(entry);
        assertEquals(1, repo.countEntries());
        assertFalse(repo.isEmpty());
        repo.deleteEntry(entry);
        assertEquals(0, repo.countEntries());
    }
    @Test
    public void test_thatICreateEntries_iCanFindEntriesById() {
        assertTrue(repo.isEmpty());
        entry.setTitle("Another Day at SemiColon");
        entry.setContent("Today was about Maven");
        entry.setDiaryId("1");
        repo.save(entry);
        assertEquals(1, repo.countEntries());
        Entry entryId = repo.getEntryById(entry.getEntryId());
        assertEquals("1", entry.getDiaryId());
        assertEquals(entryId, repo.getEntryById(entryId.getEntryId()));
    }
    @Test
    public void test_thatICreateEntry_iCanFindEntryByTitle(){
        assertTrue(repo.isEmpty());
        entry.setTitle("Another Day at SemiColon");
        entry.setContent("Today was about Maven");
        entry.setDiaryId("1");
        entry.setEntryId(entry.getEntryId());
        repo.save(entry);
        assertEquals(1, repo.countEntries());
        assertEquals("Another Day at SemiColon", repo.findByTitle("Another Day at SemiColon").getTitle());
    }
    @Test
    public void test_thatICreateEntries_iCanFindEntriesByContent(){
        assertTrue(repo.isEmpty());
        entry.setTitle("Another Day at SemiColon");
        entry.setContent("Today was about Maven");
        entry.setDiaryId("1");
        entry.setEntryId(entry.getEntryId());
        repo.save(entry);
        assertEquals(1, repo.countEntries());
        assertEquals("Today was about Maven", entry.getContent());
        assertEquals("Today was about Maven", repo.findByBody("Today was about Maven").getContent());
    }
    @Test
    public void test_thatICreateEntries_iCanFindEntriesByDiaryId(){
        assertTrue(repo.isEmpty());
        entry.setTitle("Another Day at SemiColon");
        entry.setContent("Today was about Maven");
        entry.setDiaryId("1");
        entry.setEntryId(entry.getEntryId());
        repo.save(entry);
        assertEquals(1, repo.countEntries());
        assertEquals(1, repo.findByDiaryId("1").size());
    }
    @Test
    public void test_thatICreateEntries_iCanFindEntriesByEntryId(){
        assertTrue(repo.isEmpty());
        entry.setTitle("Another Day at SemiColon");
        entry.setContent("Today was about Maven");
        entry.setDiaryId("1");
        entry.setEntryId(entry.getEntryId());
        Entry savedEntry = repo.save(entry);
        assertEquals(1, repo.countEntries());
        System.out.println(savedEntry);
    }
    @Test
    public void test_thatICreateEntries_iCanViewAllEntries(){
        assertTrue(repo.isEmpty());
        Entry entry1 = new Entry();
        entry1.setTitle("Another Day at SemiColon");
        entry1.setContent("Today was about Maven");
        entry1.setDiaryId("1");
        entry1.setEntryId(1);
        LocalDateTime dateTime = LocalDateTime.now();
        entry1.setEntryDate(String.valueOf(dateTime));
        Entry saved = repo.save(entry1);
        System.out.println(saved);
        assertEquals(1, repo.countEntries());


        Entry entry2 = new Entry();
        entry2.setTitle("Wednesday's SemiColon");
        entry2.setContent("Fabulous is addressing the cohort");
        entry2.setDiaryId("2");
        entry2.setEntryId(2);
        LocalDateTime dateTime2 = LocalDateTime.now();
        entry2.setEntryDate(String.valueOf(dateTime2));
        Entry savedEntry = repo.save(entry2);
        System.out.println(savedEntry);
        assertEquals(2, repo.countEntries());
        List<Entry> actual = repo.getAllEnteries();
        System.out.println(actual);
        assertEquals(actual, repo.getAllEnteries());
    }
    @Test
    public void test_thatEnteriesCanBeUpdated(){
        assertTrue(repo.isEmpty());
        entry.setTitle("Another Day at SemiColon");
        entry.setContent("Today was about Maven");
        entry.setDiaryId("1");
        entry.setEntryId(1);
        LocalDateTime dateTime = LocalDateTime.now();
        entry.setEntryDate(String.valueOf(dateTime));
        Entry savedRepo = repo.save(entry);
        System.out.println(savedRepo);
        assertEquals(1,repo.countEntries());
        entry.setTitle("It's a new day at SemiColon");
        entry.setContent("We had Database class; was not quite engaging");
        entry.setDiaryId("1");
        entry.setEntryId(1);
        entry.setEntryDate(String.valueOf(dateTime));
        Entry saved = repo.save(entry);
        System.out.println(saved);
        assertEquals(1,repo.countEntries());
    }
}