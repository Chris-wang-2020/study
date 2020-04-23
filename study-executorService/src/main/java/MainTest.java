import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/4/8 1:28 下午
 */
public class MainTest {

    public static void main(String[] args) throws Exception{
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
