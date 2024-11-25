package ofofo.services;

import ofofo.data.models.Entry;
import java.util.List;

public interface EntryService {
    Entry createEntry(String diaryId, String title, String body);
    Entry createEntry(String diaryId, Entry entry);
    void deleteEntry(Entry entryId);
    Entry findEntryById(long entryId);
    List<Entry> findAllEntryById(String username);
    long countNumberOfEntries(String username);
    long countNumberOfEntries();
    Entry updateEntry(String diaryId, long entryId, String title, String body);
    Entry getEntryById(long entryId);
    boolean isEntryExist();
    void deleteAllEntries(String username);
    void deleteAllEntriesByTitle(String currentUsername);
}
