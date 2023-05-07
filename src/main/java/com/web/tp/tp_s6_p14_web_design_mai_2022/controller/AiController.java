package com.web.tp.tp_s6_p14_web_design_mai_2022.controller;

import com.web.tp.tp_s6_p14_web_design_mai_2022.models.Information;
import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.Connex.Connexion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class AiController {
//    private final AutorController autor;
    public static int maxRow=6;
    Information info = new Information();



    @GetMapping("/create")
    public String createInformation(){
        return "ai/admin/InfoCreation";
    }
    @PostMapping("/create_info")
    public ModelAndView validate_creation(@ModelAttribute Information information, HttpServletRequest req){
        int id = req.getSession().getAttribute("id")!=null?(int)req.getSession().getAttribute("id"): -1;
        if (id==-1) return AutorController.login("Vous devez vous connecter pour créer une information","");
        try {
            information.setId_autor(id);
            System.out.println(information.getTitle()+" "+information.getContent());
            information.saveAll(Connexion.getConnection());
            return new ModelAndView("ai/admin/accueil");
        } catch (Exception e) {
            return new ModelAndView("ai/admin/InfoCreation","error",e.getMessage());
        }
    }
    @GetMapping("/list_info")
    public ModelAndView list_info(HttpServletRequest req) throws Exception {
        ModelAndView mod = new ModelAndView("ai/common/list_info");
        int first = req.getParameter("first")!=null&&!req.getParameter("first").equals("")?Integer.parseInt(req.getParameter("first")):0;

        ArrayList<Information> list = new ArrayList<>();
        ArrayList<Information> all = new ArrayList<>();
        if (req.getSession().getAttribute("id")!=null){
             list = info.paginateSelectAll(maxRow,first,Connexion.getConnection());
            all=info.SelectAll(Connexion.getConnection());
        }
        else{
            list = info.SelectAllPubliate(maxRow,first);
            all=info.SelectAllPubliateNp();
        }
        int t = calculatePage(maxRow,all.size());
        String error = req.getAttribute("error")!=null? (String) req.getAttribute("error") :"";
        mod.addObject("error",error);
        mod.addObject("list",list);
        mod.addObject("count",t);
        return mod;
    }
    @GetMapping("/search")
    public ModelAndView search(HttpServletRequest req) throws Exception {
        ModelAndView mod = new ModelAndView("ai/common/list_info");
        int first = req.getParameter("first")!=null?Integer.parseInt(req.getParameter("first")):0;
        if ((info.getSql()!=null) && (req.getParameter("option")!=null))info.setSql(null);
        String search = req.getParameter("search");
        System.out.println(info.getSql());
        Timestamp debut = (!Objects.equals(req.getParameter("datedebut"), "") && req.getParameter("datedebut")!=null)? new Timestamp(Date.valueOf(req.getParameter("datedebut")).getTime()):null;
        Timestamp fin = (!Objects.equals(req.getParameter("datefin"), "") && req.getParameter("datefin")!=null)?new Timestamp(Date.valueOf(req.getParameter("datefin")).getTime()):null;
        boolean ispubliate = (req.getParameter("ispubliate")!=null)?Boolean.parseBoolean(req.getParameter("ispubliate")):false;
        ArrayList<Information> result = info.search(search,debut,fin,maxRow,first,ispubliate);
        mod.addObject("list",result);
        int all = info.searchAll(info.getSql());
        int t = calculatePage(maxRow,all);
        String error = req.getAttribute("error")!=null? (String) req.getAttribute("error") :"";
        mod.addObject("error",error);
        mod.addObject("count",t);
        mod.addObject("action","search");
        mod.addObject("title","Recherche");

        return mod;
    }
    @GetMapping("/detail_info")
    public ModelAndView getDetail(@RequestParam("id")String id,HttpServletRequest req) throws Exception {
        try {
        ModelAndView mod = new ModelAndView("ai/common/Detail");
        Information information = new Information().findById(Connexion.getConnection(),id);
            mod.addObject("detail",information);
            return mod;
        }catch (Exception e){
            req.setAttribute("error",e.getMessage());
            return list_info(req);
        }
    }
    @GetMapping("/retirer_pub")
    public ModelAndView retirer_pub(@RequestParam("id")String id,HttpServletRequest req) throws Exception {
        Information info = new Information().findById(Connexion.getConnection(),id);
        info.setState("f");
        info.updateById(Connexion.getConnection());
        req.setAttribute("success","publication retirer");
        return list_info(req);
    }
    @GetMapping("/ownpub")
    public ModelAndView myOwnPublication(@RequestParam("id")String id,HttpServletRequest req) throws Exception {
        ModelAndView mod = new ModelAndView("ai/common/list_info");
        int ida = req.getSession().getAttribute("id")!=null?(int)req.getSession().getAttribute("id"): -1;
        if (ida==-1) return AutorController.login("Vous devez vous connecter pour acceder  a cet information","");
        if (id.equals(req.getSession().getAttribute("id").toString())){
            ArrayList<Information> list = info.getOwnPub(id);
            int t = calculatePage(maxRow, list.size());
            mod.addObject("list",list);
            mod.addObject("title","Mes publications");
            mod.addObject("count",t);
            return mod;
        }
        return mod;
    }
    @PostMapping("/setpubliate")
    public ModelAndView setPubliate(HttpServletRequest req) throws Exception {
        Information info = new Information().findById(Connexion.getConnection(),req.getParameter("id_info"));
        info.setState("t");
        info.updateById(Connexion.getConnection());
        req.setAttribute("success","publication reussi");
        return list_info(req);
    }
    public static int calculatePage(int pages, int t) {
        double size = (double) t / pages;
        if (t % pages != 0)
            size++;
        return (int) size;
    }
}
