package com.buaa.sensorylab.Model;

/**
 * Created by LZJing on 2015/12/6.
 */
public class MoveType {
    int nodeId;
    int type;

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MoveType() {
    }

    public MoveType(int nodeId, int type) {
        this.nodeId = nodeId;
        this.type = type;
    }
}
