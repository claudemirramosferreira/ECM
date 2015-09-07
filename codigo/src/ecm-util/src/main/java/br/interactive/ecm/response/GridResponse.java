/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.interactive.ecm.response;

import java.util.List;

public class GridResponse<T> {

    private List<T> element;
    private Integer totalProperty;

    public GridResponse(List<T> element, Integer totalProperty) {
        this.element = element;
        this.totalProperty = totalProperty;
    }

    public void setElement(List<T> element) {
        this.element = element;
    }

    public List<T> getElement() {
        return element;
    }

    public void setTotalProperty(Integer totalProperty) {
        this.totalProperty = totalProperty;
    }

    public Integer getTotalProperty() {
        return totalProperty;
    }

}
