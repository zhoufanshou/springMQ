package ws;

import javax.jws.WebService;

@WebService(name = "algorithm", // 暴露服务名称
        targetNamespace = "http://www.baidu.com"// 命名空间,一般是接口的包名倒序
)
public interface DemoService {

    public String sayHello(String user);

}
