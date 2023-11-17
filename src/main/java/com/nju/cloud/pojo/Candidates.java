package com.nju.cloud.pojo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: lzm
 * @create: 2023-11-14 22:38
 **/
@Data
public class Candidates {
    private String id;

    private List<Long> offset;

    private String trigger_word;

    private String sent_id;
}
