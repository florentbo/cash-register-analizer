package be.florentbo.register.service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        List<String> keywords = Arrays.asList("Apple", "Ananas", "Mango", "Banana", "Beer");
        Map<Character, List<String>> result = keywords.stream().sorted()
                .collect(Collectors.groupingBy(it -> it.charAt(0)));
        System.out.println(result);

        List<Florent> florents = Arrays.asList(new Florent("a",2), new Florent("a",47), new Florent("b",2));
        Map<String,Integer> map = new HashMap<>();

        florents.forEach(flo -> map.compute(flo.keyFlo, (k, v) -> v == null ? flo.value : v + flo.value));
        System.out.println(map);

        Map<Integer, Long> groupByLevel = florents.stream().collect(
                Collectors.groupingBy(Florent::getValue,Collectors.counting()));

        Map<String, Integer> groupByLevel3 = florents.stream().collect(
                Collectors.groupingBy(Florent::getKeyFlo,Collectors.summingInt(Florent::getValue)));

        List<StudentClass> studentClasses = new ArrayList<>();

        studentClasses.add(new StudentClass("Kumar", 101, "Intro to Web"));
        studentClasses.add(new StudentClass("White", 102, "Advanced Java"));
        studentClasses.add(new StudentClass("Kumar", 101, "Intro to Cobol"));
        studentClasses.add(new StudentClass("Sargent", 101, "Intro to Web"));
        studentClasses.add(new StudentClass("Sargent", 102, "Advanced Web"));
        Function<StudentClass, Double> getLevel = StudentClass::getLevel;
        Map<Double, Long> groupByLevel2 = studentClasses.stream().collect(
                Collectors.groupingBy(getLevel,Collectors.counting()));

    }

    static class StudentClass {

        private String teacher;
        private double level;
        private String className;

        public String getTeacher() {
            return teacher;
        }

        public double getLevel() {
            return level;
        }

        public String getClassName() {
            return className;
        }

        public StudentClass(String teacher, double level, String className) {
            this.teacher = teacher;
            this.level = level;
            this.className = className;
        }
    }
    static class Florent{
        private String keyFlo;
        private int value;

        public Florent(String keyFlo, int value) {
            this.keyFlo = keyFlo;
            this.value = value;
        }

        public String getKeyFlo() {
            return keyFlo;
        }

        public int getValue() {
            return value;
        }
    }
}
