class Cell(var state:Char ='?', var next:Char ='?'){
    //state will show current state
    //next will show next state after calculation
    //  ?   Unknown-NotApplicable-NotInitialized
    //  X   Alive-cell
    //  .   Dead-cell

    //Safety checks for state and next
    require((state=='?' || state=='X' || state=='.')&&(next=='?' || next=='X' || next=='.'))
        
    //Constructor with no args
    def this() = this('?','?')

    //Constructor with current state passed
    def this(state:Char) = this(state,'?')
    
    //Neighbor references will be stored in neigh
    //It's a 2D array (of size 2x6, to be exact)
    //neigh(0) will have 1-tie neighbors
    //neigh(1) will have 2-tie neighbors
    val neigh = Array.ofDim[Cell](2,6)

    //countX will get number of alive neighbors
    //i will be either 0 (for 1-tie) or 1 (for 2)
    def countX(i:Int) = neigh(i).count(_.state=='X')
    
    //score values will be used when deciding next state
    // /20 and -0.3 maps 6 to 0 and 12 to 0.3
    // math floor round to .00 precision
    def score(rule:Int) = (math floor 100*(countX(0)+countX(1)*((rule.toFloat/20)-0.3)))/100

    //These are limits for state changes. For example, 
    //AtoDmin: min score an alive cell needs (to die)
    def AtoDmin(r:Int) = 2.0
    def AtoDmax(r:Int) = if(r==6) 3.00 else 3.30
    def DtoAmin(r:Int) = if(r==6) 3.00 else 2.30
    def DtoAmax(r:Int) = if(r==6) 3.01 else 2.90

    //getNext calculates next state of the cell
    //by looking at its neighbor states
    def getNext(rule:Int) = {
        if(state=='X' && (score(rule)<AtoDmin(rule) || score(rule)>AtoDmax(rule))) '.'
        else if(state=='.' && (score(rule)>=DtoAmin(rule) && score(rule)<=DtoAmax(rule))) 'X'
        else state
    }

    //setNext stores next state in variable 'next'
    def setNext(rule:Int) = next = getNext(rule)

    //Actually updates cell's state to
    //previously calculated next state
    def update(){
        state=next
        next='?'
    }

    //Takes a probability (pr)
    //Assigns a random state char (X) or (.)
    //If random num is less than requested p,
    //  then the cell will be X (alive)
    def rand(pr:Double)= state= (if (scala.util.Random.nextDouble < pr) 'X' else '.')
  
    //Overriding toString for printing purposes
    override def toString() = state.toString

}