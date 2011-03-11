package polyglot.sudoku

class PolyglotSudokuController {
    // these will be injected by Griffon
    def model
    def view

    // void mvcGroupInit(Map args) {
    //    // this method is called after model and view are injected
    // }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    def defineProblem = { evt ->
        def problems = [
        "4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......",
        ".....6....59.....82....8....45........3........6..3.54...325..6..................",
        "..3.2.6..9..3.5..1..18.64....81.29..7.......8..67.82....26.95..8..2.3..9..5.1.3.."]
        
        println "Evento iniciado!"
        model.problem = problems[new Random().nextInt(3)]
        println "Evento finalizado!"
    }

    def solveProblem = { evt ->
        model.problem = "................................................................................."
    }    
}
