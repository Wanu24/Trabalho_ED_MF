package trabalho_ed_mf;


import java.util.Random;

import ClassImplementation.Network;
import trabalho_ed_mf.Flag;
import trabalho_ed_mf.Location;

public class Map{
    private Network<Location> network;

    public Map(){
        this.network = new Network<Location>();
    }

    public void addLocal(Location local){
        network.addVertex(local);
        System.out.println("Vertex added: " + local.getIndex());
    }

    public void removeLocal(Location local){
        network.removeVertex(local);
        System.out.println("Vertex removed: " + local.getIndex());
    }

    public void addFlag(int index, Flag flag){
        network.getVertex(index).setFlag(flag);
        network.getVertex(index).setHasFlag(true);
        System.out.println("Flag added to vertex: " + index);
    }

    public void removeFlag(int index){
        network.getVertex(index).setFlag(null);
        network.getVertex(index).setHasFlag(false);
        System.out.println("Flag removed from vertex: " + index);
    }

    public void importMap(){

    }

    public void exportMap(){

    }

    //Getters
    public Network<Location> getNetwork(){
        return network;
    }
}

