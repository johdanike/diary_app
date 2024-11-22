package ofofo.data.repositories;

import ofofo.data.models.Entry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepositories{
    private final List<Entry> entries = new ArrayList<>();
    private static final LocalDateTime entryUpdateDate = LocalDateTime.now();

    public EntryRepositoryImpl() {

    }


    @Override
    public List<Entry> getAllEnteries() {
        return List.copyOf(entries);
    }

    @Override
    public Entry getEntryById(long id) {
        for (Entry entry : entries) {
            if (entry.getEntryId() == id) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public void deleteEntry(Entry entry) {
        if(findByEntryId(entry.getEntryId()) != null) {
            this.entries.remove(entry);
        }
    }

    private long generateEntryId(){
        return entries.size();
    }

    public Entry save(Entry entry) {
        if(findByEntryId(entry.getEntryId()) != null){
            update(entry);
            return entry;
        }
        entries.add(entry);
        return entry;
    }

    private void update(Entry entry) {
        Entry updatedEntry = findByEntryId(entry.getEntryId());
        entries.set(entries.indexOf(updatedEntry), entry);
    }

    public Entry findByEntryId(long id) {
        for (Entry entry : entries) if (entry.getEntryId() == id) return entry;
        return null;
    }


    @Override
    public int countEntries() {
        return entries.size();
    }

    @Override
    public Entry findByTitle(String title) {
        for (Entry entry : entries) if (entry.getTitle() == title) return entry;
        return null;
    }

    @Override
    public Entry findByBody(String body) {
        for (Entry entry : entries) if (entry.getContent() == body) return entry;
        return null;
    }

    @Override
    public LocalDateTime date(LocalDateTime dateTime) {
        return dateTime;
    }

    @Override
    public boolean isEmpty() {
        return countEntries() == 0;
    }

    @Override
    public void deleteAll() {
        entries.clear();
    }

    @Override
    public void deleteAllEntriesById(String id) {
        entries.removeIf(entry -> entry.getDiaryId().equals(id));
    }

    @Override
    public List<Entry> findByDiaryId(String diaryId) {
        return entries.stream().filter(entry -> entry.getDiaryId().equalsIgnoreCase(diaryId)).toList();
    }
}
