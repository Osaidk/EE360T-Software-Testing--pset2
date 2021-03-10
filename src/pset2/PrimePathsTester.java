package pset2;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PrimePathsTester {

    @Test
    public void test0() {
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        Set<String> expected = new HashSet<>(Arrays.asList("[0,1,2]", "[0,2]"));
        assertEquals(expected, g.getPrimePaths());
    }

    @Test
    public void test1() {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 2);
        g.addEdge(3, 3);
        Set<String> expected = new HashSet<>(Arrays.asList("[0,1,2,0]", "[1,2,0,1]",
                "[2,0,1,2]", "[3,3]", "[2,2]"));
        assertEquals(expected, g.getPrimePaths());
    }

    @Test
    public void test2() {
        Graph g = new Graph(10);
        g.addEdge(0, 1);
        g.addEdge(1, 6);
        g.addEdge(6, 9);
        g.addEdge(4, 4);
        Set<String> expected = new HashSet<>(Arrays.asList("[0,1,6,9]", "[2]", "[7]",
                "[8]", "[3]", "[5]", "[3]", "[4,4]"));
        assertEquals(expected, g.getPrimePaths());
    }

    @Test
    public void test3() {
        Graph g = new Graph(2);
        g.addEdge(0, 1);
        g.addEdge(1, 0);
        Set<String> expected = new HashSet<>(Arrays.asList("[0,1,0]","[1,0,1]"));
        assertEquals(expected, g.getPrimePaths());
    }

    @Test
    public void test4() {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 1);
        Set<String> expected = new HashSet<>(Arrays.asList("[3,4,1,3]", "[4,1,3,4]",
                "[1,3,4,1]", "[0,2]", "[0,1,3,4]"));
        assertEquals(expected, g.getPrimePaths());
    }

    @Test
    public void test5() {
        Graph g = new Graph(3);
        Set<String> expected = new HashSet<>(Arrays.asList("[1]", "[2]", "[0]"));
        assertEquals(expected, g.getPrimePaths());
    }

    @Test
    public void test6() {
        Graph g = new Graph(10);
        g.addEdge(0, 1);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 0);
        g.addEdge(3, 1);
        g.addEdge(1, 4);
        g.addEdge(8, 9);
        g.addEdge(9, 7);
        g.addEdge(6, 6);
        g.addEdge(6, 5);
        Set<String> expected = new HashSet<>(Arrays.asList("[8,9,7]", "[2,0,2]",
                "[3,1,0,2]", "[1,0,1]", "[2,0,1,4]", "[3,1,4]", "[0,1,0]", "[0,2,0]",
                "[6,6]", "[6,5]"));
        assertEquals(expected, g.getPrimePaths());
    }

    @Test
    public void test7() {
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(1, 2);
        Set<String> expected = new HashSet<>(Arrays.asList("[1,0,2,1]", "[0,1,2,0]", "[2,0,2]", "[2,1,0,2]"
                ,"[2,1,2]", "[1,0,1]", "[0,2,1,0]", "[1,2,1]", "[2,0,1,2]", "[1,2,0,1]", "[0,1,0]",
                "[0,2,0]"));
        assertEquals(expected, g.getPrimePaths());
    }

    @Test
    public void test8() {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        Set<String> expected = new HashSet<>(Arrays.asList("[1,2,3,1]", "[3,1,2,3]", "[0,1,2,3]",
                "[1,0,1]", "[2,3,1,0]", "[0,1,0]", "[2,3,1,2]"));
        assertEquals(expected, g.getPrimePaths());
    }

    @Test
    public void test9() {
        Graph g = new Graph(1);
        g.addEdge(0, 0);
        Set<String> expected = new HashSet<>(Collections.singletonList("[0,0]"));
        assertEquals(expected, g.getPrimePaths());
    }

    @Test
    public void test10() {
        Graph g = new Graph(1);
        Set<String> expected = new HashSet<>(Collections.singletonList("[0]"));
        assertEquals(expected, g.getPrimePaths());
    }

    @Test
    public void test11() {
        Graph g = new Graph(4);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(3, 1);
        Set<String> expected = new HashSet<>(Arrays.asList("[0,2]", "[3,1,2]"));
        assertEquals(expected, g.getPrimePaths());
    }

    @Test
    public void test12() {
        Graph g = new Graph(2);
        g.addEdge(0, 0);
        g.addEdge(1, 1);
        g.addEdge(0, 1);
        Set<String> expected = new HashSet<>(Arrays.asList("[0,0]", "[0,1]", "[1,1]"));
        assertEquals(expected, g.getPrimePaths());
    }
    
    // your tests for method "getPrimePaths" in class "Graph" go here

    // you must provide at least 5 test methods;
    // each test method has at least 1 invocation of getPrimePaths;
    // each test method creates exactly 1 graph
    // each test method creates a unique graph w.r.t. "equals" method
    // each test method has at least 1 test assertion;
    // your test methods provide full statement coverage of your
    //   implementation of getPrimePaths and any helper methods
    // no test method directly invokes any method that is not
    //   declared in the Graph class as given in this homework

    // we will not test your Graph with more than 20 nodes

    // ...
}
