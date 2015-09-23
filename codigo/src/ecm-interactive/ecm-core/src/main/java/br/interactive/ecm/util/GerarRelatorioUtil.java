package br.interactive.ecm.util;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author josimar.alencar
 *
 */
@Stateless
public class GerarRelatorioUtil {

    /**
     * @param urlRelatorio
     * @param parameters
     * @param dadosRelatorio
     * @return byte[]
     */
    public byte[] createPDF(String urlRelatorio, Map<String, Object> parameters, List<?> dadosRelatorio) {

        try {
            
            JRBeanCollectionDataSource relatorio = new JRBeanCollectionDataSource(dadosRelatorio, false);
            JasperReport relatorioJasper = JasperCompileManager.compileReport(JRXmlLoader.load(getClass().getResourceAsStream(urlRelatorio)));
            JasperPrint jasperPrint;
            
            jasperPrint = JasperFillManager.fillReport( relatorioJasper, parameters, relatorio);            
            
             return JasperExportManager.exportReportToPdf(jasperPrint);
 
        } catch (JRException e) {
            
            return "Erro ao gerar o relatório.".getBytes();
        }
    }
    /**
     * @param urlRelatorio
     * @param parameters
     * @param dadosRelatorio
     * @return byte[]
     */
    public byte[] createDuasViasPDF(String urlRelatorio, Map<String, Object> parameters, List<?> dadosRelatorio) {

        try {
            
            JRBeanCollectionDataSource relatorio = new JRBeanCollectionDataSource(dadosRelatorio, false);
            JRBeanCollectionDataSource relatorio2 = new JRBeanCollectionDataSource(dadosRelatorio, false);
           
            
            JasperReport relatorioJasper = JasperCompileManager.compileReport(JRXmlLoader.load(getClass().getResourceAsStream(urlRelatorio)));
            JasperPrint jasperPrint;
            JasperPrint jasperPrint2;
            
            jasperPrint = JasperFillManager.fillReport( relatorioJasper, parameters, relatorio);
            jasperPrint2 = JasperFillManager.fillReport( relatorioJasper, parameters, relatorio2);
            
            List pages = jasperPrint2.getPages();
            
            for (int i = 0; i < pages.size(); i++) {
                 JRPrintPage object = (JRPrintPage)pages.get(i);
                     jasperPrint.addPage(object);
            }
            
             return JasperExportManager.exportReportToPdf(jasperPrint);
 
        } catch (JRException e) {
            e.printStackTrace();
            return "Erro ao gerar o relatório.".getBytes();
        }
    }
}
