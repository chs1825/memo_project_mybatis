package com.memo.convertJson.vo;


import java.util.List;

public class JSonVO {

    private String id;

    private MetaDataVO metaDataVO;

    private List<UtteranceVO> utteranceVOList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MetaDataVO getMetaDataVO() {
        return metaDataVO;
    }

    public void setMetaDataVO(MetaDataVO metaDataVO) {
        this.metaDataVO = metaDataVO;
    }

    public List<UtteranceVO> getUtteranceVOList() {
        return utteranceVOList;
    }

    public void setUtteranceVOList(List<UtteranceVO> utteranceVOList) {
        this.utteranceVOList = utteranceVOList;
    }

    @Override
    public String toString() {
        return "JSonVO{" +
                "id='" + id + '\'' +
                ", metaDataVO=" + metaDataVO +
                ", utteranceVOList=" + utteranceVOList +
                '}';
    }
}
