package com.infosys.basic.dto;

import java.io.Serializable;

public class SearchModel<T> implements Serializable {

    private static final long serialVersionUID = -8759682629137819073L;

    private PagerInfo<T> pager = new PagerInfo<T>();

    public PagerInfo<T> getPager() {
        return pager;
    }

    public void setPager(PagerInfo<T> pager) {
        this.pager = pager;
    }
}
