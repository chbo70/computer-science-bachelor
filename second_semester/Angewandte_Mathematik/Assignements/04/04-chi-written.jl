using Plots

t_1 = 0 : 0.1 : 60
t_2 = 60 : 0.1 : 150
t_3 = 150 : 0.1 : 300

v_1(t_1) = abs(1.5*t_1)
v_2(t_2) = abs(90)
v_3(t_3) = abs(-(t_3^2/300)+t_3+15)

x_1(t_1) = abs((1.5/2) * t_1^2)
x_2(t_2) = abs(90*t_2 - 2700)
x_3(t_3) = abs((-t_3^3/900) + 0.5*t_3^2 + 15*t_3 + 1050)

fig1 = plot(t_1,v_1, xlabel = "t [s]", ylabel = "v [m/s]", ylims = (0, 150), titlefont = font(7))
plot!(t_2, v_2)
plot!(t_3, v_3)

fig2 = plot(t_1,x_1,xlabel = "t [s]", ylabel = "x [m]", titlefont =font(7))
plot!(t_2, x_2)
plot!(t_3, x_3)


plt = plot(fig1, fig2, layout = (2,1), legend = false)
savefig(plt, "04_a-chi-written.png")
display(plt)
