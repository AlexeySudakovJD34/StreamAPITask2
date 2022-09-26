import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
//        System.out.println(persons);

        Stream<Person> stream1 = persons.stream();
        long kids = stream1.filter(x -> x.getAge() < 18).count();
        System.out.println("Количество несовершеннолетних: " + kids);

        Stream<Person> stream2 = persons.stream();
        List<String> draftees = stream2.
                filter(x -> x.getSex() == Sex.MAN).
                filter(x -> x.getAge() >= 18).
                filter(x -> x.getAge() < 27).
                map(x -> x.getName() + " " + x.getFamily()).collect(Collectors.toList());
        if (!draftees.isEmpty()) {
            System.out.println("Список призывников: " + draftees);
        }


        Stream<Person> stream3 = persons.stream();
        List<Person> highEducCandidates = stream3.
                filter(x -> x.getEducation() == Education.HIGHER).
                filter(x -> x.getAge() >= 18).
                filter(x -> (x.getSex() == Sex.MAN) ? x.getAge() < 65 : x.getAge() < 60).
                sorted(Comparator.comparing(Person::getFamily)).collect(Collectors.toList());
        if (!highEducCandidates.isEmpty()) {
            System.out.println("Список потенциально работоспособных людей с высшим образованием: "
                    + highEducCandidates);
        }
    }
}