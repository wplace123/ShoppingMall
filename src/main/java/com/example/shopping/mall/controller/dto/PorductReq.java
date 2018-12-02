package com.example.shopping.mall.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author wlace
 * @date 2018/11/20
 */
public class PorductReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "id not null")
    @Length(min = 0, max = 16)
    private Integer id;

    @NotNull(message = "classId not null")
    @Length(min = 0, max = 16, message = "length err")
    private Integer classId;

    @NotNull(message = "type not null")
    @Length(min = 0, max = 16)
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
