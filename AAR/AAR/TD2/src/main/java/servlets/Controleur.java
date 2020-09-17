package servlets;

import modele.Competence;
import modele.CompetenceMembre;
import modele.Membre;
import modele.Projet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

@WebServlet(name = "Controleur", urlPatterns = "/")
public class Controleur extends HttpServlet {
    private Collection<Membre> membres;
    private Collection<Projet> projets;
    private Collection<Competence> competences;

    @Override
    public void init() throws ServletException {
        membres=new ArrayList<>();
        Membre matthieu=new Membre("Matthieu","Matthieu","Matthieu");
        membres.add(matthieu);
        Membre fred=new Membre("Fred","Fred","Fred");
        membres.add(fred);

        projets=new ArrayList<>();
        Projet allanParson=new Projet("Allan Parson","Un projet musical");
        allanParson.setDirigePar(matthieu);
        matthieu.getResponsable().add(allanParson);
        projets.add(allanParson);
        Projet xion=new Projet("Xion","Pour partager une vision");
        xion.setDirigePar(fred);
        fred.getResponsable().add(xion);
        projets.add(xion);

        competences = new ArrayList<>();
        Competence java=new Competence("Java","POO");
        competences.add(java);
        Competence management=new Competence("Management", "Gestion d'équipe, résolution de conflits");
        competences.add(management);

        CompetenceMembre javamatth=new CompetenceMembre(matthieu,java,3,"java avancé");
        matthieu.getDeclare().add(javamatth);
        java.getCompMembre().add(javamatth);
        CompetenceMembre manafred=new CompetenceMembre(fred,management,4,"tout est sous contrôle");
        fred.getDeclare().add(manafred);
        management.getCompMembre().add(manafred);


        allanParson.getNecessite().addAll(competences);
        xion.getNecessite().addAll(competences);
        java.getRequisePour().addAll(projets);
        management.getRequisePour().addAll(projets);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String todo = request.getParameter("TODO");

        if ((request.getSession().getAttribute("util")==null) && ((todo == null) || (todo.equals("selogguer")==false))) {
            request.getRequestDispatcher("/WEB-INF/accueil.jsp").
                    forward(request, response);
        } else {
            if (todo == null) {
                menu(request, response);

            } else {
                switch (todo) {
                    case "selogguer":
                        String log = request.getParameter("login");
                        String mdp = request.getParameter("mdp");
                        for (Membre m : membres) {
                            if (m.getLogin().equals(log) &&
                                    m.getMotdepasse().equals(mdp)) {
                                // le login est mis en session pour s'en souvenir...
                                request.getSession().setAttribute("util", log);
                                menu(request, response);
                                break;
                            }
                        }
                        break;

                    case "participer":
                        Membre membre = getMembreFromLogin((String) request.getSession().getAttribute("util"));
                        Projet projet = getProjetFromIntituleP(request.getParameter("intituleP"));
                        if ((projet.getContributionDe().contains(membre) == false) && (projet.getDirigePar() != membre)) {
                            projet.getContributionDe().add(membre);
                            membre.getParticipe().add(projet);
                        }
                        menu(request, response);

                        break;

                    default:
                        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
                }
            }
        }
    }

    private Collection<Projet> competences(Membre m) {
        // On initialise les projets : tous les projets moins ceux auquel m participe
        Collection<Projet> pcomp=new ArrayList<>();
        pcomp.addAll(projets);
        pcomp.removeAll(m.getResponsable());
        pcomp.removeAll(m.getParticipe());

        // les competences de m
        Collection<Competence> compm=new ArrayList<>();
        for (CompetenceMembre cm:m.getDeclare()) {
            compm.add(cm.getDeType());
        }

        Collection<Projet> res=new ArrayList<>();

        // on ne garde que les projets avec des competences de m
        for (Projet p:pcomp) {
            for (Competence comp:p.getNecessite()) {
                if (compm.contains(comp)) {
                    res.add(p);
                    break;
                }
            }
        }
        return res;
    }

    private Collection<Projet> autres(Membre m, Collection<Projet> hascomp) {
        Collection<Projet> pautres=new ArrayList<>();
        pautres.addAll(projets);
        pautres.removeAll(m.getResponsable());
        pautres.removeAll(m.getParticipe());
        pautres.removeAll(hascomp);
        return pautres;
    }

    private Membre getMembreFromLogin(String login) {
        for (Membre m:membres) {
            if (m.getLogin().equals(login)) {
                return m;
            }
        }
        return null;
    }

    private Projet getProjetFromIntituleP(String intituleP) {
        for (Projet p:projets) {
            if (p.getIntituleP().equals(intituleP)) {
                return p;
            }
        }
        return null;
    }

    private void menu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Membre membre= getMembreFromLogin((String)request.getSession().getAttribute("util"));

        // on affiche la page de menu, en lui passant le membre qui vient de se logguer
        request.setAttribute("courant",membre);

        Collection<Projet> pcomp=competences(membre);
        request.setAttribute("competent",pcomp);
        request.setAttribute("autres",autres(membre,pcomp));

        request.getRequestDispatcher("/WEB-INF/menu.jsp").forward(request,response);
    }

}









