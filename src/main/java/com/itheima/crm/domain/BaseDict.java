package com.itheima.crm.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * CREATE TABLE `base_dict` (
 * `dict_id` varchar(32) NOT NULL COMMENT '数据字典id(主键)',
 * `dict_type_code` varchar(10) NOT NULL COMMENT '数据字典类别代码',
 * `dict_type_name` varchar(64) NOT NULL COMMENT '数据字典类别名称',
 * `dict_item_name` varchar(64) NOT NULL COMMENT '数据字典项目名称',
 * `dict_item_code` varchar(10) DEFAULT NULL COMMENT '数据字典项目代码(可为空)',
 * `dict_sort` int(10) DEFAULT NULL COMMENT '排序字段',
 * `dict_enable` char(1) NOT NULL COMMENT '1:使用 0:停用',
 * `dict_memo` varchar(64) DEFAULT NULL COMMENT '备注',
 * PRIMARY KEY (`dict_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 *
 * @author administratorad
 */
@Data
@Entity
@Table(name = "base_dict")
public class BaseDict {
    @Id
    private String dict_id;  // '数据字典id(主键)',
    private String dict_type_code;  // '数据字典类别代码',
    private String dict_type_name;  // '数据字典类别名称',
    private String dict_item_name;  // '数据字典项目名称',
    private String dict_item_code;  // '数据字典项目代码(可为空)',
    private Integer dict_sort;      // '排序字段',
    private String dict_enable;     // '1:使用 0:停用',
    private String dict_memo;
}
