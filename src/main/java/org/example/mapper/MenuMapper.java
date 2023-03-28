package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Menu;

import java.util.List;
@Mapper
public interface MenuMapper {
    List getMenu(int role);
    List getMenuAll();
    int insertMenu(Menu menu);
}
