package com.nju.cloud.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @description: 法案
 * @author: lzm
 * @create: 2023-11-14 22:37
 **/
@Data
@Document(collection = "Bill")
public class Bill {
    @Id
    private String id;

    private String title;

    private String case_no;

    private String crime;

    //测试集上独有
    private List<Candidates> candidates;

    private List<TokenAndSentence> content;
    //训练集上和验证集
    private List<Event> events;
    //训练集和验证集
    private List<Mention> negative_triggers;

    @Data
    private class TokenAndSentence {
        private String sentence;

        private List<String> tokens;
    }

    @Data
    private class Event {
        private String id;

        private String type;

        private Integer type_id;

        private List<Mention> mention;
    }

    @Data
    private class Mention {
        private String id;

        private String trigger_word;

        private Integer sent_id;

        private List<Integer> offset;
    }
}
