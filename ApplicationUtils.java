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

    /*
    * Returns current time in that timezone as a java.lang.String
    * @param timeZone Standard Time Zone
    * TODO: pass time/date format as argument
    */
    public static String getTodayIn(String timeZone) {
        return ZonedDateTime.now(ZoneId.of(timeZone)).format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"));
    }
    
    /*
    * Prints current time in all US and some Asia timezones
    */
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

    /*
    * Prints all time zones with current time whose time is behind today in Asia/Dhaka Time Zone
    */
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

    /*
    * Uses thread sleep for waiting, may seem like a brute wait, but useful
    * @param seconds No of seconds this thread should sleep
    */
    public static void waitForSeconds(int seconds) {
        try {
            SECONDS.sleep(seconds);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /*
    * Fills up any HTML form using jQuery and Selenium API
    * @param driver WebDriver
    * @param input any combination of input string for fill up
    */
    public static void fillUpAnyForm(WebDriver driver, String input) {

        List<WebElement> selectList = driver.findElements(By.tagName("select"));

        for (WebElement select: selectList) {

            try {

                Select dropDown = new Select(select);

                if (!dropDown.isMultiple()) {

                    try {
                        dropDown.selectByIndex(dropDown.getOptions().size() - 1);

                    } catch (NoSuchElementException noSuchElementException) {
                         continue;
                    }
                }

            } catch (StaleElementReferenceException staleElementReferenceException) {

                continue;
            }
        }

        JavascriptExecutor javascriptExecutor =  (JavascriptExecutor) driver;

        String passwordScript = "$('input:password').each(function(){" +
                                    "$(this).val('maxLength');" +
                                "});";

        javascriptExecutor.executeScript(passwordScript);

        String textareaScript =
                "var allTextArea = document.getElementsByTagName(\"textarea\");"
                + "for (var i=0, max = allTextArea.length; i < max; i++) {"
                +       "$(allTextArea[i]).val('" + input + "');"
                + "}";

        javascriptExecutor.executeScript(textareaScript);

        String filteredInputScript =
                "var allInputs = document.getElementsByTagName(\"input\");"
                + "for (var i=0, max = allInputs.length; i < max; i++) {"
                +       "if($(allInputs[i]).attr('type') == \"checkbox\") {"
                +           "$(allInputs[i]).attr('checked','checked');"
                +       "} else if($(allInputs[i]).attr('type') == \"text\" && !$(allInputs[i]).hasClass('ui-autocomplete-input')) {" +
                                "if(($(allInputs[i]).attr('id') != undefined" +
                                    "&& !($(allInputs[i]).attr('id').toLowerCase().indexOf(\"date\") > 0) " +
                                    "&& !($(allInputs[i]).attr('id').toLowerCase().indexOf(\"email\") > 0) " +
                                    "&& !($(allInputs[i]).attr('id').toLowerCase().indexOf(\"time\") > 0)) ||" +
                                    "($(allInputs[i]).attr('name') != undefined " +
                                    "&& !($(allInputs[i]).attr('name').toLowerCase().indexOf(\"date\") > 0) " +
                                    "&& !($(allInputs[i]).attr('name').toLowerCase().indexOf(\"email\") > 0) " +
                                    "&& !($(allInputs[i]).attr('name').toLowerCase().indexOf(\"time\") > 0)) ||" +
                                    "($(allInputs[i]).attr('className') != undefined " +
                                    "&& !($(allInputs[i]).attr('className').toLowerCase().indexOf(\"date\") > 0) " +
                                    "&& !($(allInputs[i]).attr('className').toLowerCase().indexOf(\"email\") > 0) " +
                                    "&& !($(allInputs[i]).attr('className').toLowerCase().indexOf(\"time\") > 0))) {" +
                                       "if($(allInputs[i]).attr('maxLength') >= 9) {"+
                                            "$(allInputs[i]).val('" + input + "');" +
                                        "} else {" +
                                            "$(allInputs[i]).val('" + input.substring(0,3) + "');" +
                                        "}"
                +               "}" +
                          "}"
                + "}";

        javascriptExecutor.executeScript(filteredInputScript);

        String submitScript =
                 "var allInputs = document.getElementsByTagName(\"input\");"
                + "for (var i=0, max = allInputs.length; i < max; i++) {"
                +       "if($(allInputs[i]).attr('value') == \"Save\" || $(allInputs[i]).attr('value') == \"Submit\") {"
                +           "$(allInputs[i]).click();"
                +       "}"
                + "}";

        javascriptExecutor.executeScript(submitScript);
    }
}
