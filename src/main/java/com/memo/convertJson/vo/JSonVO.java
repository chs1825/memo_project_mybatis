package com.memo.convertJson.vo;


public class JSonVO {

    private String id;

    private MetaDataVO metaDataVO;

    private UtteranceVO utteranceVO;

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

    public UtteranceVO getUtteranceVO() {
        return utteranceVO;
    }

    public void setUtteranceVO(UtteranceVO utteranceVO) {
        this.utteranceVO = utteranceVO;
    }

    @Override
    public String toString() {
        return "JSonVO{" +
                "id='" + id + '\'' +
                ", metaDataVO=" + metaDataVO +
                ", utteranceVO=" + utteranceVO +
                '}';
    }
}
