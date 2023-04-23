package com.memo.convertJson.vo;

import java.util.List;
import java.util.Map;

public class UtteranceVO {

    private List<Map<String,String>> utteranceList;

    public List<Map<String, String>> getUtteranceList() {
        return utteranceList;
    }

    public void setUtteranceList(List<Map<String, String>> utteranceList) {
        this.utteranceList = utteranceList;
    }

    @Override
    public String toString() {
        return "UtteranceVO{" +
                "utteranceList=" + utteranceList +
                '}';
    }
}
