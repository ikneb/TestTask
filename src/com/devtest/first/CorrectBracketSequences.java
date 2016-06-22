package com.devtest.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Eduard on 19.06.2016.
 */
public class CorrectBracketSequences {

    public static void main(String[] args) throws IOException {
        CorrectBracketSequences correctBracketSequences = new CorrectBracketSequences();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //read the number bracket
        int n = Integer.parseInt(reader.readLine());
        System.out.println(correctBracketSequences.calc(n));
    }

    //I use the algorithm Catalina to find the solution
    private int calc(int n) {
        int[] array = new int[n + 1];
        array[0] = 1;
        for (int i = 1; i <= n; ++i) {
            array[i] = 0;
            for (int j = 0; j < i; ++j) {
                array[i] += array[j] * array[i - 1 - j];
            }
        }
        return array[n];
    }
}
