package it.milestone.gestore_eventi;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Chiedere all'utente di inserire un nuovo evento con tutti i parametri
        System.out.println("Inserisci il titolo dell'evento: ");
        String titolo = scanner.nextLine();

        System.out.println("Inserisci la data dell'evento (yyyy-mm-dd): ");
        LocalDate data = null;
        while (true) {
            try {
                String dataInput = scanner.nextLine();
                data = LocalDate.parse(dataInput);

                if (data.isBefore(LocalDate.now())) {
                    System.err.println("Errore: Non puoi inserire una data passata.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.err.println("Formato data non valido. Riprova con il formato yyyy-mm-dd.");
            }
        }

        System.out.println("Inserisci il numero totale di posti: ");
        int postiTotali = scanner.nextInt();

        Evento evento = null;
        try {
            evento = new Evento(titolo, data, postiTotali);
            System.out.println("Evento creato con successo!");
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
            System.exit(1);
        }

        /* Chiedere all’utente se e quante prenotazioni vuole fare e provare ad effettuarle,
           implementando opportuni controlli */
        int numeroPrenotazioni = 0;
        boolean prenotazioniEffettuate = false;

        System.out.println("Vuoi effettuare delle prenotazioni? (sì/no): ");
        String acconsentiPrenotazione = scanner.next().toLowerCase();
        scanner.nextLine();

        if (acconsentiPrenotazione.equals("sì") || acconsentiPrenotazione.equals("si")) {
            while (true) {
                System.out.println("Quante prenotazioni vuoi effettuare? ");
                numeroPrenotazioni = Integer.parseInt(scanner.nextLine());

                if (numeroPrenotazioni < 1) {
                    System.err.println("Devi effettuare almeno 1 prenotazione. Riprova.");
                } else if (numeroPrenotazioni > (evento.getPostiTotali() - evento.getPostiPrenotati())) {
                    System.err.println("Errore: Non ci sono abbastanza posti disponibili. " +
                            "Puoi prenotare al massimo " + (evento.getPostiTotali() - evento.getPostiPrenotati()) + " posti.");
                } else {
                    break;
                }
            }

            boolean prenotazioneEffettuata = true;
            for (int i = 0; i < numeroPrenotazioni; i++) {
                try {
                    evento.prenota();
                    prenotazioniEffettuate = true;
                } catch (Exception e) {
                    System.out.println("Errore: " + e.getMessage());
                    prenotazioneEffettuata = false;
                }
            }

            if (prenotazioneEffettuata) {
                prenotazioniEffettuate = true;
                System.out.println("Prenotazione effettuata!");
            }

            // Stampare a video il numero di posti prenotati e quelli disponibili
            System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
            System.out.println("Posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));
        } else {
            System.out.println("Nessuna prenotazione effettuata.");
        }

        /* Chiedere all’utente se e quanti posti vuole disdire e provare ad effettuare 
           le disdette, implementando opportuni controlli */
        if (prenotazioniEffettuate) {
            System.out.println("Vuoi disdire delle prenotazioni? (sì/no): ");
            String acconsentiDisdette = scanner.next().toLowerCase();
            scanner.nextLine();

            if (acconsentiDisdette.equals("sì") || acconsentiDisdette.equals("si")) {
                int numeroDisdette = 0;
                while (true) {
                    System.out.println("Quanti posti vuoi disdire? ");
                    try {
                        numeroDisdette = Integer.parseInt(scanner.nextLine());

                        // Controllo per un numero di disdette valido
                        if (numeroDisdette < 1) {
                            System.err.println("Errore: Devi inserire un numero valido di disdette (almeno 1).");
                        } else if (numeroDisdette > evento.getPostiPrenotati()) {
                            System.err.println("Errore: Devi inserire un numero valido di disdette (da 1 a " + evento.getPostiPrenotati() + ").");
                        } else {
                            break;
                        }

                    } catch (NumberFormatException e) {
                        System.err.println("Errore: Devi inserire un numero valido.");
                    }
                }

                if (evento.getPostiPrenotati() <= 0) {
                    System.err.println("Errore: Non ci sono prenotazioni da disdire.");
                } else {
                    boolean disdettaEffettuata = true;
                    for (int i = 0; i < numeroDisdette; i++) {
                        try {
                            evento.disdici();
                        } catch (Exception e) {
                            System.out.println("Errore durante la disdetta: " + e.getMessage());
                            disdettaEffettuata = false;
                        }
                    }

                    if (disdettaEffettuata) {
                        System.out.println("Disdetta effettuata!");
                    }

                    // Stampare a video il numero di posti prenotati e quelli disponibili
                    System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
                    System.out.println("Posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));
                }
            } else {
                System.out.println("Nessuna disdetta effettuata.");
            }
        } else {
            System.out.println("Non ci sono prenotazioni da disdire.");
        }

        scanner.close();
    }
}
