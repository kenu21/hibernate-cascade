package core.basesyntax.dao.impl;

import java.util.List;

import core.basesyntax.AbstractTest;
import core.basesyntax.dao.SmileDao;
import core.basesyntax.model.Smile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SmileDaoImplTest extends AbstractTest {
    private SmileDao smileDao;

    @Before
    public void setUp() {
        smileDao = new SmileDaoImpl(getEntityManagerFactory());
    }

    @Override
    protected Class<?>[] entities() {
        return new Class<?>[]{
                Smile.class
        };
    }

    @Test
    public void create_Ok() {
        Smile funny = new Smile("funny");
        Smile actual = smileDao.create(funny);
        Assert.assertNotNull("Check you have implemented the `create` method " +
                "in the SmileDaoImpl class", actual);
        Assert.assertNotNull("ID for smile should be autogenerated", actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void getAll_Ok() {
        Smile funny = new Smile("funny");
        Smile sad = new Smile("sad");
        Smile relieved = new Smile("relieved");
        Smile disappointed = new Smile("disappointed");

        smileDao.create(funny);
        smileDao.create(sad);
        smileDao.create(relieved);
        smileDao.create(disappointed);

        List<Smile> smiles = smileDao.getAll();
        Assert.assertNotNull(smiles);
        Assert.assertFalse(smiles.isEmpty());
        Assert.assertEquals(4, smiles.size());
        System.out.println(smiles);
    }
}
