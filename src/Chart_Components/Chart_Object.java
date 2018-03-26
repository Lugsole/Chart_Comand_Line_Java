package Chart_Components;

public class Chart_Object {

    char[][] buffer;
    int height;
    int width;
    int connector;
    public void Make_buffer() {
    }
    public void Add_connector(){
    }

    @Override
    public String toString() {
        this.Make_buffer();
        String str_out = "";
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                str_out += Character.toString(buffer[i][j]);
            }
            str_out += "\n";
        }
        return str_out;
    }
}
