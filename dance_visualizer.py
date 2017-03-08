#!/Users/bin/Python
from pyx import *

c = canvas.canvas()
input = open('./foo.txt', 'r')
i = 0
grad = color.gradient.Rainbow
## draws a new path for each generation in a differnt color
for line in input:
  cur_color = [grad.getcolor(i)]
  i += .1
  pos = [0, 0]
  for char in line:
    prev = pos[:]
    if char == '0':
      pos[1] += 1
    elif char == '1':
      pos[0] += 1
    elif char == '2':
      pos[1] -= 1
    elif char == '3':
      pos[0] -= 1
    else:
      continue
    c.stroke(path.line(prev[0], prev[1], pos[0], pos[1]), cur_color)
circle = path.circle(0, 0, .2)
c.stroke(circle, [deco.filled([color.grey(0.5)])])
c.writePDFfile("path")
