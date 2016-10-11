

hello = -> 'hello world'

#positiveNumbers = [1,2,3,4,5]
#negativeNumbers = (-num for num in positiveNumbers)
#code = ['U','U','D','D','L','R','L','R','B','A']
#codeKeyValues = for key in code
#  switch key
#    when 'L' then 37
#    when 'U' then 38
#    when 'R' then 39
#    when 'D' then 40
#    when 'A' then 65
#    when 'B' then 66
#
#
#evens = (x for x in [2..10] by 2)
#
#root = global ? window
#root.httpCode  =
#  movedParmanently:301
#  pageNotFound : 404
#  serverError:500
#
#Boy = ->
#Boy::sing = alert(" i am a boy i can sing ... ")
#sue = new Boy()
#sue.sing()
#
#Gift = (@name) ->
#  Gift.count++
#  @day = Gift.count
#  @announce()
#
#Gift.count = 0
#Gift::announce = -> alert("On day #{@day} I receive #{@name} ")
#
#gift1 = new Gift(" Pear Tree")
#gift2 = new Gift(" turtle doves")

#class Tribble
#  constructor: ->
#    @isAlive = true
#    Tribble.count++
#
#  #Prototype properties
#  breed: -> new Tribble() if @isAlive
#  die: ->
#    Tribble.count-- if @isAlive
#    @isAlive = false
#
#  #class-level properties
#  @count: 0
#  @makeTrobble = -> alert ('Trubble' for i in [0..@count]).join(",")
#
#
#class Pet
#  constructor: -> @isHungry = true
#  eat: -> @isHungry = false
#class Dog extends Pet
#  eat: -> alert("* crunch crunch*") super()
#  fetch: -> alert(" Yip Yip ")
#  @isHungry = true
#
#Dog dd = new Dog()
##dd.eat()
##dd.fetch()
#
#class Shape
#  constructor: (@width)->
#    computeArea: -> throw new Error("i am an abstract class!")
#class Square extends Shape
#  computeArea: -> Math.pow @width ,2
#class Circle extends Shape
#  radius: -> @width / 2
#  computeArea: -> Math.PI * Math.pow(@radius() ,2 )
#
#showArea = (shape) ->
#  unless shape instanceof Shape
#    throw new Error("showArea reqquires a shape instance")
#  alert(shape.computeArea())

#showArea new Square(2)
#showArea new Circle(2)
#showArea new Dog()

requisition = (captain) ->
  switch captain
    when 'shen','yang','yangyang'
      alert("hello yanyyang")
    when 'niuke'
      alert("hello niuke")
    else
      throw new Error("Invalid input")



Raven = ->
Raven::quoth = -> alert("nevermore")
raven1 = new Raven()
#raven1.quoth()

Raven::quoth = -> alert("i am hungry ...")
#raven1.quoth()
raven2 = new Raven()
#raven2.quoth()
raven2.quoth = -> alert("demo2")

#raven1.quoth()
#raven2.quoth()

#alert(raven1.hasOwnProperty('quoth'))
#alert(raven2.hasOwnProperty('quoth'))

class Tribble
  constructor: ->
    @isAlive = true
    Tribble.count++
  #prototype properties
  breed: -> new Tribble() if @isAlive
  die: ->
    Tribble.count-- if @isAlive
    @isAlive = false
  #class level
  @count = 0
  @makeTrouble: -> alert('Troubble' for i in [0...@count]).join(",")


#t1 = new Tribble
#t2 = new Tribble
#t1.breed()
#t1.breed()
#t1.breed()
#t1.die()
#
#Tribble.makeTrouble()


class Shape
  constructor: (@width)->
  computeArea: -> throw new Error("i am an abstruct class!")

class Square extends Shape
  computeArea: -> Math.pow @width,2
class Circle extends Shape
  computeArea: -> Math.PI* Math.pow @width/2,2

showArea = (shape) ->
  unless shape instanceof Shape
    throw new Error("shape requires a shape instance!")
  alert(shape.computeArea())

#showArea new Square(2)
#showArea new Circle(2)
clearArray = (arr) ->
  arr.splice 0,arr.length
  return

#foo = (name) -> alert(name)
#run = (func, args...) -> func.apply this,args

#for x in [1, 2,3,4,5]
#  do (x) ->
#    setTimeout (-> alert(x)), 50

objContains = (obj,match) ->
  for k,v of obj
    if v is match
      return true
    return false

wordList = ['shen','ok','yangyang']
objMin = Math.min.apply Math,(w.length for w in wordList)

$ ->
  $('h2').click -> $(this).html $(this).html()+"2"
  $('h3').click -> $(this).html $(this).html()+"!"
  $('#logo').click -> $(this).html $(this).html()+"logo"
  $("#logo").click -> $(this).html $(this).html()+"xx"
  $('h1').click(->$(this).html $(this).html()+'@')
  .click(->$(this).html $(this).html()+'#')
  .click(->$(this).html $(this).html()+'$')
  .click(-> alert($(this).text()))
  $("#logo")
  .css(fontSize:64)
  .hover(->$(this).css(fontWeight:'bold'))
  .click(->alert('hello '+$(this).text()))
  return


