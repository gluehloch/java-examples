package de.awtools.lambda;

import java.util.List;

public class ExtendsVsSuperGenerics {

    List<Long> longs() {
        return List.of(1L, 100L);
    }
    
    List<Number> numbers() {
        return List.of(2L, 3L);
    }
    
    public void toDo() {
        List<Long> longs = longs();
        List<Number> nums = numbers();
        List<Number> a = returnList(nums);
        List<Number> b = returnList(longs);
        
        assert a.size() == 2;
        assert b.size() == 2;
    }
    
    public List<Number> returnList(List<? super Long> longs) {
        longs.add(1L);
        // longs.add((Number) Long.valueOf(1L)); // Number ist super von Long. Does not compile!
        return numbers();
    }

    public List<Number> returnList2(List<? extends Number> longs) {
        // longs.add((Number) Long.valueOf(1L)); // Does not compile!
        // longs.add(Integer.valueOf(1)); // Integer ist extend von Number. Does not compile!
        return numbers();
    }
    
    public List<Number> returnList3(List<Number> numbers) {
        numbers.add(Long.valueOf(1L));
        numbers.add(Integer.valueOf(0));
        return numbers;
    }
    
    public List<?> returnList4(List<?> list) {
        // list.add("Test"); // Does not compile!
        // list.add(Integer.valueOf(0)); // Does not compile!
        return list;
    }
}
