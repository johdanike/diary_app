package ofofo.controllers;

import ofofo.data.models.Entry;
import ofofo.services.DiaryService;
import ofofo.services.DiaryServiceImpl;
import ofofo.services.EntryService;
import ofofo.services.EntryServiceImpl;

import java.util.List;

public class EntryController {
    private final EntryService entryService = new EntryServiceImpl();

    public List<Entry>findEnteriesBelongingTo(String username) {
        return entryService.findAllEntryById(username);
    }

    public Entry findEntryById(long id, String username) {
        return entryService.findEntryById(id);
    }

    public Entry createEntry(String diaryId, String title, String content) {
        return entryService.createEntry(diaryId, title, content);
    }

    public void deleteAll(String currentUsername) {
        entryService.deleteAllEntries(currentUsername);
    }

    public void deleteAllEntriesByTitle(String currentUsername) {
        entryService.deleteAllEntriesByTitle(currentUsername);
    }
}
