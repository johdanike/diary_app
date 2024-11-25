package ofofo.services;

import ofofo.data.models.Entry;
import ofofo.data.repositories.EntryRepositories;
import ofofo.data.repositories.EntryRepositoryImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EntryServiceImpl implements EntryService {

    private final EntryRepositories entryRepository;
    private boolean isExisting = false;

    public EntryServiceImpl() {
        entryRepository = new EntryRepositoryImpl();
    }

    @Override
    public Entry createEntry(String diaryId, String title, String body) {
        validateEntryParams(diaryId, title, body);

            Entry entry = new Entry();
            entry.setDiaryId(diaryId);
            entry.setTitle(title);
            entry.setContent(body);
            entry.setEntryId(generateEntryId());
            entry.setEntryDate(LocalDateTime.now().format(DATE_FORMAT));
            isExisting = true;

            return entryRepository.save(entry);
    }

    @Override
    public Entry createEntry(String diaryId, Entry entry) {
        Entry newEntry = new Entry();
        validateEntryParams(diaryId, entry.getTitle(), entry.getContent());
        newEntry.setDiaryId(diaryId);
        newEntry.setTitle(entry.getTitle());
        newEntry.setContent(entry.getContent());
        newEntry.setEntryDate(LocalDateTime.now().format(DATE_FORMAT));
        isExisting = true;

        return entryRepository.save(newEntry);
    }

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private void validateEntryParams(String diaryId, String title, String body) {
        if (diaryId == null || diaryId.isEmpty()) {
            throw new IllegalArgumentException("diaryId cannot be null");
        }
        else if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be null");
        }
        else if (body == null || body.isEmpty()) {
            throw new IllegalArgumentException("body cannot be null");
        }
    }

    private long generateEntryId() {
        return entryRepository.countEntries() + 1;
    }

    @Override
    public void deleteEntry(Entry entryId) {
        entryRepository.deleteEntry(entryId);
    }

    @Override
    public Entry findEntryById(long entryId) {
        if(!isExisting){
            throw new IllegalArgumentException("Entry not found");
        }
        return entryRepository.findByEntryId(entryId);
    }

    @Override
    public List<Entry> findAllEntryById(String username) {
        return List.copyOf(entryRepository.findByDiaryId(username));
    }

    @Override
    public long countNumberOfEntries(String username) {
        long count = 0;
        for (Entry entry : findEntryByDiaryId(username)) {
            if (entry.getDiaryId().equals(username)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public long countNumberOfEntries() {
        return entryRepository.countEntries();
    }


    private List<Entry> findEntryByDiaryId(String username) {
        return entryRepository.findByDiaryId(username);
    }

    @Override
    public Entry updateEntry(String diaryId, long entryId, String title, String body) {
        Entry entry = findEntryById(entryId);
        System.out.println(entry);
        if(entry != null) {
            entry.setEntryDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            entry.setTitle(title);
            entry.setContent(body);
            entry.setDiaryId(diaryId);
            entry.setEntryId(entryId);
            entryRepository.save(entry);
            System.out.println(entry);
            return entry;
        }
        return null;
    }

    @Override
    public Entry getEntryById(long entryId) {
        return entryRepository.findByEntryId(entryId);
    }

    @Override
    public boolean isEntryExist() {
        return isExisting;
    }


    @Override
    public void deleteAllEntries(String username) {
        entryRepository.deleteAllEntriesById(username);
    }

    @Override
    public void deleteAllEntriesByTitle(String currentUsername) {
        if(findEntryByDiaryId(currentUsername) != null) {
            entryRepository.deleteAllEntriesById(currentUsername);
        }
    }


}
