demo1 = -> 'hello coffee script ... 1'
demo2 = -> 'hello coffee script ... 2'
greeting = (args) -> "hello #{args} !"
square = 'red'
xx = square + 5
cube = (num) -> Math.pow num,3
#odd = (num) -> num % 2 is 1
odd = (num) ->
  if typeof num is 'number'
    if num is Math.round num
      if num > 0
        num % 2 is 1
      else
        throw "#{num} is not positie"
    else
      throw "#{num} is not an integer"
  else
    throw  "#{num} is not a number"

newodd = (num) ->
  unless typeof num is 'number'
    throw "#{num} is not a number"
  unless num is Math.round num
    throw "#{num} is not a integer"
  unless num > 0
    throw "#{num} is not a positive"
  num % 2 is 1

count = 0
increament = -> count++

#setName = (name) ->@name = name
setName = (@name) ->

cat = {}
cat.setName = setName
cat.setName 'Yangyang'

pig = {}
setName.apply(pig,['Bobe'])

horse = {}
cat.setName.apply(horse,['Mr . ddd'])

ringFireAlarm = (isDirill = true) ->
  if isDirill
    alert("Yes")
  else 
    alert("No")

