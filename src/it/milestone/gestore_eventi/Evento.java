package it.milestone.gestore_eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
	private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;
    
    private boolean isEventoPassato() {
        return data.isBefore(LocalDate.now());
    }
    
    public Evento(String titolo, LocalDate data, int postiTotali) throws IllegalArgumentException, IllegalStateException {
    	this.titolo = titolo;
    	this.data = data;
    	if (isEventoPassato()) {
    		throw new IllegalStateException("La data è già passata.");
    	} if (postiTotali <= 0) {
    		throw new IllegalArgumentException("Il valore inserito non è valido. Inserisci almeno un posto.");
    	}	
    	this.postiTotali = postiTotali;
    	this.postiPrenotati = 0;
    }
    
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) throws IllegalStateException {
        if (data.isBefore(LocalDate.now())) {
        	throw new IllegalStateException("La data è già passata.");
        }
        this.data = data;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }
    
    public void prenota() throws IllegalStateException {
        if (postiPrenotati >= postiTotali) {
        	throw new IllegalStateException("Impossibile prenotare. Non ci sono posti disponibili.");
        } else if (isEventoPassato()) {
        	throw new IllegalStateException("Impossibile prenotare. L'evento è gia passato.");
        } else {
        	postiPrenotati++;
        }
    }
    
    public void disdici() throws IllegalStateException {
    	if (isEventoPassato()) {
    		throw new IllegalStateException("Impossibile disdire. L'evento è già passato.");
    	} else if (postiPrenotati <= 0) {
    		throw new IllegalStateException("Impossibile disdire. Non ci sono prenotazioni.");
    	} else {
    		postiPrenotati--;
    	}
    }

	public String toString() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return data.format(dateFormatter) + " - " + titolo;
	}
}

