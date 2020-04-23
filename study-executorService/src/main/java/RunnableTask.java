
/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/4/8 1:26 下午
 */
public class RunnableTask implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
