package com.jie.tree;

import lombok.Data;

import java.util.List;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/22 14:38
 * team 树结构-团队实体类
 */
@Data
public class TeamTreeDTO {

    /**
     * 团队id
     */
    private Integer teamId;
    /**
     * 团队名称
     */
    private String teamName;
    /**
     * 父级id
     */
    private Integer teamParentId;

    /**
     * 团队等级
     */
    private Integer teamGrade;
    /**
     * 子团队信息
     */
    private List<TeamTreeDTO> teamDTOList;
}
