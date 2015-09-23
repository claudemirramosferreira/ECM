package br.interactive.ecm.service.seguranca;

//import br.interactive.ecm.model.dao.seguranca.SysMaDAO;
import br.interactive.ecm.model.dao.seguranca.UserSessionDAO;
import br.interactive.ecm.model.dto.seguranca.MenuDTO;
//import br.interactive.ecm.model.entity.SysMa;
import br.interactive.ecm.model.entity.UserSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wendell.santana
 * @category Service Serviços do menu.
 */
@Stateless
public class MenuService {

//    @Inject
//    private SysMaDAO menuDAO;

    @Inject
    private UserSessionDAO userSessionDAO;

//    /**
//     * Montar all servicos menu model.
//     *
//     * @param acessoItem the acesso item
//     * @return the menu model
//     */
//    public MenuDTO montarAllServicosMenuDTO(SysMa acessoItem) {
//
//        MenuDTO menuDTO = new MenuDTO();
//        menuDTO.setId(acessoItem.getId());
//        menuDTO.setTipo(acessoItem.getTipo());
//        menuDTO.setTitulo(acessoItem.getTitulo());
//        menuDTO.setUri(acessoItem.getUri());
//
//        Collections.sort(acessoItem.getSysMas());
//
//        for (SysMa sysMa : acessoItem.getSysMas()) {
//            menuDTO.getSubs().add(montarAllServicosMenuDTO(sysMa));
//        }
//
//        return menuDTO;
//    }
//
//
//    /**
//     * Gets the menu by usuario.
//     *
//     * @param request
//     * @return the menu by usuario
//     */
//    public List<MenuDTO> getMenuByUsuario(HttpServletRequest request) {
//
//        String token = request.getHeader("Authorization");
//        UserSession userSession = userSessionDAO.getUserSessionByToken(token);
//
//        List<SysMa> sysMas = menuDAO.getAllServicos();
//        List<MenuDTO> menuDTOs = new ArrayList<>();
//
////        List<String> listaIds = perfilServicoDAO.getServicosUsuario(userSession.getTxLogin());
////        this.removeMenuServicosNaoUsados(sysMas, listaIds);
//
//        for (SysMa sysMa : sysMas) {
//            menuDTOs.add(montarAllServicosMenuDTO(sysMa));
//        }
//
//        return menuDTOs;
//    }
//
//    /**
//     * Removes the menu servicos nao usados.
//     *
//     * @param sysMas the sys mas
//     * @param codigoServicoList the codigo servico list
//     */
//    private void removeMenuServicosNaoUsados(List<SysMa> sysMas, List<String> codigoServicoList) {
//        if (sysMas != null) {
//            for (int i = 0; i < sysMas.size(); i++) {
//                // verifica se exite servico no grupo
//                if (sysMas.get(i).getSysMas().size() > 0) {
//                    this.removeMenuServicosNaoUsados(sysMas.get(i).getSysMas(), codigoServicoList);
//                    // verifica se o grupo tem servicos
//                    if ((sysMas.get(i).getSysMas() == null) || (sysMas.get(i).getSysMas().size() <= 0)) {
//                        sysMas.remove(i--);
//                    }
//                } else if (sysMas.get(i).getSysMas().size() <= 0) {
//                    // verifica se o servico está entre os servicos do usuario,
//                    // se nao contiver remove
//                    if (!codigoServicoList.contains(sysMas.get(i).getId())) {
//                        sysMas.remove(i--);
//                    } else if (!sysMas.get(i).getTipo().equals("m") && !sysMas.get(i).getTipo().equals("s")) {
//                        sysMas.remove(i--);
//                    }
//                }
//            }
//        }
//    }

}
