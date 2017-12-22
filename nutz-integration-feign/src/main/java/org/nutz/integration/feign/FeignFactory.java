package org.nutz.integration.feign;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.nutz.integration.feign.annotation.FeignClient;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.IocContext;
import org.nutz.ioc.ObjectProxy;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.resource.Scans;

/**
 *
 */
public class FeignFactory {

    private static final Log log = Logs.get();

    protected PropertiesProxy conf;

    protected NutIoc ioc;

    public void init() throws Exception {
        String prefix = "feign.";
        for (String key : conf.getKeys()) {
            if (key.length() < prefix.length()+1 || !key.startsWith(prefix))
                continue;
            String name = key.substring(prefix.length());
            if ("pkgs".equals(name)) {
                log.debug("found feign packages = " + conf.get(key));
                for (String pkg : Strings.splitIgnoreBlank(conf.get(key), ",")) {
                    addPackage(pkg);
                }
                continue;
            }
        }
    }

    public void addPackage(String pkg) {
        IocContext iocContext = ioc.getIocContext();
        for (Class<?> klass : Scans.me().scanPackage(pkg)) {
            FeignClient feignClient = klass.getAnnotation(FeignClient.class);
            if (feignClient != null) {

              //  ObjectProxy objectProxy =    ioc.addValueProxyMaker();
                Object o = Feign.builder()
                        .encoder(new JacksonEncoder())
                        .decoder(new JacksonDecoder())
                        .target(klass, "http://localhost:8080");
                //iocContext.save("app",klass.getSimpleName(),objectProxy.setObj(o));
                System.out.println("found feignclient annotation ,todo feign builder ");
            }
        }
    }


}
