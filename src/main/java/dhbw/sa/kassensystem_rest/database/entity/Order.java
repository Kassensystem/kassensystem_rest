package dhbw.sa.kassensystem_rest.database.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Model für einen Datensatz der Datenbanktabelle orders.
 *
 * @author Marvin Mai
 */
@Component
public class Order {
    @JsonProperty private int orderID;
    @JsonProperty private int tableID;
    @JsonProperty private double price;
    @JsonProperty private DateTime date;
    @JsonProperty private boolean paid;
    @JsonProperty private int waiterID;

    /*Constructors*/

    /**
     * Default-Contructor
     */
    public Order() {
    }

    /**
     * Konstruktor für eine vollständige Bestellung, die aus der MySQL-Datenbank gelesen wurde.
     * @param orderID ID der Bestellung aus der Datenbank.
     * @param tableID Tisch der Bestellung aus der Datenbank.
     * @param price Preis der Bestellung aus der Datenbank.
     * @param date Datum und Zeitpunkt der Bestellung aus der Datenbank, not null.
     * @param paid Bezahlstatus der Bestellung aus der Datenbank.
	 * @param waiterID ID der Bedienung, unter dessen Account die Bestellung erstellt wurde.
     */
    @JsonCreator
    public Order(@JsonProperty("orderID") int orderID,
          	@JsonProperty("tableID") int tableID,
          	@JsonProperty("price") double price,
          	@JsonProperty("date") DateTime date,
          	@JsonProperty("paid") boolean paid,
		 	@JsonProperty("waiterID") int waiterID) {

        if(date == null)
            date = DateTime.now();
            //throw new NullPointerException("Es muss ein Datum übergeben werden!");

        this.orderID = orderID;
        this.tableID = tableID;
        this.price = price;
        this.date = date;
        this.paid = paid;
        this.waiterID = waiterID;
    }

    /**
     * Konstruktor zum Erstellen einer neuen Bestellung,
     * die anschließend an die MySQL-Datenbank übertragen werden soll
     * @param tableID Tisch der neuen Bestellung, not null.
     * @param price Preis der neuen Bestellung.
     * @param date Datum der neuen Bestellung, not null.
     * @param paid Bezahlstatus der neuen Bestellung.
     */
    public Order(int tableID, double price, DateTime date, boolean paid, int waiterID) {

        if(date == null)
            throw new NullPointerException("Es ein Datum übergeben werden!");

        this.tableID = tableID;
        this.price = price;
        this.date = date;
        this.paid = paid;
        this.waiterID = waiterID;
    }

	public Order(int tableID, double price, boolean paid) {
        this.tableID = tableID;
        this.price = price;
        this.paid = paid;
    }

    /*Getter*/

    public int getOrderID() {
        return this.orderID;
    }

    public int getTable() {
        return this.tableID;
    }

    public Table getTable(ArrayList<Table> allTables) {
        for(Table t: allTables) {
            if(t.getTableID() == this.tableID)
                return t;
        }
        return null;
    }

    public double getPrice() {
        return this.price;
    }

    public DateTime getDate() {
        return this.date;
    }

    public boolean isPaid() {
        return paid;
    }

	public int getWaiterID()
	{
		return waiterID;
	}


    /*Setter*/
    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(DateTime dateTime) {

        if(dateTime == null)
            throw new NullPointerException("Es muss ein Datum festgelegt werden!");

        this.date = dateTime;
    }

    public void setPaid() {
        this.paid = true;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

	public void setWaiterID(int waiterID)
	{
		this.waiterID = waiterID;
	}


}
