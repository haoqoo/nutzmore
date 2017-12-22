package org.nutz.integration.feign;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

/**
 *
 */
public class FeignFactoryTest {

    NutIoc ioc;
    FeignFactory feignFactory;

    @Before
    public void setUp() throws Exception {
        // 模拟Mvc环境下的@IocBy
        ioc = new NutIoc(new ComboIocLoader("*anno", "org.nutz.integration.feign","*feign"));
        feignFactory = ioc.get(FeignFactory.class);
        System.out.println("setUp.........");
    }

    @Test
    public void test(){

        ioc.get(UserFeignClient.class).index();
        System.out.println(1234);
    }

    @After
    public void tearDown() throws Exception {
        if (ioc != null)
            ioc.depose();
    }
}
