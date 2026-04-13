import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
import java.io.*;
import java.net.*;
import java.time.*;

public class Lab11_Tests {
    /*
        Complete the test case below that checks to see that threads A and B have both contributed 100 entries respectively
        to the shared ArrayList when they have both finished running.
    */
    @Test
    public void test1() {
        Lab11_Thread threadA = new Lab11_Thread("A1", 100);
        Lab11_Thread threadB = new Lab11_Thread("B1", 100);

        threadA.start();
        threadB.start();

        while(threadA.isAlive() && threadB.isAlive()){
            continue;
        }
        ArrayList<String> dataA = threadA.getData();
        ArrayList<String> dataB = threadB.getData();
        assertEquals(dataA.size() + dataB.size(), "200");

    }

    /*
        Complete the test case below that checks to see if the shared ArrayList has at least 10 entries after 500ms of system time
    */
    @Test
    public void test2() {

        Lab11_Thread threadA = new Lab11_Thread("A2", 500);
        Lab11_Thread threadB = new Lab11_Thread("B2", 500);

        threadA.start();
        threadB.start();
        try {
            Thread.sleep(500);
        } catch (Exception e){
            e.printStackTrace();
        }
        ArrayList<String> dataA = threadA.getData();
        boolean check = dataA.size() >= 10;
        assertEquals(check, true);


    }

    /*
        Complete the test case below that checks to see if thread A finishes adding its 10 entries before thread B was allowed to
        add anything to the shared ArrayList
    */
    @Test
    public void test3() {
        Lab11_Thread threadA = new Lab11_Thread("A3", 10);
        Lab11_Thread threadB = new Lab11_Thread("B3", 10);

        threadA.start();

        try {
            threadA.join();
        } catch (Exception e){
            e.printStackTrace();
        }
        int count = 0;
        ArrayList<String> dataA = threadA.getData();
        for (int i = 0; i < dataA.size(); i++) {
            if (dataA.get(i).substring(0, 2).equals("A3")) {
                count++;
            }
        }
        threadB.start();
        assertEquals(count, 10);

    }
}