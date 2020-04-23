
import java.util.concurrent.Callable;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/4/8 1:25 下午
 */
public class CallableTask implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return null;
    }
}
