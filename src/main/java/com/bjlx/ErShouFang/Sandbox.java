package com.bjlx.ErShouFang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;

/**
 * 用于测试
 * Created by xiaozhi on 2016/10/21.
 */
public class Sandbox {



    public static void main(String[] args) {
//        List<Long> participants = Arrays.asList(1L,2L);
//        List<Long> filtered = participants.stream().filter(item -> !item.equals(1L)).collect(Collectors.toList());
//        for(Long id : filtered) {
//            System.out.println(id);
//        }
        System.out.print(new ObjectMapper().valueToTree(NullNode.getInstance()).toString());
    }
}
