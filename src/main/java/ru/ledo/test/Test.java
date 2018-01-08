package ru.ledo.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {

    public static void main (String[] args) {
        /**
        Formula testFormula = new FormulaImpl();

        testFormula.calculate(1);
        testFormula.sqrt(100);
         */

        Formula test = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        test.calculate(1);
        test.sqrt(100);


        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (o1, o2) -> o1.compareTo(o2));

        //Converter<String, Integer> convertor = (from) -> Integer.valueOf(from);

        Converter<String, Integer> convertor = Integer::valueOf;
        Integer result = convertor.conver("123");
        System.out.println("result = " + result);

        PersonFactory<Person> testFactory = Person::new;
        testFactory.createPerson("Peter", "Jackson");

        int num = 10;
        Converter<Integer, String> secondConveeter = (from) -> String.valueOf(num + from);
        secondConveeter.conver(2);


    }
}
