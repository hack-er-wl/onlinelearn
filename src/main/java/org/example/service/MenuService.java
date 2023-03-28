package org.example.service;

import org.example.entity.Menu;
import org.example.mapper.MenuMapper;
import org.example.service.impl.MenuServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MenuService implements MenuServiceImpl {
    @Resource
    private MenuMapper menuMapper;
    @Override
    public List getMenu(int role) {
        return menuMapper.getMenu(role);
    }
    @Override
    public List getMenuAll() {
        return menuMapper.getMenuAll();
    }

    @Override
    public int insertMenu(Menu menu) {
        return menuMapper.insertMenu(menu);
    }
}
