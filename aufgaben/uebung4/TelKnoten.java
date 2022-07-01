package uebung4;

public class TelKnoten {
    public final int x;
    public final int y;

    public TelKnoten(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int hashCode() {
        Integer tx = x;
        Integer ty = y;
        return (tx.hashCode() + ty.hashCode()) * 23;
    }
    @Override
    public String toString() {
        return "{"+x+"|"+y+"}";
    }
    @Override
    public boolean equals(Object obj){
        if (obj instanceof TelKnoten){
            if (((TelKnoten) obj).x == this.x && ((TelKnoten) obj).y == this.y) {
                return true;
            }
        }
        return false;
    }
}
