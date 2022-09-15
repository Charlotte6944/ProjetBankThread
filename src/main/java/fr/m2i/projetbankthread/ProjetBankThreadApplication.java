package fr.m2i.projetbankthread;

import fr.m2i.projetbankthread.mutex.Mutex;
import fr.m2i.projetbankthread.thread.AddDebitToTransaction;
import fr.m2i.projetbankthread.thread.AddSalaryToTransactionThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetBankThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetBankThreadApplication.class, args);

        Mutex mutex = new Mutex();
        AddSalaryToTransactionThread addSalaryToTransactionThread = new AddSalaryToTransactionThread(mutex);
        AddDebitToTransaction addDebitToTransaction = new AddDebitToTransaction(mutex);

        new Thread(addSalaryToTransactionThread).start();
        new Thread(addDebitToTransaction).start();

    }

}
