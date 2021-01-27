package org.example.disruptor.exp04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created at 2020/11/30
 *
 * @author quzile3
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
public class ArrayTests {

    public static void main(String[] args) {
        Object[] o = new Object[4];

        // https://dev.mysql.com/doc/refman/8.0/en/non-typed-operators.html
        // https://www.runoob.com/mysql/mysql-operator.html

        // 算术运算符 + - * / %
        // 比较运算符
        // 逻辑运算符
        // 位运算符

        Map<String, Object> params1 = new HashMap<>();
        params1.put("a1.or", "");
        params1.put("a2.or", "");
        params1.put("a3.or", "");

        Map<String, Object> params0 = new HashMap<>();
        params0.put("a", "a");
        params0.put("b", "b");
        params0.put("c.between", Arrays.asList("", ""));
        params0.put("d.gt", "a");
        params0.put("e.eq", "a");
        params0.put("f.like", "a");
        params0.put("g.regexp", "a");
        params0.put("h.in", "a");
        params0.put("i.is_not_null", "a");
        params0.put("j.is_null", "a");
        params0.put("$ref_1", params1);

    }
}
