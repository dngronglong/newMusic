package com.dong.music.controller;

import com.dong.music.beans.KuGouMusicBean;
import com.dong.music.beans.MusicBean;
import com.dong.music.beans.MusicHQ;
import com.dong.music.utils.GetUrl;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/mic")
@RestController
@SessionAttributes("list")
public class MusicController {

    //搜索音乐
    @RequestMapping(value = "/search", produces = {"application/json;charset=utf-8"})
    public Map search(@RequestParam Map<String, Object> map1) {
        Map map = new HashMap();
        System.out.println(map1);
        if (map1.get("source").toString().equals("tencent")) {
            map.put("songList", GetUrl.getMusicList(map1.get("words").toString(), map1.get("pages").toString(), map1.get("count").toString()));
        }else if (map1.get("source").toString().equals("kugou")){
            KuGouMusicBean kkuGou=GetUrl.getJson(map1.get("words").toString(),map1.get("pages").toString(), map1.get("count").toString());
            map.put("songList",kkuGou);
            System.out.println(map);
        }
        return map;
    }

    /**
     * 根据mid查找音乐信息--QQ音乐
     *
     * @param mid
     * @param musicName
     * @return
     */
    @RequestMapping("/Qplay")
    public MusicBean qPlay(String mid, String musicName) {
        return GetUrl.qqUrl(mid, musicName);
    }
    /**
     * 新歌热搜榜
     */
    @RequestMapping("/top")
    public Map top(){
        Map map=new HashMap();
        map.put("top",GetUrl.top());
        return map;
    }
    /**
     * 推荐音乐
     */
    @RequestMapping("/random")
    public Map random(){
        Map map=new HashMap();
        map.put("random",GetUrl.random());
        return map;
    }
    /**
     * 获取封面图
     */
    @RequestMapping("/ablum")
    public MusicHQ ablum(String mid){
        return GetUrl.getAblum(mid);
    }
    //    @RequestMapping(value = "/addList")
//    public void addList(String listName,int id){
//        SongListBean songListBean=new SongListBean();
//        songListBean.setName(listName);
//        songListBean.setU_id(id);
//        listRepository.saveAndFlush(songListBean);
//        redisUtils.remove("user"+userId+"SongList");
//        redisUtils.setObjectList("user"+userId+"SongList",songListService.findSongListByUserId(userId));
//    }
//    @RequestMapping(value = "/findAll")
//    public List<SongListBean> findAll(int id){
//        userId=id;
//        List<SongListBean> list=new ArrayList<>();
//        if(redisUtils.exists("user"+id+"SongList")){
//            list= redisUtils.getObbjectList("user"+id+"SongList");
//            System.out.println(list+"使用了缓存");
//        }else{
//            list=songListService.findSongListByUserId(id);
//            System.out.println("使用了数据库");
//        }
//        return list;
//    }
//    @RequestMapping(value = "/addSong")
//    public SongBean addSong(String name,String hash,int id){
//        SongBean songBean=new SongBean();
//        songBean.setHash(hash);
//        songBean.setName(name);
//        songBean.setU_l_id(id);
//        songRepository.saveAndFlush(songBean);
//        return songBean;
//    }
//    @RequestMapping(value = "/s_list")
//    public List<SongBean> find(Integer userId,Integer listId){
//        return songRepository.findById(listId,userId);
//    }

//    @RequestMapping("/getUrl")
//    public MusicBean getUrl(String mid,String quality,String musicName){
////        String json=GetUrl.getUrl(mid);
//        System.out.println(mid+","+quality);
//        MusicBean musicBean=GetUrl.qqUrl(mid,musicName);
//        if (quality.equals("m4a")){
//            musicBean.setUrl(musicBean.getM4a());
//            musicBean.setAudio_name(musicBean.getAudio_name()+".m4a");
//        }else if(quality.equals("mp3_l")){
//            musicBean.setUrl(musicBean.getMp3_l());
//            musicBean.setAudio_name(musicBean.getAudio_name()+".mp3");
//        }else if(quality.equals("mp3_h")){
//            musicBean.setUrl(musicBean.getMp3_h());
//            musicBean.setAudio_name(musicBean.getAudio_name()+".mp3");
//        }else if(quality.equals("ape")){
//            musicBean.setUrl(musicBean.getApe());
//            musicBean.setAudio_name(musicBean.getAudio_name()+".ape");
//        }else if(quality.equals("flac")){
//            musicBean.setUrl(musicBean.getFlac());
//            musicBean.setAudio_name(musicBean.getAudio_name()+".flac");
//        }
//        return musicBean;
//    }
}
