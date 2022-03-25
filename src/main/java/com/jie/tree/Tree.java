package com.jie.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/10 16:26
 * 构建树
 */


public class Tree {

    /**
     * 构建团队菜单树
     *
     * @param teamDTOs
     * @param pid
     * @return pid 初始传入为0
     */
    private List<TeamTreeDTO> buildTeamTree(List<TeamTreeDTO> teamDTOs, Integer pid) {
        List<TeamTreeDTO> treeList = new ArrayList<>(teamDTOs.size());
        for (TeamTreeDTO teamDTO : teamDTOs) {
            if (Objects.equals(pid, teamDTO.getTeamParentId())) {
                teamDTO.setTeamDTOList(buildTeamTree(teamDTOs, teamDTO.getTeamId()));
                treeList.add(teamDTO);
            }
        }
        return treeList;
    }
}
