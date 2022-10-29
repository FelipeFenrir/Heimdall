/*
 * Copyright (c) 2020. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package com.heimdall.entrypoint.util;

import br.com.fenrir.commons.utils.domain.filter.Joins;
import br.com.fenrir.commons.utils.domain.filter.Paging;
import br.com.fenrir.commons.utils.domain.filter.Filter;
import br.com.fenrir.commons.utils.domain.filter.Ordering;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.util.List;

/**
 * Search Object with Joins, Filter and Paging.<br>
 * Exemplo de JSON:<br>
 * <pre>
 * {
 *   "search": {
 *           "joins": [{
 *                           "type": "@F",
 *                           "parameter": "dog"
 *                    }
 *           ],
 *           "filter": [{
 *                           "parameter": "name",
 *                           "operator": "==",
 *                           "value": "fulan*",
 *                           "type": "str",
 *                           "condition": "&&"
 *                   },
 *                   {
 *                           "parameter": "idade",
 *                           "operator: "&lt;",
 *                           "value": "30",
 *                           "type": "int",
 *                           "condition": "||"
 *                   },
 *                   {
 *                           "parameter": "idade",
 *                           "operator: "&gt;",
 *                           "value": "17",
 *                           "type": "int",
 *                           "condition": "||"
 *                   }
 *           ],
 *           "orders": [{
 *                           "parameter": "name",
 *                           "sortdirection": "asc"
 *                    },
 *                    {
 *                           "parameter": "idade",
 *                           "sortdirection": "desc"
 *                    }
 *           ],
 *           "paging": {
 *                   "page": "0",
 *                   "size": "10"
 *           }
 *   }
 * }</pre>
 *
 * @author Felipe de Andrade Batista
 */
public class RequestWrapper {

    @SerializedName("joins")
    @Expose
    private List<Joins> joins = null;

    @SerializedName("filter")
    @Expose
    private List<Filter> filter = null;

    @SerializedName("orders")
    @Expose
    private List<Ordering> orders = null;

    @SerializedName("paging")
    @Expose
    private Paging paging;

    public List<Joins> getJoins() {
        return joins;
    }

    public void setJoins(List<Joins> joins) {
        this.joins = joins;
    }

    /**
     * List Joins.
     *
     * @param myJoins {@link Joins}.
     * @return RequestWrapper.
     */
    public RequestWrapper withJoins(List<Joins> myJoins) {
        this.joins = myJoins;
        return this;
    }

    public List<Filter> getFilter() {
        return filter;
    }

    public void setFilter(List<Filter> filter) {
        this.filter = filter;
    }

    /**
     * List Filters.
     *
     * @param myFilter {@link Filter}.
     * @return RequestWrapper.
     */
    public RequestWrapper withFilter(List<Filter> myFilter) {
        this.filter = myFilter;
        return this;
    }

    public List<Ordering> getOrders() {
        return orders;
    }

    public void setOrders(List<Ordering> orders) {
        this.orders = orders;
    }

    /**
     * List Orders.
     *
     * @param myOrders {@link Ordering}.
     * @return RequestWrapper.
     */
    public RequestWrapper withOrders(List<Ordering> myOrders) {
        this.orders = myOrders;
        return this;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    /**
     * List Pages.
     *
     * @param myPaging {@link Paging}.
     * @return RequestWrapper.
     */
    public RequestWrapper withPaging(Paging myPaging) {
        this.paging = myPaging;
        return this;
    }

    /**
     * Return Json String.
     *
     * @return the Json Stringfy.
     */
    public String toJson() {
        GsonBuilder gbuilder = new GsonBuilder();
        gbuilder.disableHtmlEscaping();
        Gson gson = gbuilder.create();
        return gson.toJson(this);
    }

    /**
     * Return a Object RequestWrapper from Json.
     *
     * @param json String Json.
     * @return {@link RequestWrapper}.
     */
    public RequestWrapper fromJson(String json) {
        Gson gson = new Gson();
        RequestWrapper other = gson.fromJson(json, this.getClass());
        this.setJoins(other.getJoins());
        this.setFilter(other.getFilter());
        this.setOrders(other.getOrders());
        this.setPaging(other.getPaging());
        return this;
    }

    /**
     * Return a Object Filter from Json.
     *
     * @param json String Json.
     * @return {@link RequestWrapper}.
     */
    @JsonIgnore
    public RequestWrapper setFiltersFromJson(String json) {
        Gson gson = new Gson();
        Type typeOfT = new TypeToken<List<Filter>>() { }.getType();
        this.filter = gson.fromJson(json, typeOfT);
        return this;
    }

    /**
     * Return a Object Joins from Json.
     *
     * @param json String Json.
     * @return {@link RequestWrapper}.
     */
    @JsonIgnore
    public RequestWrapper setJoinsFromJson(String json) {
        Gson gson = new Gson();
        Type typeOfT = new TypeToken<List<Joins>>() { }.getType();
        this.joins = gson.fromJson(json, typeOfT);
        return this;
    }

    /**
     * Return a Object Ordering from Json.
     *
     * @param json String Json.
     * @return {@link RequestWrapper}.
     */
    @JsonIgnore
    public RequestWrapper setOrdersFromJson(String json) {
        Gson gson = new Gson();
        Type typeOfT = new TypeToken<List<Ordering>>() { }.getType();
        this.orders = gson.fromJson(json, typeOfT);
        return this;
    }

    /**
     * Return a Object Paging from Json.
     *
     * @param json String Json.
     * @return {@link RequestWrapper}.
     */
    @JsonIgnore
    public RequestWrapper setPagingFromJson(String json) {
        Gson gson = new Gson();
        this.paging = gson.fromJson(json, Paging.class);
        return this;
    }
}
