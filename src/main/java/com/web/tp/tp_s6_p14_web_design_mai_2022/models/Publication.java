package com.web.tp.tp_s6_p14_web_design_mai_2022.models;

import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.DAO.ObjectBDD;
import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.inter.KeyAnnotation;
import com.web.tp.tp_s6_p14_web_design_mai_2022.utils.inter.TableAnnotation;

@TableAnnotation
public class Publication extends ObjectBDD {
    @KeyAnnotation
    private int id_info;
    @KeyAnnotation
    private int date_move;
    @KeyAnnotation
    private int state;

    public int getId_info() {
        return id_info;
    }

    public void setId_info(int id_info) {
        this.id_info = id_info;
    }

    public int getDate_move() {
        return date_move;
    }

    public void setDate_move(int date_move) {
        this.date_move = date_move;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Publication() {
    }

    public Publication(int id_info, int date_move, int state) {
        this.id_info = id_info;
        this.date_move = date_move;
        this.state = state;
    }
}
