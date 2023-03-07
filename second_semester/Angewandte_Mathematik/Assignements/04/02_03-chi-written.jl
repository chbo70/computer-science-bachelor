using QuadGK, Plots

# Exercise 2, Integral Check
#quadgk(x -> sqrt(1 + x^2), 0.0, 8.0)

# Exercise 3, Sketch

x = -2 : 0.001 : 2
f(x) = x^3 - 2*x + 2
g(x) = x + 2

fig1 = plot(x, f, label="y = x³ -2x + 2")
plot!(fig1,g, label="y = x + 2")
savefig(fig1, "03-chi-sketch.png")

display(fig1)

