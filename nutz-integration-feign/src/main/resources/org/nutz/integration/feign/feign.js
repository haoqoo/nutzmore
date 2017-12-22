var ioc = {
    // 读取配置文件
    conf: {
        type: "org.nutz.ioc.impl.PropertiesProxy",
        fields: {
            paths: ["feign.properties"]
            //paths : ["custom/"]
        }
    },
    feignFactory: {
        type: "org.nutz.integration.feign.FeignFactory",
        events: {
            create: "init"
        },
        fields: {
            ioc: {
                refer: "$ioc"
            },
            conf: {
                refer: "@confName"
            }
        }
    },

};
