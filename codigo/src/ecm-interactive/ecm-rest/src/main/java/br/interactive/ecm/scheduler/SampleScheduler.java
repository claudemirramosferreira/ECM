package br.interactive.ecm.scheduler;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 * Scheduler para notificação de alertas.
 *
 * @author robson.ramos
 */
@Stateless(name = "AlertaScheduler", mappedName = "AlertaScheduler")
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class SampleScheduler {
    
    private static Logger LOGGER = Logger.getLogger(SampleScheduler.class.getName());

//    @Inject
//    private ServicoCustomizadoFacade servicoCustomizadoFacade;
    
    public SampleScheduler() {
        System.out.println("--------- Inicializando Scheduler: AlertaScheduler");
    }
    
    /**
     * Configurado para enviar email todos os dias da semana às 08:30
     */
    @Schedule(dayOfWeek = "Mon, Tue, Wed, Thu, Fri", hour = "08", minute = "30", second = "00", persistent = true)
    public void startTimer() {
        LOGGER.log( Level.INFO, "Starting AlertaScheduler at: " + new Date());
//        servicoCustomizadoFacade.enviarEmailAlertaExtintores();
    }

}
