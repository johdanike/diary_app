package ofofo.services;

import ofofo.data.models.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServiceImplTest {
    Diary diary;
    DiaryService diaryService;
    @BeforeEach
    public void setUp() {
        diary = new Diary();
        diaryService = new DiaryServiceImpl();
    }

    @Test
    public void iCanRegisterNewUser_userCountIsOne(){
        diary.setUsername("JohDan");
        diary.setPassword("password");
        diaryService.register(diary.getUsername(), diary.getPassword());
        assertEquals(1, diaryService.countDiary());
    }
    @Test
    public void test_thatIRegisterNewUserTwice_userCountIsStillOne(){
        diary.setUsername("JohDan");
        diary.setPassword("password");
        diaryService.register(diary.getUsername(), diary.getPassword());
        assertEquals(1, diaryService.countDiary());
        diary.setUsername("JohDan");
        diary.setPassword("password");
        assertThrows(IllegalArgumentException.class, () -> diaryService.register(diary.getUsername(), diary.getPassword()));
    }
    @Test
    public void test_thatUserCanLogin(){
        diary.setUsername("JohDan");
        diary.setPassword("password");
        diaryService.register(diary.getUsername(), diary.getPassword());
        diaryService.logOut(diary.getUsername());
        assertFalse(diaryService.isLoggedIn());
        diaryService.login(diary.getUsername(), diary.getPassword());
        assertTrue(diaryService.isLoggedIn());
    }
    @Test
    public void test_thatUserCanLogout(){
        diary.setUsername("JohDan");
        diary.setPassword("password");
        diaryService.register(diary.getUsername(), diary.getPassword());
        diaryService.login(diary.getUsername(), diary.getPassword());
        assertTrue(diaryService.isLoggedIn());
        diaryService.logOut(diary.getUsername());
        assertFalse(diaryService.isLoggedIn());
    }
    @Test
    public void test_thatUserCanChangePassword(){
        diary.setUsername("JohDan");
        diary.setPassword("password");
        diaryService.register(diary.getUsername(), diary.getPassword());

        Diary newDiary = diaryService.changePassword(diary.getUsername(), diary.getPassword(), "newPassword");
        assertEquals( "newPassword", newDiary.getPassword());
        assertFalse(diaryService.isLoggedIn());
        diaryService.login(newDiary.getUsername(), newDiary.getPassword());
        assertTrue(diaryService.isLoggedIn());
    }
    @Test
    public void test_thatUserCanChangeUsername(){
        diary.setUsername("JohDan");
        diary.setPassword("password");
        diaryService.register(diary.getUsername(), diary.getPassword());
        diaryService.login(diary.getUsername(), diary.getPassword());
        assertTrue(diaryService.isLoggedIn());
        diaryService.logOut(diary.getUsername());
        assertFalse(diaryService.isLoggedIn());

        Diary newDiary = diaryService.changeUsername(diary.getUsername(), diary.getPassword(), "Fems");
        assertEquals("fems", newDiary.getUsername());
        assertFalse(diaryService.isLoggedIn());
        diaryService.login(newDiary.getUsername(), newDiary.getPassword());
        assertTrue(diaryService.isLoggedIn());
        diaryService.logOut(newDiary.getUsername());
        assertFalse(diaryService.isLoggedIn());
    }
    @Test
    public void test_thatUserCanDeleteDiary(){
        diary.setUsername("JohDan");
        diary.setPassword("password");
        diaryService.register(diary.getUsername(), diary.getPassword());
        diaryService.login(diary.getUsername(), diary.getPassword());
        assertTrue(diaryService.isLoggedIn());
        diaryService.deleteDiary(diary.getUsername(), diary.getPassword());
        assertFalse(diaryService.isLoggedIn());
        assertEquals(0, diaryService.countDiary());
    }
    @Test
    public void test_thatICanCreateMultipleDiaries(){
        diary.setUsername("JohDan");
        diary.setPassword("password");
        diaryService.register(diary.getUsername(), diary.getPassword());
        assertEquals(1, diaryService.countDiary());

        diary.setUsername("Femi");
        diary.setPassword("anotherPassword");
        diaryService.register(diary.getUsername(), diary.getPassword());
        assertEquals(2, diaryService.countDiary());

        diary.setUsername("Funmi");
        diary.setPassword("yetAnotherPassword");
        diaryService.register(diary.getUsername(), diary.getPassword());
        assertEquals(3, diaryService.countDiary());
    }
    @Test
    public void test_thatICanCreateMultiply_3_Diaries_iDeleteTheSecondDiary_countIsNow_2(){
        diary.setUsername("JohDan");
        diary.setPassword("password");
        diaryService.register(diary.getUsername(), diary.getPassword());
        assertEquals(1, diaryService.countDiary());

        diary.setUsername("Femi");
        diary.setPassword("anotherPassword");
        diaryService.register(diary.getUsername(), diary.getPassword());
        assertEquals(2, diaryService.countDiary());

        diary.setUsername("Funmi");
        diary.setPassword("yetAnotherPassword");
        diaryService.register(diary.getUsername(), diary.getPassword());
        assertEquals(3, diaryService.countDiary());

        diaryService.deleteDiary("femi", "anotherPassword");
        assertEquals(2, diaryService.countDiary());
    }
    @Test
    public void test_thatDeletedDiaryCannotBeLoggedInto(){
        diary.setUsername("JohDan");
        diary.setPassword("password");
        diaryService.register(diary.getUsername(), diary.getPassword());
        diaryService.login(diary.getUsername(), diary.getPassword());
        assertTrue(diaryService.isLoggedIn());
        assertEquals(1, diaryService.countDiary());

        diaryService.deleteDiary("johdan", "password");
        assertFalse(diaryService.isLoggedIn());
        assertEquals(0, diaryService.countDiary());
    }

}