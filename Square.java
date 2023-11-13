public class Square
{
    public static final int SPACE = 0;
    public static final int WALL = 1;
    public static final int START = 2;
    public static final int EXIT = 3;
    public static final int ON_LIST = 4;
    public static final int EXPLORED = 5;
    public static final int ON_FINAL_PATH = 6;

    private int type;
    private int row;
    private int col;
    private Square previous;

    


    /**
     * Constructor for objects of class Square
     * 
     * @param initialRow    the row for this Square in the Maze
     * @param initialCol    the column for this Square in the Maze
     * @param initialType   the type (space, wall, start, exit) for this
     *                          Square in the Maze
     */
    
     public Square(int initialRow, int initialCol, int initialType)
    {
        this.row = initialRow;
        this.col = initialCol;
        this.type = initialType;
        this.previous = null;
    }

    /**
     * Returns this square's type
     *
     * @return    this square's type
     */
    public int getType()
    {
        return this.type;
    }

    /**
     * Returns this square's row
     *
     * @return    this square's row
     */
    public int getRow()
    {
        return this.row;
    }

    /**
     * Returns this square's column
     *
     * @return    this square's column
     */
    public int getCol()
    {
        return this.col;
    }

    public Square getPrevious()
    {
        return this.previous;
    }

    /**  Sets an empty space into the work list situation
     *  o - is on the solver work list
        . - has been explored
        x - is on the final path to the exit
        @return false if square is not an empty space space or invalid type, true if square changed.
     */
    public boolean setSpaceType(int newType) // only works if SQUARE IS AN EMPTY SPACE
    {
        if(newType < 0 || newType > Square.ON_FINAL_PATH)
            return false;
        if(this.type == Square.SPACE)
        {
            type = newType;
            return true;
        }
        else
        {
            return false;
        }
    } 

    /**
     * Sets type to new type.
     * 
     * @param newType new int type
     */
    public void setType(int newType)
    {
        type = newType;
    }

    public void setPrevious(Square newPreviousSquare)
    {
        this.previous = newPreviousSquare;
    }


    public void reset()
    {
        if(type >= Square.ON_LIST && type <= Square.ON_FINAL_PATH) // make sure is a space
            type = 0;
        previous = null;
    }


    @Override
    public String toString()
    {
        String str = null;

        switch(this.getType())
        {
            case Square.SPACE:
            {
                str = "_";
                break;
            }
            case Square.WALL:
            {
                str = "#";
                break;
            }
            case Square.START:
            {
                str = "S";
                break;
            }
            case Square.EXIT:
            {
                str = "E";
                break;
            }
            case Square.ON_LIST:
            {
                str = "o";
                break;
            }
            case Square.EXPLORED:
            {
                str = ".";
                break;
            }
            case Square.ON_FINAL_PATH:
            {
                str = "x";
                break;
            }
        }
        return str;
    }


    public String allToString() // debug reasons
    {
        return ("Row: " + this.row + ", Col: " + this.col + ", Type (int): " + this.type + ", Type (String): " + this.toString());
    }

    @Override
    public boolean equals(Object other)
    {
        // self check
        if(this == other)
        {
            return true;
        }

        // null check
        if(other == null)
        {
            return false;
        }

        // type check
        if(this.getClass() != other.getClass())
        {
            return false;
        }

        Square otherSq = (Square)other;

        // field comparision
        return (this.row == otherSq.row) &&
                (this.col == otherSq.col) &&
                (this.type == otherSq.type);
    }
}
