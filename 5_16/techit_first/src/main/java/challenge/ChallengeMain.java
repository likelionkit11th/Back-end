package challenge;

import likelion.techit_first.Lecturer;
import likelion.techit_first.Person;
import likelion.techit_first.Student;

import java.util.*;

public class ChallengeMain {
    public static void main(String[] args){
        Person studentKim = new Student("kim",26);
        Person studentLee = new Student("Lee",28);
        Person studentPark = new Student("Park",24);
        Lecturer lecturer = new Lecturer("Park ParkPark",35);
        List<Person> everyone = new ArrayList<>();
        everyone.add(studentKim);
        everyone.add(studentLee);
        everyone.add(studentPark);
        everyone.add(lecturer);
        printItem(everyone);
    }
    public static <T> void printItem(Iterable<T> iterable){
        Iterator<T> iterator = iterable.iterator();
        if(!iterator.hasNext()){
            System.out.println("No Elements");
            return;
        }
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("idx\t\titem\n");
        for(int i=0;iterator.hasNext();i++){
            T item = iterator.next();
            strBuilder.append(String.format("%d\t\t%s\n",i,item));
        }
        System.out.println(strBuilder);
    }
}
