import dhbw.sa.databaseApplication.database.DatabaseService;
import dhbw.sa.databaseApplication.printer.PrinterService;

public class PrinterService_Test {
    private static DatabaseService dbs = new DatabaseService();

    public static void main(String [] args) {

        //PrinterTest
        PrinterService printerService = new PrinterService();
        //printerService.printOrder(dbs.getOrderById(4), dbs.getAllItems(), dbs.getAllTables());
        //printerService.printOrder(dbs.getOrderById(4), dbs.getAllItems(), dbs.getAllTables(), false);
        //printerService.printOrder(dbs.getOrderById(3), dbs.getAllItems(), dbs.getAllTables());
    }
}
