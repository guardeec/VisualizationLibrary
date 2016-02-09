package generators.methods;

/**
 * Created by guardeec on 09.02.16.
 */
public class Color {

    public static Integer[] getColor(float param){
        int numberOfColors = 2;
        Integer[] color;

        if (param>1 || param<0){
            throw new IllegalArgumentException("params must be 0...1");
        }else {
            color = new Integer[3];
            int deg = (int) (param * 60 * numberOfColors);
            int step = deg/60+1;
            int padding = (deg % 60)*(255-85)/60;

            switch (step){
                case 1:{
                    color[0]=255;
                    color[1]=85+padding;
                    color[2]=85;
                    break;
                }
                case 2:{
                    color[0]=255-padding;
                    color[1]=255;
                    color[2]=85;
                    break;
                }
                case 3:{
                    color[0]=85;
                    color[1]=255;
                    color[2]=85+padding;
                    break;
                }
                case 4:{
                    color[0]=85;
                    color[1]=255-padding;
                    color[2]=255;
                    break;
                }
                case 5:{
                    color[0]=85+padding;
                    color[1]=85;
                    color[2]=255;
                    break;
                }
                case 6:{
                    color[0]=255;
                    color[1]=85;
                    color[2]=255-padding;
                    break;
                }
            }
        }
        return color;
    }
}
