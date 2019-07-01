package com.leigq.www.shiro.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author leigq
 * @since 2019-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "permissionId", type = IdType.AUTO)
    private Integer permissionId;

    private Boolean available;

    @TableField("parentId")
    private Long parentId;

    @TableField("parentIds")
    private String parentIds;

    private String permission;

    @TableField("permissionName")
    private String permissionName;

    @TableField("resourceType")
    private String resourceType;

    private String url;


}
