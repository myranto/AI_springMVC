package com.web.tp.tp_s6_p14_web_design_mai_2022.models;

import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.Connex.Connexion;
import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.DAO.ObjectBDD;
import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.inter.IdAnnotation;
import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.inter.KeyAnnotation;
import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.inter.TableAnnotation;

@TableAnnotation
public class Autor extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation
    private int id;
    @KeyAnnotation
    private String name;
    @KeyAnnotation
    private String email;
    @KeyAnnotation
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Autor(int id) {
        this.id = id;
    }

    public Autor(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public Object[] login() throws Exception {
        setName(null);
        try {
            Autor o = (Autor) search(Connexion.getConnection()).get(0);
            return new Object[]{o.getId(),o.getName()};
        }catch (Exception e){
            throw new Exception("mot de passe ou identifiant incorrect pour : "+getEmail());
        }
    }

    public Autor() {
    }

}
