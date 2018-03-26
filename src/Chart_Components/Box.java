package Chart_Components;

public class Box extends Chart_Object {
    /* The text in the box */
    final String name;

    /* Setter that includes the box text */
    public Box(String name) {
        this.name = name;
    }
    
    /* Sets the location of the connector */
    public void Compute_connector(){
        this.connector = this.width/2;
    }
    
    /* add'd the connector at the location computed */
    @Override
    public void Add_connector(){
        this.Compute_connector();
        buffer[this.connector][0] = '╧';
    }
    
    @Override
    public void Make_buffer() {
        /* split the text into multiple lines */
        String[] lines = name.split("\\r?\\n");
        /* Get the length of the first string */
        int str_len = lines[0].length();
        /* for every line in the lines */
        for (int i = 1; i < lines.length; i++) {
            /* set the current line to write a string */
            String line = lines[i];
            /* if it is larger than the longest known string */
            if (line.length() > str_len) {
                /* set it to the current size */
                str_len = line.length();
            }
        }
        /* if it is an even number of width */
        if (str_len % 2 == 0) {
            /* Make the number odd */
            str_len++;
        }
        /* The width is 4 more than the string length */
        width = str_len + 4;
        /* The height is 2 more than the amount of lines */
        height = 2 + lines.length;
        /* this creates the buffer */
        buffer = new char[width][height];
        /* for the width of the buffer */
        for (int i = 0; i < buffer.length; i++) {
            /* and the height of the buffer */
            for (int j = 0; j < buffer[i].length; j++) {
                /* set equal to zero */
                buffer[i][j] = ' ';
            }
        }
        /* for the width of the screen minus the left most and right most chars */
        for (int i = 1; i < width - 1; i++) {
            buffer[i][0] = '═';
            buffer[i][height-1] = '═';
        }
        /* get all the char's of the text */
        char[] word = name.toCharArray();
        /* This is equivalent to the x position of the char to be modified */
        int Line_Number = 1;
        /* This is equivalent to the y position of the char to be modified */
        int char_number = 2;
        /* loops through all of the char's */
        for (int i = 0; i < word.length; i++) {
            /* if it is a new line char */
            if (word[i] == '\n') {
                /* increment the line number */
                Line_Number++;
                /* reset the char number to the left */
                char_number = 2;
            } else {
                /* write to the char buffer */
                buffer[char_number][Line_Number] = word[i];
                /* increment the char number */
                char_number++;
            }
        }
        /* for the height of the buffer */
        for (int i = 1; i < height - 1; i++) {
            /* set the left side of the box to a box char */
            buffer[0][i] = '║';
            /* set the right side of the box to a box char */
            buffer[width - 1][i] = '║';
        }
        /* put the top left of buffer to be a corner */
        buffer[0][0] = '╔';
        /* put the bottom left of buffer to be a corner */
        buffer[0][height - 1] = '╚';
        /* put the top right of buffer to be a corner */
        buffer[width - 1][0] = '╗';
        /* put the bottom right of buffer to be a corner */
        buffer[width - 1][height - 1] = '╝';
    }
}
