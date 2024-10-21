package it.milestone.gestore_eventi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgrammaEventi {
    private String titolo;
    private List<Evento> eventi;

    public ProgrammaEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    // metodo che aggiunge alla lista un Evento, passato come parametro
    public void aggiungiEvento(Evento evento) {
        eventi.add(evento);
    }

    // metodo che restituisce una lista con tutti gli eventi presenti in una certa data
    public List<Evento> listaEventiData(LocalDate data) {
        List<Evento> listaEventiData = new ArrayList<>();
        for (Evento evento : eventi) {
            if (evento.getData().isEqual(data)) {
                listaEventiData.add(evento);
            }
        }
        return listaEventiData;
    }

    // metodo che restituisce quanti eventi sono presenti nel programma
    public int contaEventi() {
        return eventi.size();
    }

    // metodo che svuota la lista di eventi
    public void svuotaEventi() {
        eventi.clear();
    }

    @Override
    public String toString() {
        String eventiOrdinati = "Programma: " + titolo + "\n";
        // ordina gli eventi per data
        eventi.sort((e1, e2) -> e1.getData().compareTo(e2.getData()));
        // aggiungi ogni evento alla stringa
        for (Evento evento : eventi) {
            eventiOrdinati += evento.getData() + " - " + evento.getTitolo() + "\n";
        }
        return eventiOrdinati;
    }

    public static void main(String[] args) throws Exception {
        ProgrammaEventi programma = new ProgrammaEventi("Eventi Musicali");

        Evento evento1 = new Evento("Serata Jazz", LocalDate.of(2024, 11, 19), 100);
        Evento evento2 = new Evento("Serata di musica classica", LocalDate.of(2025, 01, 21), 200);

        programma.aggiungiEvento(evento1);
        programma.aggiungiEvento(evento2);

        System.out.println(programma.toString());
    }
}
