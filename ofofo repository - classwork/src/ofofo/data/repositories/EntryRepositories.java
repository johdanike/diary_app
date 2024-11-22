package ofofo.data.repositories;

import ofofo.data.models.Entry;

import java.time.LocalDateTime;
import java.util.List;

public interface EntryRepositories {
    List<Entry> getAllEnteries();
    Entry getEntryById(long id);
    void deleteEntry(Entry entry);
    Entry save (Entry entry);
    int countEntries();
    Entry findByTitle(String title);
    Entry findByBody(String body);
    LocalDateTime date(LocalDateTime dateTime);
    boolean isEmpty();
    void deleteAll();
    void deleteAllEntriesById(String id);
    List<Entry>findByDiaryId(String diaryId);
    Entry findByEntryId(long id);
}
