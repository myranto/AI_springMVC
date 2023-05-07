package com.web.tp.tp_s6_p14_web_design_mai_2022.controller;

import com.web.tp.tp_s6_p14_web_design_mai_2022.models.Autor;
import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.Connex.Connexion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class AutorController {
    private final AiController controller;

    public AutorController(AiController controller) {
        this.controller = controller;
    }

    @GetMapping("/login")
    public static ModelAndView login(String error,String success){
        ModelAndView mod = new ModelAndView("login");
        error = (error!=null)?error:"";
        success = (success!=null)?success:"";
        mod.addObject("error",error);
        mod.addObject("success",success);
        return mod;
    }
    @GetMapping("/register")
    public ModelAndView register(String error){
        ModelAndView mod = new ModelAndView("register");
        error = (error!=null)?error:"";
        mod.addObject("error",error);
        return mod;
    }
    @PostMapping("/inscription")
    public ModelAndView Inscription(@ModelAttribute Autor autor,HttpServletRequest req){
        String pwd = req.getParameter("password2")!=null?req.getParameter("password2"):"";
        if (Objects.equals(autor.getPassword(), "") || Objects.equals(autor.getEmail(), "") || autor.getName().equals(""))
            return register("veuillez completer chaque champ");

        if (!pwd.equals(autor.getPassword()))
            return register("votre mot de passe doit etre correct");
        try {
            autor.saveAll(Connexion.getConnection());
            return login("","inscription reussi !!");
        } catch (Exception e) {
            return register(e.getMessage());
        }
    }
    @PostMapping("/validate_login")
    public ModelAndView validateLogin(@ModelAttribute Autor autor, HttpServletRequest req){
        try {
            Object[] val = autor.login();
            req.getSession().setAttribute("id",val[0]);
            req.getSession().setAttribute("name",val[1]);
            System.out.println(req.getSession().getAttribute("name"));
            return controller.list_info(req);
        } catch (Exception e) {
            return login(e.getMessage(),"");
        }
    }
    @GetMapping("/logout")
    public ModelAndView logOut(@RequestParam("id") int id,HttpServletRequest req){
        if (id==(int)req.getSession().getAttribute("id")){
            req.getSession().removeAttribute("id");
            return login("","Deconnexion reussi!!!");
        }
        return login("vous n'avez jamais ete connecter","");
    }
}
