package pset2;

import java.util.*;

public class Graph {
    private int numNodes; // number of nodes in the graph
    private boolean[][] edges;

    // edges[i][j] is true if and only if there is an edge from node i to node j

    // class invariant: fields "edges" is non-null;
    //                  "edges" is a square matrix;
    //                  numNodes is number of rows in "edges"
    public Graph(int size) {
        numNodes = size;
        if (numNodes <= 0) {
            throw new IllegalArgumentException();
        }
        edges = new boolean[size][size];
    }

    public String toString() {
        return "numNodes: " + numNodes + "\n" + "edges: " + Arrays.deepToString(edges);
    }

    public boolean equals(Object o) {
        if (o == null || o.getClass() != Graph.class) return false;
        return toString().equals(o.toString());
    }

    public void addEdge(int from, int to) {
        if (!(from < 0 || from > this.numNodes - 1 || to < 0 || to > this.numNodes - 1)) {
            edges[from][to] = true;
        }
    }

    public Set<String> getPrimePaths() {
        // Compute all prime paths on this graph and return them in the form of a set of strings
        // Each string should be in the form of a sequence of integers enclosed within square brackets [ ]
        // For example, the path from node 0 to node 1 to node 2 is represented as String "[0,1,2]"
        //  note how the integers are comma-separated, no spaces between

        // your code goes here
        ArrayList<ArrayList<String>> pathsArray = new ArrayList<>();
        Set<Integer> visitedSources = new HashSet<>();
        ArrayList<ArrayList<Integer>> adjList = createAdjLists(edges);
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][i]) { // Self loop added as a path if there exists an edge from a node to itself.
            ArrayList<String> temp = new ArrayList<>();
            temp.add(String.valueOf(i));
            temp.add(String.valueOf(i));
            pathsArray.add(temp);
            }
            visitedSources.add(i);
            ArrayList<String> path = new ArrayList<>();
            path.add(String.valueOf(i));
            getSourcePaths(pathsArray, path, adjList, visitedSources);
        }
        removeInnerCycles(pathsArray); // in case some inner cycles slip by :))
        removeSimplePaths(pathsArray);
        return formulatePaths(pathsArray);
    }

    private ArrayList<ArrayList<String>> getSourcePaths(ArrayList<ArrayList<String>> pathsArray,
                                                        ArrayList<String> path,
                                                        ArrayList<ArrayList<Integer>> adjList,
                                                        Set<Integer> visitedSources) { // Modified DFS algorithm
        int node = Integer.parseInt(path.get(path.size() - 1)); // Begin path finding from last added node
        visitedSources.add(node);
        if (nodeHasNeighbors(adjList, node)) {
            for (int nextNode : adjList.get(node)) {
                if (nextNode == Integer.parseInt(path.get(0)) &&
                        visitedSources.contains(nextNode)) { // in case of a cycle with first
                    path.add(String.valueOf(nextNode));      // and last nodes repeated to
                    pathsArray.add(path);                    // prevent infinite loops and overflow
                    continue;
                }
                if (path.contains(String.valueOf(nextNode)) &&
                        nextNode != Integer.parseInt(path.get(0))) { // in case of an inner
                    pathsArray.add(path);                            // cycle within a path
                    continue;                                        // hopefully no more overflow
                }
                ArrayList<String> anotherPath = new ArrayList<>(path);
                anotherPath.add(String.valueOf(nextNode));
                pathsArray = getSourcePaths(pathsArray, anotherPath, adjList, visitedSources);
            }
        } else {
            pathsArray.add(path);
        }
        return pathsArray;
    }

    private boolean nodeHasNeighbors(ArrayList<ArrayList<Integer>> adjList, int node) { // node has edges?
        return adjList.get(node).size() > 0;
    }

    private ArrayList<ArrayList<Integer>> createAdjLists(boolean[][] edges) { // adjacency lists creation
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            ArrayList<Integer> nodeAdjList = new ArrayList<>();
            adjList.add(nodeAdjList);
            for (int j = 0; j < edges.length; j++) {
                if (i != j && edges[i][j]) {
                    adjList.get(i).add(j);
                }
            }
        }
        return adjList;
    }

    private Set<String> formulatePaths(ArrayList<ArrayList<String>> pathsArray) {
        // Required formula for paths
        Set<String> paths = new HashSet<>();

        for (ArrayList<String> Path : pathsArray) {
            StringBuilder formulatedPath = new StringBuilder().append("[");
            for (String node : Path) {
                if (formulatedPath.length() > 1)
                    formulatedPath.append(",");
                formulatedPath.append(node);
            }
            formulatedPath.append("]");
            String temp = formulatedPath.toString();
            paths.add(temp);
        }
        return paths;
    }

    private void removeInnerCycles(ArrayList<ArrayList<String>> pathsArray) {
        for (ArrayList<String> path : pathsArray) {
            for (int i = 0; i < path.size(); i++) {
                for (int j = 1; j < path.size() - 1; j++) {
                    if (path.get(i).equals(path.get(j)) && i != j) {
                        path.remove(j);
                        i--;
                        break;
                    }
                }
            }
        }
    }

    private void removeSimplePaths(ArrayList<ArrayList<String>> paths) {
        for (int i = 0; i < paths.size(); i++) {
            for (int j = 1; j < paths.size(); j++) {
                if (!paths.get(i).equals(paths.get(j))) {
                    if (Collections.indexOfSubList(paths.get(i), paths.get(j)) != -1) {
                        paths.remove(paths.get(j));
                        j--;
                    } else if (Collections.indexOfSubList(paths.get(j), paths.get(i)) != -1) {
                        paths.remove(paths.get(i));
                        i--;
                        break;
                    }
                }
            }
        }
    }
}