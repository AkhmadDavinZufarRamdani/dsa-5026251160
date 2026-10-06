package lw03.unguided;

import java.io.File;
import java.util.ArrayList;
import java.util.* ;

public class Main {
    public static void main(String[] args) throws Exception {

        
        Set<String> registeredStudents = new HashSet<>();

        
        Set<String> checkedInStudents = new HashSet<>();

        
        List<String> checkInResults = new ArrayList<>();

        
        Scanner registrationScanner = new Scanner(
                new File("src/lw03/unguided/registrations.txt")
        );

        while (registrationScanner.hasNextLine()) {
            String studentId = registrationScanner.nextLine();

            registeredStudents.add(studentId);
        }

        registrationScanner.close();

        
        Scanner checkInScanner = new Scanner(
                new File("src/lw03/unguided/checkins.txt")
        );

        int rejectedAttempts = 0;

        while (checkInScanner.hasNextLine()) {
            String studentId = checkInScanner.nextLine();

            
            if (!registeredStudents.contains(studentId)) {

                checkInResults.add(
                        studentId + ": Rejected (not registered)"
                );

                rejectedAttempts++;

            
            } else if (checkedInStudents.contains(studentId)) {

                checkInResults.add(
                        studentId + ": Rejected (already checked in)"
                );

                rejectedAttempts++;

            
            } else {

                checkedInStudents.add(studentId);

                checkInResults.add(
                        studentId + ": Checked in"
                );
            }
        }

        checkInScanner.close();

        //  mahasiswa tidak hadir
        int absentStudents =
                registeredStudents.size() - checkedInStudents.size();

        //  hasil
        System.out.println("===== Event Check-In Results =====");

        for (String result : checkInResults) {
            System.out.println(result);
        }

        System.out.println("===== Final Event Summary =====");
        System.out.println(
                "Registered students: " + registeredStudents.size()
        );
        System.out.println(
                "Successful check-ins: " + checkedInStudents.size()
        );
        System.out.println(
                "Absent students: " + absentStudents
        );
        System.out.println(
                "Rejected attempts: " + rejectedAttempts
        );
    }
}