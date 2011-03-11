application {
    title = 'PolyglotSudoku'
    startupGroups = ['polyglot-sudoku']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "polyglot-sudoku"
    'polyglot-sudoku' {
        model = 'polyglot.sudoku.PolyglotSudokuModel'
        controller = 'polyglot.sudoku.PolyglotSudokuController'
        view = 'polyglot.sudoku.PolyglotSudokuView'
    }

}
