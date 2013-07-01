/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gameModelProfiles;

import java.util.ArrayList;
import model.basic.Bone;

/**
 *
 * @author Chingo
 */
public class ModelProfile {

    String name;
    ArrayList<Bone> bones;
    ArrayList<Bone> head;
    ArrayList<Bone> body;
    ArrayList<Bone> hand;
    ArrayList<Bone> legs; // Also known as calf

    public ModelProfile(String name) {
        bones = new ArrayList<>();
        head = new ArrayList<>();
        body = new ArrayList<>();
        hand = new ArrayList<>();
        legs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Bone> getBones() {
        return bones;
    }

    public ArrayList<Bone> getHead() {
        return head;
    }

    public ArrayList<Bone> getBody() {
        return body;
    }

    public ArrayList<Bone> getHand() {
        return hand;
    }

    public ArrayList<Bone> getLegs() {
        return legs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBones(ArrayList<Bone> bones) {
        this.bones = bones;
    }

    public void setHead(ArrayList<Bone> head) {
        this.head = head;
    }

    public void setBody(ArrayList<Bone> body) {
        this.body = body;
    }

    public void setHand(ArrayList<Bone> hand) {
        this.hand = hand;
    }

    public void setLegs(ArrayList<Bone> legs) {
        this.legs = legs;
    }
}
