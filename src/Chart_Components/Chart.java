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

	@Override
	public void Compute_connector(){
		/* the connector is half of the west */
		this.connector = this.width/2;
	}
	@Override
	public void set_Width(){
		this.b = new Box(this.name);
		b.set_Width();
		int subs_width = 0;

		for (int i = 0; i < list.size(); i++) {
			Chart_Object get = list.get(i);
			get.set_Width();
			subs_width += get.width;
			if (i+1 < list.size())
			subs_width += 3;

		}
		if(b.width < subs_width)
			width = subs_width;
		else
			width = b.width;
			// System.out.println(this.name);
			// System.out.printf("my Width %d sub width's added %d\n", b.width, subs_width);
			// System.out.println(width);
	}

	@Override
	public void set_Height(){
		this.b = new Box(this.name);
		this.b.set_Height();
		for (int i = 0; i < list.size(); i++) {
			Chart_Object get = list.get(i);
			/* Make this chart width wider by the amount of the sub chart */
			get.set_Height();

			if (this.height < get.height) {
				/* set the height to the current objects height */
				this.height = get.height;
			}
		}
		this.height += b.height + 1;
	}

	@Override
	public void Add_connector(){
		/* compute the connector */
		this.Compute_connector();
		/* draw the connector */
		buffer[this.connector][0] = '╧';
	}

	private void Make_Buffers(){
		set_Width();
		/* Loop through every sup chart part */
		set_Height();
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
		this.b = new Box(this.name);
		b.set_Height();
		b.set_Width();
		b.Compute_connector();
		this.b.Make_buffer();
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
				// System.out.printf("x %d\n",object_x + x );
					buffer[object_x + x][object_y + y] = b.buffer[object_x][object_y];
			}
		}
		if(list.size() > 0)
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
		if(this.list.size() == 0) {
			return;
		}
		/* Loop through all of the chart objects */
		for (int i = 0; i < list.size(); i++) {
			/* Get the chart object */
			Chart_Object get = list.get(i);
			get.Make_buffer();
			get.Add_connector();
			/* for every x coordinate */
			for (int object_x = 0; object_x < get.width; object_x++) {
				/* for every y coordinate */

				for (int object_y = 0; object_y < get.height; object_y++) {
					/* copy the chart object buffer too the main buffer */
					//if(object_x + x < width && object_y + y < height)
					if(object_x + x < width && object_y + y < height)
						buffer[object_x + x][object_y + y] = get.buffer[object_x][object_y];
				}
			}
			/* move the x position the width to the left and then three more too the right */
			x += get.width + 3;
		}
		boolean started = false;
		boolean top = false;
		boolean bottem = false;
		for (int i = 0; i <this.width; ++i) {
			/* put a bar across the screen */
			if(buffer[i][b.height-1] == '╤'){
				top = true;
			}else{
				top = false;
				}
			if(buffer[i][b.height+1] == '╧'){
				bottem = true;
			}else{
				bottem = false;
			}
			buffer[i][b.height] = get_line_char(started, top, bottem);
			if(top || bottem)
				started = true;
		}
		for (int i = this.width-1; i >0; --i) {
			/* put a bar across the screen */
			if(buffer[i][b.height-1] == '╤'){
				top = true;
			}else{
				top = false;
			}
			if(buffer[i][b.height+1] == '╧'){
				bottem = true;
			}else{
				bottem = false;
			}
			if(!top && !bottem)
				buffer[i][b.height] = ' ';
			if(!top && bottem)
				buffer[i][b.height] = '┐';
			if(top && !bottem)
				buffer[i][b.height] = '┘';
			if(top && bottem && buffer[i][b.height] == '├')
				buffer[i][b.height] = '│';
			if(top && bottem && buffer[i][b.height] == '┼')
				buffer[i][b.height] = '┤';

			if(top || bottem)
				break;
		}
	}

	char get_line_char(boolean started, boolean top, boolean bottem){
		if(started)
			if(top)
				if(bottem)
					return '┼';
				else
					return '┴';
			else
				if(bottem)
					return '┬';
				else
					return '─';
		else
			if(top)
				if(bottem)
					return '├';
				else
					return '└';
			else
				if(bottem)
					return '┌';
				else
					return ' ';
	}

}
