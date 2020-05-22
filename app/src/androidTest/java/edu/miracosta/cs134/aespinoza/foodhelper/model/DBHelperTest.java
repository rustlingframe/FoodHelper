package edu.miracosta.cs134.aespinoza.foodhelper.model;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBHelperTest {

    private static DBHelper testDB ;
    private static Context testContext ;

    private FoodResource testFoodResource ;

    @Before
    public void setUp() throws Exception {
        testContext = InstrumentationRegistry.getTargetContext() ;
        testDB = new DBHelper(testContext) ;

        testContext.deleteDatabase(DBHelper.DATABASE_NAME) ;
        /**
         * long id,   String organizationName,  String name, String address, String city,
         *                          String state, String zipCode, String phone, double latitude, double longitude,
         *                          String eventDescription, int isDiscounted, int isFree
         */
        testFoodResource = new FoodResource(12, "testOrg", "testName",
                "testAddress", "TestCity", "TestState", "TestZip",
                "TestPhone", 1.1, 1.1, "TestDesc",
                1, 2) ;
    }

    @After
    public void tearDown() throws Exception {
        testDB.deleteAllFoodResource();
    }

    @Test
    public void onCreate() {
        assertEquals("Testing name of database", DBHelper.DATABASE_NAME, testDB.getDatabaseName()) ;
    }

    @Test
    public void onUpgrade() {
        assertEquals("Testing name of database", DBHelper.DATABASE_NAME, testDB.getDatabaseName()) ;
    }

    @Test
    public void addFoodResource() {
        // Put tests in a loop:
        for (int i = 1; i <= 10; i++)
        {
            testDB.addFoodResource(testFoodResource);
            assertEquals("Testing size of database", i, testDB.getAllFoodResource().size()) ;
            assertEquals("Test all fields of course added to database", testFoodResource.getLocation(), testDB.getFoodResource(i).getLocation()) ;
        }
    }

    @Test
    public void getAllFoodResource() {
        assertEquals("Testing size of all courses", 0, testDB.getAllFoodResource().size());
        testDB.addFoodResource(testFoodResource);
        assertEquals("Testing size of all courses", 1, testDB.getAllFoodResource().size());
    }

    @Test
    public void deleteFoodResource() {
        // Sad path: abnormal behavior
        testDB.deleteFoodResource(testFoodResource) ;
        assertEquals("Testing size of all courses", 0, testDB.getAllFoodResource().size());
        assertEquals("Testing size of all courses", 0, testDB.getAllFoodResource().size());

        // Happy path: normal behavior
        testDB.addFoodResource(testFoodResource) ;
        assertEquals("Testing size of all courses", 1, testDB.getAllFoodResource().size());

        testDB.deleteFoodResource(testFoodResource) ;
        assertEquals("Testing size of all courses", 0, testDB.getAllFoodResource().size());

    }

    @Test
    public void deleteAllFoodResource() {
        testDB.deleteAllFoodResource() ;
        assertEquals("Testing deleteAllOfferings", 0, testDB.getAllFoodResource().size()) ;

        testDB.addFoodResource(testFoodResource);
        testDB.addFoodResource(testFoodResource);
        testDB.addFoodResource(testFoodResource);

        testDB.deleteAllFoodResource() ;
        assertEquals("Testing deleteAllOfferings", 0, testDB.getAllFoodResource().size()) ;
    }

    @Test
    public void getFoodResource() {
        testDB.addFoodResource(testFoodResource) ;
        assertEquals("Testing getFoodResource", testFoodResource.getId(), testDB.getFoodResource(1).getId()) ;

        testFoodResource = new FoodResource(12, "testOrg", "testName",
                "testAddress", "TestCity", "TestState", "TestZip",
                "TestPhone", 1.1, 1.1, "TestDesc",
                1, 2) ;
        testDB.addFoodResource(testFoodResource) ;

        assertEquals("Testing getFoodResource", testFoodResource, testDB.getFoodResource(2));
    }
}