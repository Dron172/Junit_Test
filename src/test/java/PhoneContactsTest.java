import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class PhoneContactsTest {

    private static PhoneContacts testPhoneContacts = new PhoneContacts();

    @BeforeEach
    void initTest() {
        System.out.println("test start");
        testPhoneContacts.clearPhoneContacts();
    }


    @Test
    void testCreateSomeGroup() {

        // given
        String[] groupOfContacts = {"контакты без группы", "тестовая группа контактов",};
        //when
        testPhoneContacts.createGroup("тестовая группа контактов");
        //then
        Assertions.assertArrayEquals(groupOfContacts, testPhoneContacts.getGroups());

    }

    @ParameterizedTest
    @MethodSource("contactsTest")
    void testAddSomeContactToGroupsParam(Contacts testContact) {

        // given
        Contacts[] contactList = {testContact};
        String[] groups = {"тестовая группа контактов"};
        testPhoneContacts.createGroup("тестовая группа контактов");

        //when
        testPhoneContacts.addContactToGroups(testContact, groups);

        //then
        Assertions.assertArrayEquals(contactList, testPhoneContacts.getContacts("тестовая группа контактов"));

    }

    static Stream<Contacts> contactsTest() {
        return Stream.of(new Contacts("Иван", "Иванов", "+79098882222"),
                new Contacts("Петр", "Петров", "+79000555707"),
                new Contacts("Сергей", "Сергеев", "+7919858721"));
    }


    @Test
    void testGetContacts() {
        // given
        Contacts contact = new Contacts("Иван", "Иванов", "+7919858721");
        testPhoneContacts.createGroup("тестовая группа контактов");
        testPhoneContacts.addContactToGroups(contact, new String[]{"тестовая группа контактов"});

        //when
        Contacts[] contacts = testPhoneContacts.getContacts("тестовая группа контактов");

        //then
        Assertions.assertNotNull(contacts[0]);
        Assertions.assertEquals(contact, contacts[0]);

    }

    @AfterEach
    void finalizeTest() {
        System.out.println("Test complete");
    }


}