package com.dong.music.service;

import java.util.List;
import java.util.Map;

public interface UserListService {
    List<Map> findSongListByUserId(int id);
}
