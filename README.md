> This is the hex Game of Life (where you have 6 
neighbors instead of 8) written in Scala.
> This was a class assignment.
> Code reviews and pull requests are always welcome.



# How to Run

> To run the program, run following commands from
>   the directory where source codes are located: 

$ scalac *.scala
$ scala Life

> Or if you want to pass arguments:

$ scala Life -12 -size 16



# Arguments

> Program accepts following optional arguments.

> -12      Runs in rule-12 mode when passed.
>          Program runs in rule-6 mode unless passed.

> -size n  Sets the size of the plane to n.
>          By default the plane has size 100.

> -f path  Reads the file at path and uses it as plane.
>          When this argument is used -size is ignored.

> -g n     Runs the simulation for n many times.
>          Simulation runs for 10 generations by default. 

> -p n     Prints every nth generation if passed.
>          By default, program prints all generations.

> -i n     Sets the probability of being alive.
>          This is only used if cells are randomly assigned.
>          That is, if -f is used, this will be ignored.
>          By default the probabilty is set to 1/2.


#Structure

> We have Cell, Row and Plane objects.

> In main, we will create a plane object. We will be utilizing its methods to run the game of life smoothly.

> Plane object will consist of (size) many row objects.

> Row objects will consist of cell objects.

> Each class have their own functions. Usually, main makes its calls to plane object. Then plane object transfers call to its row objects. Each row object then applies some function to each of its rows.

> We also have a loop that runs however many times user wants it to run.

> No more border cells are used (unlike Java version, which is not uploaded to GitHub for now). Instead of that, try-catch blocks are utilized in neighbor assignment.


#Neighbors

> Neighbor assignment is done once and for all, as in Java version. Since we know exact locations of neighbors of each cells, we can store them in an array of references.

> When assigning neighbors, for loop sometimes goes out of bounds. For example, cell at (0,0) would think cell at (-1,0) is its neighbor. This possible errors are handled by try-catch blocks.

> Assume our cell is [i,j] and its at an even row. (First) {second} neighbors are shown as follows:

>                          { i-2,j }
>  {i-1,j-2}   (i-1,j-1)   ( i-1,j )   {i-1,j+1}
>              (i  ,j-1)   [ i  ,j ]   (i  ,j+1)
>  {i+1,j-2}   (i+1,j-1)   ( i+1,j )   {i+1,j+1}
>                          { i+2,j }

> And when rows are shifted:

>                          { i-2,j }
>         {i-1,j-2}   (i-1,j-1)   ( i-1,j )   {i-1,j+1}
>              (i  ,j-1)   [ i  ,j ]   (i  ,j+1)
>         {i+1,j-2}   (i+1,j-1)   ( i+1,j )   {i+1,j+1}
>                          { i+2,j }


> Likewise, for [i,j]s that live on odd rows:

>              { i-2,j }
>  {i-1,j-1}   ( i-1,j )   (i-1,j+1)   {i-1,j+2}
>  (i  ,j-1)   [ i  ,j ]   (i  ,j+1)
>  {i+1,j-1}   ( i+1,j )   (i+1,j+1)   {i+1,j+2}
>              { i+2,j }

> Shifted:
>                     { i-2,j }
>  {i-1,j-1}   ( i-1,j )   (i-1,j+1)   {i-1,j+2}
>         (i  ,j-1)   [ i  ,j ]   (i  ,j+1)
>  {i+1,j-1}   ( i+1,j )   (i+1,j+1)   {i+1,j+2}
>                     { i+2,j }