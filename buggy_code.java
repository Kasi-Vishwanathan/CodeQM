import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        numbers.add(6);

        // Remove even numbers using modern removeIf method
        numbers.removeIf(n -> n % 2 == 0);

        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }

        System.out.println("Sum of odd numbers: " + sum);
        System.out.println("Odd numbers: " + numbers);
    }
}
import java.util.Objects;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public static void main(String[] args) {
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Alice", 30);
        System.out.println(p1.equals(p2)); // Now correctly prints true
        System.out.println(p1 == p2);      // Prints false (reference comparison)
    }
}
package cusip;

public class CusipValidator {
    public static boolean validateCusip(String cusip) {
        if (cusip == null || cusip.length() != 9) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 8; i++) {
            char c = cusip.charAt(i);
            int num;
            if (Character.isLetter(c)) {
                num = Character.toUpperCase(c) - 'A' + 10; // A=10, B=11, ..., Z=35
            } else if (Character.isDigit(c)) {
                num = Character.getNumericValue(c);
            } else {
                return false; // Invalid character
            }

            if (i % 2 == 1) { // Multiply by 2 for even positions (1-based index)
                num *= 2;
            }

            sum += (num / 10) + (num % 10);
        }

        int computedCheckDigit = (10 - (sum % 10)) % 10;
        char actualCheckDigit = cusip.charAt(8);
        return actualCheckDigit == (computedCheckDigit + '0');
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide at least one CUSIP to validate.");
            return;
        }

        for (String cusip : args) {
            System.out.println(cusip + ": " + CusipValidator.validateCusip(cusip));
        }
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BuggyCode4 {
    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
        myList.add("Hello");
        myList.add("world");
        myList.add("java");
        myList.add("PROgramming");
        
        System.out.println("Original list: " + myList);
        List<String> processedList = processList(myList);
        System.out.println("Processed list: " + processedList);
    }

    public static List<String> processList(List<String> strings) {
        List<String> processedList = new ArrayList<>();
        for (String s : strings) {
            if (s.length() > 3) {
                s = s.toUpperCase(Locale.ENGLISH);
            } else {
                s = s.toLowerCase(Locale.ENGLISH);
            }
            processedList.add(s);
        }
        return processedList;
    }
}
package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataProcessor {
    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(2, 3, 5, 8, 10);
        System.out.println("Results: " + processData(data));
    }

    public static List<Integer> processData(List<Integer> input) {
        return input.stream()
                .filter(num -> num > 5)
                .map(num -> num * 2)
                .collect(Collectors.toList());
    }
}