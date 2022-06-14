package com.tape.service.impl;

import com.tape.dao.IArticleDao;
import junit.framework.TestCase;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticelServiceImplTest extends TestCase {

    @Autowired
    private IArticleDao articleDao;

    public void testcountArticle() {
        ArticelServiceImpl articelServiceImpl = new ArticelServiceImpl();
        Integer count = articelServiceImpl.countArticle(1);
        Assert.assertEquals(java.util.Optional.of(10),count);
    }
}