package edu.glut.tiny.data.dao;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import edu.glut.tiny.data.AppDatabase;
import edu.glut.tiny.data.entity.Contacts;

import static org.junit.Assert.*;



@RunWith(AndroidJUnit4.class)
public class ContactsDaoTest {

    private static final String TAG = "ContactsDaoTest";
    Context appContext;
    ContactsDao contactsDao;

    @Before
    public void init() {
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        contactsDao = AppDatabase.getInstance(appContext).getContactsDao();
    }

    @Test
    public void add() {
        try {
            List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
            for (String username : usernames) {
                contactsDao.add(new Contacts(username));
                System.out.println("hhhhhhhh");
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void select(){
        List<Contacts> contacts = contactsDao.selectAll();
        System.out.println("aaaaaaaaaa");
        for (Contacts contact : contacts) {
            System.out.println(contact.getContactsFriendUsername());
        }
    }

    @Ignore
    @Test
    public void deleteAll(){
        contactsDao.deleteAll();
        System.out.println("deletaAlldeletaAlldeletaAll");

    }
    @Test
    public void updateContactsSeq(){
//        contactsDao.updateContactsSeq();
        System.out.println("updateContactsSequpdateContactsSeq");

    }


    @Test
    public void selectContactsByUsername() {
        String admin = contactsDao.selectContactsByUsername("admin").get(0).getContactsFriendUsername();
        assertEquals(admin,"admin5");

    }

}