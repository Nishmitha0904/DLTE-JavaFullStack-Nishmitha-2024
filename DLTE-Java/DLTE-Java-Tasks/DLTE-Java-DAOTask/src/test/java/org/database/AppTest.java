package org.database;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppTest 
{
    @Mock
    private StorageTarget mockStorageTarget;
    @Mock
    private UserRepository mockUserRepository;
    private UserServices services;

    @Before
    public void prepareStore() {
        when(mockStorageTarget.getUserRepository()).thenReturn(mockUserRepository);
        services=new UserServices(mockStorageTarget);
    }

    @Test
    public void testSave() {
        User user1 = new User("sanath","San@1234","Bangalore","sanath@gmail.com",7865746785L,50000.0);
        User user2 = new User("rakesh","Rak@1234","Mangalore","rakesh@gmail.com",8766574832L, 60000.0);

        doNothing().when(mockUserRepository).save(user1);
        services.callSave(user1);
        verify(mockUserRepository).save(user1);
    }

    @Test
    public void testFindById() {
        String username1 = "sanath";
        String username2 = "rakesh";

        User user1 = new User("sanath","San@1234","Bangalore","sanath@gmail.com",7865746785L,50000.0);
        User user2 = new User("rakesh","Rak@1234","Mangalore","rakesh@gmail.com",8766574832L, 60000.0);

        when(mockUserRepository.findById(username2)).thenReturn(user2);

        User actualUser = services.callFindById(username2);

        assertNotSame(user1.getEmail(), actualUser.getEmail());
        assertEquals(user2.getUsername(), actualUser.getUsername());

    }
}
