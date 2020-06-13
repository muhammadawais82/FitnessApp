package logic2magic.com.health_fitness;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CalorieBurnedCalculator {

    // Fill with your data


    static double weight = 67.0; // kg

    static double height = 178.0; // cm

   // static double stepsCount = 4793;

//Don't edit below this


    final static double walkingFactor = 0.57;

    static double CaloriesBurnedPerMile;

    static double strip;

    static double stepCountMile; // step/mile

    static double conversationFactor;

    static double CaloriesBurned;

    static NumberFormat formatter = new DecimalFormat("#0.00");

    static double distance;


    public String colroires_result(double steps)
    {
        CaloriesBurnedPerMile = walkingFactor * (weight * 2.2);

        strip = height * 0.415;

        stepCountMile = 160934.4 / strip;

        conversationFactor = CaloriesBurnedPerMile / stepCountMile;

        CaloriesBurned = steps * conversationFactor;

        System.out.println();

        distance = (steps * strip) / 100000;

       // System.out.println("Distance: " + formatter.format(distance)
         //       + " km");

        return "Calories burned: "
                + formatter.format(CaloriesBurned) + " cal";

    }

}
