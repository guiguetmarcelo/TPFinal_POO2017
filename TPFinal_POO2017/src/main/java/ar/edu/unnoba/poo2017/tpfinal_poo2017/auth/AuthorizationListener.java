
package ar.edu.unnoba.poo2017.tpfinal_poo2017.auth;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Usuario;
import ar.edu.unobba.poo2017.tpfinal_poo2017.controller.SessionBacking;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author poo
 */
public class AuthorizationListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();

        SessionBacking sessionBacking = null;
        try {
            sessionBacking = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{sessionBacking}", SessionBacking.class);
        } catch (Exception e) {
        }

        // SessionBacking sessionBacking = (SessionBacking)event.getFacesContext().getExternalContext().getSessionMap().get("sessionBacking");
        Usuario currentUser = null;
        if (sessionBacking != null) {
            currentUser = (Usuario) sessionBacking.getUsuario();
        }

        if (currentUser == null) {
            if (!currentPage.equals("/errorpage.xhtml") && !currentPage.equals("/login.xhtml")) {
                NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                nh.handleNavigation(facesContext, null, "/login.xhtml?faces-redirect=true");
            }
        } else {
            try {
                if (!currentUser.isAdmGen()) {
                    if (currentPage.contains("/empresas") || currentPage.contains("view_all.xhtml")) {
                        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                        nh.handleNavigation(facesContext, null, "/error404.xhtml?faces-redirect=false");
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
