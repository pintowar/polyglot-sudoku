package polyglot.sudoku

application(title: 'Polyglot Sudoku',
  pack: true,
  locationRelativeTo: null,
  locationByPlatform:true,
  iconImage: imageIcon('/gnome-sudoku-48x48.png').image,
  iconImages: [imageIcon('/gnome-sudoku-48x48.png').image,
               imageIcon('/gnome-sudoku-32x32.png').image,
               imageIcon('/gnome-sudoku-16x16.png').image]) {
    // add content here
    menuBar{
        menu(text:"File"){
            menuItem(text:"New...", actionPerformed: controller.&defineProblem)
            menu(text:"Solvers"){
            	menuItem(text:"Python", actionPerformed: controller.&solvePython)
            	menuItem(text:"Groovy", actionPerformed: controller.&solveGroovy)
            }
            separator()
            menuItem(text:"Quit", actionPerformed: {System.exit(0)})
        }
    }
    widget(new PuzzleContentPane(), problem: bind (source: model, sourceProperty:'problem'))
}
