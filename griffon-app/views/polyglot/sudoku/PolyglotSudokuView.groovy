package polyglot.sudoku

application(title: 'Polyglot Sudoku',
  pack: true,
  locationRelativeTo: null,
  locationByPlatform:true,
  iconImage: imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]) {
    // add content here
    menuBar{
        menu(text:"File"){
            menuItem(text:"New...", actionPerformed: controller.&defineProblem)
            menuItem(text:"Solve", actionPerformed: controller.&solveProblem)
            separator()
            menuItem(text:"Quit", actionPerformed: {System.exit(0)})
        }
    }
    widget(new PuzzleContentPane(), problem: bind (source: model, sourceProperty:'problem'))
}
