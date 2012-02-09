package org.simple.project.dao;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.simple.project.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.ExpectedException;

import static org.junit.Assert.*;

public class RoomTypeDaoTest extends BaseDaoTestCase {
    @Autowired
    private RoomTypeDao roomTypeDao;
    
    @Test
    public void testFindPersonByLastName() throws Exception {
        List<RoomType> roomType = roomTypeDao.findByName("Standard");
        assertTrue(roomType.size() > 0);
    }
    
    
    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemovePerson() throws Exception {
    	RoomType roomType = new RoomType();
    	roomType.setName("Eco Single");
    	roomType.setDescription("Eco1 : 1 Single Bed, Bathroom");

    	roomType = roomTypeDao.save(roomType);
        flush();

        roomType = roomTypeDao.get(roomType.getId());

        assertEquals("Eco Single", roomType.getName());
        assertNotNull(roomType.getId());

        log.debug("removing roomType...");

        roomTypeDao.remove(roomType.getId());
        flush();

        // should throw DataAccessException
        roomTypeDao.get(roomType.getId());
    }
    
}
