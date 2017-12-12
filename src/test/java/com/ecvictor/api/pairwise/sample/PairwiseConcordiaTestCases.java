package com.ecvictor.api.pairwise.sample;

import com.ecvictor.api.pairwise.IInventory;
import com.ecvictor.api.pairwise.PairwiseInventoryFactory;
import org.junit.Test;

import java.util.List;
import java.util.Map;



import com.ecvictor.api.pairwise.IInventory;
import com.ecvictor.api.pairwise.PairwiseInventoryFactory;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by caoc on 3/25/2017.
 */
public class PairwiseConcordiaTestCases {
    @Test
    public void buildTestSets() {
        String NAV_SCENARIO =
                "Browser: Chrome, Firefox, InternetExplorer, Safari"
                        + "\nTopMenu: About, Academics, Admissions, Campus life, Research, International"
                        + "\nSection: Title, Body, Button, Link, Image";


        //First, generate the list of vectors we *want*
        IInventory inventory = PairwiseInventoryFactory.generateParameterInventory(NAV_SCENARIO);
        List<Map<String, String>> rawDataSet = inventory.getTestDataSet().getTestSets();

        //Now, go through the vectors in the database to figure out what we already *have*
        // If we don't have it already, create it
        int index = 0;
        for (Map<String, String> rawTestCase: rawDataSet) {
            System.out.println(String.format("Test Case %03d: [%s] [%s] [%s]",index++, rawTestCase.get("Browser"), rawTestCase.get("TopMenu"), rawTestCase.get("Section")));
        }

    }
}

