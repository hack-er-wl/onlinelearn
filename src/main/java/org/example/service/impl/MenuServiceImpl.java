package org.example.service.impl;

import org.example.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MenuServiceImpl {
    List getMenu(int role);
    List getMenuAll();
    int insertMenu(Menu menu);
}
