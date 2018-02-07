package util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author rakibamin
 * @since 1/20/2018
 */
public class ApplicationUtils{

    public static String getTodayIn(String timeZone) {
        return ZonedDateTime.now(ZoneId.of(timeZone)).format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"));
    }

    public static void printNowInAllTimeZones() {
        System.out.println("US/Samoa: " + getTodayIn("US/Samoa"));
        System.out.println("US/Aleutian: " + getTodayIn("US/Aleutian"));
        System.out.println("US/Hawaii: " + getTodayIn("US/Hawaii"));
        System.out.println("US/Alaska: " + getTodayIn("US/Alaska"));
        System.out.println("US/Pacific: " + getTodayIn("US/Pacific"));
        System.out.println("US/Pacific-New: " + getTodayIn("US/Pacific-New"));
        System.out.println("US/Arizona: " + getTodayIn("US/Arizona"));
        System.out.println("US/Mountain: " + getTodayIn("US/Mountain"));
        System.out.println("US/Central: " + getTodayIn("US/Central"));
        System.out.println("US/East-Indiana: " + getTodayIn("US/East-Indiana"));
        System.out.println("US/Eastern: " + getTodayIn("US/Eastern"));
        System.out.println("US/Indiana-Starke: " + getTodayIn("US/Indiana-Starke"));
        System.out.println("US/Michigan: " + getTodayIn("US/Michigan"));
        System.out.println("Pacific/Guam: " + getTodayIn("Pacific/Guam"));
        System.out.println("America/Puerto_Rico: " + getTodayIn("America/Puerto_Rico"));
        System.out.println("Asia/Bangkok: " + getTodayIn("Asia/Bangkok"));
        System.out.println("Asia/Colombo: " + getTodayIn("Asia/Colombo"));
        System.out.println("Asia/Dhaka: " + getTodayIn("Asia/Dhaka"));
        System.out.println("Asia/Jakarta: " + getTodayIn("Asia/Jakarta"));
        System.out.println("Asia/Kathmandu: " + getTodayIn("Asia/Kathmandu"));
        System.out.println("Asia/Kolkata: " + getTodayIn("Asia/Kolkata"));
        System.out.println("Asia/Kuala_Lumpur: " + getTodayIn("Asia/Kuala_Lumpur"));
        System.out.println("Asia/Manila: " + getTodayIn("Asia/Manila"));
        System.out.println("Asia/Phnom_Penh: " + getTodayIn("Asia/Phnom_Penh"));
        System.out.println("Asia/Singapore: " + getTodayIn("Asia/Singapore"));
        System.out.println("Asia/Thimphu: " + getTodayIn("Asia/Thimphu"));
    }

    public static void printTimeZonesInYesterday() {

        List<String> timeZones = Arrays.asList("US/Samoa","US/Aleutian","US/Hawaii","US/Alaska","US/Pacific",
                "US/Pacific-New", "US/Arizona","US/Mountain","US/Central","US/East-Indiana","US/Eastern",
                "US/Indiana-Starke","US/Michigan", "Pacific/Guam","America/Puerto_Rico","Asia/Bangkok","Asia/Colombo",
                "Asia/Dhaka","Asia/Jakarta", "Asia/Kathmandu","Asia/Kolkata","Asia/Kuala_Lumpur","Asia/Manila",
                "Asia/Phnom_Penh","Asia/Singapore", "Asia/Thimphu");

        for (String timeZone : timeZones) {

            if(ZonedDateTime.now(ZoneId.of(timeZone)).toLocalDate()
                    .isBefore(ZonedDateTime.now(ZoneId.of("Asia/Dhaka")).toLocalDate())) {

                System.out.println(timeZone + " " + getTodayIn(timeZone));
            }
        }
    }

    public static void waitForSeconds(int seconds) {
        try {
            SECONDS.sleep(seconds);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
