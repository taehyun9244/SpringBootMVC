package hello.itemservice.web.test;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TestRepository {

    private static final Map<Long, Test> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public Test save(Test test) {
        test.setId(++sequence);
        store.put(test.getId(), test);
        return test;
    }

}
