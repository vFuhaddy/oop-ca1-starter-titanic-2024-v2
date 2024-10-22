package org.example;
// CA1
import java.io. * ;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import static org.example.PassengerClass.SECOND;

public class Main {
    public static void main(String[] args) {

        String fileName = "titanic-data-100.csv"; // file should be in the project folder (below pom.xml)

        ArrayList<Passenger> passengerList= new ArrayList<>();

        loadPassengerDataFromFile( passengerList, fileName);

        displayAllPassengers( passengerList );


        // Assignment: Implement and test the following methods.
        // See the description of each method in the CA1 Specification PDF file from Moodle

        getPassengerNames(passengerList);
        getPassengersContainingNames(passengerList, "Paul");
        getPassengersOlderThan(passengerList, 25);
        countPassengersByGender(passengerList,"male");
        sumFares(passengerList);
        maleSurvivors(passengerList);
        ticketOwner(passengerList, "PC 17759");
        averageAge(passengerList);
        getPassengersByTicketClass(passengerList, SECOND);
        sortPassengersByPassengerId(passengerList);
        sortPassengersByName(passengerList);
        sortPassengersByAgeThenName(passengerList);
        sortPassengersByGenderThenPassengerNumber(passengerList);
        sortPassengersByFareThenSurvival(passengerList);
        sortPassengersByTicketClass(passengerList);
        sortPassengersByAgeUsingInnerClass(passengerList);
        sortPassengersByTicketNumberLambda(passengerList);
        sortPassengersByTicketNumberStatic(passengerList);
        findPassengerByTicketNumber(passengerList, "A/5 21171");
        findPassengerByPassengerId(passengerList, "53");

        System.out.println("Finished, Goodbye!");
    }

    public static String[] getPassengerNames(ArrayList<Passenger> passengers) {
        
        String[] names = new String[passengers.size()];
     
        for (int i = 0; i < passengers.size(); i++) {
            names[i] = passengers.get(i).getName();
        }
    
        return names;
    }

    public static List<Passenger> getPassengersContainingNames(ArrayList<Passenger> passengers, String name) 
    {
        List<Passenger> containsName = new ArrayList<>();
        
        for (Passenger passenger : passengers) {
            if (passenger.getName().toLowerCase().contains(name.toLowerCase())) {
                containsName.add(passenger);
            }
        }
        
        return containsName;
    }

    public static List<Passenger> getPassengersOlderThan(ArrayList<Passenger> passengers, int age) {
        List<Passenger> olderThan = new ArrayList<>();
        
        
        for (Passenger passenger : passengers) {
            if (passenger.getAge() > age) {
                olderThan.add(passenger);
            }
        }
        
        return olderThan;
    }

    public static int countPassengersByGender(ArrayList<Passenger> passengers,String gender) {
        int count = 0;
        
       
        for (Passenger passenger : passengers) {
            if (passenger.getGender().equalsIgnoreCase(gender)) {
                count++;
            }
        }
        
        return count;
    }

    public static double sumFares(ArrayList<Passenger> passengers) {
        double totalFares = 0;
        
       
        for (Passenger passenger : passengers) {
            totalFares = totalFares + passenger.getFare();
        }
        
        return totalFares;
    }

    public static String[] maleSurvivors(ArrayList<Passenger> passengers) {
        List<String> maleSurvivorNames = new ArrayList<>();
        
        
        for (Passenger passenger : passengers) {
            if (passenger.getSurvived() == 1 && "male".equalsIgnoreCase(passenger.getGender())) {
                maleSurvivorNames.add(passenger.getName()); 
            }
        }
        
        
        String[] result = maleSurvivorNames.toArray(new String[0]);
        return result;
    }

    public static Passenger ticketOwner(ArrayList<Passenger> passengers, String ticketNumber) {
        
        for (Passenger passenger : passengers) {
            if (passenger.getTicketNumber().equals(ticketNumber)) {
                return passenger; 
            }
        }
        
        return null;
    }
    
    public static double averageAge(ArrayList<Passenger> passengers) {

        int totalAge = 0;
        for (Passenger passenger : passengers) {
            totalAge = totalAge + passenger.getAge();
        }
        return (double) totalAge / passengers.size();
    }

    public static List<Passenger> getPassengersByTicketClass(ArrayList<Passenger> passengers,PassengerClass passengerClass) {
        List<Passenger> classPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getPassengerClass() == passengerClass) {
                classPassengers.add(passenger);
            }
        }
        return classPassengers;
    }

    public static List<Passenger> sortPassengersByPassengerId(ArrayList<Passenger> passengers) {
        
        List<Passenger> sortedPassengers = new ArrayList<>(passengers);
        
        
        sortedPassengers.sort(Comparator.comparing(Passenger::getPassengerId));
        
        return sortedPassengers;
    }

    public static List<Passenger> sortPassengersByName(ArrayList<Passenger> passengers) {
        
        List<Passenger> sortedPassengers = new ArrayList<>(passengers);
        
        sortedPassengers.sort(Comparator.comparing(Passenger::getName));
        
        return sortedPassengers;
    }

    public static List<Passenger> sortPassengersByAgeThenName(ArrayList<Passenger> passengers) {
        passengers.sort(Comparator.comparingInt(Passenger::getAge)
            .thenComparing(Passenger::getName));
        return passengers; 
    }

    public static List<Passenger> sortPassengersByGenderThenPassengerNumber(ArrayList<Passenger> passengers) {
        passengers.sort(Comparator.comparing(Passenger::getGender)
            .thenComparing(Passenger::getPassengerId));
        return passengers; 
    }

    public static List<Passenger> sortPassengersByFareThenSurvival(ArrayList<Passenger> passengers) {
        passengers.sort(Comparator.comparingDouble(Passenger::getFare)
            .thenComparing(Passenger::getSurvived));
        return passengers;
    }

    public static List<Passenger> sortPassengersByTicketClass(ArrayList<Passenger> passengers) {
        passengers.sort(Comparator.comparing(Passenger::getPassengerClass));
        return passengers;
    }

    public static List<Passenger> sortPassengersByAgeUsingInnerClass(ArrayList<Passenger> passengers) {
        passengers.sort(new Comparator<Passenger>() {
            @Override
            public int compare(Passenger p1, Passenger p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });
        return passengers; 
    }

    public static List<Passenger> sortPassengersByTicketNumberLambda(ArrayList<Passenger> passengers) {
        passengers.sort((p1, p2) -> p1.getTicketNumber().compareTo(p2.getTicketNumber()));
        return passengers; 
    }

    public static List<Passenger> sortPassengersByTicketNumberStatic(ArrayList<Passenger> passengers) {
        passengers.sort(Comparator.comparing(Passenger::getTicketNumber));
        return passengers; 
    }


//Source for reference: https://www.geeksforgeeks.org/binary-search-in-java/
    public static Passenger findPassengerByTicketNumber(ArrayList<Passenger> passengers, String ticketNumber) {
        passengers.sort(Comparator.comparing(Passenger::getTicketNumber)); 
        int index = java.util.Collections.binarySearch(passengers, new Passenger(ticketNumber), 
            Comparator.comparing(Passenger::getTicketNumber));
        return (index >= 0) ? passengers.get(index) : null;
    }
//Source for reference: https://www.geeksforgeeks.org/binary-search-in-java/
    public static  Passenger findPassengerByPassengerId(ArrayList<Passenger> passengers, String passengerId) {
        passengers.sort(Comparator.comparing(Passenger::getPassengerId)); 
        int index = java.util.Collections.binarySearch(passengers, new Passenger(passengerId), 
            Comparator.comparing(Passenger::getPassengerId));
        return (index >= 0) ? passengers.get(index) : null; 
    }

    /**
     * Populate an ArrayList of Passenger objects with data from a text file
     * @param passengerList - an ArrayList to be filled with Passenger objects
     * @param fileName - name of CSV text file containing passenger details
     */
    public static void loadPassengerDataFromFile( ArrayList<Passenger> passengerList, String fileName) {

        // Format of each row of data is:
        // Name,Age,Height(m),GPA  - these heading names are included as the first row in file
        // John Malone,20,1.78,3.55   for example

        // Use a Regular Expression to set both comma and newline as delimiters.
        //  sc.useDelimiter("[,\\r\\n]+");
        // Text files in windows have lines ending with "CR-LF" or "\r\n" sequences.
        // or may have only a newline - "\n"
        // Windows uses CRLF (\r\n, 0D 0A) line endings while Unix just uses LF (\n, 0A).
        //
        try (Scanner sc = new Scanner(new File(fileName))
                .useDelimiter("[,\\r\\n]+"))
        {

            // skip past the first line, as it has field names (not data)
            if(sc.hasNextLine())
                sc.nextLine();   // read the header line containing column titles, but don't use it

            // while there is a next token to read....
            System.out.println("Go...");

            while (sc.hasNext())
            {
                String passengerId = sc.next();    // read passenger ID
                int survived = sc.nextInt();     // 0=false, 1=true
                int passengerClass = sc.nextInt();  // passenger class, 1=1st, 2=2nd or 3rd
                String name = sc.next();
                String gender = sc.next();
                int age = sc.nextInt();
                int siblingsAndSpouses = sc.nextInt();
                int parentsAndChildren = sc.nextInt();
                String ticketNumber = sc.next();
                double fare = sc.nextDouble();
                String cabin = sc.next();
                String embarkedAt = sc.next();

                System.out.println(passengerId +", " + name);

                passengerList.add(
                        new Passenger( passengerId, survived, passengerClass,
                                name, gender, age, siblingsAndSpouses,parentsAndChildren,ticketNumber,
                                fare, cabin, embarkedAt)
                );
            }
        } catch (FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught. The file " +fileName+ " may not exist." + exception);
        }
    }

    public static void displayAllPassengers( ArrayList<Passenger> passengerList ) {
        System.out.println("Displaying all passengers:");
        for( Passenger passenger : passengerList)
        {
            System.out.println(passenger);
        }
    }
}