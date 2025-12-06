package net.tagpad;

import net.tagpad.packageA.StringUtils;
import net.tagpad.packageB.ConfigService;
import net.tagpad.packageB.DataService;
import net.tagpad.packageB.LoggingService;
import net.tagpad.packageC.User;
import net.tagpad.packageC.Product;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LoggingService logger = new LoggingService();
        ConfigService config = ConfigService.getInstance();
        DataService dataService = new DataService();

        logger.logInfo("Application started - Version: " + config.get("app.version"));

        // Demo user creation
        User user = new User("USR001", "john_doe", "john@example.com");
        logger.logInfo("Created user: " + user);

        // Demo product creation
        Product product = new Product("SKU001", "Laptop", 999.99, 5);
        logger.logInfo("Created product: " + product);

        // Demo string utilities
        String testStr = "hello world";
        logger.logInfo("Capitalized: " + StringUtils.capitalize(testStr));
        logger.logInfo("Reversed: " + StringUtils.reverse(testStr));

        // Demo data service
        dataService.addData("Item 1");
        dataService.addData("Item 2");
        dataService.addData("Item 3");
        logger.logInfo("Data service has " + dataService.getDataCount() + " items");

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

        logger.logInfo("Application finished successfully");
    }
}