package Chart_Components;

import java.util.ArrayList;
import java.util.List;

public class Chart extends Chart_Object {

    final String name;
    private Box b;
    // a list of chart sub objects
    List<Chart_Object> list = new ArrayList<Chart_Object>();

    public Chart(String name) {
        this.name = name;
    }

    
    public void add(Chart_Object c) {
        /* add the chart object to the list */
        list.add(c);
    }

    public void Compute_connector(){
        /* the connector is half of the west */
        this.connector = this.width/2;
    }
    
    @Override
    public void Add_connector(){
        /* compute the connector */
        this.Compute_connector();
        /* draw the connector */
        buffer[this.connector][0] = '╧';
    }
    
    private void Make_Buffers(){
        /* Loop through every sup chart part */
        for (int i = 0; i < list.size(); i++) {
            /*  */
            Chart_Object get = list.get(i);
            /* have the sup chart make its buffer */
            get.Make_buffer();
            /* have the sup chart add a connector */
            get.Add_connector();
            /* Make this chart width wider by the amount of the sub chart */
            this.width += get.width;
            /* make the height bigger if it is the biggest part of the sub chart */
            if (height < get.height) {
                /* set the height to the current objects height */
                height = get.height;
            }
            /* if not the last element */
            if (i < list.size() - 1) {
                /* add 3 to the width */
                this.width += 3;
            }
        }
        /* create the multi line box element for the herder */
        b = new Box(this.name);
        /* make box element's buffer */
        b.Make_buffer();
        /* make box element contain a connector */
        b.Compute_connector();
        /* make the height bigger based on the header height and enough space for the bar */
        height += b.height + 1;
        /* make the memory space buffer for sending  */
        buffer = new char[width][height];

    }
    private void Make_buffer_Clear(){
        /* for loop through the width */
        for (int i = 0; i < width; i++) {
            /* for loop through the height */
            for (int j = 0; j < height; j++) {
                /* replace the char with a space */
                buffer[i][j] = ' ';
            }
        }
    }
    
    private void Coppy_Header_Buffer(){
        /* Get the header's connector location */
        this.connector = b.connector;
        /* Get the x starting location */
        int x = this.width / 2 - b.width / 2;
        /* The y set off is zero */
        int y = 0;
        /* for every x coordinate */
        for (int object_x = 0; object_x < b.width; object_x++) {
            /* for every y coordinate */
            for (int object_y = 0; object_y < b.height; object_y++) {
                /* copy the box buffer to this buffer */
                buffer[object_x + x][object_y + y] = b.buffer[object_x][object_y];
            }
        }
        buffer[x + b.connector][b.height-1] = '╤';
    }
    
    @Override
    public void Make_buffer() {
        /* Set up the sub object's buffer */
        this.Make_Buffers();
        /* clear the buffer cleared */
        this.Make_buffer_Clear();
        /* copies the header buffer */
        this.Coppy_Header_Buffer();

        /* Get the x starting location */
        int x = 0;
        /* The y set off is zero */
        int y = b.height+1;
        /* for the width of the screen make a bar across the screen */
        for (int i = 0; i < this.width; i++) {
            /* put a bar across the screen */
            buffer[x + i][b.height] = '─';
        }
        /* Loop through all of the chart objects */
        for (int i = 0; i < list.size(); i++) {
            /* Get the chart object */
            Chart_Object get = list.get(i);
            /* If it is the first chart object */
            if (i == 0) {
                /* for all of the chars before the connector */
                for (int j = 0; j < get.connector; j++) {
                    /* replace the bar with a space */
                    buffer[x + j][b.height] = ' ';
                }
                /* Replace above the connector with a corner piece */
                buffer[x + get.connector][b.height] = '┌';
            /* If it is the last chart object */
            } else if (i == list.size() - 1) {
                /* remove all the line past the last connector */
                for (int j = get.connector + 1; j < get.width; j++) {
                    /* replace the location with a star */
                    buffer[x + j][b.height] = ' ';
                }
                /* add the connector to the bar */
                buffer[x + get.connector][b.height] = '┐';
            }
            /* if not the first or last */
            if (i != 0 && i != list.size() - 1) {
                /* add a connector too the bar */
                buffer[x + get.connector][b.height] = '┬';
            }
            /* for every x coordinate */
            for (int object_x = 0; object_x < get.width; object_x++) {
                /* for every y coordinate */
                for (int object_y = 0; object_y < get.height; object_y++) {
                    /* copy the chart object buffer too the main buffer */
                    buffer[object_x + x][object_y + y] = get.buffer[object_x][object_y];
                }
            }
            /* move the x position the width to the left and then three more too the right */
            x += get.width + 3;
        }
        /* set x to the box top left */
        x = this.width / 2 - b.width / 2;
        /* if the connector location for the header box is just a bar */
        if(buffer[x + b.connector][b.height] == '─'){
            /* replace it with a bar with an up connector */
            buffer[x + b.connector][b.height] = '┴';
        /* if the connector location for the header box is already connected down */
        }else if(buffer[x + b.connector][3] == '┬'){
            /* replace it with a connector that connects all ways */
            buffer[x + b.connector][b.height] = '┼';
        }
    }
}
