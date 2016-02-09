package generators.methods;

/**
 * Created by guardeec on 09.02.16.
 */
public class Color {

    public static Integer[] getColor(float param){
        Integer[] color;
        if (param>1 || param<0){
            throw new IllegalArgumentException("params must be 0...1");
        }else {
            color = new Integer[3];
            color[0]=85;
            color[1]=255;
            color[2]=255;
        }
        return color;
    }
}
