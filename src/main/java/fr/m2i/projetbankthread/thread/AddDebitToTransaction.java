package fr.m2i.projetbankthread.thread;

import fr.m2i.projetbankthread.mutex.Mutex;
import org.springframework.web.reactive.function.client.WebClient;

public class AddDebitToTransaction implements Runnable{

    private Mutex mutex;

    public AddDebitToTransaction(Mutex mutex) {
        this.mutex = mutex;
    }

    @Override
    public void run() {

        while(true) {
            synchronized (mutex) {
                System.out.println("Add Debit to Transaction");
                WebClient webClient = WebClient.create();
                String resultat = webClient.get()
                        .uri("http://localhost:9001/api/removeCredit")
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
                // Si on enlève le block, on fait un .subscribe(
                // value->System.out.println(value),
                // error->error.printStackTrace()
                // ()->
                //)
                System.out.println(webClient);

            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
