package com.hz.myzuul.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * grayscale_publishing
 * @author 
 */
@Data
public class GrayscalePublishing implements Serializable {
    private Integer id;

    private String serviceId;

    private Integer version;

    private String userId;

    private static final long serialVersionUID = 1L;
}