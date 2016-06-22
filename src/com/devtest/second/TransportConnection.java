package com.devtest.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Eduard on 19.06.2016.
 */
public class TransportConnection {

    private static final int MAX_COST = 200000;
    private static final int MAX_TEST = 10;
    private static final int MAX_CITY = 10000;


    private int s;//the number of tests
    private int n; //the number of cities
    private int m; //the number of all connection;
    private int r; //the number of paths to find
    private ArrayList<Integer> connection[]; //list connection
    private ArrayList<Integer> allCost[];//list cost
    private List<String> name; //array names cities
    private boolean used[]; //array to store information about passed and not passed the cities
    private int minCost[]; //array to store information about min cost transport connection with start city

    private int begin[]; //beginning path
    private int end[];//end path

    private BufferedReader cin;
    private PrintWriter cout;
    private StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        TransportConnection transportConnection = new TransportConnection();
        transportConnection.runTest();
    }

    private void runTest() throws IOException {
        cin = new BufferedReader(new InputStreamReader(System.in));
        cout = new PrintWriter(System.out);

        s = Integer.parseInt(cin.readLine());//read the number of tests
        if (s >= MAX_TEST) {  //check the number of test
            while (true) {
                if (s < MAX_TEST)break;
                System.out.println("Max the number test equals " + MAX_TEST);
                s = Integer.parseInt(cin.readLine());
            }
        }
        for (int j = 0; j < s; ++j) {
            readData();
            for (int i = 0; i < r; ++i) {
                dejkstra(begin[i]);
                printData(end[i]);
            }
        }
        cin.close();
        cout.close();
    }

    //read data with console
    private void readData() throws IOException {
        n = Integer.parseInt(cin.readLine());        //read the number of cities
        if (n >= MAX_CITY) { //check the number of city
            while (true) {
                if (n < MAX_CITY)break;
                System.out.println("Max the number city equals " + MAX_CITY);
                n = Integer.parseInt(cin.readLine());
            }
        }
        connection = new ArrayList[n];                      //initialized list connection
        for (int i = 0; i < n; ++i) {
            connection[i] = new ArrayList<Integer>();
        }
        allCost = new ArrayList[n];                   //initialized list
        for (int i = 0; i < n; ++i) {
            allCost[i] = new ArrayList<Integer>();
        }
        name = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            String nameStr = cin.readLine();//read name
            name.add(nameStr);             //name add in list
            int p = Integer.parseInt(cin.readLine());//the number of neighbours of city NAME

            for (int j = 0; j < p; ++j) {
                int u = i;
                tokenizer = new StringTokenizer(cin.readLine());
                int nr = Integer.parseInt(tokenizer.nextToken()); //index of a city connected to NAME
                int cost = Integer.parseInt(tokenizer.nextToken());//the transportation cost
                nr--;
                connection[u].add(nr);// index sity add in list
                allCost[u].add(cost); // cost add in list
            }
            m += p;//add the number all cost
        }
        r = Integer.parseInt(cin.readLine()); //read the number of path to find
        begin = new int[r];
        end = new int[r];
        for (int j = 0; j < r; ++j) {
            tokenizer = new StringTokenizer(cin.readLine());
            String beginName = tokenizer.nextToken();           //read begin city
            String endName = tokenizer.nextToken();             //read destination
            for (int i = 0; i < name.size(); ++i) {
                if (beginName.equals(name.get(i))) begin[j] = i;
                if (endName.equals(name.get(i))) end[j] = i;
            }
        }
    }

    //using the algorithm of Dijkstra with start city
    private void dejkstra(int s) {

        //initialized arrays
        used = new boolean[n];
        Arrays.fill(used, false);

        minCost = new int[n];
        Arrays.fill(minCost, MAX_COST);

        minCost[s] = 0; //min distance of start city equals 0
        for (int iter = 0; iter < n; ++iter) {
            int v = -1;
            int distV = MAX_COST;
            //select the city in which isn't found min cost
            for (int i = 0; i < n; ++i) {
                if (used[i]) {
                    continue;
                }
                if (distV < minCost[i]) {
                    continue;
                }
                v = i;
                distV = minCost[i];
            }
            //consider all transport cost emanating from the city
            for (int i = 0; i < connection[v].size(); ++i) {
                int u = connection[v].get(i);
                int weightU = allCost[v].get(i);
                //improve the cost
                if (minCost[v] + weightU < minCost[u]) {
                    minCost[u] = minCost[v] + weightU;
                }
            }
            //mark viewed the city, he found the min transport cost
            used[v] = true;
        }
    }

    //print result
    // if not connection between cities print -1
    private void printData(int v) throws IOException {
        if (minCost[v] != MAX_COST) {
            cout.print(minCost[v] + " ");
        } else {
            cout.print("-1 ");
        }
        cout.println();
    }
}