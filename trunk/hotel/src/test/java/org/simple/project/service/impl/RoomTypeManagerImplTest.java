package org.simple.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.service.impl.BaseManagerMockTestCase;
import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.simple.project.dao.RoomTypeDao;
import org.simple.project.model.RoomType;

import static org.junit.Assert.*;

public class RoomTypeManagerImplTest extends BaseManagerMockTestCase {
	private RoomTypeManagerImpl manager = null;
    private RoomTypeDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(RoomTypeDao.class);
        manager = new RoomTypeManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

//    @Test
//    public void testGetRoomType() {
//        log.debug("testing get...");
//
//        final Long id = 7L;
//        final RoomType roomType = new RoomType();
//
//        // set expected behavior on dao
//        context.checking(new Expectations() {{
//            one(dao).get(with(equal(id)));
//            will(returnValue(roomType));
//        }});
//
//        RoomType result = manager.get(id);
//        assertSame(roomType, result);
//    }
//
//    @Test
//    public void testGetRoomTypes() {
//        log.debug("testing getAll...");
//
//        final List roomTypes = new ArrayList();
//
//        // set expected behavior on dao
//        context.checking(new Expectations() {{
//            one(dao).getAll();
//            will(returnValue(roomTypes));
//        }});
//
//        List result = manager.getAll();
//
//        assertSame(roomTypes, result);
//    }
//
//    @Test
//    public void testSaveRoomType() {
//        log.debug("testing save...");
//
//        final RoomType roomType = new RoomType();
//        roomType.setName("Test");
//        roomType.setDescription("Test");
//        // enter all required fields
//        
//        // set expected behavior on dao
//        context.checking(new Expectations() {{
//            one(dao).save(with(same(roomType)));
//        }});
//
//        manager.save(roomType);
//    }
//
//    @Test
//    public void testRemoveRoomType() {
//        log.debug("testing remove...");
//
//        final Long id = 6L;
//
//        // set expected behavior on dao
//        context.checking(new Expectations() {{
//            one(dao).remove(with(equal(id)));
//        }});
//
//        manager.remove(id);
//    }
}
