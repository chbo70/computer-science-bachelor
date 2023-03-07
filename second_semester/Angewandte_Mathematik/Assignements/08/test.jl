function right()
    f(x)=sinh(x)/cosh(x)
e = 5
s = -1
N = 3
h = (e-s)/N
x = collect(s+h:2:e)
solution = 0.0
for i in 1:N
    #s+i*h
    solution += (f(x[i])*h)
end
println(solution)
end
