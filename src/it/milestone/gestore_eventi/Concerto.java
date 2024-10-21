package it.milestone.gestore_eventi;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento {
	private LocalTime ora;
    private double prezzo;
    
	public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, double prezzo) throws Exception {
		super(titolo, data, postiTotali);
		this.ora = ora;
		this.prezzo = prezzo;
	}
	
	public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getDataFormattata() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return getData().format(dateFormatter);
    } 

    public String getOraFormattata() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return ora.format(timeFormatter);
    }

    public String getPrezzoFormattato() {
        DecimalFormat decimal = new DecimalFormat("##,##0.00€");
        return decimal.format(prezzo);
    }

    @Override
    public String toString() {
        return getDataFormattata() + " " + "ore " + getOraFormattata() + ", " + getTitolo() + ", " + getPrezzoFormattato() + ".";
    }
    
    public static void main(String[] args) {
    	try {
    		Concerto concertoRock = new Concerto("Concerto Rock", LocalDate.of(2025, 5, 15), 100, LocalTime.of(20, 30), 120.00);
    		System.out.println(concertoRock.toString());
    		
    		concertoRock.prenota();
    	    concertoRock.prenota();
    	    concertoRock.prenota();
    		System.out.println("Posti prenotati: " + concertoRock.getPostiPrenotati());
    		
    		concertoRock.disdici();
    		concertoRock.disdici();
    		System.out.println("Posti prenotati dopo disdetta: " + concertoRock.getPostiPrenotati());
    		
    		// Eccezione testata con data passata
    		Concerto concertoPop = new Concerto("Concerto Pop", LocalDate.of(2022, 12, 25), 50, LocalTime.of(18, 00), 80.00);
    		System.out.println(concertoPop.toString());
            // Gestione delle eccezioni
    		} catch (Exception e) {
                System.err.println("Errore: " + e.getMessage());
    		}
    }
}


