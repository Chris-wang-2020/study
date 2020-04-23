package increment;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/4/22 8:31 上午
 */
public class IncrementTest {

    static int i;

    public static void main(String[] args) {
        i = i++;
        int j = i++;
        int k = i + i++*++i;
        System.out.println("i =" +  i);
        System.out.println("j =" +  j);
        System.out.println("k =" +  k);
    }

    /**
     * 1、栈帧结构分局部变量表和操作数栈；
     * 2、i++ 和 ++i 类的操作
     */
}
